package com.tamer.child_math;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tamer.child_math.Firebase.FirebaseUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class Login3Activity extends AppCompatActivity implements View.OnClickListener {

    SQLiteDatabase db;
    DataBaseHelper dataBaseHelper;
    Button btn_sgin;
    EditText editTextUser , editTextPassword ;
    Cursor cursor;
    DatabaseReference reff ;
    int lan;
    CheckBox checkBox;
    Members members;
    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        dataBaseHelper = new DataBaseHelper(this);
        db = dataBaseHelper.getReadableDatabase();
        btn_sgin = (Button)findViewById(R.id.btnSignIn2);
        editTextUser = (EditText)findViewById(R.id.editTextUser2);
        editTextPassword = (EditText)findViewById(R.id.editTextPass2);
        checkBox = (CheckBox)findViewById(R.id.teacher);

        reff = FirebaseDatabase.getInstance().getReference("Members");
        btn_sgin.setOnClickListener(this);
        progressBar = (ProgressBar)findViewById(R.id.progressBar7);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        progressBar.setVisibility(View.VISIBLE);
        String username = editTextUser.getText().toString();
        String passsword = editTextPassword.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(username,passsword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){


                    startActivity(new Intent(Login3Activity.this , Main2Activity.class));
                }
                else{
                    Toast.makeText(Login3Activity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
      /*  cursor = db.rawQuery("SELECT * FROM "+DataBaseHelper.TABLE_NAME+" WHERE "+DataBaseHelper.COL_2+" =? AND "+ DataBaseHelper.COL_3+" =? ",new String[]{username,passsword});
        if(cursor != null){
            if(cursor.getCount()>0){
                members.setName(username.intern());
                members.setPassword(passsword.intern());
                members.setStatusOfAdd(0);
                members.setStatusOfDivide(0);
                members.setStatusOfMulti(0);
                members.setStatusOfSub(0);
                reff.push().setValue(members);
                Intent intent = new Intent(this, Test.class);
                intent.putExtra("lan",0);
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
            }
        }*/
    }
}
