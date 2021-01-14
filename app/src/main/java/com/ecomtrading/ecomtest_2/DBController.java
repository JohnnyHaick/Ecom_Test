package com.ecomtrading.ecomtest_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user on 4/18/2018.
 */

public class DBController extends SQLiteOpenHelper {

    public DBController(Context applicationcontext) {
        super(applicationcontext, "androidsqlite.db", null, 3);
    }
    //Creates Table
    @Override
    public void onCreate(SQLiteDatabase database) {
        String  billq, bank, agent,area,clients, pk;

        billq = "CREATE TABLE bill (RemoteID TEXT PRIMARY KEY, CommunityName TEXT, GeographicalDistrict TEXT, Accessibility TEXT, " +
                "DistanceToECOM TEXT, ConnectedToECG TEXT, LicenseDate TEXT, " +
                "Latitude TEXT, Longitude TEXT, CreatedBy TEXT, CreatedDate TEXT, " +
                "UpdatedBy TEXT, UpdatedDate TEXT, Photo TEXT, updateStatus TEXT)";

        pk = "CREATE TABLE pk (RemoteID TEXT PRIMARY KEY, CommunityName TEXT, GeographicalDistrict TEXT, Accessibility TEXT, " +
                "DistanceToECOM TEXT, ConnectedToECG TEXT, LicenseDate TEXT, " +
                "Latitude TEXT, Longitude TEXT, CreatedBy TEXT, CreatedDate TEXT, " +
                "UpdatedBy TEXT, UpdatedDate TEXT, Photo TEXT, updateStatus TEXT)";

        agent = "CREATE TABLE agent (Id TEXT PRIMARY KEY, Fullname TEXT, Username TEXT, Password TEXT)";
        bank = "CREATE TABLE bank (Id TEXT PRIMARY KEY, Name TEXT)";
        area = "CREATE TABLE area (AreaId TEXT PRIMARY KEY, AreaName TEXT, ZoneName TEXT)";
        clients = "CREATE TABLE clients (ClientId TEXT PRIMARY KEY, ClientName TEXT, Zone TEXT, Area TEXT, " +
                "BillNumber TEXT, Type TEXT, BillStatus TEXT, ValuationNumber TEXT, Outstanding TEXT, AmountPaid TEXT)";


        database.execSQL(billq);
        database.execSQL(agent);
        database.execSQL(bank);
        database.execSQL(area);
        database.execSQL(clients);
        database.execSQL(pk);
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String query;
        String billq;
        String unreg;
        String regi;
        String billcheque;
        String billcash;
        String eddie;
        String unbillie;
        String sewreg;
        String rentie;
        String billb;
        String agent;
        String areazone;
        String rateuse;
        String zone;
        String area;
        String propreg;
        String clients;
        String reprint;
        String useagent;
        String countess;
        String bank;
        String pk;



        query = "DROP TABLE IF EXISTS users";
        billq = "DROP TABLE IF EXISTS bill";
        unreg = "DROP TABLE IF EXISTS unregi";
        regi = "DROP TABLE IF EXISTS regis";
        billcheque = "DROP TABLE IF EXISTS bc";
        billcash = "DROP TABLE IF EXISTS bcash";
        eddie = "DROP TABLE IF EXISTS editie";
        unbillie = "DROP TABLE IF EXISTS unbillie";
        sewreg = "DROP TABLE IF EXISTS sew";
        rentie = "DROP TABLE IF EXISTS rent";
        billb = "DROP TABLE IF EXISTS billb";
        agent = "DROP TABLE IF EXISTS agent";
        areazone = "DROP TABLE IF EXISTS areazone";
        pk = "DROP TABLE IF EXISTS pk";
        rateuse = "DROP TABLE IF EXISTS rateuse";
        zone = "DROP TABLE IF EXISTS zone";
        area =  "DROP TABLE IF EXISTS area";
        propreg = "DROP TABLE IF EXISTS prop";
        clients = "DROP TABLE IF EXISTS clients";
        reprint = "DROP TABLE IF EXISTS reprint";
        useagent = "DROP TABLE IF EXISTS useagent";
        countess = "DROP TABLE IF EXISTS countess";
        bank = "DROP TABLE IF EXISTS bank";

        database.execSQL(query);
        database.execSQL(billq);
        database.execSQL(unreg);
        database.execSQL(regi);
        database.execSQL(bank);
        database.execSQL(billcheque);
        database.execSQL(billcash);
        database.execSQL(eddie);
        database.execSQL(unbillie);
        database.execSQL(sewreg);
        database.execSQL(rentie);
        database.execSQL(billb);
        database.execSQL(agent);
        database.execSQL(areazone);
        database.execSQL(rateuse);
        database.execSQL(zone);
        database.execSQL(area);
        database.execSQL(propreg);
        database.execSQL(clients);
        database.execSQL(reprint);
        database.execSQL(useagent);
        database.execSQL(countess);
        database.execSQL(pk);


        onCreate(database);
    }
    /**
     * Inserts User into SQLite DB
     * @param queryValues
     */

    public void billinsertUser(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("RemoteID", queryValues.get("RemoteID"));
        values.put("CommunityName", queryValues.get("CommunityName"));
        values.put("GeographicalDistrict", queryValues.get("GeographicalDistrict"));
        values.put("Accessibility", queryValues.get("Accessibility"));
        values.put("DistanceToECOM", queryValues.get("DistanceToECOM"));
        values.put("ConnectedToECG", queryValues.get("ConnectedToECG"));
        values.put("LicenseDate", queryValues.get("LicenseDate"));
        values.put("Latitude", queryValues.get("Latitude"));
        values.put("Longitude", queryValues.get("Longitude"));
        values.put("CreatedBy", queryValues.get("CreatedBy"));
        values.put("CreatedDate", queryValues.get("CreatedDate"));
        values.put("UpdatedBy", queryValues.get("UpdatedBy"));
        values.put("UpdatedDate", queryValues.get("UpdatedDate"));
        values.put("Photo", queryValues.get("Photo"));
        values.put("updateStatus", "no");
        database.insertWithOnConflict("bill", null, values, SQLiteDatabase.CONFLICT_REPLACE);

        database.close();
    }

    public void pkinsertUser(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("RemoteID", queryValues.get("RemoteID"));
        values.put("CommunityName", queryValues.get("CommunityName"));
        values.put("GeographicalDistrict", queryValues.get("GeographicalDistrict"));
        values.put("Accessibility", queryValues.get("Accessibility"));
        values.put("DistanceToECOM", queryValues.get("DistanceToECOM"));
        values.put("ConnectedToECG", queryValues.get("ConnectedToECG"));
        values.put("LicenseDate", queryValues.get("LicenseDate"));
        values.put("Latitude", queryValues.get("Latitude"));
        values.put("Longitude", queryValues.get("Longitude"));
        values.put("CreatedBy", queryValues.get("CreatedBy"));
        values.put("CreatedDate", queryValues.get("CreatedDate"));
        values.put("UpdatedBy", queryValues.get("UpdatedBy"));
        values.put("UpdatedDate", queryValues.get("UpdatedDate"));
        values.put("Photo", queryValues.get("Photo"));
        values.put("updateStatus", "no");
        database.insertWithOnConflict("pk", null, values, SQLiteDatabase.CONFLICT_REPLACE);

        database.close();
    }


    public void saveBank(String id, String name) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", id);
        contentValues.put("Name", name);
        database.insertWithOnConflict("bank", null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public ArrayList<String> getAllBank() {
        ArrayList<String> results  = new ArrayList<>();
        String YOUR_QUERY  = "SELECT * FROM bank";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor c = database.rawQuery(YOUR_QUERY, null);
//Now iterate with cursor
        if (c != null ) {
            if  (c.moveToFirst()) {
                do {  results.add(c.getString(c.getColumnIndex("Name")));
                }while (c.moveToNext());
            }
        }
        database.close();
        return results;
    }


    /*public void saveArea(String aid, String aname, String zname) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("AreaId", aid);
        contentValues.put("AreaName", aname);
        contentValues.put("ZoneName", zname);

        database.insert("area", null, contentValues);
    }*/

    public void areainsertUser(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("AreaId", queryValues.get("AreaId"));
        values.put("AreaName", queryValues.get("AreaName"));
        values.put("ZoneName", queryValues.get("ZoneName"));
        database.insertWithOnConflict("area", null, values, SQLiteDatabase.CONFLICT_REPLACE);

        database.close();
    }


    /*public ArrayList<String> getAllArea(String id) {
        ArrayList<String > results  = new ArrayList<>();
        String YOUR_QUERY  = "SELECT  * FROM area where ZoneId = '"+id+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor c = database.rawQuery(YOUR_QUERY, null);
//Now iterate with cursor
        if (c != null ) {
            if  (c.moveToFirst()) {
                do {  results.add(c.getString(c.getColumnIndex("AreaName")));
                }while (c.moveToNext());
            }
        }
        database.close();
        return results;
    }*/

    public ArrayList<String> getAllArea(String Id) {
        ArrayList<String> results  = new ArrayList<>();
            String YOUR_QUERY  = "SELECT ZoneName FROM area WHERE AreaName like '"+Id+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor c = database.rawQuery(YOUR_QUERY, null);
//Now iterate with cursor
        if (c != null ) {
            if  (c.moveToFirst()) {
                do {  results.add(c.getString(c.getColumnIndex("ZoneName")));
                }while (c.moveToNext());
            }
        }
        database.close();
        return results;
    }

    /*public ArrayList<String> getAllArea(String id) {
        ArrayList<String > results  = new ArrayList<>();
        //String YOUR_QUERY  = "SELECT * FROM area";
        //"SELECT AreaId FROM area WHERE AreaName=?", new String[] {id + ""};
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor c = database.rawQuery("SELECT AreaName FROM area WHERE ZoneId=?", new String[] {id + ""});
//Now iterate with cursor
        if (c != null ) {
            if  (c.moveToFirst()) {
                do {  results.add(c.getString(c.getColumnIndex("AreaName")));
                }while (c.moveToNext());
            }
        }
        database.close();
        return results;
*//*
        ArrayList<HashMap<String, String>> wordList;
        JSONObject kuami = new JSONObject();
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM users where udpateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                kuami.put("CollectorId", cursor.getString(1));
                kuami.put("Title", cursor.getString(2));
                kuami.put("Message", cursor.getString(3));
                kuami.put("Flag", Integer.parseInt(cursor.getString(4)));

            } while (cursor.moveToNext());
        }
        database.close();
        Gson gson = new GsonBuilder().create();
        return kuami;*//*
    }*/

    public String getAreaId(String id) {
        Cursor cursor = null;
        String empName = "";
        SQLiteDatabase database = this.getWritableDatabase();

        try {
            cursor = database.rawQuery("SELECT AreaId FROM area WHERE ZoneName=?", new String[] {id + ""});
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                empName = cursor.getString(cursor.getColumnIndex("AreaId"));
            }
            return empName;
        }finally {
            cursor.close();
            //database.close();
        }
    }


    public String getBankId(String id) {
        Cursor cursor = null;
        String empName = "";
        SQLiteDatabase database = this.getWritableDatabase();

        try {
            cursor = database.rawQuery("SELECT Id FROM bank WHERE Name=?", new String[] {id + ""});
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                empName = cursor.getString(cursor.getColumnIndex("Id"));
            }
            return empName;
        }finally {
            cursor.close();
            //database.close();
        }
    }

    public ArrayList<HashMap<String, String>> getBillUsers() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM bill";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("RemoteID", cursor.getString(0));
                map.put("CommunityName", cursor.getString(1));
                map.put("GeographicalDistrict", cursor.getString(2));
                map.put("Accessibility", cursor.getString(3));
                map.put("DistanceToECOM", cursor.getString(4));
                map.put("ConnectedToECG", cursor.getString(5));
                map.put("LicenseDate", cursor.getString(6));
                map.put("Latitude", cursor.getString(7));
                map.put("Longitude", cursor.getString(8));
                map.put("CreatedBy", cursor.getString(9));
                map.put("CreatedDate", cursor.getString(10));
                map.put("UpdatedBy", cursor.getString(11));
                map.put("UpdatedDate", cursor.getString(12));
                map.put("Photo", cursor.getString(13));
                map.put("updateStatus", cursor.getString(14));


                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return wordList;
    }

    public ArrayList<HashMap<String, String>> getPkUsers() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM pk";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("RemoteID", cursor.getString(0));
                map.put("CommunityName", cursor.getString(1));
                map.put("GeographicalDistrict", cursor.getString(2));
                map.put("Accessibility", cursor.getString(3));
                map.put("DistanceToECOM", cursor.getString(4));
                map.put("ConnectedToECG", cursor.getString(5));
                map.put("LicenseDate", cursor.getString(6));
                map.put("Latitude", cursor.getString(7));
                map.put("Longitude", cursor.getString(8));
                map.put("CreatedBy", cursor.getString(9));
                map.put("CreatedDate", cursor.getString(10));
                map.put("UpdatedBy", cursor.getString(11));
                map.put("UpdatedDate", cursor.getString(12));
                map.put("Photo", cursor.getString(13));
                map.put("updateStatus", cursor.getString(14));


                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return wordList;
    }


    /*
    values.put("Id", queryValues.get("Id"));
        values.put("Firstname", queryValues.get("Firstname"));
        values.put("Lastname", queryValues.get("Lastname"));
        values.put("Phone", queryValues.get("Phone"));
        values.put("Address", queryValues.get("Address") );
        values.put("ZoneId", queryValues.get("ZoneId"));
        values.put("AreaId", queryValues.get("AreaId"));
        values.put("Location", queryValues.get("Location"));
        values.put("Email", queryValues.get("Email"));
        values.put("GPS", queryValues.get("GPS"));
        values.put("ContactPersonName", queryValues.get("ContactPersonName"));
        values.put("ContactPersonPhone", queryValues.get("ContactPersonPhone"));
        values.put("ContactPersoneEmail", queryValues.get("ContactPersoneEmail"));
        values.put("CollectorId", queryValues.get("CollectorId"));
        values.put("BillBoardMake", queryValues.get("BillBoardMake"));
        values.put("BillBoardClass", queryValues.get("BillBoardClass"));
        values.put("BillBoardType", queryValues.get("BillBoardType"));
        values.put("BillBoardCategory", queryValues.get("BillBoardCategory"));
        values.put("NumberOfFaces", queryValues.get("NumberOfFaces"));
        values.put("updateStatus", "no");*/

    /**
     * Compose JSON out of SQLite records
     * @return
     */
    public JSONObject composeJSONfromSQLite() throws JSONException {
        ArrayList<HashMap<String, String>> wordList;
        JSONObject kuami = new JSONObject();
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM users where udpateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                kuami.put("CollectorId", cursor.getString(1));
                kuami.put("Title", cursor.getString(2));
                kuami.put("Message", cursor.getString(3));
                kuami.put("Flag", Integer.parseInt(cursor.getString(4)));

            } while (cursor.moveToNext());
        }
        database.close();
        Gson gson = new GsonBuilder().create();
        return kuami;
    }

    public JSONObject composeJSONfromBillSQLite() throws JSONException {
        ArrayList<HashMap<String, String>> wordList;
        JSONObject kuami = new JSONObject();
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM bill where updateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                kuami.put("RemoteID", cursor.getString(0));
                kuami.put("CommunityName", cursor.getString(1));
                kuami.put("GeographicalDistrict", cursor.getString(2));
                kuami.put("Accessibility", cursor.getString(3));
                kuami.put("DistanceToECOM", cursor.getString(4));
                kuami.put("ConnectedToECG", cursor.getString(5));
                kuami.put("LicenseDate", cursor.getString(6));
                kuami.put("Latitude", cursor.getString(7));
                kuami.put("Longitude", cursor.getString(8));
                kuami.put("CreatedBy", cursor.getString(9));
                kuami.put("CreatedDate", cursor.getString(10));
                kuami.put("UpdatedBy", cursor.getString(11));
                kuami.put("UpdatedDate", cursor.getString(12));
                kuami.put("Photo", cursor.getString(13));

            } while (cursor.moveToNext());
        }
        database.close();
        Gson gson = new GsonBuilder().create();
        return kuami;
    }

    public JSONObject composeJSONfromPkSQLite() throws JSONException {
        ArrayList<HashMap<String, String>> wordList;
        JSONObject kuami = new JSONObject();
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM pk where updateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                kuami.put("RemoteID", cursor.getString(0));
                kuami.put("CommunityName", cursor.getString(1));
                kuami.put("GeographicalDistrict", cursor.getString(2));
                kuami.put("Accessibility", cursor.getString(3));
                kuami.put("DistanceToECOM", cursor.getString(4));
                kuami.put("ConnectedToECG", cursor.getString(5));
                kuami.put("LicenseDate", cursor.getString(6));
                kuami.put("Latitude", cursor.getString(7));
                kuami.put("Longitude", cursor.getString(8));
                kuami.put("CreatedBy", cursor.getString(9));
                kuami.put("CreatedDate", cursor.getString(10));
                kuami.put("UpdatedBy", cursor.getString(11));
                kuami.put("UpdatedDate", cursor.getString(12));
                kuami.put("Photo", cursor.getString(13));

            } while (cursor.moveToNext());
        }
        database.close();
        Gson gson = new GsonBuilder().create();
        return kuami;
    }



    /**
     * Get Sync status of SQLite
     * @return
     */
    public String getSyncStatus(){
        String msg = null;
        if(this.dbSyncCount() == 0){
            msg = "SQLite and Remote MySQL DBs are in Sync!";
        }else{
            msg = "DB Sync needed\n";
        }
        return msg;
    }

    /**
     * Get SQLite records that are yet to be Synced
     * @return
     */
    public int dbSyncCount(){
        int count = 0;
        String selectQuery = "SELECT  * FROM users where udpateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        count = cursor.getCount();
        database.close();
        return count;
    }

    public int dbBillSyncCount(){
        int count = 0;
        String selectQuery = "SELECT  * FROM bill where updateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        count = cursor.getCount();
        database.close();
        return count;
    }

    public int dbPkSyncCount(){
        int count = 0;
        String selectQuery = "SELECT  * FROM pk where updateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        count = cursor.getCount();
        database.close();
        return count;
    }

    /**
     * Update Sync status against each User ID
     * //@param id
     * @param status
     */

    public void deleteBill(String Id){
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "DELETE FROM bill WHERE Id like '"+Id+"'";
        Log.d("query",updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void deletePk(String Id){
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "DELETE FROM pk WHERE Id like '"+Id+"'";
        Log.d("query",updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void deleteUseAgent(){
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "DELETE FROM useagent";
        Log.d("query",updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}
