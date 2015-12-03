package com.example.jorge.profileexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.sql.SQLException;

/**
 * Created by jorge on 30/11/15.
 */

public class ProfileDbAdapter implements BaseColumns{

    private static final String TABLE_NAME = "profiles";
    public  static final String COL_NAME   = "name";
    public  static final String COL_MESSAGE = "message";
    public  static final String COL_ID = "_id";
    public  static final String COL_PHOTO = "photo";
    public  static final String COL_GPS = "gps";

    private static final String TAG ="ProfileDbAdapter";
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    private static final String DATABASE_CREATE =
            "create table " + ProfileDbAdapter.TABLE_NAME + " (" +
                    ProfileDbAdapter.COL_ID     + " integer primary key autoincrement, " +
                    ProfileDbAdapter.COL_NAME   + " text not null, " +
                    ProfileDbAdapter.COL_MESSAGE   + " text not null, " +
                    ProfileDbAdapter.COL_PHOTO   + " text not null, " +
                    ProfileDbAdapter.COL_GPS   + " text not null); " ;

    private static final String DATABASE_DELETE=
        "DROP TABLE IF EXISTS "+ ProfileDbAdapter.TABLE_NAME;

    private final Context context;

    /**
     * constructor
     *
     * @param c context
     */
    public ProfileDbAdapter(Context c) {
        this.context = c;
    }

    /**
     * open database
     *
     * @return database adapter
     */
    public ProfileDbAdapter open () {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    /**
     * Close database
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * add a product to the database
     * @param name
     *
     * @return row id of new product
     */
    public long add(String name, String message, String photo, String gps) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME,   name);
        values.put(COL_MESSAGE,   message);
        values.put(COL_PHOTO,   photo);
        values.put(COL_GPS, gps);
        return db.insert(TABLE_NAME, null, values);
    }


    public boolean update(long id, String name, String message, String photo, String gps) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME,   name);
        values.put(COL_MESSAGE,   message);
        values.put(COL_PHOTO,   photo);
        values.put(COL_GPS,   gps);

        return db.update(TABLE_NAME, values, COL_ID + "=" + id, null) > 0;
    }

    public boolean remove(long id) {
        return db.delete(TABLE_NAME, COL_ID + "=" + id, null) > 0;
    }

    /**
     * getProduct all products from the database
     *
     * @return cursor over all products
     */
    public Cursor getAll() {
        return db.query(
                TABLE_NAME, //table name
                new String[]{COL_ID, COL_NAME, COL_MESSAGE, COL_PHOTO, COL_GPS}, // columns
                null, null, null, null, null);
    }

    /**
     * get a cursor for a product from the database
     *
     * @param id product id
     * @return cursor located on product if found
     * @throws SQLException if not found
     */
    public Cursor get(long id) throws SQLException {
        Cursor cursor =
                db.query(true,      // distinct
                        TABLE_NAME,
                        new String[]{COL_ID, COL_NAME, COL_MESSAGE, COL_PHOTO, COL_GPS}, // columns
                        COL_ID + "=" + id,                           // where
                        null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     * remove all products from the database
     */
    public void clear() {
        db.delete(TABLE_NAME, null, null);
        open();
    }

    private static class DBHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "Profiles.db";
        public static final int    DATABASE_VERSION = 1;

        DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DELETE);
            onCreate(db);
        }

        @Override
        public void onDowngrade (SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
}



