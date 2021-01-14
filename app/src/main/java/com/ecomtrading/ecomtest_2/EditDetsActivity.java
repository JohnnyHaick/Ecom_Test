package com.ecomtrading.ecomtest_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

public class EditDetsActivity extends AppCompatActivity {
    EditText comname;
    AutoCompleteTextView conn, dte, acc, geodis;
    Button buttons, buttoncan, snapbutt;
    ImageView imageprev;
    TextView dateText;
    private final int requestCode = 20;
    Bitmap bm;
    String img_str;
    byte[] imageInByte;
    private LocationManager locationManager;

    private LocationManager locManager;
    private LocationListener locListener = new MyLocationListener();

    private boolean gps_enabled = false;
    private boolean network_enabled = false;
    String longitude;
    String latitude;
    String[] connops = {"Yes", "No"};
    String select;
    ArrayList<String> County;
    ArrayList<CommonModel> accw;
    ArrayList<String> CountryName;
    ArrayList<GeoModel> geow;
    int gid;
    ArrayAdapter<String> adapto;
    DBController controller = new DBController(this);
    final String uniqueID = UUID.randomUUID().toString();
    final String uniqueid = uniqueID.substring(0, uniqueID.length()-23);
    ArrayAdapter<String> adaptey;
    String accid;
    ArrayAdapter<String> adaptoe;
    String dtid, datus;
    private int year, month, day;
    Calendar calendar;
    String cd, zd, ad, td, md, pd, st, sd, ct, pn, cin, geo, ark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dets);

        //initialisation
        geow = new ArrayList<GeoModel>();
        CountryName=new ArrayList<String>();
        accw = new ArrayList<CommonModel>();
        dateText = (TextView)findViewById(R.id.dateText);

        cd = getIntent().getStringExtra("cid");
        /*intentcan.putExtra("cin", cin);
        intentcan.putExtra("geo", geo);
        intentcan.putExtra("ark", ark);*/
        cin = getIntent().getStringExtra("cin");
        geo = getIntent().getStringExtra("geo");
        ark = getIntent().getStringExtra("ark");
        zd = getIntent().getStringExtra("dte");
        ad = getIntent().getStringExtra("cte");
        td = getIntent().getStringExtra("dol");
        md = getIntent().getStringExtra("lat");
        pd = getIntent().getStringExtra("longi");
        sd = getIntent().getStringExtra("cb");
        st = getIntent().getStringExtra("cd");
        ct = getIntent().getStringExtra("ub");
        pn = getIntent().getStringExtra("ud");


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        buttoncan = (Button)findViewById(R.id.buttoncan);
        buttons = (Button)findViewById(R.id.buttons);
        snapbutt = (Button)findViewById(R.id.snapbutt);

        comname = (EditText)findViewById(R.id.comname);

        conn = (AutoCompleteTextView)findViewById(R.id.conn);
        dte = (AutoCompleteTextView)findViewById(R.id.dte);
        acc = (AutoCompleteTextView)findViewById(R.id.acc);
        geodis = (AutoCompleteTextView)findViewById(R.id.geodis);

        dte.setText(zd);
        conn.setText(ad);
        comname.setText(cin);
        geodis.setText(geo);
        acc.setText(ark);


        dateText = (TextView)findViewById(R.id.dateText);

        adaptey = new ArrayAdapter<String>(EditDetsActivity.this,
                android.R.layout.simple_list_item_1, controller.getAllArea("Accessibility"));
        acc.setAdapter(adaptey);
        acc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                acc.showDropDown();
                return false;
            }
        });
        acc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);

                accid = controller.getAreaId(selection);

                Log.e("accid", accid);

            }
        });

        adaptoe = new ArrayAdapter<String>(EditDetsActivity.this,
                android.R.layout.simple_list_item_1, controller.getAllArea("ECOM_Distance"));
        dte.setAdapter(adaptoe);
        dte.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dte.showDropDown();
                return false;
            }
        });
        dte.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);

                dtid = controller.getAreaId(selection);

                Log.e("dtid", dtid);

            }
        });

        //ecg connection dropdown
        ArrayAdapter<String> nadapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, connops);
        conn.setAdapter(nadapter);
        conn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                conn.showDropDown();
                return false;
            }
        });

        conn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (selection.equals("Yes")){
                    select = "Y";
                }
                else{
                    select = "N";
                }

            }
        });

        adapto = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, controller.getAllBank());
        geodis.setAdapter(adapto);
        geodis.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                geodis.showDropDown();
                return false;
            }
        });
        geodis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String select = (String) parent.getItemAtPosition(position);
                gid = Integer.parseInt(controller.getBankId(select));
                //Log.e("moi", String.valueOf(controller.getAllArea(String.valueOf(bid))));
                Log.e("ul", String.valueOf(gid));

            }
        });

        //cancel button
        buttoncan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ibcc_builder = new AlertDialog.Builder(EditDetsActivity.this);
                ibcc_builder.setMessage("Do you want to cancel this registration?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intentibcc = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intentibcc);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alert = ibcc_builder.create();
                alert.setTitle("Alert");
                alert.show();
            }
        });

        //final post
        buttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AutoCompleteTextView conn, dte, acc, geodis;
                if(comname.getText().toString().trim().length()==0){
                    comname.setError("Kindly Fill This Field");
                }
                else if (conn.getText().toString().trim().length()==0){
                    conn.setError("Kindly Fill This Field");
                }
                else if (dte.getText().toString().trim().length()==0){
                    dte.setError("Kindly Fill This Field");
                }
                else if (acc.getText().toString().trim().length()==0){
                    acc.setError("Kindly Fill This Field");
                }
                else if (geodis.getText().toString().trim().length()==0){
                    geodis.setError("Kindly Fill This Field");
                }
                else {
                    try {
                        checkInternetConnection();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        //image
        imageprev = (ImageView)findViewById(R.id.Imageprev);

        snapbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoCaptureIntent, requestCode);

                // Check Camera
                //clickpic();
            }
        });

        //location
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);

        locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        try {
            gps_enabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }
        try {
            network_enabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        // don't start listeners if no provider is enabled

        if (gps_enabled) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
        }
        if (network_enabled) {
            locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
        }

    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        /*Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();*/
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        else{
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);        }
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };


    private void showDate(int year, int month, int day) {
        dateText.setText(new StringBuilder().append(month).append("/")
                .append(day).append("/").append(year));
        datus = dateText.getText().toString();
    }

    private void checkInternetConnection() throws JSONException {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // ARE WE CONNECTED TO THE NET
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnectedOrConnecting()) {
            new RegRequest().execute();
            addNewUser();
//syncSQLiteMySQLDB();

        }else {
            addNewUser();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode == requestCode && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            imageprev.setImageBitmap(bitmap);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            imageInByte = baos.toByteArray();
            img_str = Base64.encodeToString(imageInByte, 0);
            Log.e("Array", String.valueOf(imageInByte));
//save your stuff
        }
    }

    class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                // This needs to stop getting the location data and save the battery power.
                locManager.removeUpdates(locListener);

                longitude = String.valueOf(location.getLongitude());
                latitude = String.valueOf(location.getLatitude());
                String altitiude = "Altitiude: " + location.getAltitude();
                String accuracy = "Accuracy: " + location.getAccuracy();
                String time = "Time: " + location.getTime();

                //textView.setText(longitude + "\n" + latitude);
                //progress.setVisibility(View.GONE);
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }
    }


    public void addNewUser() {

        final DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        final Date dateobj = new Date();

        HashMap<String, String> queryValues = new HashMap<String, String>();
        queryValues.put("RemoteID", uniqueid);
        queryValues.put("CommunityName", comname.getText().toString().trim());
        queryValues.put("GeographicalDistrict", String.valueOf(gid));
        queryValues.put("Accessibility", accid);
        queryValues.put("DistanceToECOM", dtid);
        queryValues.put("ConnectedToECG", select);
        queryValues.put("LicenseDate", datus);
        queryValues.put("Latitude", latitude);
        queryValues.put("Longitude", longitude);
        queryValues.put("CreatedBy", "");
        queryValues.put("CreatedDate", "");
        queryValues.put("UpdatedBy", "Johnny Haick");
        queryValues.put("UpdatedDate", df.format(dateobj));
        queryValues.put("Photo", "img_str");



        controller.billinsertUser(queryValues);
        Toast.makeText(getApplicationContext(), "Saved ",
                Toast.LENGTH_LONG).show();
        Intent intentibs = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intentibs);
        Log.e("regparams", queryValues.toString());
        //this.callHomeActivity(view);

    }

    public class RegRequest extends AsyncTask<String, Void, String> {

        final DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        final Date dateobj = new Date();
        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{

                java.net.URL url = new URL("http://41.139.29.11/CropDoctorWebApi/Help/Api/POST-api-InstructionSheet-SaveCommunityData");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("CommunityName", comname.getText().toString().trim());
                postDataParams.put("GeographicalDistrict", gid);
                postDataParams.put("Accessibility", Integer.parseInt(accid));
                postDataParams.put("DistanceToECOM", Integer.parseInt(dtid));
                postDataParams.put("ConnectedToECG", select);
                postDataParams.put("LicenseDate", datus);
                postDataParams.put("Latitude", latitude);
                postDataParams.put("Longitude", longitude);
                postDataParams.put("RemoteID", uniqueid);
                postDataParams.put("CreatedBy", "");
                postDataParams.put("CreatedDate", "");
                postDataParams.put("UpdatedBy", "Johnny Haick");
                postDataParams.put("UpdatedDate", df.format(dateobj));
                postDataParams.put("Photo", img_str);

                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 );
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                //conn.setRequestProperty("Authorization", "Basic " + "NTI5MjNmMzVjNmZjYzVkZDJiNzY2MGQ0ZDI0Y2JkZTJkZDljNzVjNzhjYzA1ZjY2N2Y0MDRkMGUzNWNhNjFhY2YxMTM3YTM2NGY2N2M4MTA=");

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";
                    /*Intent mintent = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(mintent);*/
                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result,
                    Toast.LENGTH_LONG).show();

        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}