package com.ocr.applinhkien.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.ocr.applinhkien.PreferenceHelper;
import com.ocr.applinhkien.R;


public class SplashScreen extends AppCompatActivity {

    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fast_screen );

        preferenceHelper = new PreferenceHelper(this);

        Handler handler = new Handler();
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        }, 500 );
    }

    private void nextActivity() {

      //  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (!preferenceHelper.isLogin()) {
            //chua login
            Intent intent = new Intent( this, SignInActivity.class );
            startActivity( intent );

        } else {
            //da login
            Intent intent = new Intent( this, MainActivity.class );
            startActivity( intent );

        }
      finish();
    }
}