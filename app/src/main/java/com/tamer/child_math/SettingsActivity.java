package com.tamer.child_math;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    HomeWatcher mHomeWatcher ;
    TextView textView7 ,textView9,textView10,textView11,textView12,textView13;
    Button backButtonsInSitting ;
    Switch aSwitch ;
    int tmp , tempMusic;
    int lan , toSettingActivity = 0;
    //, logInTheHomePage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        textView7 = (TextView)findViewById(R.id.textView7);
        textView9 = (TextView)findViewById(R.id.textView9);

        textView11 = (TextView)findViewById(R.id.textView11);

        textView13 = (TextView)findViewById(R.id.textView13);
        backButtonsInSitting = (Button)findViewById(R.id.backButtonsInSitting) ;
        aSwitch = (Switch)findViewById(R.id.switch1);
       // logInTheHomePage = (Button)findViewById(R.id.logInTheHomePage);

        Intent intent = getIntent();
        lan = intent.getIntExtra("lan", 0); // here 0 is the default value
        toSettingActivity = intent.getIntExtra("toSettingActivity", 0); // here 0 is the default value
        Log.d("tam",lan+"");
        Context context;
        Resources resources;
        switch (lan) {
            case 0:
                Log.d("en",lan+"");
                context = LocaleHelper.setLocale(SettingsActivity.this, "en");
                resources = context.getResources();
                textView7.setText(R.string.option_setting);
                textView9.setText(R.string.language_setting);

                textView11.setText(resources.getString(R.string.lettters_setting));

                textView13.setText(resources.getString(R.string.sound_setting));

                // logInTheHomePage.setText(resources.getString(R.string.ok_setting));
                break;
            case 1:
                context = LocaleHelper.setLocale(SettingsActivity.this, "in");
                resources = context.getResources();
                textView7.setText(resources.getString(R.string.option_setting));
                break;
            case 2:
                Log.d("ar",lan+"");
                context = LocaleHelper.setLocale(SettingsActivity.this, "ar");
                resources = context.getResources();
                textView7.setText(resources.getString(R.string.option_setting));
                textView9.setText(resources.getString(R.string.language_setting));

                textView11.setText(resources.getString(R.string.lettters_setting));

                textView13.setText(resources.getString(R.string.sound_setting));

                break;
        }
        Spinner spinner = findViewById(R.id.spinerOFLanguages);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinerOFLanguages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.spinerOFColorBackground, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        Spinner spinner2 = findViewById(R.id.spinnerLettersSize);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.spinerOFSizeLetters, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        if (LocaleHelper.getLanguage(SettingsActivity.this).equalsIgnoreCase("en")) {
            spinner.setSelection(adapter.getPosition("English"));
        } else if (LocaleHelper.getLanguage(SettingsActivity.this).equalsIgnoreCase("in")) {
            spinner.setSelection(adapter.getPosition("Hebrew"));
        } else {
            spinner.setSelection(adapter.getPosition("Arabic"));
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Context context;
                Resources resources;
                lan = i;
                switch (i) {

                    case 0:
                        context = LocaleHelper.setLocale(SettingsActivity.this, "en");
                        resources = context.getResources();
                        textView7.setText(resources.getString(R.string.option_setting));
                        textView9.setText(resources.getString(R.string.language_setting));

                        textView11.setText(resources.getString(R.string.lettters_setting));

                        textView13.setText(resources.getString(R.string.sound_setting));

                       // logInTheHomePage.setText(resources.getString(R.string.ok_setting));
                        break;
                    case 1:
                        context = LocaleHelper.setLocale(SettingsActivity.this, "iw");
                        resources = context.getResources();
                        textView7.setText(resources.getString(R.string.option_setting));
                        textView9.setText(resources.getString(R.string.language_setting));
                        textView11.setText(resources.getString(R.string.lettters_setting));
                        textView13.setText(resources.getString(R.string.sound_setting));

                        break;
                    case 2:
                        context = LocaleHelper.setLocale(SettingsActivity.this, "ar");
                        resources = context.getResources();
                        textView7.setText(resources.getString(R.string.option_setting));
                        textView9.setText(resources.getString(R.string.language_setting));
                        textView11.setText(resources.getString(R.string.lettters_setting));
                        textView13.setText(resources.getString(R.string.sound_setting));

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

        aSwitch.setOnCheckedChangeListener(this);
        setUpClick();
    }

    private void setUpClick() {
        Button btnbackButtonsInSitting = (Button) findViewById(R.id.backButtonsInSitting);



        btnbackButtonsInSitting.setOnClickListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
       // Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backButtonsInSitting:

                if(toSettingActivity == 0 ) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("lan", lan);
                    intent.putExtra("music" , tempMusic);
                    startActivity(intent);
                    finish();
                }
                if(toSettingActivity == 1 ) {
                    Intent intent = new Intent(this, Test.class);
                    intent.putExtra("lan", lan);
                    intent.putExtra("music" , tempMusic);
                    startActivity(intent);
                    finish();
                }
                break;
        }
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

        if(aSwitch.isChecked()){
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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
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
            tempMusic= 1;
            mHomeWatcher.startWatch();
        }
        else {
            Log.d("msuic is false","Stop music");
            tempMusic = 0 ;
            doUnbindService();
            Intent music = new Intent();
            mHomeWatcher.stopWatch();
            mServ.pauseMusic();
            music.setClass(this,MusicService.class);
            stopService(music);
        }
    }
}
