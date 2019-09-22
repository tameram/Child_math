package com.tamer.child_math;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class addAQuestion extends AppCompatActivity implements  View.OnClickListener {
    FirebaseAuth firebaseAuth;
    EditText editTextquestion;
    EditText editTextAnswer;
    int level ;
    int operator;
    Button button;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aquestion);
        Spinner spinner = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinerOFLevelTest, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new LevelSpinnerClass());
        editTextAnswer = (EditText)findViewById(R.id.editText6);
        editTextquestion = (EditText)findViewById(R.id.editText22);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.operators, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new OperatorsSpinnerClass());

    }





    @Override
    public void onClick(View v) {
        String question = editTextquestion.getText().toString();
        int answer = Integer.parseInt(editTextAnswer.getText().toString());
        questionInFireBase questionInFireBase = new questionInFireBase(question,answer,level,operator);
        //Toast.makeText(this,""+questionInFireBase.getQuestion()+"tt "+questionInFireBase.getAnswer()+"level "+questionInFireBase.getLevel(),Toast.LENGTH_LONG).show();
        new FireBaseDataBaseHelperForQuestions().addQuestion(questionInFireBase, new FireBaseDataBaseHelperForQuestions.DataStatus() {
            @Override
            public void DataIsLoaded(List<com.tamer.child_math.questionInFireBase> questionInFireBaseList, List<String> keys) {
                Toast.makeText(addAQuestion.this , "the Question is added ",Toast.LENGTH_LONG).show();
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

     class LevelSpinnerClass implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            level = position +1 ;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

     class OperatorsSpinnerClass implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            operator = position ;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
