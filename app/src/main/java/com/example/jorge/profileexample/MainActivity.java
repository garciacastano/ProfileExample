package com.example.jorge.profileexample;

import java.sql.SQLException;
import java.util.ArrayList;
import android.database.Cursor;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.AdapterView;

/**
 * Main class of the application *
 * @author Jorge García, Javier Gonzalez *
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private GridView mGridView;
    private GridViewAdapter mGridAdapter;
    private ArrayList<Profile> mGridData;
    private ProfileDbAdapter dbAdapter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "ON CREATE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_list);

        mGridView = (GridView) findViewById(R.id.profile_grid);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Log.d(TAG, "CLICK DETAILS");
                //Get item at position
                Profile item = (Profile) parent.getItemAtPosition(position);
                if(item.getGps()==null || item.getMessage()==null){
                    item=downloadDetails(item, position);
                }

                Intent intent = new Intent(MainActivity.this, ProfileDetails.class);
                intent.putExtra(item.getName(), "name");
                intent.putExtra(item.getPhoto(), "photo");
                intent.putExtra(item.getGps(), "gps");
                intent.putExtra(item.getMessage(), "message");
                //Start details activity
                startActivity(intent);
            }
        });

        //Initialize with empty data
        mGridData = new ArrayList<>();
        mGridAdapter = new GridViewAdapter(this, R.layout.profile_cell, mGridData);
        mGridView.setAdapter(mGridAdapter);

        dbAdapter = new ProfileDbAdapter(this);
        dbAdapter.open();
        dbAdapter.clear();

        //Examples
        loadExamples();
        downloadPreviews();

    }

    private void downloadPreviews() {
        Log.d(TAG, "DOWNLOAD PREVIEWS");
        cursor = dbAdapter.getAll();

        Log.d("Número de perfiles = ", Integer.toString(cursor.getCount()));
        //Log.d("Número de parámetros = ", Integer.toString(cursor.getColumnCount()));
        while(cursor.moveToNext()){
            long id = Long.parseLong(cursor.getString(cursor.getColumnIndex(ProfileDbAdapter.COL_ID)));
            String name = cursor.getString(cursor.getColumnIndex(ProfileDbAdapter.COL_NAME));
            Log.d(TAG, name);
            String photo = cursor.getString(cursor.getColumnIndex(ProfileDbAdapter.COL_PHOTO));
            mGridData.add(new Profile(id, name, photo));
        }
        mGridAdapter.setGridData(mGridData);
    }

    private Profile downloadDetails(Profile item, int position) {
        Log.d(TAG, "DOWNLOAD DETAILS");
        try{
            cursor = dbAdapter.get(item.getId());
        }
        catch (SQLException e){
            Log.d(TAG, "ERROR DOWNLOADING DETAILS");
        }

        String message = cursor.getString(cursor.getColumnIndex(ProfileDbAdapter.COL_MESSAGE));
        String gps = cursor.getString(cursor.getColumnIndex(ProfileDbAdapter.COL_GPS));
        item.setMessage(message);
        item.setGps(gps);
        mGridData.set(position, item);

        mGridAdapter.setGridData(mGridData);
        return item;
    }

    private void loadExamples() {
        Log.d(TAG, "LOAD EXAMPLES");
        dbAdapter.add("Jorge", "21 años", "jorge", "Madrid");
        dbAdapter.add("Javi", "21 años", "javi", "Galicia");
        dbAdapter.add("Cris", "21 años", "cris","Santander");
        dbAdapter.add("Eva", "20 años", "eva","Extremadura");
    }
}
