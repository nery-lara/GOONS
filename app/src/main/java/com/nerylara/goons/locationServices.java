package com.nerylara.goons;

import android.content.Context;
import android.location.Location;
import android.preference.PreferenceManager;
import android.util.Log;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class locationServices {

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Context mContext;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mLatitude;
    private SharedPreferences mLongitude;
    private String relativeUrl = "location";
    private String contentType = "application/json";
    private String userId;

    /* set context */
    locationServices(Context context){
        mContext = context;
        mLatitude = PreferenceManager.getDefaultSharedPreferences(mContext);
        mLongitude = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    void setId(String id) {

        userId = id;
    }

    Double getLongitude(){
        String lon = mLongitude.getString("Longitude", "");
        return Double.parseDouble(lon);

    }

    Double getLatitude(){
        String lat = mLongitude.getString("Latitude", "");
        return Double.parseDouble(lat);

    }

    public void requestLocation() {
        /* need to check permission has been granted */
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(mContext);

        mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){

                    double longitude = location.getLongitude();
                    double latitude = location.getLatitude();
                    Log.e("mylatitude:", ""+latitude);
                    Log.e("mylongitude:", ""+longitude);
                    Log.e("userId:", ""+userId);
                    JSONObject loc = new JSONObject();
                    JSONObject locationDetails = new JSONObject();
                    try {
                        loc.put("type", "location");
                        loc.put("userid", userId);
                        locationDetails.put("lat", latitude);
                        locationDetails.put("long", longitude);
                        loc.put("location", locationDetails);
                    }catch(JSONException e) {
                        //do something
                    }

                    String entity = loc.toString();

                    HttpUtils.post(mContext, relativeUrl, new StringEntity(entity, "UTF-8"), contentType, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            // If the response is JSONObject instead of expected JSONArray
                            Log.d("My Location:", "" + response);
                            try {
                                JSONObject serverResp = new JSONObject(response.toString());
                            } catch (JSONException e) {
                                // do something
                            }
                        }
                    });

                    mEditor = mLongitude.edit();
                    mEditor.putString("Latitude", Double.toString(latitude));
                    mEditor.apply();

                    mEditor = mLatitude.edit();
                    mEditor.putString("Longitude", Double.toString(longitude));
                    mEditor.apply();
                }
            }

        });
    }

    protected void createLocationRequest() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
}

