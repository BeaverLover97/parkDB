package com.example.bal_mdscherrer.parkdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bal_mdscherrer on 4/5/2016.
 */
// 0 - title
// 1 - overview
// 2 - release_date
// 3 - vote_average
// 4 - vote_count
// 5 - popularity
// 6 - poster_path
// 7 - backdrop_path

    /**
     * Created by bal_mdscherrer on 1/25/2016.
     */

// http://stackoverflow.com/questions/3900740/android-sqlite-database-shared-between-activities
    public final class MyDBHandler extends SQLiteOpenHelper {




        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "com.example.android.moviedb";
        public static final String TABLE_PARKS = "movies";


        public static final String COLUMN_ID = "id ";
        public static final String COLUMN_NAME = "parkName";
        public static final String COLUMN_LAT = "latitude";
        public static final String COLUMN_LONG = "latitude";



        public MyDBHandler(Context context, String name,
                           SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_MOVIE_TABLE = "CREATE TABLE " +
                    TABLE_PARKS + "("
                    + COLUMN_ID + " TEXT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_LAT + " INTEGER,"
                    + COLUMN_LONG + " INTEGER" + ")";

            db.execSQL(CREATE_MOVIE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARKS);
            onCreate(db);
        }


        public void addPark(Park park) {


            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, park.getName());
            values.put(COLUMN_LAT, park.getLat());
            values.put(COLUMN_LONG, park.getLong());

            SQLiteDatabase db = this.getWritableDatabase();

            Log.i("database", "id added: " + db.insert(TABLE_PARKS, null, values));
            db.close();
        }



        public ArrayList<Park> getFavArray() {
            String query = "Select * FROM " + TABLE_PARKS;

            ArrayList<Park> array = new ArrayList<>();
            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.rawQuery(query, null);


            Log.i("database","total rows: " + cursor.getCount());



            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Park currentPark = new Park();

                currentPark.setName(cursor.getString(0));
                currentPark.setLat(cursor.getInt(1));
                currentPark.setLong(cursor.getInt(2));

                array.add(currentPark);
                cursor.moveToNext();
            }
            db.close();
            Log.i("database", "size of arrayfav: " + array.size());
            return array;

        }

    }
