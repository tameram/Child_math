package com.tamer.child_math;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Quistions extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    final int IFITFROMQUESTION = 0 ;
    int lan ;
    private int level;
    private int ifWantAdd;
    private int ifWantSub;
    private int ifWantMulti;
    TextView main_Title , the_Question , choose_level;
    Button next;



    private int ifWantDivide;
    private RadioButton ifWantAddR , ifWantSubR , ifWantMultiR , ifWantDivideR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Spinner spinner = findViewById(R.id.spinnerALevelInQuestion);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinerOFLevelTest, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        main_Title = (TextView)findViewById(R.id.textView);
        the_Question=(TextView)findViewById(R.id.textView3);
        choose_level =(TextView)findViewById(R.id.textView2);
        next = (Button)findViewById(R.id.button);
        ifWantAddR=(RadioButton)findViewById(R.id.radioButtonAdd);
        ifWantSubR=(RadioButton)findViewById(R.id.radioButtonSubtract);
        ifWantMultiR=(RadioButton)findViewById(R.id.radioButtonMultiply);
        ifWantDivideR=(RadioButton)findViewById(R.id.radioButtonDivide);

        Intent intent = getIntent();
        lan = intent.getIntExtra("lan", 0); // here 0 is the default value

        Context context;
        Resources resources;
        switch (lan) {
            case 0:

                context = LocaleHelper.setLocale(Quistions.this, "en");
                resources = context.getResources();
                main_Title.setText(resources.getString(R.string.questions_Questions));
                the_Question.setText(resources.getString(R.string.questions_Whatdotoyprefertostudy));
                choose_level.setText(resources.getString(R.string.questions_ChooseaLevel));
                next.setText(resources.getString(R.string.questions_Starttest));
                ifWantAddR.setText(resources.getString(R.string.questions_Add));
                ifWantSubR.setText(resources.getString(R.string.questions_Subtract));
                ifWantMultiR.setText(resources.getString(R.string.questions_Multiply));
                ifWantDivideR.setText(resources.getString(R.string.questions_Divide));
                // logInTheHomePage.setText(resources.getString(R.string.ok_setting));
                break;
            case 1:
                context = LocaleHelper.setLocale(Quistions.this, "iw");
                resources = context.getResources();
                main_Title.setText(resources.getString(R.string.questions_Questions));
                the_Question.setText(resources.getString(R.string.questions_Whatdotoyprefertostudy));
                choose_level.setText(resources.getString(R.string.questions_ChooseaLevel));
                next.setText(resources.getString(R.string.questions_Starttest));
                ifWantAddR.setText(resources.getString(R.string.questions_Add));
                ifWantSubR.setText(resources.getString(R.string.questions_Subtract));
                ifWantMultiR.setText(resources.getString(R.string.questions_Multiply));
                ifWantDivideR.setText(resources.getString(R.string.questions_Divide));
                break;
            case 2:
                Log.d("ar",lan+"");
                context = LocaleHelper.setLocale(Quistions.this, "ar");
                resources = context.getResources();
                main_Title.setText(resources.getString(R.string.questions_Questions));
                the_Question.setText(resources.getString(R.string.questions_Whatdotoyprefertostudy));
                choose_level.setText(resources.getString(R.string.questions_ChooseaLevel));
                next.setText(resources.getString(R.string.questions_Starttest));
                ifWantAddR.setText(resources.getString(R.string.questions_Add));
                ifWantSubR.setText(resources.getString(R.string.questions_Subtract));
                ifWantMultiR.setText(resources.getString(R.string.questions_Multiply));
                ifWantDivideR.setText(resources.getString(R.string.questions_Divide));
                break;
        }


        setUpClick();
    }



    private void setUpClick() {
        Button btnStart = (Button) findViewById(R.id.button);
        //Button btn = (Button)findViewById(R.id.button11);
        ifWantAddR = (RadioButton) findViewById(R.id.radioButtonAdd);
        ifWantSubR = (RadioButton) findViewById(R.id.radioButtonSubtract);
        ifWantMultiR = (RadioButton) findViewById(R.id.radioButtonMultiply);
        ifWantDivideR = (RadioButton) findViewById(R.id.radioButtonDivide);

        btnStart.setOnClickListener(this);
        //btn.setOnClickListener(this);
        ifWantAddR.setOnClickListener(this);
        ifWantSubR.setOnClickListener(this);
        ifWantMultiR.setOnClickListener(this);
        ifWantDivideR.setOnClickListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        level = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
       // Toast.makeText(adapterView.getContext(),
        //        "OnItemSelectedListener : " + adapterView.getItemAtPosition(i).toString(),
        //        Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radioButtonAdd:
                if(ifWantAddR.isSelected()){
                    ifWantAddR.setSelected(false);
                    ifWantAddR.setChecked(false);
                    ifWantAdd = 0;
                }
                else{
                    ifWantAddR.setSelected(true);
                    ifWantAddR.setChecked(true);
                    ifWantAdd = 1;
                }
                break;
            case R.id.radioButtonSubtract:
                if(ifWantSubR.isSelected()){
                    ifWantSubR.setSelected(false);
                    ifWantSubR.setChecked(false);
                    ifWantSub=0;

                }
                else{
                    ifWantSubR.setSelected(true);
                    ifWantSubR.setChecked(true);
                    ifWantSub=1;
                }
                break;
            case R.id.radioButtonMultiply:
                if(ifWantMultiR.isSelected()){
                    ifWantMultiR.setSelected(false);
                    ifWantMultiR.setChecked(false);
                    ifWantMulti=0;

                }
                else{
                    ifWantMultiR.setSelected(true);
                    ifWantMultiR.setChecked(true);
                    ifWantMulti=1;
                }
                break;
            case R.id.radioButtonDivide:
                if(ifWantDivideR.isSelected()){
                    ifWantDivideR.setSelected(false);
                    ifWantDivideR.setChecked(false);
                    ifWantDivide=0;

                }
                else{
                    ifWantDivideR.setSelected(true);
                    ifWantDivideR.setChecked(true);
                    ifWantDivide=1;
                }
                break;
           // case R.id.button11:
                //Toast.makeText(this,"add is "+ifWantAdd + "Sub is "+ifWantSub + "Multi is "+ifWantMulti+"Divide is "+ifWantDivide,Toast.LENGTH_LONG).show();
             //   Toast.makeText(this,"the level is "+ level,Toast.LENGTH_LONG).show();
              //  break;
            case R.id.button:
            //    ifWantAddB = findViewById(R.id.radioButtonAdd).callOnClick();
                Intent intent = new Intent(this, opertion.class);

                intent.putExtra("typeOfLevel",level);
                intent.putExtra("haveAAdd",ifWantAdd);
                intent.putExtra("haveASub",ifWantSub);
                intent.putExtra("haveAMulti",ifWantMulti);
                intent.putExtra("haveADivide",ifWantDivide);
                intent.putExtra("ifItFrom",IFITFROMQUESTION);

                intent.putExtra("lan",lan);
                startActivity(intent);

                break;
        }
    }

    public int getLevel() {
        return level;
    }

    public int getIfWantAdd() {
        return ifWantAdd;
    }

    public int getIfWantMulti() {
        return ifWantMulti;
    }

    public int getIfWantSub() {
        return ifWantSub;
    }

    public int getIfWantDivide() {
        return ifWantDivide;
    }
}
