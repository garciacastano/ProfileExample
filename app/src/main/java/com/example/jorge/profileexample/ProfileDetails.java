package com.example.jorge.profileexample;

/**
 * Created by jorge on 26/11/15.
 */


    import android.content.Context;
    import android.os.Bundle;
    import android.support.v7.app.ActionBar;
    import android.support.v7.app.AppCompatActivity;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.content.Intent;



    public class ProfileDetails extends AppCompatActivity {
        private TextView nameTextView;
        private TextView messageTextView;
        private TextView gpsTextView;
        private ImageView photoView;
        private Context mContext;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.profile_details);

            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();

            // TODO: 2/12/15
            /*
            * No recoge la informacion enviada por el intent en los getStringExtra.
            * Es enviada, sin embargo, correctamente desde MainActivity
            */

            Intent i = getIntent();
            //Bundle extras = i.getExtras();
            String name = i.getStringExtra("name");
            String photo = i.getStringExtra("photo");
            String message = i.getStringExtra("message");
            String gps = i.getStringExtra("gps");

            nameTextView = (TextView) findViewById(R.id.nameDetails);
            messageTextView = (TextView) findViewById(R.id.messageDetails);
            gpsTextView = (TextView) findViewById(R.id.gpsDetails);
            photoView = (ImageView) findViewById(R.id.photoDetails);

            nameTextView.setText(name);
            messageTextView.setText(message);
            gpsTextView.setText(gps);

            // TODO: 2/12/15
            /*
            * Mismo problema que en GridViewAdapter
            *
            */
            photoView.setBackgroundResource(R.drawable.jorge);

        }
    }

