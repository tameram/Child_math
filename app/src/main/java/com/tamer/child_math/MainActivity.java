package com.tamer.child_math;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;
//splash --> https://www.ssaurel.com/blog/create-a-splash-screen-on-android-the-right-way/


public class MainActivity extends Activity implements View.OnClickListener {

    private int lan , toSettingActivity = 0 ,tempMusic = 1;
    Button optionInHomePage , logInTheHomePage,signUpTheHomePage , logInTheHomePageTeacher;
    HomeWatcher mHomeWatcher ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        optionInHomePage = (Button)findViewById(R.id.optionInHomePage);
        logInTheHomePage = (Button)findViewById(R.id.logInTheHomePage);
        signUpTheHomePage = (Button)findViewById(R.id.signUpTheHomePage);
        logInTheHomePageTeacher = (Button)findViewById(R.id.logInTheHomePageTeacher);

        Intent intent = getIntent();
        lan = intent.getIntExtra("lan", 0); // here 0 is the default value
        tempMusic = intent.getIntExtra("music", 1);
        Log.d("the music",tempMusic+"");
        Context context;
        Resources resources;
        switch (lan) {

            case 0:
                context = LocaleHelper.setLocale(MainActivity.this, "en");
                resources = context.getResources();


                logInTheHomePage.setText(resources.getString(R.string.mainactivity_logInTheHomePage));
                signUpTheHomePage.setText(resources.getString(R.string.mainactivity_signUpTheHomePage));
                break;
            case 1:
                context = LocaleHelper.setLocale(MainActivity.this, "iw");
                resources = context.getResources();

                logInTheHomePage.setText(resources.getString(R.string.mainactivity_logInTheHomePage));
                signUpTheHomePage.setText(resources.getString(R.string.mainactivity_signUpTheHomePage));
                break;
            case 2:
                context = LocaleHelper.setLocale(MainActivity.this, "ar");
                resources = context.getResources();

                logInTheHomePage.setText(resources.getString(R.string.mainactivity_logInTheHomePage));
                signUpTheHomePage.setText(resources.getString(R.string.mainactivity_signUpTheHomePage));
                break;
        }

        if(tempMusic == 1) {
                //BIND Music Service

            doBindService();
            Intent music = new Intent();
            music.setClass(this, MusicService.class);
            startService(music);

            //Start HomeWatcher
            mHomeWatcher = new HomeWatcher(this);
            mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
                @Override
                public void onHomePressed() {
                    if (mServ != null) {
                        mServ.pauseMusic();
                    }
                }
                @Override
                public void onHomeLongPressed() {
                    if (mServ != null) {
                        mServ.pauseMusic();
                    }
                }
            });
            mHomeWatcher.startWatch();
        }
      /*  if(tempMusic == 0){
            Intent music = new Intent();
            music.setClass(this,MusicService.class);
            stopService(music);
        }*/

        setUpClick();
    }



    //Bind/Unbind music service
    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon,Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mServ != null) {
            mServ.resumeMusic();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Detect idle screen
        PowerManager pm = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (pm != null) {
            isScreenOn = pm.isScreenOn();
        }

        if (!isScreenOn) {
            if (mServ != null) {
                mServ.pauseMusic();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //UNBIND music service
        doUnbindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        stopService(music);

    }


    private void setUpClick() {
        Button btnSum = (Button) findViewById(R.id.optionInHomePage);
        Button btnLogIn = (Button) findViewById(R.id.logInTheHomePage);
        Button btnsignUpTheHomePage = (Button) findViewById(R.id.signUpTheHomePage);
        logInTheHomePageTeacher = (Button)findViewById(R.id.logInTheHomePageTeacher);

        btnsignUpTheHomePage.setOnClickListener(this);
       btnSum.setOnClickListener(this);
       btnLogIn.setOnClickListener(this);
        logInTheHomePageTeacher.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.optionInHomePage:
                Intent intent = new Intent(this, SettingsActivity.class);
                intent.putExtra("lan",lan);
                intent.putExtra("toSettingActivity",toSettingActivity);
                startActivity(intent);
                break;
            case R.id.logInTheHomePage:
                Intent intent1 = new Intent(this, BaseLoginActivity.class);
                intent1.putExtra("lan",lan);
                startActivity(intent1);
                break;
            case R.id.signUpTheHomePage:
                Intent intent2 = new Intent(this, SignupActivity.class);
                intent2.putExtra("lan",lan);
                startActivity(intent2);
                break;
            case R.id.logInTheHomePageTeacher:
                Intent intent3 = new Intent(this, teacherLogINActivity.class);
                intent3.putExtra("lan",lan);
                startActivity(intent3);
                break;

             //   Toast.makeText(this, "this is a toast!!!", Toast.LENGTH_SHORT).show();
             //   break;
        }
    }
}
