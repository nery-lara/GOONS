package com.nerylara.goons;

import android.content.Context;
import android.util.Log;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/* post request */
public class active {

    private String userId;
    private boolean active = false;


    void setUserId(String id){
        userId = id;
    }

    void setActive(boolean active){
        this.active = active;
    }

    boolean getActive(){
        return this.active;
    }

    private String activeDetails(){
        /*create JSONObject: return string*/
        JSONObject activeDetails = new JSONObject();
        try {
            activeDetails.put("type", "active");
            activeDetails.put("userid", userId);
            activeDetails.put("active", active);
        }catch(JSONException e) {
            //do something
        }
        return activeDetails.toString();
    }

    public void sendActiveDetails(Context mContext) {
        String relativeUrl = "active";
        String contentType = "application/json";
        String entity = activeDetails();
        /* send post request*/
        HttpUtils.post(mContext, relativeUrl, new StringEntity(entity, "UTF-8"), contentType, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("active response:", "" + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    String type = serverResp.getString("type");
                    /* should check for errors */
                } catch (JSONException e) {
                    // do something
                }
            }
        });

    }

}
