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

public class signUp {

    private String proposedId;
    private String userId;
    private String password;
    private int imageNum;
    private String contentType = "application/json";
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private Context mContext;

    signUp(Context context){
        mContext = context;
        mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    void setProposedId(String proposedId){
        this.proposedId = proposedId;
    }

    void setId(String id) {
        userId = id;
    }

    void setimageNum(int num){
        imageNum = num;
    }

    void setPassword(String passwd){
        password = passwd;
    }

    public boolean getVerify(){
        return mPreferences.getBoolean("verify", false);
    }

    private String proposedIDetails(){
        /*create JSONObject: return string*/
        JSONObject proposedIDetails = new JSONObject();
        try {
            proposedIDetails.put("type", "propose");
            proposedIDetails.put("proposeid", proposedId);
        }catch(JSONException e) {
            //do something
        }
        return proposedIDetails.toString();
    }

    public void sendProposedIDetails() {
        String relativeUrl = "propose";
        String entity = proposedIDetails();
        /* send post request*/
        HttpUtils.post(mContext, relativeUrl, new StringEntity(entity, "UTF-8"), contentType, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("proposed response:", "" + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    String type = serverResp.getString("type");
                    if(type.equals("propose")){
                        boolean verify = serverResp.getBoolean("verify");
                        mEditor = mPreferences.edit();
                        mEditor.putBoolean("verify", verify);
                        mEditor.apply();
                    }
                    /* should check for errors */
                } catch (JSONException e) {
                    // do something
                }
            }
        });
    }

    private String signUpDetails(){
        /*create JSONObject: return string*/
        JSONObject signUpDetails = new JSONObject();
        try {
            signUpDetails.put("type", "signup");
            signUpDetails.put("userid", userId);
            signUpDetails.put("password", password);
            signUpDetails.put("imagenum", imageNum);
        }catch(JSONException e) {
            //do something
        }
        return signUpDetails.toString();
    }

    public void sendSignUpDetails(Context mContext) {
        String relativeUrl2 = "signUp";
        String entity = signUpDetails();
        /* send post request*/
        HttpUtils.post(mContext, relativeUrl2, new StringEntity(entity, "UTF-8"), contentType, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("signUp response:", "" + response);
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
