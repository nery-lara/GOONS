package com.nerylara.goons;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class game {

    private int clicks = 400;
    private String userId;
    private String opponentId;
    private long userTime;
    private String outcome;
    private Context mContext;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    game(Context context){
        mContext = context;
        mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);

    }

    public void setId(String id){
        userId = id;
    }

    public String getId(){
        return userId;
    }

    public String getOutcome(){
        return outcome;
    }

    void clicked(){
        long begin = 0;
        long end;
        clicks--;
        if(clicks == 399){
            begin = System.currentTimeMillis();
            Log.e("begin time", ""+begin);

        }
        if(clicks == 0){
            end = System.currentTimeMillis();
            Log.e("end time", ""+end);
            userTime = durationTime(end, begin);
            sendGameDetails();
        }
    }

    private long durationTime(long stop, long start){
        return (stop - start);
    }

    private String gameDetails(){

        /*create JSONObject: return string*/
        JSONObject gameDetails = new JSONObject();
        try {
            gameDetails.put("type", "game");
            gameDetails.put("userid", userId);
            gameDetails.put("opponentid", opponentId);
            gameDetails.put("gameduration", userTime);
        }catch(JSONException e) {
            //do something
        }
        return gameDetails.toString();

    }

    private void sendGameDetails() {
        String relativeUrl = "game";
        String contentType = "application/json";
        String entity = gameDetails();
        /* send post request*/
        HttpUtils.post(mContext, relativeUrl, new StringEntity(entity, "UTF-8"), contentType, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
                mEditor = mPreferences.edit();
                // If the response is JSONObject instead of expected JSONArray
                Log.d("game response:", "" + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    String type = serverResp.getString("type");
                    if(type.equals("game")){
                        String outcome = serverResp.getString("outcome");
                        mEditor = mPreferences.edit();
                        mEditor.putString("outcome", outcome);
                        mEditor.apply();
                    }
                    /* should check for errors */
                } catch (JSONException e) {
                    // do something
                }
            }
        });

        outcome = mPreferences.getString("outcome", "");

    }

}
