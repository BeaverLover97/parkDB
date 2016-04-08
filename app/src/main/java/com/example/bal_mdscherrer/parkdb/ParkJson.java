package com.example.bal_mdscherrer.parkdb;
//http://stackoverflow.com/questions/13814503/reading-a-json-file-in-android
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bal_mdscherrer on 4/6/2016.
 */
public class ParkJson {

    private Context context;

    public String loadJSONFromAsset(Context context) {
        String json;
        this.context = context;
        try {
            InputStream is = context.getAssets().open("park_data.Json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public ArrayList<String> getJsonFromAsset() {
        ArrayList<String> formList = new ArrayList<String>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context));
            JSONArray m_jArry = obj.getJSONArray("data");

                String parkData = "";
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                parkData = jo_inside.getString("data");
                Log.d("Data", "Park Data:  " + parkData);
                formList.add(parkData);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return formList;
    }
}
