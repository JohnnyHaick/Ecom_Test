package com.ecomtrading.ecomtest_2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class EditActivity extends AppCompatActivity {
    private ListView lv;
    String url;
    private String TAG = EditActivity.class.getSimpleName();
    String water;
    String ClientId;
    String ClientName, Zone, Area, BillNumber, BillAmount, BusinessMobile, BusinessTelephone, PrimaryContactPhone;
    String TotalAmountPaid;
    String AmountDue;
    //private ProgressBar spinner;
    String PaymentId;
    String Stat;
    String PaymentChannel;
    String Amount;
    String RevenueCollectorName;
    String BillType;
    String Description;
    String Amountbill;
    String Id;
    String pui;
    ArrayList<HashMap<String, String>> contactList;
    ListViewAdapter adapter;
    String bread;
    String BillStatus;
    String Outstanding;
    HashMap<String, String> contact = new HashMap<>();
    SQLiteDatabase newDB;
    static EditText cnText;
    ClientModel clientModel;
    ArrayList<HashMap<String, String>> worldc;
    ArrayList<ClientModel> worldcup;
    //ArrayList<ClientModel> worldc;
    String cin, cid;
    String Type, community_name, geographical_district, accessibility, distance_to_ecom, connected_to_ecg, date_of_license, id, latitude, longitude, Image;
    String dte, cte, dol, lat, longi, cb, ub, ud, cd;
    String geo;
    String ark;
    DBController controller = new DBController(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        worldc = new ArrayList<HashMap<String, String>>();
        worldcup = new ArrayList<ClientModel>();

        cnText = (EditText)findViewById(R.id.cnText);
        cnText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    /*CashDetsActivity.this.adapter.getFilter().filter(s);
                    adapter.notifyDataSetChanged();*/
            }

            @Override
            public void afterTextChanged(Editable s) {
                String teext = cnText.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(teext);
            }
        });


        water = getIntent().getStringExtra("WATER");
        bread = getIntent().getStringExtra("LIFE");
        contactList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listd);
        lv.setTextFilterEnabled(true);
        /*spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);*/
        adapter = new ListViewAdapter(this, worldcup);
        lv.setAdapter(adapter);

        openAndQueryDatabase();
    }

    private void openAndQueryDatabase() {
        try {
            DBController dbController = new DBController(this.getApplicationContext());
            newDB = dbController.getWritableDatabase();
/*
            billq = "CREATE TABLE bill (Id TEXT PRIMARY KEY, community_name TEXT, geographical_district TEXT, accessibility TEXT, distance_to_ecom TEXT, connected_to_ecg TEXT, date_of_license TEXT, " +
                    "latitude TEXT, longitude TEXT, Image TEXT, updateStatus TEXT)";*/
            Cursor c = newDB.rawQuery("SELECT RemoteID, CommunityName, GeographicalDistrict, Accessibility, DistanceToECOM, ConnectedToECG, LicenseDate, Latitude, Longitude, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate FROM pk", null);

            Log.e("joy", String.valueOf(c));
            if (c != null ) {
                if (c.moveToFirst()) {
                    do {
                        /*id = c.getString(c.getColumnIndex("Id"));
                        community_name = c.getString(c.getColumnIndex("community_name"));
                        geographical_district = c.getString(c.getColumnIndex("geographical_district"));
                        accessibility = c.getString(c.getColumnIndex("accessibility"));
                        distance_to_ecom = c.getString(c.getColumnIndex("distance_to_ecom"));
                        connected_to_ecg = c.getString(c.getColumnIndex("connected_to_ecg"));
                        date_of_license = c.getString(c.getColumnIndex("date_of_license"));
                        latitude = c.getString(c.getColumnIndex("latitude"));
                        longitude = c.getString(c.getColumnIndex("longitude"));
                        Image = c.getString(c.getColumnIndex("Image"));*/

/*
                        HashMap<String, String> contact = new HashMap<>();

                        contact.put("ClientName", ClientName);
                        contact.put("ClientId", ClientId);
                        contact.put("Zone", Zone);
                        contact.put("Area", Area);
                        contact.put("BillStatus", BillStatus);
                        contact.put("BillNumber", BillNumber);
                        contact.put("Outstanding", Outstanding);
                        contact.put("Type", Type);

                        contactList.add(contact);*/
                        clientModel = new ClientModel();
                        //Cursor c = newDB.rawQuery("SELECT RemoteID, CommunityName, GeographicalDistrict, Accessibility, DistanceToECOM, ConnectedToECG, LicenseDate, Latitude, Longitude, CreatedBy, CreatedDate, UpdatedBy, UpdatedDate FROM bill", null);

                        clientModel.setId(c.getString(c.getColumnIndex("RemoteID")));
                        clientModel.setCommunity_name(c.getString(c.getColumnIndex("CommunityName")));
                        clientModel.setGeographical_district(c.getString(c.getColumnIndex("GeographicalDistrict")));
                        clientModel.setAccessibility(c.getString(c.getColumnIndex("Accessibility")));
                        clientModel.setDistance_to_ecom(c.getString(c.getColumnIndex("DistanceToECOM")));
                        clientModel.setConnected_to_ecg(c.getString(c.getColumnIndex("ConnectedToECG")));
                        clientModel.setDate_of_license(c.getString(c.getColumnIndex("LicenseDate")));
                        clientModel.setLatitude(c.getString(c.getColumnIndex("Latitude")));
                        clientModel.setLongitude(c.getString(c.getColumnIndex("Longitude")));
                        clientModel.setCreatedBy(c.getString(c.getColumnIndex("CreatedBy")));
                        clientModel.setCreatedDate(c.getString(c.getColumnIndex("CreatedDate")));
                        clientModel.setUpdatedBy(c.getString(c.getColumnIndex("UpdatedBy")));
                        clientModel.setUpdatedDate(c.getString(c.getColumnIndex("UpdatedDate")));

                       /* clientModel = new ClientModel();

                        clientModel.setClientId(c.getString(c.getColumnIndex("ClientId")));
                        clientModel.setClientName(c.getString(c.getColumnIndex("ClientName")));
                        clientModel.setBillStatus(c.getString(c.getColumnIndex("BillStatus")));
                        clientModel.setBillNumber(c.getString(c.getColumnIndex("BillNumber")));
                        clientModel.setZone(c.getString(c.getColumnIndex("Zone")));
                        clientModel.setArea(c.getString(c.getColumnIndex("Area")));
                        clientModel.setOutstanding(c.getString(c.getColumnIndex("Outstanding")));
                        clientModel.setAmountPaid(c.getString(c.getColumnIndex("AmountPaid")));
                        clientModel.setValuationNumber(c.getString(c.getColumnIndex("ValuationNumber")));
                        clientModel.setType(c.getString(c.getColumnIndex("Type")));*/


                        //clientModel.setAmountPaid(c.getString(c.getColumnIndex("AmountPaid")));
                        worldcup.add(clientModel);
                        adapter = new ListViewAdapter(this, worldcup);
                        lv.setAdapter(adapter);
                        //Log.e("jo", String.valueOf(contactList));


                    }while (c.moveToNext());
                }

            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            /*if (newDB != null)
                newDB.execSQL("DELETE FROM " + tableName);
            newDB.close();*/
        }

    }


    public class ListViewAdapter extends BaseAdapter {

        // Declare Variables
        Context mContext;
        LayoutInflater inflater;
        public List<ClientModel> ClientModellist = null;
        public ArrayList<ClientModel> arraylist;

        public ListViewAdapter(Context context, List<ClientModel> ClientModellist) {
            mContext = context;
            this.ClientModellist = ClientModellist;
            inflater = LayoutInflater.from(mContext);
            this.arraylist = new ArrayList<ClientModel>();
            if (ClientModellist!=null)
                this.arraylist.addAll(ClientModellist);
        }


        public class ViewHolder {
            TextView rank;
            TextView country;
            TextView population;
            TextView sold;
            TextView note;
        }

        @Override
        public int getCount() {

            return ClientModellist.size();
        }

        @Override
        public ClientModel getItem(int position) {
            return ClientModellist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View view, ViewGroup parent) {
            final ViewHolder holder;
            if (view == null) {
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.dets_list, null);
                // Locate the TextViews in listview_item.xml
                holder.rank = (TextView) view.findViewById(R.id.email);
                holder.country = (TextView) view.findViewById(R.id.tele);

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            // Set the results into TextViews
            // R.id.email, R.id.mobile, R.id.tele, R.id.name, R.id.call
            holder.rank.setText("Community Name: "+ClientModellist.get(position).getCommunity_name());
            holder.country.setText("Date of License: "+ClientModellist.get(position).getDate_of_license());


            // Listen for ListView Item Click
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    cin = ClientModellist.get(position).getCommunity_name();
                    cid = ClientModellist.get(position).getId();
                    geo = ClientModellist.get(position).getGeographical_district();
                    dte = ClientModellist.get(position).getDistance_to_ecom();
                    cte = ClientModellist.get(position).getConnected_to_ecg();
                    dol = ClientModellist.get(position).getDate_of_license();
                    lat = ClientModellist.get(position).getLatitude();
                    longi = ClientModellist.get(position).getLongitude();
                    cb = ClientModellist.get(position).getCreatedBy();
                    cd = ClientModellist.get(position).getCreatedDate();
                    ub = ClientModellist.get(position).getUpdatedBy();
                    ud = ClientModellist.get(position).getUpdatedDate();
                    ark = ClientModellist.get(position).getAccessibility();


                    AlertDialog.Builder a_builder;
                    a_builder = new AlertDialog.Builder(EditActivity.this);
                    a_builder.setMessage("Kindly Select").setCancelable(true)
                            .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intentcan = new Intent(getApplicationContext(), EditDetsActivity.class);
                                    intentcan.putExtra("cid", cid);
                                    intentcan.putExtra("cin", cin);
                                    intentcan.putExtra("geo", geo);
                                    intentcan.putExtra("ark", ark);
                                    intentcan.putExtra("dte", dte);
                                    intentcan.putExtra("cte", cte);
                                    intentcan.putExtra("dol", dol);
                                    intentcan.putExtra("lat", lat);
                                    intentcan.putExtra("longi", longi);
                                    intentcan.putExtra("cb", cb);
                                    intentcan.putExtra("cd", cd);
                                    intentcan.putExtra("ub", ub);
                                    intentcan.putExtra("ud", ud);

                                    startActivity(intentcan);
                                }
                            }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            controller.deleteBill(cid);
                            Toast.makeText(getApplicationContext(),
                                    "Record Deleted",
                                    Toast.LENGTH_SHORT).show();
                            Intent intentcan = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intentcan);
                        }
                    });
                    AlertDialog alert = a_builder.create();
                    alert.setTitle("Alert");
                    alert.show();
                    /*cin = ClientModellist.get(position).getClientName();
                    String ad = ClientModellist.get(position).getOutstanding();
                    String cis = ClientModellist.get(position).getClientId();
                    String ap = ClientModellist.get(position).getAmountPaid();
                    String bn = ClientModellist.get(position).getBillNumber();



                    Intent pintent = new Intent(EditActivity.this, StatementActivity.class);
                    pintent.putExtra("ad", ad);
                    pintent.putExtra("cis", cis);
                    pintent.putExtra("ap", ap);
                    pintent.putExtra("bn", bn);
                    pintent.putExtra("cin", cin);

                    startActivity(pintent);*/
                    /*PaymentActivity.BillNumber = contactList.get(position).get("BillNumber").toString();
                    PaymentActivity.ad = ad;
                    PaymentActivity.cin = cis;*/
                }
            });

            return view;
        }

        // Filter Class
        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            ClientModellist.clear();
            if (charText.length() == 0) {
                ClientModellist.addAll(arraylist);
            } else {
                for (ClientModel wp : arraylist) {
                    if (wp.getCommunity_name().toLowerCase(Locale.getDefault()).contains(charText)) {
                        ClientModellist.add(wp);
                    }
                    else if (wp.getDate_of_license().toLowerCase(Locale.getDefault()).contains(charText)) {
                        ClientModellist.add(wp);
                    }

                }
            }
            notifyDataSetChanged();
        }
    }


/*private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            url = "http://gawest.paypot.com/api/utilities/getcustomerlist?clientId="+water+"&type="+bread;
            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + url);
            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    //JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        ClientId = c.getString("ClientId");
                        ClientName = c.getString("ClientName");

                        Zone = c.getString("Zone");
                        Area = c.getString("Area");
                        BillNumber = c.getString("BillNumber");
                        BillAmount = c.getString("BillAmount");
                        BusinessMobile = c.getString("BusinessMobile");
                        BusinessTelephone = c.getString("BusinessTelephone");
                        PrimaryContactPhone = c.getString("PrimaryContactPhone");
                        TotalAmountPaid = c.getString("TotalAmountPaid");
                        AmountDue = c.getString("AmountDue");

                        // Phone node is JSON Object
                        JSONArray phone = c.getJSONArray("Bills");

                        for (int j = 0; j < phone.length(); j++) {
                            JSONObject phon = phone.getJSONObject(j);
                            BillType = phon.getString("BillType");
                            Description = phon.getString("Description");
                            Amountbill = phon.getString("Amount");
                            Id = phon.getString("Id");
                        }

                        *//*JSONArray trans = c.getJSONArray("Payments");
                        for(int x = 0; x<trans.length(); x++){
                            JSONObject tran = trans.getJSONObject(x);
                            PaymentId = tran.getString("PaymentId");
                            Stat = tran.getString("Status");
                            PaymentChannel = tran.getString("PaymentChannel");
                            Amount = tran.getString("Amount");
                            RevenueCollectorName = tran.getString("RevenueCollectorName");
                        }*//*
                        //Log.e(TAG, "Response from url: " + trans);
                        HashMap<String, String> contact = new HashMap<>();
                        contact.put("ClientId", ClientId);
                        contact.put("ClientName", ClientName);
                        contact.put("Zone", Zone);
                        contact.put("Area", Area);
                        contact.put("BillNumber", BillNumber);
                        contact.put("BillAmount", BillAmount);
                        contact.put("BusinessMobile", BusinessMobile);
                        contact.put("BusinessTelephone", BusinessTelephone);
                        contact.put("TotalAmountPaid", TotalAmountPaid);
                        contact.put("AmountDue", AmountDue);


                        contactList.add(contact);


                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Network Error",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Kindly Check Internet Connection",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            spinner.setVisibility(View.GONE);
            Log.e(TAG, "Couldn't get json from server."+BillType);

            adapter = new SimpleAdapter(CashDetsActivity.this, contactList,
                    R.layout.dets_list, new String[]{ "ClientName","ClientId", "Zone", "Area", "PrimaryContactPhone"},
                    new int[]{R.id.email, R.id.mobile, R.id.tele, R.id.name, R.id.call});
            lv.setAdapter(adapter);
            spinner.setVisibility(View.INVISIBLE);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //String select = (String) parent.getItemAtPosition(position);

                    String cis = contactList.get(position).get("ClientId");
                    String cin = contactList.get(position).get("ClientName");
                    String zoe = contactList.get(position).get("Zone");
                    String are = contactList.get(position).get("Area");
                    String tap = contactList.get(position).get("TotalAmountPaid");
                    String ad = contactList.get(position).get("AmountDue");
                    String btel = contactList.get(position).get("BusinessTelephone");
                    String bmob = contactList.get(position).get("BusinessMobile");
                    String pcp = contactList.get(position).get("PrimaryContactPhone");




                    PaymentActivity.billText.setText("Outstanding Amount:  "+ad);
                    PaymentActivity.billid.setText("Client Name:    "+cin);
                    PaymentActivity.BillNumber = contactList.get(position).get("BillNumber").toString();
                    PaymentActivity.ad = ad;
                    PaymentActivity.cin = cis;
                    //PaymentActivity.outb = ad

                    onBackPressed();
                    //pui = client.getText().toString().trim();
                }
            });
            //setting value to textviews
            *//*
            transdets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent trantent = new Intent("com.example.user.gawest.TransActivity");
                    trantent.putExtra("YUP", client.getText().toString().trim());
                    startActivity(trantent);
                }
            });
            edittie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent editent = new Intent("com.example.user.gawest.EditActivity");
                    startActivity(editent);
                }
            });
            //client.setText("");
            billdets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent billintent = new Intent("com.example.user.gawest.CusStateActivity");
                    billintent.putExtra("YOU", client.getText().toString().trim());
                    startActivity(billintent);

                    *//**//*billintent.putExtra("BT", BillType);
                    billintent.putExtra("DESC", Description);
                    billintent.putExtra("AMT", Amountbill);*//**//*

                }
            });
*//*
        }
    }*/

}
