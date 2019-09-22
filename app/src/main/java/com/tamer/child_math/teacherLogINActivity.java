package com.tamer.child_math;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class teacherLogINActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_sgin;
    EditText editTextUser , editTextPassword ;
    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private  String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_log_in);
        btn_sgin = (Button)findViewById(R.id.btnSignIn2);
        editTextUser = (EditText)findViewById(R.id.editTextUser2);
        editTextPassword = (EditText)findViewById(R.id.editTextPass2);
        progressBar = (ProgressBar)findViewById(R.id.progressBar72);

        firebaseAuth = FirebaseAuth.getInstance();
        btn_sgin.setOnClickListener(this);

    }



    // FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
    //String userid=user.getUid();
    // DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
    //reference.child(userid).addValueEventListener(new ValueEventListener() {
    //@Override
    // public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
    //   boolean ifisaTecher = dataSnapshot.getValue(Members.class).isIfHisATeacher();
    //   Log.d("he is a thecher ",ifisaTecher+"");
    // }

    //@Override
    //public void onCancelled(@NonNull DatabaseError databaseError) {

    // }
    //});

    @Override
    public void onClick(View v) {
        progressBar.setVisibility(View.VISIBLE);
        String username = editTextUser.getText().toString();
        String passsword = editTextPassword.getText().toString();
//        firebaseAuth = FirebaseAuth.getInstance();
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
 //       myRef = mFirebaseDatabase.getReference().child("Users");
 //       FirebaseUser user = firebaseAuth.getCurrentUser();
//        userID = user.getUid();
//        DatabaseReference members = myRef.child(userID);





        firebaseAuth.signInWithEmailAndPassword(username,passsword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                final boolean[] ifisaTecher = new boolean[1];
                 FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                String userid=user.getUid();
                 DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                reference.child(userid).addValueEventListener(new ValueEventListener() {
                    @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ifisaTecher[0] = dataSnapshot.getValue(Members.class).isIfHisATeacher();
                       if(ifisaTecher[0] == false){
                           Toast.makeText(teacherLogINActivity.this,"YOU ARE NOT A TEACHER",Toast.LENGTH_LONG).show();
                           startActivity(new Intent(teacherLogINActivity.this , teacherLogINActivity.class));
                           finish();
                       }

                     }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                     }
                    });

                Log.d("he is a teacher : ",ifisaTecher[0]+"");
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful() ){
                        startActivity(new Intent(teacherLogINActivity.this , teacherMainActivity.class));

                }
                else{
                    Toast.makeText(teacherLogINActivity.this,task.getException()+"",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getUserData()
    {
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        String userid=user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               /* String email = dataSnapshot.getValue(me.class).getEmail();
                String firstName = dataSnapshot.getValue(User.class).getFirstName();
                Log.d("Datasnapshot",email+" "+firstName);*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
