package com.ecomtrading.ecomtest_2;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_CONTACTS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;
import static java.lang.Thread.sleep;


public class MainActivity extends AppCompatActivity {
    RelativeLayout relay1;
    CardView toRegis, updateReg;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            relay1.setVisibility(View.VISIBLE);

        }
    };
    private static final int PERMISSION_REQUEST_CODE = 200;
    ArrayList<String> County;
    ArrayList<AccModel> accw;
    ArrayList<String> CountryName;
    ArrayList<GeoModel> geow;
    String keyname = "Geo District";
    String uri = keyname.replace(" ", "%20");
    String URL2 = "http://41.139.29.11/CropDoctorWebAPI/api/CommunityRegistration/Masters?sMasterType="+uri;
    String URL3 = "http://41.139.29.11/CropDoctorWebAPI/api/CommunityRegistration/Masters?sMasterType=Common";
    int gid;
    DBController controller = new DBController(this);
    String acc, acc_token;
    String TokenUrl = "http://41.139.29.11/CropDoctorWebAPI/api/token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkPermission()) {

            //Snackbar.make(view, "Permission already granted.", Snackbar.LENGTH_LONG).show();
            /*Toast.makeText(getApplicationContext(),
                    "Permission already granted",
                    Toast.LENGTH_SHORT).show();*/

        } else {
            requestPermission();
        }


        geow = new ArrayList<GeoModel>();
        CountryName=new ArrayList<String>();
        accw = new ArrayList<AccModel>();
        County = new ArrayList<String>();


        relay1 = (RelativeLayout) findViewById(R.id.rellay1);

        handler.postDelayed(runnable, 3000);

        GetToken();

        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        toRegis = findViewById(R.id.toRegis);
        updateReg = findViewById(R.id.updateReg);

        toRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regintent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(regintent);
            }
        });

        updateReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent upintent = new Intent(getApplicationContext(), EditActivity.class);
                startActivity(upintent);
            }
        });
    }


    private boolean checkPermission() {
        //ACCESS_FINE_LOCATION, CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, READ_CONTACTS, WRITE_CONTACTS, READ_PHONE_STATE
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int result3 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result4 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_CONTACTS);
        int result5 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_CONTACTS);
        int result6 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_PHONE_STATE);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED && result3 == PackageManager.PERMISSION_GRANTED
                && result4 == PackageManager.PERMISSION_GRANTED && result5 == PackageManager.PERMISSION_GRANTED && result6 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, READ_CONTACTS, WRITE_CONTACTS, READ_PHONE_STATE}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted && cameraAccepted)
                        //Snackbar.make(view, "Permission Granted, Now you can access location data and camera.", Snackbar.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),
                                "Permissions Granted",
                                Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(getApplicationContext(),
                                "Permissions Denied",
                                Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, READ_CONTACTS, WRITE_CONTACTS, READ_PHONE_STATE},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                    }
                }


                break;
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    /*@Override
    public void onPermissionsGranted(final int requestCode) {
        Toast.makeText(this, "Permissions Received.", Toast.LENGTH_LONG).show();
    }*/


    private void loadToken(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{

                        JSONObject jsonObject1= new JSONObject(response);
                        acc = jsonObject1.getString("access_token");


                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){@Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> params = new HashMap<String, String>();
            String creds = String.format("%s:%s","murali","welcome");
            String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
            params.put("Authorization", auth);
            return params;
        }
        };
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

    }

    private void loadGeoData(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{


                    JSONArray parentArray = new JSONArray(response);

                    for(int i=0;i<parentArray.length();i++){
                        JSONObject jsonObject1=parentArray.getJSONObject(i);
                        GeoModel geoModel = new GeoModel();

                        geoModel.setGeo_District_Code(jsonObject1.getInt("Geo_District_Code"));
                        geoModel.setGeo_District_Name(jsonObject1.getString("Geo_District_Name"));
                        Log.e("json", String.valueOf(jsonObject1));


                        geow.add(geoModel);

                        // Populate spinner with country names
                        controller.saveBank(String.valueOf(jsonObject1.getInt("Geo_District_Code")), jsonObject1.getString("Geo_District_Name"));

                    }

                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){@Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            //String encodedCredentials = access_token;
            headers.put("Authorization", "Bearer " + acc_token);

            return headers;
        }
        };
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

    }


    private void loadAccData(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{


                    JSONArray parentArray = new JSONArray(response);
                    Log.e("parentArray", String.valueOf(parentArray));


                    for(int i=0;i<parentArray.length();i++){
                        JSONObject jsonObject1=parentArray.getJSONObject(i);
                        AccModel accModel = new AccModel();

                        accModel.setMst_Code(jsonObject1.getInt("Mst_Code"));
                        accModel.setMst_Type(jsonObject1.getString("Mst_Type"));
                        accModel.setMst_Desc(jsonObject1.getString("Mst_Desc"));


                        accw.add(accModel);

                        // Populate spinner with country names
                        County.add(jsonObject1.optString("Mst_Desc"));
                        HashMap<String, String> queryValues = new HashMap<String, String>();
                        queryValues.put("AreaId", jsonObject1.getString("Mst_Code"));
                        queryValues.put("AreaName", jsonObject1.getString("Mst_Type"));
                        queryValues.put("ZoneName", jsonObject1.getString("Mst_Desc"));



                        controller.areainsertUser(queryValues);
                        Log.e("llakla", String.valueOf(queryValues));
                        //controller.saveArea(jsonObject1.getString("Mst_Code"),jsonObject1.getString("Mst_Type"), jsonObject1.getString("Mst_Desc"));
                        Log.e("accw", String.valueOf(jsonObject1));
                    }

                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){@Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json");
            Log.e("acc_token", acc_token);
            //String encodedCredentials = access_token;
            headers.put("Authorization", "Bearer " + acc_token);

            return headers;
        }
        };
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

    }


/*
    private void GetToken(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{



                        JSONObject jsonObject1= new JSONObject(response);
                        AccModel accModel = new AccModel();


                        accModel.setMst_Code(jsonObject1.getInt("Mst_Code"));
                        accModel.setMst_Type(jsonObject1.getString("Mst_Type"));
                        accModel.setMst_Desc(jsonObject1.getString("Mst_Desc"));


                        accw.add(accModel);

                        // Populate spinner with country names
                        County.add(jsonObject1.optString("Mst_Desc"));
                        HashMap<String, String> queryValues = new HashMap<String, String>();
                        queryValues.put("AreaId", jsonObject1.getString("Mst_Code"));
                        queryValues.put("AreaName", jsonObject1.getString("Mst_Type"));
                        queryValues.put("ZoneName", jsonObject1.getString("Mst_Desc"));



                        controller.areainsertUser(queryValues);
                        Log.e("llakla", String.valueOf(queryValues));
                        //controller.saveArea(jsonObject1.getString("Mst_Code"),jsonObject1.getString("Mst_Type"), jsonObject1.getString("Mst_Desc"));
                        Log.e("accw", String.valueOf(jsonObject1));

                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){@Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> params = new HashMap<String, String>();
            String creds = String.format("%s:%s:%s","murali","welcome", "password");
            String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
            params.put("Authorization", auth);
            return params;
        }
        };
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

    }
*/

    public void GetToken()
    {

        //String url = "http://localhost:8081/token";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());

        StringRequest postRequest = new StringRequest(Request.Method.POST, TokenUrl,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.e("TOKEN_AUTH", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            acc_token = jsonObject.getString("access_token");
                            Log.e("acc", acc_token);
                            loadGeoData(URL2);
                            loadAccData(URL3);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.e("KL", error.networkResponse.toString());
                    }
                }
        ) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String> ();
                params.put("grant_type", "password");
                params.put("username", "murali");
                params.put("password", "welcome");
                return params;
            }
            @Override
            protected VolleyError parseNetworkError(VolleyError response) {
                try {

                    String json = new String(response.networkResponse.data, HttpHeaderParser.parseCharset(response.networkResponse.headers));
                    Log.e("KL", "ERROR_RESPONSE = " + json);
                }catch (Exception e){}
                return super.parseNetworkError(response);
            }
        };

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        postRequest.setRetryPolicy(policy);
        requestQueue.add(postRequest);
    }


}