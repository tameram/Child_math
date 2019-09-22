package com.tamer.child_math;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Test extends AppCompatActivity implements View.OnClickListener {

    final int IFITFROMTEST = 1 ;
    int lan , toSettingActivity = 1;
    TextView textView;
    Button btnIngnoreTest ;
    Button btnoptionInTestPage ;
    Button btnstartInTestPage , btnForTestOnLine ;
    String userID ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textView = (TextView)findViewById(R.id.gr) ;
         btnIngnoreTest = (Button) findViewById(R.id.ignoreInTestPage);
         btnoptionInTestPage = (Button) findViewById(R.id.optionInTestPage);
         btnstartInTestPage = (Button) findViewById(R.id.startInTestPage);
        btnForTestOnLine = (Button)findViewById(R.id.buttonForTestOnLine);

        Intent intent = getIntent();
        lan = intent.getIntExtra("lan", 0); // here 0 is the default value
        userID = intent.getStringExtra("userID");
        Log.d("tam",lan+"");
        Context context;
        Resources resources;
        switch (lan) {
            case 0:
                Log.d("en",lan+"");
                context = LocaleHelper.setLocale(Test.this, "en");
                resources = context.getResources();
                textView.setText(resources.getString(R.string.test_Test));
                btnIngnoreTest.setText(resources.getString(R.string.test_ignore));

                btnstartInTestPage.setText(resources.getString(R.string.test_Start));
                // logInTheHomePage.setText(resources.getString(R.string.ok_setting));
                break;
            case 1:
                context = LocaleHelper.setLocale(Test.this, "iw");
                resources = context.getResources();
                textView.setText(resources.getString(R.string.test_Test));
                btnIngnoreTest.setText(resources.getString(R.string.test_ignore));

                btnstartInTestPage.setText(resources.getString(R.string.test_Start));
                break;
            case 2:
                Log.d("ar",lan+"");
                context = LocaleHelper.setLocale(Test.this, "ar");
                resources = context.getResources();
                textView.setText(resources.getString(R.string.test_Test));
                btnIngnoreTest.setText(resources.getString(R.string.test_ignore));

                btnstartInTestPage.setText(resources.getString(R.string.test_Start));
                break;
        }


        setUpClick();
    }

    private void setUpClick() {

        btnstartInTestPage.setOnClickListener(this);
        btnIngnoreTest.setOnClickListener(this);
        btnoptionInTestPage.setOnClickListener(this);
        btnForTestOnLine.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ignoreInTestPage:
                Intent intent = new Intent(this, Quistions.class);
                intent.putExtra("lan",lan);
                intent.putExtra("userID",userID);
                startActivity(intent);
                finish();
                break;
            case R.id.optionInTestPage:
                Intent intent1 = new Intent(this, SettingsActivity.class);
                intent1.putExtra("lan",lan);
                intent1.putExtra("toSettingActivity",toSettingActivity);
                startActivity(intent1);
                finish();
                break;
            case R.id.startInTestPage:
                Intent intent2 = new Intent(this, opertion.class);
                intent2.putExtra("ifItFrom",IFITFROMTEST);
                intent2.putExtra("lan",lan);
                intent2.putExtra("userID",userID);
                startActivity(intent2);
                finish();
                break;
            case R.id.buttonForTestOnLine:
                Intent intent3 = new Intent(this,CreateOnLineGame.class);
                intent3.putExtra("userID",userID);
                startActivity(intent3);
                //finish();
                break;
        }
    }

}