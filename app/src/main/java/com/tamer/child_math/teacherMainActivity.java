package com.tamer.child_math;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class teacherMainActivity extends ListActivity implements View.OnClickListener {
    public DbHandlerToTeacher dbHandler;
    private SimpleCursorAdapter adapter;
    Button btn_addQuestion;
    Button btn;
    DataSnapshot ds;


    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

        ListView listViewMain = getListView();
        btn_addQuestion = (Button) findViewById(R.id.btn_AddQuestion);
        btn_addQuestion.setOnClickListener(this);
        btn = (Button)findViewById(R.id.btn_DeleteAll);
        btn.setOnClickListener(this);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("Questions");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_AddQuestion:
                Intent intent = new Intent(this, addAQuestion.class);
                startActivity(intent);
                break;
            case R.id.btn_DeleteAll:
                //Log.d(", ",""+myRef.toString());
                Toast.makeText(teacherMainActivity.this,""+myRef.getDatabase(),Toast.LENGTH_LONG).show();
                break;
        }
    }
}
