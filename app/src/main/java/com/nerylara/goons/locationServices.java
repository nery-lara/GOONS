package com.nerylara.goons;

import android.content.Context;
import android.location.Location;
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

public class locationServices {

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Context mContext;
    private String relativeUrl = "location";
    private String contentType = "application/json";
    private String userId;

    /* set context */
    protected void setContext(Context context){
        mContext = context;
    }

    void setId(String id) {
        userId = id;
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

