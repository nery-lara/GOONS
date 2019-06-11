package com.nerylara.goons;

import android.content.Context;
import android.util.Log;
import com.loopj.android.http.*;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class joinSquad {

    private String userId;
    private String squad;


    void setId(String id) {
        userId = id;
    }

    String getId() {
        return userId;
    }

    void setSquad(String squad) {
        this.squad = squad;
    }

    String getSquad() {
        return this.squad;
    }

    private String squaDetails(){

        /*create JSONObject: return string*/
        JSONObject squaDetails = new JSONObject();
        try {
            squaDetails.put("type", "joinSquad");
            squaDetails.put("userid", userId);
            squaDetails.put("squad", squad);
        }catch(JSONException e) {
            //do something
        }
        return squaDetails.toString();

    }

    public void sendSquaDetails(Context mContext) {
        String relativeUrl = "joinSquad";
        String contentType = "application/json";
        String entity = squaDetails();
        /* send post request*/
        HttpUtils.post(mContext, relativeUrl, new StringEntity(entity, "UTF-8"), contentType, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("joinSquad response:", "" + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    /* should check for errors */
                } catch (JSONException e) {
                    // do something
                }
            }
        });

    }

}
