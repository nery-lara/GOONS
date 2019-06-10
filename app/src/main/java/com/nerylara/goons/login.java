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

public class login {

    private String userId;
    private String password;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private Context mContext;

    login(Context context){
        mContext = context;
        mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    void setUserId(String id){
        userId = id;
    }

    void setPassword(String passwd){
        password = passwd;
    }

    public boolean getVerify(){
        return mPreferences.getBoolean("verify", false);
    }

    /*create JSONObject: return string*/
    private String loginDetails(){
        JSONObject loginDetails = new JSONObject();
        try {
            loginDetails.put("type", "login");
            loginDetails.put("userid", userId);
            loginDetails.put("password", password);
        }catch(JSONException e) {
            //do something
        }
        return loginDetails.toString();
    }

    /* send post request*/
    public void sendLoginDetails() {
        String relativeUrl = "login";
        String contentType = "application/json";
        String entity = loginDetails();
        HttpUtils.post(mContext, relativeUrl, new StringEntity(entity, "UTF-8"), contentType, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("sendLoginDetails response:", "" + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    String type = serverResp.getString("type");
                    if(type.equals("login")){
                        boolean verify = serverResp.getBoolean("verify");
                        mEditor = mPreferences.edit();
                        mEditor.putBoolean("verify", false);
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
