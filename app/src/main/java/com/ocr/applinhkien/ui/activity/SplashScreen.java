package com.ocr.applinhkien.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.ocr.applinhkien.R;
import com.ocr.applinhkien.model.UserManager;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fast_screen );
        UserManager.getInstance().init(this);


        Handler handler = new Handler();
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        }, 500 );
    }

    private void nextActivity() {
        UserManager userManager = UserManager.getInstance();

      //  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (!userManager.isLoggedIn()) {
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