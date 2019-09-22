package com.tamer.child_math;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Result extends AppCompatActivity implements View.OnClickListener {

    private int baseAdd , baseSub , baseMulti , baseDivide , tmpAdd , tmpSub , tmpMulti , tmpDivide , correctAnswer ;
    double addProgD , subProgD , multiProgD , divideProgD ;
    int addProgI ,subProgI , multiProgI , divideProgI ;
    ProgressBar progressBarAdd , progressBarSub ,progressBarMulti ,progressBarDivide ;
    TextView textViewTitle , textViewAdd , textViewSub ,textViewDivide ,textViewMulit;
    int level ;
    Button buttonProfile;
    private int lan ;
    Intent intent;
    int ifItFrom ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        intent = getIntent();

        baseAdd = intent.getIntExtra("addOper",1);
        baseSub = intent.getIntExtra("subOper",1);
        baseMulti = intent.getIntExtra("multOper",1);
        baseDivide = intent.getIntExtra("divOper",1);
       /* tmpAdd = intent.getIntExtra("addOperTmp",1);
        tmpSub = intent.getIntExtra("subOperTmp",1);
        tmpMulti = intent.getIntExtra("multOperTmp",1);
        tmpDivide = intent.getIntExtra("divOperTmp",1);*/
        ifItFrom = intent.getIntExtra("ifItFrom",1);

        correctAnswer = intent.getIntExtra("numOfCorectQuestions",1);
        level = intent.getIntExtra("level",1);
        lan = intent.getIntExtra("lan", 0); // here 0 is the default value

        textViewAdd = (TextView)findViewById(R.id.textView15);
        textViewSub = (TextView)findViewById(R.id.textView16);
        textViewMulit = (TextView)findViewById(R.id.textView17);
        textViewDivide = (TextView)findViewById(R.id.textView18);
        textViewTitle = (TextView)findViewById(R.id.textView6);
        buttonProfile =(Button)findViewById(R.id.buttonGoToYouProfile) ;




        Context context;
        Resources resources;
        switch (lan) {
            case 0:
                Log.d("en",lan+"");
                context = LocaleHelper.setLocale(Result.this, "en");
                resources = context.getResources();
                textViewAdd.setText(resources.getString(R.string.result_Add));
                textViewTitle.setText(resources.getString(R.string.result_Result));
                textViewSub.setText(resources.getString(R.string.result_Subtract));
                textViewMulit.setText(resources.getString(R.string.result_Multiply));
                textViewDivide.setText(resources.getString(R.string.result_Divide));
                buttonProfile.setText(resources.getString(R.string.result_Gotoyourprofile));
                // logInTheHomePage.setText(resources.getString(R.string.ok_setting));
                break;
            case 1:
                context = LocaleHelper.setLocale(Result.this, "iw");
                resources = context.getResources();
                textViewAdd.setText(resources.getString(R.string.result_Add));
                textViewSub.setText(resources.getString(R.string.result_Subtract));
                textViewMulit.setText(resources.getString(R.string.result_Multiply));
                textViewDivide.setText(resources.getString(R.string.result_Divide));
                buttonProfile.setText(resources.getString(R.string.result_Gotoyourprofile));
                break;
            case 2:
                Log.d("ar",lan+"");
                context = LocaleHelper.setLocale(Result.this, "ar");
                resources = context.getResources();
                textViewAdd.setText(resources.getString(R.string.result_Add));
                textViewSub.setText(resources.getString(R.string.result_Subtract));
                textViewMulit.setText(resources.getString(R.string.result_Multiply));
                textViewDivide.setText(resources.getString(R.string.result_Divide));
                buttonProfile.setText(resources.getString(R.string.result_Gotoyourprofile));
                break;
        }

        Log.d("tm",baseAdd+" and "+baseSub+" and "+baseMulti+" and "+baseDivide+" and "+
                tmpAdd+" and "+tmpSub+" and "+tmpMulti+" and "+tmpDivide+" and ");
        ///////////////////////////// add ////////////////////////////
        progressBarAdd = (ProgressBar)findViewById(R.id.progressBar8);
      /*  if(baseAdd != 0)
            addProgD = (double)(tmpAdd * 1.0/ baseAdd);
        addProgD*=100;
Log.d("tm",addProgD+"");
        addProgI = (int) addProgD;*/

        progressBarAdd.setProgress(baseAdd);

        ///////////////////////////// sub ////////////////////////////
        progressBarSub = (ProgressBar)findViewById(R.id.progressBar9);
      /*  if(baseSub != 0)
            subProgD = (double)(tmpSub * 1.0/ baseSub);
        subProgD*=100;

        subProgI = (int) subProgD;*/

        progressBarSub.setProgress(baseSub);

        ///////////////////////////// multi ////////////////////////////
        progressBarMulti = (ProgressBar)findViewById(R.id.progressBar10);
       /* if(baseMulti != 0)
            multiProgD = (double)(tmpMulti* 1.0 / baseMulti);
        multiProgD*=100;

        multiProgI = (int) multiProgD;*/

        progressBarMulti.setProgress(baseMulti);

        ///////////////////////////// divide ////////////////////////////
        progressBarDivide = (ProgressBar)findViewById(R.id.progressBar11);
        /*if(baseDivide != 0)
            divideProgD = (double)(tmpDivide* 1.0 / baseDivide);
        divideProgD*=100;

        divideProgI = (int) divideProgD;*/

        progressBarDivide.setProgress(baseDivide);


        setUpClick();
    }

    private void setUpClick() {
        Button btnGoToYouProfile = (Button) findViewById(R.id.buttonGoToYouProfile);


        btnGoToYouProfile.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonGoToYouProfile:

                Intent intent = new Intent(this, Main2Activity.class);
                intent.putExtra("lan",lan);
                intent.putExtra("addProgI",addProgI);
                intent.putExtra("subProgI",subProgI);
                intent.putExtra("multiProgI",multiProgI);
                intent.putExtra("divideProgI",divideProgI);
                intent.putExtra("level",level);
                intent.putExtra("ifItFrom",ifItFrom);

                startActivity(intent);
                finish();
                break;

        }
    }


}


