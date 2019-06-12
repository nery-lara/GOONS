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
    private Context mContext;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences nMembers;
    private SharedPreferences eMembers;
    private SharedPreferences wMembers;
    private SharedPreferences sMembers;
    private SharedPreferences nRep;
    private SharedPreferences eRep;
    private SharedPreferences wRep;
    private SharedPreferences sRep;
    private SharedPreferences nWins;
    private SharedPreferences eWins;
    private SharedPreferences wWins;
    private SharedPreferences sWins;





    squad(Context context){
        mContext = context;
        nMembers = PreferenceManager.getDefaultSharedPreferences(mContext);
        eMembers = PreferenceManager.getDefaultSharedPreferences(mContext);
        wMembers = PreferenceManager.getDefaultSharedPreferences(mContext);
        sMembers = PreferenceManager.getDefaultSharedPreferences(mContext);
        nRep = PreferenceManager.getDefaultSharedPreferences(mContext);
        eRep = PreferenceManager.getDefaultSharedPreferences(mContext);
        wRep = PreferenceManager.getDefaultSharedPreferences(mContext);
        sRep = PreferenceManager.getDefaultSharedPreferences(mContext);
        nWins = PreferenceManager.getDefaultSharedPreferences(mContext);
        eWins = PreferenceManager.getDefaultSharedPreferences(mContext);
        wWins = PreferenceManager.getDefaultSharedPreferences(mContext);
        sWins = PreferenceManager.getDefaultSharedPreferences(mContext);
    }


    void setId(String id){

        userId = id;
    }

    int getNorthMemberCount(){
        return (nMembers.getInt("nMembers", 0));
    }

    int getEastMemberCount(){
        return (eMembers.getInt("eMembers", 0));
    }

    int getWestMemberCount(){
        return (wMembers.getInt("wMembers", 0));
    }

    int getSouthMemberCount(){
        return (sMembers.getInt("eMembers", 0));
    }

    int getNorthRep(){
        return (nRep.getInt("nRep", 0));
    }

    int getEastRep(){
        return (eRep.getInt("eRep", 0));
    }

    int getWestRep() {
        return (wRep.getInt("wRep", 0));
    }

    int getSouthRep() {
        return (sRep.getInt("sRep", 0));
    }

    int getNorthWins(){
        return (nWins.getInt("nWins", 0));
    }

    int getEastWins(){
        return (eWins.getInt("eWins", 0));
    }

    int getWestWins(){
        return (wWins.getInt("wWins", 0));
    }

    int getSouthWins(){
        return (sWins.getInt("sWins", 0));
    }

    private String squadDetails(){
        /*create JSONObject: return string*/
        JSONObject squadDetails = new JSONObject();
        try {
            squadDetails.put("type", "squad");
            squadDetails.put("userid", userId);
        }catch(JSONException e) {
            //do something
        }
        return squadDetails.toString();
    }

    public void sendSquadDetails() {
        String relativeUrl = "squads";
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
                    if(type.equals("squads")) {
                        Log.d("squad response:", "" + response);
                        JSONObject north = serverResp.getJSONObject("north");
                        Log.d("squad north:", "" + north);
                        JSONObject east = serverResp.getJSONObject("east");
                        JSONObject west = serverResp.getJSONObject("west");
                        JSONObject south = serverResp.getJSONObject("south");
                        int northWins = north.getInt("wins");
                        Log.d("squad northwins:", "" + northWins);
                        int eastWins = east.getInt("wins");
                        int westWins = west.getInt("wins");
                        int southWins = south.getInt("wins");
                        int nMembs = north.getInt("count");
                        int eMembs = east.getInt("count");
                        int wMembs = west.getInt("count");
                        int sMembs = south.getInt("count");
                        int nReps = north.getInt("rep");
                        int eReps = east.getInt("rep");
                        int wReps= west.getInt("rep");
                        int sReps = south.getInt("rep");





                        mEditor = nMembers.edit();
                        mEditor.putInt("nMembers", nMembs);
                        mEditor.apply();

                        mEditor = eMembers.edit();
                        mEditor.putInt("eMembers", eMembs);
                        mEditor.apply();

                        mEditor = wMembers.edit();
                        mEditor.putInt("wMembers", wMembs);
                        mEditor.apply();

                        mEditor = sMembers.edit();
                        mEditor.putInt("sMembers", sMembs);
                        mEditor.apply();

                        mEditor = nRep.edit();
                        mEditor.putInt("nRep", nReps);
                        mEditor.apply();

                        mEditor = eRep.edit();
                        mEditor.putInt("eRep", eReps);
                        mEditor.apply();

                        mEditor = wRep.edit();
                        mEditor.putInt("wRep", wReps);
                        mEditor.apply();

                        mEditor = sRep.edit();
                        mEditor.putInt("sRep", sReps);
                        mEditor.apply();

                        mEditor = nWins.edit();
                        mEditor.putInt("nWins", northWins);
                        mEditor.apply();

                        mEditor = eWins.edit();
                        mEditor.putInt("eWins", eastWins);
                        mEditor.apply();

                        mEditor = wWins.edit();
                        mEditor.putInt("wWins", westWins);
                        mEditor.apply();

                        mEditor = sWins.edit();
                        mEditor.putInt("sWins", southWins);
                        mEditor.apply();

                    }


                    /* should check for errors */
                } catch (JSONException e) {
                    // do something
                }
            }
        });

    }
}
