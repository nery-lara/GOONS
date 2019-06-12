package com.nerylara.goons;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/*get*/
public class squad {

    private String userId;
    private String squad;
    private Context mContext;
    private SharedPreferences mRep;
    private SharedPreferences.Editor repEditor;
    private SharedPreferences mMembers;
    private SharedPreferences.Editor membersEditor;
    private SharedPreferences mWins;
    private SharedPreferences.Editor winsEditor;
    private SharedPreferences mLosses;
    private SharedPreferences.Editor lossesEditor;

    squad(Context context){
        mContext = context;
        mRep = PreferenceManager.getDefaultSharedPreferences(mContext);
        mMembers = PreferenceManager.getDefaultSharedPreferences(mContext);
        mWins = PreferenceManager.getDefaultSharedPreferences(mContext);
        mLosses = PreferenceManager.getDefaultSharedPreferences(mContext);
    }


    void setId(String id){

        userId = id;
    }

    void setSquad(String squad){
        this.squad = squad;
    }

    String getMembers(){
        return (mMembers.getString("rank", ""));
    }

    String getRep(){
        return (mRep.getString("rank", ""));
    }

    double getWinrate(){
        int wins = mWins.getInt("wins", 0);
        int losses = mWins.getInt("losses", 0);
        return (wins/(wins+losses));
    }


    private String squadDetails(){
        /*create JSONObject: return string*/
        JSONObject squadDetails = new JSONObject();
        try {
            squadDetails.put("type", "squad");
            squadDetails.put("squad", squad);
            squadDetails.put("userid", userId);
        }catch(JSONException e) {
            //do something
        }
        return squadDetails.toString();
    }

    public void sendSquadDetails() {
        String relativeUrl = "squad";
        String contentType = "application/json";
        String entity = squadDetails();
        /* send post request*/
        HttpUtils.post(mContext, relativeUrl, new StringEntity(entity, "UTF-8"), contentType, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("squad response:", "" + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    String type = serverResp.getString("type");
                    if(type.equals("squad")) {
                        JSONArray members = serverResp.getJSONArray("members");
                        String membs = members.toString();
                        String rep = serverResp.getString("rep");
                        int wins = serverResp.getInt("wins");
                        int losses = serverResp.getInt("losses");


                        membersEditor = mMembers.edit();
                        membersEditor.putString("members", membs);
                        membersEditor.apply();

                        repEditor = mRep.edit();
                        repEditor.putString("rep", rep);
                        repEditor.apply();

                        winsEditor = mWins.edit();
                        winsEditor.putInt("wins", wins);
                        winsEditor.apply();

                        lossesEditor = mLosses.edit();
                        lossesEditor.putInt("losses", losses);
                        lossesEditor.apply();
                    }


                    /* should check for errors */
                } catch (JSONException e) {
                    // do something
                }
            }
        });

    }
}
