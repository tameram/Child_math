package com.tamer.child_math;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    DataBaseHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    int lan ;
    TextView textView21 , textView22 ,textView23 ,textView20;
    EditText editTextName ,editTextPassword , editTextEmail ;
    Button button10;
    FirebaseAuth firebaseAuth;

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sqLiteOpenHelper = new DataBaseHelper(this);
        textView20 = (TextView)findViewById(R.id.textView20);
        textView21 = (TextView)findViewById(R.id.textView27);
        textView22 = (TextView)findViewById(R.id.textView22);
        textView23 = (TextView)findViewById(R.id.textView23);
        button10 = (Button)findViewById(R.id.btnSignIn2);
        editTextName = (EditText)findViewById(R.id.editTextUser2);
        editTextPassword = (EditText)findViewById(R.id.editTextPass2);
        editTextEmail = (EditText)findViewById(R.id.editText4);
        progressBar = (ProgressBar)findViewById(R.id.progressBar6);
        Intent intent = getIntent();
        lan = intent.getIntExtra("lan", 0); // here 0 is the default value
        Log.d("tam",lan+"");
        Context context;
        Resources resources;
        switch (lan) {
            case 0:
                Log.d("en",lan+"");
                context = LocaleHelper.setLocale(SignupActivity.this, "en");
                resources = context.getResources();
                textView20.setText(resources.getString(R.string.signUp_Signup));
                textView21.setText(resources.getString(R.string.signUp_UserName));
                textView22.setText(resources.getString(R.string.signUp_Password));
                textView23.setText(resources.getString(R.string.signUp_Email));
                button10.setText(resources.getString(R.string.signUp_Signup));
                // logInTheHomePage.setText(resources.getString(R.string.ok_setting));
                break;
            case 1:
                context = LocaleHelper.setLocale(SignupActivity.this, "iw");
                resources = context.getResources();
                textView20.setText(resources.getString(R.string.signUp_Signup));
                textView21.setText(resources.getString(R.string.signUp_UserName));
                textView22.setText(resources.getString(R.string.signUp_Password));
                textView23.setText(resources.getString(R.string.signUp_Email));
                button10.setText(resources.getString(R.string.signUp_Signup));
                break;
            case 2:
                Log.d("ar",lan+"");
                context = LocaleHelper.setLocale(SignupActivity.this, "ar");
                resources = context.getResources();
                textView20.setText(resources.getString(R.string.signUp_Signup));
                textView21.setText(resources.getString(R.string.signUp_UserName));
                textView22.setText(resources.getString(R.string.signUp_Password));
                textView23.setText(resources.getString(R.string.signUp_Email));
                button10.setText(resources.getString(R.string.signUp_Signup));
                break;
        }
        firebaseAuth = FirebaseAuth.getInstance();
        setUpClick();


    }

    public void insertData(String name , String password , String email){
        final String ZERO = "0";
        ContentValues contentValues = new ContentValues();
        contentValues.put(sqLiteOpenHelper.COL_2, name);
        contentValues.put(sqLiteOpenHelper.COL_3, password);
        contentValues.put(sqLiteOpenHelper.COL_4, email);


        long id = sqLiteDatabase.insert(DataBaseHelper.TABLE_NAME , null,contentValues);

    }

    private void setUpClick() {
        Button btnLogIn = (Button) findViewById(R.id.btnSignIn2);


        btnLogIn.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(firebaseAuth != null){

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignIn2:
                //sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
                progressBar.setVisibility(View.VISIBLE);
                final String name = editTextName.getText().toString();
                final String password = editTextPassword.getText().toString();
                final String email = editTextEmail.getText().toString();
                firebaseAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            Members members = new Members(name,password,email,false,0,0,0,0,0);
                            FirebaseDatabase.getInstance().getReference("Users").child(firebaseAuth.getCurrentUser().getUid()).setValue(members).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(SignupActivity.this,"Regester suucssfully",Toast.LENGTH_LONG).show();
                                }
                            });



                        }
                        else {
                            Toast.makeText(SignupActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });



                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("lan",lan);
                startActivity(intent);
                finish();
                break;
        }


    }



}
