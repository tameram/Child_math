package com.tamer.child_math;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.iid.FirebaseInstanceId;

public class BaseLoginActivity extends AppCompatActivity implements View.OnClickListener {

    SQLiteDatabase db;
    DataBaseHelper dataBaseHelper;
    Button btn_sgin;
    EditText editTextUser , editTextPassword ;
    Cursor cursor;
    int lan;
    CheckBox checkBoxTeacher ;
    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        dataBaseHelper = new DataBaseHelper(this);
        db = dataBaseHelper.getReadableDatabase();
        btn_sgin = (Button)findViewById(R.id.btnSignIn);
        editTextUser = (EditText)findViewById(R.id.editTextUser);
        editTextPassword = (EditText)findViewById(R.id.editTextPass);
        btn_sgin.setOnClickListener(this);
        checkBoxTeacher = (CheckBox)findViewById(R.id.teacher);
        progressBar = (ProgressBar)findViewById(R.id.progressBar7);

        firebaseAuth = FirebaseAuth.getInstance();

        Log.d(TAG, "onCreate: ");
    }

    private static final String TAG = "BaseLoginActivity";
    @Override
    public void onClick(View v) {
        progressBar.setVisibility(View.VISIBLE);
        String username = editTextUser.getText().toString();
        String passsword = editTextPassword.getText().toString();

        Log.d(TAG, "onClick: firebaseAuth");

        firebaseAuth.signInWithEmailAndPassword(username,passsword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    FirebaseUtils.updateFCM(getApplicationContext(),    FirebaseInstanceId.getInstance().getToken(),null);

                    startActivity(new Intent(BaseLoginActivity.this , Main2Activity.class));
                }
                else{
                    Toast.makeText(BaseLoginActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

        /*
        cursor = db.rawQuery("SELECT * FROM "+DataBaseHelper.TABLE_NAME+" WHERE "+DataBaseHelper.COL_2+" =? AND "+ DataBaseHelper.COL_3+" =? ",new String[]{username,passsword});
        if(cursor != null){
            if(cursor.getCount()>0){
                if(!checkBoxTeacher.isChecked()) {
                    Intent intent = new Intent(this, Main2Activity.class);
                    intent.putExtra("lan", 1);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(this, teacherMainActivity.class);
                    intent.putExtra("lan", 1);
                    startActivity(intent);
                    finish();
                }
            }
            else{
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
            }
        }*/
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
