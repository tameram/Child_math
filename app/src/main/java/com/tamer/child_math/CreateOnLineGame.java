package com.tamer.child_math;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tamer.child_math.Firebase.FirebaseUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateOnLineGame extends AppCompatActivity implements View.OnClickListener {
    String userID ;
    Button buttonInvent;
    EditText editTextEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_on_line_game);
        Bundle extras = getIntent().getExtras();
        Intent intent = getIntent();

        //lan = intent.getIntExtra("lan", 0); // here 0 is the default value
        userID = intent.getStringExtra("userID");
        buttonInvent = (Button)findViewById(R.id.invent);
        editTextEmail = (EditText)findViewById(R.id.editTextForEmail);

        buttonInvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateOnLineGame.this,"yees",Toast.LENGTH_LONG).show();

                FirebaseUtils.sendRequestToUser(CreateOnLineGame.this,editTextEmail.getText().toString());

            }
        });
       // buttonInvent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

      /*  FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("game");
        myRef.push().setValue(userID);
        Toast.makeText(this,userID+" ",Toast.LENGTH_LONG).show();
        myRef.setValue(null);*/

        }

}
