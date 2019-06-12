package com.nerylara.goons;

//this one should be a get

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class profile {

    private String userId;
    private String squad;
    private int imageNum;
    private int rank;
    private int winRate;
    private boolean queued;
    Context mContext;
    private SharedPreferences mSquad;
    private SharedPreferences.Editor squadEditor;
    private SharedPreferences mRank;
    private SharedPreferences.Editor rankEditor;
    private SharedPreferences mWins;
    private SharedPreferences.Editor winsEditor;
    private SharedPreferences mLosses;
    private SharedPreferences.Editor lossesEditor;
    private SharedPreferences mImageNum;
    private SharedPreferences.Editor imageNumEditor;

    profile(Context context){
        mContext = context;
        mSquad = PreferenceManager.getDefaultSharedPreferences(mContext);
        mRank = PreferenceManager.getDefaultSharedPreferences(mContext);
        mWins = PreferenceManager.getDefaultSharedPreferences(mContext);
        mLosses = PreferenceManager.getDefaultSharedPreferences(mContext);
        mImageNum = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public void setId(String id){
        userId = id;
    }



    int getImageNum(){
        return (mImageNum.getInt("imagenum", 0));
    }


    String getSquad(){
        return (mSquad.getString("squad", ""));
    }


    String getRank(){
        return (mRank.getString("rank", ""));
    }

    double getWinrate(){
        int wins = mWins.getInt("wins", 0);
        int losses = mWins.getInt("losses", 0);
        if(wins+losses == 0){
            return 0;
        }
        return (wins/(wins+losses));
    }




    private String profileDetails(){
        /*create JSONObject: return string*/
        JSONObject signUpDetails = new JSONObject();
        try {
            signUpDetails.put("type", "profile");
            signUpDetails.put("userid", userId);
        }catch(JSONException e) {
            //do something
        }
        return signUpDetails.toString();
    }

    public void sendProfileDetails(Context mContext) {
        String relativeUrl = "profile";
        String contentType = "application/json";
        String entity = profileDetails();
        /* send post request*/
        HttpUtils.get(mContext, relativeUrl, new StringEntity(entity, "UTF-8"), contentType, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("profile response:", "" + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    String type = serverResp.getString("type");
                    if(type.equals("profile")){

                        Log.d("user squad:", "in profile bro" );
                         JSONObject user = serverResp.getJSONObject("user");
                        Log.d("user object", "" + user);
                         String squad = user.getString("squad");
                        Log.d("user squad:", "" + squad);
                         String rank = user.getString("rank");
                         int wins = user.getInt("wins");
                        Log.d("user wins:", "" + wins);
                         int losses = user.getInt("losses");
                        Log.d("user losses:", "" + losses);
                         int imageNum = user.getInt("imagenum");
                        Log.d("user imagenum:", "" + imageNum);

                        imageNumEditor = mImageNum.edit();
                        imageNumEditor.putInt("imagenum", imageNum);
                        imageNumEditor.commit();

                        squadEditor = mSquad.edit();
                        squadEditor.putString("squad", squad);
                        squadEditor.apply();

                        rankEditor = mRank.edit();
                        rankEditor.putString("rank", rank);
                        rankEditor.apply();

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
