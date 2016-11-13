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
    public static final String THERMO_COL_6 = "thermo_setTemp";

    public static final String LIGHT_TABLE_NAME = "lights";
    public static final String LIGHT_COL_1 = "light_id";
    public static final String LIGHT_COL_2 = "light_name";
    public static final String LIGHT_COL_3 = "light_status";
    public static final String LIGHT_COL_4 = "light_onTime";
    public static final String LIGHT_COL_5 = "light_offTime";
    public static final String LIGHT_COL_6 = "light_setStatus";

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
        db.execSQL("CREATE TABLE "+THERMO_TABLE_NAME+" ("+THERMO_COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+THERMO_COL_2+" TEXT, "+THERMO_COL_3+" INTEGER, "+THERMO_COL_4+" TEXT, "+THERMO_COL_5+" TEXT, "+THERMO_COL_6+" INTEGER)");
        db.execSQL("CREATE TABLE "+LIGHT_TABLE_NAME+" ("+LIGHT_COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+LIGHT_COL_2+" TEXT, "+LIGHT_COL_3+" INTEGER, "+LIGHT_COL_4+" TEXT, "+LIGHT_COL_5+" TEXT, "+LIGHT_COL_6+" INTERGER)");
        db.execSQL("CREATE TABLE "+LOCK_TABLE_NAME+" ("+LOCK_COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+LOCK_COL_2+" TEXT, "+LOCK_COL_3+" INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+THERMO_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+LIGHT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+LOCK_TABLE_NAME);
        onCreate(db);
    }

    public void insertThermostat(Thermostat thermostat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(THERMO_COL_2, thermostat.getName());
        contentValues.put(THERMO_COL_3, thermostat.getStatus());
        contentValues.put(THERMO_COL_4, thermostat.getOnTime());
        contentValues.put(THERMO_COL_5, thermostat.getOffTime());
        contentValues.put(THERMO_COL_6, thermostat.getSetTemp());
        db.insert(THERMO_TABLE_NAME, null, contentValues);
    }

    public void insertLight(Light light) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LIGHT_COL_2, light.getName());
        contentValues.put(LIGHT_COL_3, light.getStatus());
        contentValues.put(LIGHT_COL_4, light.getOnTime());
        contentValues.put(LIGHT_COL_5, light.getOffTime());
        contentValues.put(LIGHT_COL_6, light.getSetStatus());
        db.insert(LIGHT_TABLE_NAME, null, contentValues);
    }

    public void insertLock(Lock lock) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOCK_COL_2, lock.getName());
        contentValues.put(LOCK_COL_3, lock.getStatus());
        db.insert(LOCK_TABLE_NAME, null, contentValues);
    }

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
                thermostat.setSetTemp(Integer.parseInt(cursor.getString(5)));

                thermostatList.add(thermostat);
            } while (cursor.moveToNext());
        }

        return thermostatList;
    }

    //Get all lights
    public List<Light> getAllLights(){
        List<Light> lightList = new ArrayList<Light>();

        //Select All query
        String selectQuery = "SELECT * FROM "+ LIGHT_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //Loop all rows and add it to the list
        if(cursor.moveToFirst()){
            do {
                Light light = new Light();
                light.setId(Integer.parseInt(cursor.getString(0)));
                light.setName(cursor.getString(1));
                light.setStatus(Integer.parseInt(cursor.getString(2)));
                light.setOnTime(cursor.getString(3));
                light.setOffTime(cursor.getString(4));
                light.setSetStatus(Integer.parseInt(cursor.getString(5)));

                lightList.add(light);
            } while (cursor.moveToNext());
        }

        return lightList;
    }

    //Get all locks
    public List<Lock> getAllLocks(){
        List<Lock> lockList = new ArrayList<Lock>();

        //Select All query
        String selectQuery = "SELECT * FROM "+ LOCK_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //Loop all rows and add it to the list
        if(cursor.moveToFirst()){
            do {
                Lock lock = new Lock();
                lock.setId(Integer.parseInt(cursor.getString(0)));
                lock.setName(cursor.getString(1));
                lock.setStatus(Integer.parseInt(cursor.getString(2)));

                lockList.add(lock);
            } while (cursor.moveToNext());
        }

        return lockList;
    }


    public Thermostat getThermostat(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(THERMO_TABLE_NAME, new String[] { THERMO_COL_1,
                        THERMO_COL_2, THERMO_COL_3, THERMO_COL_4, THERMO_COL_5, THERMO_COL_6 }, THERMO_COL_2 + "=?",
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
        thermostat.setSetTemp(Integer.parseInt(cursor.getString(5)));

        return thermostat;
    }

    public Light getLight(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(LIGHT_TABLE_NAME, new String[] { LIGHT_COL_1,
                        LIGHT_COL_2, LIGHT_COL_3, LIGHT_COL_4, LIGHT_COL_5, LIGHT_COL_6 }, LIGHT_COL_2 + "=?",
                new String[]{name}, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        Light light = new Light();

        light.setId(Integer.parseInt(cursor.getString(0)));
        light.setName(cursor.getString(1));
        light.setStatus(Integer.parseInt(cursor.getString(2)));
        light.setOnTime(cursor.getString(3));
        light.setOffTime(cursor.getString(4));
        light.setSetStatus(Integer.parseInt(cursor.getString(5)));

        return light;
    }

    public Lock getLock(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(LOCK_TABLE_NAME, new String[] { LOCK_COL_1,
                        LOCK_COL_2, LOCK_COL_3 }, LOCK_COL_2 + "=?",
                new String[]{name}, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        Lock lock = new Lock();

        lock.setId(Integer.parseInt(cursor.getString(0)));
        lock.setName(cursor.getString(1));
        lock.setStatus(Integer.parseInt(cursor.getString(2)));

        return lock;
    }

    public int updateThermostat(Thermostat thermostat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(THERMO_COL_2, thermostat.getName());
        contentValues.put(THERMO_COL_3, thermostat.getStatus());
        contentValues.put(THERMO_COL_4, thermostat.getOnTime());
        contentValues.put(THERMO_COL_5, thermostat.getOffTime());
        contentValues.put(THERMO_COL_6, thermostat.getSetTemp());
        return db.update(THERMO_TABLE_NAME, contentValues, THERMO_COL_2 + " = ?", new String[]{thermostat.getName()});
    }

    public int updateLight(Light light){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LIGHT_COL_2, light.getName());
        contentValues.put(LIGHT_COL_3, light.getStatus());
        contentValues.put(LIGHT_COL_4, light.getOnTime());
        contentValues.put(LIGHT_COL_5, light.getOffTime());
        contentValues.put(LIGHT_COL_6, light.getSetStatus());
        return db.update(LIGHT_TABLE_NAME, contentValues, LIGHT_COL_2 + " = ?", new String[]{light.getName()});
    }

    public int updateLock(Lock lock){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOCK_COL_2, lock.getName());
        contentValues.put(LOCK_COL_3, lock.getStatus());
        return db.update(LOCK_TABLE_NAME, contentValues, LOCK_COL_2 + " = ?", new String[]{lock.getName()});
    }

    public void deleteThermostat(Thermostat thermostat){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(THERMO_TABLE_NAME, THERMO_COL_2 + " = ?",
                new String[]{thermostat.getName()});
        db.close();
    }

    public void deleteLight(Light light) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LIGHT_TABLE_NAME, LIGHT_COL_2 + " = ?",
                new String[]{light.getName()});
        db.close();
    }

    public void deleteLock(Lock lock) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LOCK_TABLE_NAME, LOCK_COL_2 + " = ?",
                new String[]{lock.getName()});
        db.close();
    }

}
