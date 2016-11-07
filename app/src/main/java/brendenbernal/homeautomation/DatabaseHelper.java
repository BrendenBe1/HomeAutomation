package brendenbernal.homeautomation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by peter on 11/6/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "devices.db";

    public static final String THERMO_TABLE_NAME = "lights";
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
        db.execSQL("CREATE TABLE "+THERMO_TABLE_NAME+" ("+THERMO_COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+THERMO_COL_2+" TEXT, "+THERMO_COL_3+" INTEGER "+THERMO_COL_4+" TEXT, "+THERMO_COL_5+" TEXT)");
        db.execSQL("CREATE TABLE "+LIGHT_TABLE_NAME+" ("+LIGHT_COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+LIGHT_COL_2+" TEXT, "+LIGHT_COL_3+" INTEGER "+LIGHT_COL_4+" TEXT, "+LIGHT_COL_5+" TEXT)");
        db.execSQL("CREATE TABLE "+LOCK_TABLE_NAME+" ("+LOCK_COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+LOCK_COL_2+" TEXT, "+LOCK_COL_3+" INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+THERMO_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+LIGHT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+LOCK_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertThermo(String name, int status, String onTime, String offTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(THERMO_COL_2, name);
        contentValues.put(THERMO_COL_3, status);
        contentValues.put(THERMO_COL_4, onTime);
        contentValues.put(THERMO_COL_5, offTime);
        long result = db.insert(THERMO_TABLE_NAME, null, contentValues);
        if(result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean insertLight(String name, int status, String onTime, String offTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LIGHT_COL_2, name);
        contentValues.put(LIGHT_COL_3, status);
        contentValues.put(LIGHT_COL_4, onTime);
        contentValues.put(LIGHT_COL_5, offTime);
        long result = db.insert(LIGHT_TABLE_NAME, null, contentValues);
        if(result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean insertLock(String name, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOCK_COL_2, name);
        contentValues.put(LOCK_COL_3, status);
        long result = db.insert(LOCK_TABLE_NAME, null, contentValues);
        if(result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public Cursor getAllData(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+tableName, null);
        return result;
    }
}
