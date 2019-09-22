package com.tamer.child_math;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        Log.d(TAG, "onCreate: "+firebaseAuth.getUid());
        if(firebaseUser != null) {
            startActivity(new Intent( this, Main2Activity.class));

        }
        else{
            startActivity(new Intent( this, MainActivity.class));
        }
      //  SharedPreferences sharedPref = getSharedPreferences("data",MODE_PRIVATE);
      //  int number = sharedPref.getInt("isLogged", 0);
      //  if(number == 0) {
            //Open the login activity and set this so that next it value is 1 then this conditin will be false.
       //     SharedPreferences.Editor prefEditor = sharedPref.edit();
        //    prefEditor.putInt("isLogged",1);

            finish();
         //   prefEditor.commit();
       // } else {
        //    Intent intent = new Intent(this, Main2Activity.class);
        //    startActivity(intent);
        //    finish();
     //   }

    }


}
