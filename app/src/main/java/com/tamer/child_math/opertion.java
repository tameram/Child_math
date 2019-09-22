package com.tamer.child_math;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tamer.child_math.Firebase.FirebaseUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class opertion extends AppCompatActivity implements View.OnClickListener {
    private int questionNum = 1 ;
    String userID ;
    private int  lan , typeOfQuestions = -1 , wantAAdd = -1 , wantASub = -1 , wantAMulti = -1 , wantADivide = -1;
    TextView textView1;
    TextView textView2 , textViewQuestion;
    operat operat = new operat();
    String exer , theRealAnswer;
    EditText theAnswer;
    int addOper =0, subOper =0, multOper=0 , divOper=0 ,otherOper = 0 , theInputAnswer ;
    int addOperTmp =0, subOperTmp=0 , multOperTmp =0, divOperTmp=0;
    int addOperResult =0, subOperResult=0 , multOperResult =0, divOperResult=0;
    double tempForStatus;
    boolean ifItCorrect ;
    int tmp , numOfCorectQuestions = 0 , ifItFrom , correctAnswer , inputQuestionOperator;
    Button buttonClr , buttonOk , buttonThatSet ;
    ProgressBar progressBar;
    int questionNumber ;
    List<question> questions ;
    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opertion);

        progressBar = (ProgressBar)findViewById(R.id.progressBar14);
        textView1 = (TextView)findViewById(R.id.textView19);
        textView2 = (TextView)findViewById(R.id.textView5);
        theAnswer = (EditText) findViewById(R.id.editText);
        buttonClr =(Button)findViewById(R.id.btn_clr) ;
        buttonOk = (Button)findViewById(R.id.buttonOk);
        buttonThatSet = (Button)findViewById(R.id.buttonThatSet);
        textViewQuestion = (TextView)findViewById(R.id.textView4) ;
        questions = new ArrayList<>();
        Context context;
        Resources resources;
        Intent intent = getIntent();
        lan = intent.getIntExtra("lan", 0); // here 0 is the default value
        userID = intent.getStringExtra("userID");

        if (intent.hasExtra("referId")){
            String referId = intent.getStringExtra("referId");


            FirebaseUtils.sendResponseToUser(this,referId,1);
        }

        progressBar.setVisibility(View.VISIBLE);
         questions = readFromFireBase();
        progressBar.setVisibility(View.GONE);

Log.d("hhhhhhhhhhhh",questions.size()+"");

        ///////////// for language /////////////////////////

        switch (lan) {
            case 0:
                Log.d("en",lan+"");
                context = LocaleHelper.setLocale(opertion.this, "en");
                resources = context.getResources();
                buttonOk.setText(resources.getString(R.string.opertions_OK));
                buttonThatSet.setText(resources.getString(R.string.opertions_thatset));
                textViewQuestion.setText(resources.getString(R.string.opertions_Question));
                // logInTheHomePage.setText(resources.getString(R.string.ok_setting));
                break;
            case 1:
                context = LocaleHelper.setLocale(opertion.this, "iw");
                resources = context.getResources();
                buttonOk.setText(resources.getString(R.string.opertions_OK));
                buttonThatSet.setText(resources.getString(R.string.opertions_thatset));
                textViewQuestion.setText(resources.getString(R.string.opertions_Question));
                break;
            case 2:
                Log.d("ar",lan+"");
                context = LocaleHelper.setLocale(opertion.this, "ar");
                resources = context.getResources();
                buttonOk.setText(resources.getString(R.string.opertions_OK));
                buttonThatSet.setText(resources.getString(R.string.opertions_thatset));
                textViewQuestion.setText(resources.getString(R.string.opertions_Question));
                break;
        }



        /////////////////////////////////////////////////////////////////////

/*
        typeOfQuestions = intent.getIntExtra("typeOfLevel",-1);
        wantAAdd = intent.getIntExtra("haveAAdd",1);
        wantASub= intent.getIntExtra("haveASub",1);
        wantAMulti= intent.getIntExtra("haveAMulti",1);
        wantADivide= intent.getIntExtra("haveADivide",1);
        ifItFrom = intent.getIntExtra("ifItFrom",1);*/

        ///////////////////////////////////////////////////////////////////////

       // exer = operat.randomQuestion(typeOfQuestions,wantAAdd,wantASub,wantAMulti,wantADivide);
      //  tmp = operat.getCorrectAnswers();







/*        typeOfQuestions = quistions.getLevel();
        if(typeOfQuestions != 1 || typeOfQuestions != 2 ||typeOfQuestions != 3)
            typeOfQuestions = -1;
        wantAAdd = quistions.getIfWantAdd();
        if(wantAAdd != 1 || wantAAdd != 0)
            wantAAdd = 1;
        wantASub = quistions.getIfWantSub();
        if(wantASub != 1 || wantASub != 0)
            wantASub = 1;
        wantAMulti = quistions.getIfWantMulti();
        if(wantAMulti != 1 || wantAMulti != 0)
            wantAMulti = 1;
        wantAMulti = quistions.getIfWantMulti();
        if(wantADivide != 1 || wantADivide != 0)
            wantADivide = 1;*/


        setUpClick();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();

       // textView2.setText(questions.get(1).question);
    }

    private void setUpClick() {
        Button btnbuttonThatSet = (Button) findViewById(R.id.buttonThatSet);
        Button btnbuttonOk = (Button) findViewById(R.id.buttonOk);

        Button btn1 ,btn2 ,btn3 ,btn4 ,btn5 ,btn6 ,btn7 ,btn8 ,btn9 ,btn0 , btnClr;
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);
        btn9 = (Button) findViewById(R.id.button9);
        btn0 = (Button) findViewById(R.id.button0);
        btnClr = (Button) findViewById(R.id.btn_clr);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnClr.setOnClickListener(this);

        btnbuttonThatSet.setOnClickListener(this);
        btnbuttonOk.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
            String str="" ;
        switch (view.getId()) {
            case R.id.button0:
                theAnswer.setText(theAnswer.getText().insert(theAnswer.getText().length(),"0"));
                break;
            case R.id.button1:
                theAnswer.setText(theAnswer.getText().insert(theAnswer.getText().length(),"1"));
                break;
            case R.id.button2:
                theAnswer.setText(theAnswer.getText().insert(theAnswer.getText().length(),"2"));
                break;
            case R.id.button3:
                theAnswer.setText(theAnswer.getText().insert(theAnswer.getText().length(),"3"));
                break;
            case R.id.button4:
                theAnswer.setText(theAnswer.getText().insert(theAnswer.getText().length(),"4"));
                break;
            case R.id.button5:
                theAnswer.setText(theAnswer.getText().insert(theAnswer.getText().length(),"5"));
                break;
            case R.id.button6:
                theAnswer.setText(theAnswer.getText().insert(theAnswer.getText().length(),"6"));
                break;
            case R.id.button7:
                theAnswer.setText(theAnswer.getText().insert(theAnswer.getText().length(),"7"));
                break;
            case R.id.button8:
                theAnswer.setText(theAnswer.getText().insert(theAnswer.getText().length(),"8"));
                break;
            case R.id.button9:
                theAnswer.setText(theAnswer.getText().insert(theAnswer.getText().length(),"9"));
                break;
            case R.id.btn_clr :
                theAnswer.setText(theAnswer.getText().delete(theAnswer.getText().length()-1,theAnswer.getText().length()));
                break;
            case R.id.buttonThatSet:
                Intent intent = new Intent(this, Result.class);
                tempForStatus =  ((double)addOperTmp/addOper)*100.0 ;
                addOperResult = (int)tempForStatus;
                tempForStatus =  ((double)subOperTmp/subOper)*100.0 ;
                subOperResult = (int)tempForStatus;
                tempForStatus =  ((double)multOperTmp/multOper)*100.0 ;
                multOperResult = (int)tempForStatus;
                tempForStatus =  ((double)divOperTmp/divOper)*100.0 ;
                divOperResult = (int)tempForStatus;
                intent.putExtra("addOper",addOperResult);
                intent.putExtra("subOper",subOperResult);
                intent.putExtra("multOper",multOperResult);
                intent.putExtra("divOper",divOperResult);
                /*intent.putExtra("addOper",operat.getAddOper());
                intent.putExtra("subOper",operat.getSubOper());
                intent.putExtra("multOper",operat.getMultOper());
                intent.putExtra("divOper",operat.getDivOper());
                intent.putExtra("numOfCorectQuestions",numOfCorectQuestions);
                intent.putExtra("addOperTmp",addOperTmp);
                intent.putExtra("subOperTmp",subOperTmp);
                intent.putExtra("multOperTmp",multOperTmp);
                intent.putExtra("divOperTmp",divOperTmp);
                intent.putExtra("level",typeOfQuestions);*/
                intent.putExtra("lan",lan);
                intent.putExtra("ifItFrom",ifItFrom);
                startActivity(intent);









               /*Log.d("this is array qussetion",questions.get(1).question+"");
                questionNumber = getRandomElement(questions);
                textView2.setText(questions.get(questionNumber).question);*/

                break;
            case R.id.buttonOk:
                questionNum+=1;

                theRealAnswer =theAnswer.getText().toString();
                correctAnswer = questions.get(questionNumber).answer;
                inputQuestionOperator = questions.get(questionNumber).operator;
                Log.d(" the question",questions.get(questionNumber).question+
                        " answer : "+questions.get(questionNumber).answer +
                        " operator : "+questions.get(questionNumber).operator +
                        " level : "+questions.get(questionNumber).level);
                switch (inputQuestionOperator){
                    case 0 :
                        addOper++;
                        break;
                    case 1 :
                        subOper++;
                        break;
                    case 2 :
                        multOper++;
                        break;
                    case 3 :
                        divOper++;
                        break;
                    case 4:
                        addOper++;
                        subOper++;
                        multOper++;
                        divOper++;
                        break;

                }



                if(TextUtils.isEmpty(theRealAnswer) )
                    theInputAnswer = 0;
                else
                    theInputAnswer = Integer.parseInt(theRealAnswer);

                if(correctAnswer == theInputAnswer){
                    numOfCorectQuestions++;
                    switch (inputQuestionOperator){
                        case 0 :
                            addOperTmp++;
                            break;
                        case 1 :
                            subOperTmp++;
                            break;
                        case 2 :
                            multOperTmp++;
                            break;
                        case 3 :
                            divOperTmp++;
                            break;
                        case 4:
                            addOperTmp++;
                            subOperTmp++;
                            multOperTmp++;
                            divOperTmp++;
                            break;

                    }
                }


/////////////////////////////////////////////////////////////////////////////////





              /*  exer  = operat.randomQuestion(typeOfQuestions,wantAAdd,wantASub,wantAMulti,wantADivide);
                while (exer.isEmpty())
                    exer  = operat.randomQuestion(typeOfQuestions,wantAAdd,wantASub,wantAMulti,wantADivide);
                addOper = operat.getAddOper()-addOper;
                subOper = operat.getSubOper()-subOper;
                multOper = operat.getMultOper()-multOper;
                divOper = operat.getDivOper()-divOper;

                ifItCorrect = operat.ifItCorrect(theInputAnswer,operat.getCorrectAnswers());
                Log.d("tamer",addOper+"");
                if(theInputAnswer == tmp){

                    Log.d("inpooo","yes");
                    addOperTmp+=addOper;
                    subOperTmp += subOper;
                    multOperTmp += multOper;
                    divOperTmp += divOper;
                    numOfCorectQuestions++;
                }
                tmp = operat.getCorrectAnswers();
                addOper = operat.getAddOper();
                Log.d("the question",exer);
                Toast.makeText(this,addOperTmp+" - "+ subOperTmp+" * "+multOperTmp+" / "+divOperTmp,Toast.LENGTH_SHORT).show();
                textView2.setText(exer);*/
                questionNumber = getRandomElement(questions);
                textView2.setText(questions.get(questionNumber).question);
                theAnswer.setText("");
                if(questionNum==20){
                    Intent intent3 = new Intent(this, Result.class);
                    tempForStatus =  ((double)addOperTmp/addOper)*100.0 ;
                    addOperResult = (int)tempForStatus;
                    tempForStatus =  ((double)subOperTmp/subOper)*100.0 ;
                    subOperResult = (int)tempForStatus;
                    tempForStatus =  ((double)multOperTmp/multOper)*100.0 ;
                    multOperResult = (int)tempForStatus;
                    tempForStatus =  ((double)divOperTmp/divOper)*100.0 ;
                    divOperResult = (int)tempForStatus;
                    intent3.putExtra("addOper",addOperResult);
                    intent3.putExtra("subOper",subOperResult);
                    intent3.putExtra("multOper",multOperResult);
                    intent3.putExtra("divOper",divOperResult);
                    /*intent3.putExtra("numOfCorectQuestions",numOfCorectQuestions);
                    intent3.putExtra("addOperTmp",addOperTmp);
                    intent3.putExtra("subOperTmp",subOperTmp);
                    intent3.putExtra("multOperTmp",multOperTmp);
                    intent3.putExtra("divOperTmp",divOperTmp);
                    intent3.putExtra("level",typeOfQuestions);*/
                    intent3.putExtra("lan",lan);
                    intent3.putExtra("ifItFrom",ifItFrom);
                    startActivity(intent3);

                    finish();
                    break;

                }
                textView1.setText(questionNum+"");

                //addOper = operat.getAddOper();
               // subOper = operat.getSubOper();
                //multOper = operat.getMultOper();
                //divOper = operat.getDivOper();
              //  Toast.makeText(this, "+ ="+wantAAdd+"- ="+wantASub+"* = "+wantAMulti+"/ = "+wantADivide, Toast.LENGTH_SHORT).show();
             //   Intent intent1 = new Intent(this, opertion.class);
                //startActivity(intent1);
             //   break;

            //   Toast.makeText(this, "this is a toast!!!", Toast.LENGTH_SHORT).show();
            //   break;
        }
    }

    public ArrayList<question> readFromFireBase(){
        ArrayList<question>arrayList = new ArrayList<>() ;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Questions");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Log.d("", "Value is: " + postSnapshot.getValue(question.class).operator);
                    questions.add(postSnapshot.getValue(question.class));


                }
                questionNumber = getRandomElement(questions);
                textView2.setText(questions.get(questionNumber).question);
                 Log.d("", "Value is: " + questions.get(questionNumber).operator);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return arrayList;
    }

    // Function select an element base on index
    // and return an element
    public int getRandomElement(List<question> list)
    {
        Random rand = new Random();
        return rand.nextInt(list.size());
    }


}

