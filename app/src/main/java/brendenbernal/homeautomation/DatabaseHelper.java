package brendenbernal.homeautomation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter on 11/6/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "devices.db";

    public static final String THERMO_TABLE_NAME = "thermostats";
    public static final String THERMO_COL_1 = "thermo_id";
    public static final String THERMO_COL_2 = "thermo_name";
    public static final String THERMO_COL_3 = "thermo_status";
    public static final String THERMO_COL_4 = "thermo_onTime";
    public static final String THERMO_COL_5 = "thermo_offTime";

    public static final String LIGHT_TABLE_NAME = "lights";
    public static final String LIGHT_COL_1 = "light_id";
    public static final String LIGHT_COL_2 = "light_name";
    public static final String LIGHT_COL_3 = "light_status";
    public static final String LIGHT_COL_4 = "light_onTime";
    public static final String LIGHT_COL_5 = "light_offTime";

    public static final String LOCK_TABLE_NAME = "locks";
    public static final String LOCK_COL_1 = "lock_id";
    public static final String LOCK_COL_2 = "lock_name";
    public static final String LOCK_COL_3 = "lock_status";


    public DatabaseHelper(Context context) {
        //When ffunction is called, create database.
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+THERMO_TABLE_NAME+" ("+THERMO_COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+THERMO_COL_2+" TEXT, "+THERMO_COL_3+" INTEGER, "+THERMO_COL_4+" TEXT, "+THERMO_COL_5+" TEXT)");
        db.execSQL("CREATE TABLE "+LIGHT_TABLE_NAME+" ("+LIGHT_COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+LIGHT_COL_2+" TEXT, "+LIGHT_COL_3+" INTEGER, "+LIGHT_COL_4+" TEXT, "+LIGHT_COL_5+" TEXT)");
        db.execSQL("CREATE TABLE "+LOCK_TABLE_NAME+" ("+LOCK_COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+LOCK_COL_2+" TEXT, "+LOCK_COL_3+" INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+THERMO_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+LIGHT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+LOCK_TABLE_NAME);
        onCreate(db);
    }

    /*public boolean insertLight(Light light) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LIGHT_COL_2, thermostat.getName());
        contentValues.put(LIGHT_COL_3, thermostat.getStatus());
        contentValues.put(LIGHT_COL_4, thermostat.getOnTime());
        contentValues.put(LIGHT_COL_5, thermostat.getOffTime());
        long result = db.insert(LIGHT_TABLE_NAME, null, contentValues);
        if(result == -1) {
            db.close();
            return false;
        }else {
            db.close();
            return true;
        }
    }*/


    public void insertThermostat(Thermostat thermostat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(THERMO_COL_2, thermostat.getName());
        contentValues.put(THERMO_COL_3, thermostat.getStatus());
        contentValues.put(THERMO_COL_4, thermostat.getOnTime());
        contentValues.put(THERMO_COL_5, thermostat.getOffTime());
        db.insert(THERMO_TABLE_NAME, null, contentValues);
        db.close();
    }

    /*public boolean insertLock(String name, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOCK_COL_2, name);
        contentValues.put(LOCK_COL_3, status);
        long result = db.insert(LOCK_TABLE_NAME, null, contentValues);
        if(result == -1) {
            db.close();
            return false;
        }else {
            db.close();
            return true;
        }
    }*/

    //Get all thermostats
    public List<Thermostat> getAllThermostats(){
        List<Thermostat> thermostatList = new ArrayList<Thermostat>();

        //Select All query
        String selectQuery = "SELECT * FROM "+ THERMO_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //Loop all rows and add it to the list
        if(cursor.moveToFirst()){
            do {
                Thermostat thermostat = new Thermostat();
                thermostat.setId(Integer.parseInt(cursor.getString(0)));
                thermostat.setName(cursor.getString(1));
                thermostat.setStatus(Integer.parseInt(cursor.getString(2)));
                thermostat.setOnTime(cursor.getString(3));
                thermostat.setOffTime(cursor.getString(4));

                thermostatList.add(thermostat);
            } while (cursor.moveToNext());
        }

        return thermostatList;
    }

    public Thermostat getThermostat(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(THERMO_TABLE_NAME, new String[] { THERMO_COL_1,
                        THERMO_COL_2, THERMO_COL_3, THERMO_COL_4, THERMO_COL_5 }, THERMO_COL_2 + "=?",
                new String[]{name}, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        Thermostat thermostat = new Thermostat();

        thermostat.setId(Integer.parseInt(cursor.getString(0)));
        thermostat.setName(cursor.getString(1));
        thermostat.setStatus(Integer.parseInt(cursor.getString(2)));
        thermostat.setOnTime(cursor.getString(3));
        thermostat.setOffTime(cursor.getString(4));

        return thermostat;
    }

    public Cursor getAllThermostatData(){
        String selectQuery = "SELECT * FROM "+ THERMO_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }
}
