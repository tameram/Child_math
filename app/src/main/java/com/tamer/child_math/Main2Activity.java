package com.tamer.child_math;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tamer.child_math.Firebase.FirebaseUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

// shaingggg message
// dialog to out
public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    int lan;
    TextView textViewTitle;
    Button buttonAdd, buttonSub, buttonMulti, buttonDivide, buttonSignout , buttonTest;
    int typeOfLevelAdd , typeOfLevelSub , typeOfLevelDivide ,typeOfLevelMulti, level;
    int typeOfLevelAddBaseTmp , typeOfLevelSubBaseTmp , typeOfLevelDivideBaseTmp ,typeOfLevelMultiBaseTmp;
    int typeOfLevelAddBase , typeOfLevelSubBase , typeOfLevelDivideBase ,typeOfLevelMultiBase;
    ProgressBar progressBarAdd , progressBarSub ,progressBarMulti ,progressBarDivide;
    int ifItFrom ;
    TextView theName;
    String ideas = "my name is tamer";
    //FirebaseAuth firebaseAuth;
    //FirebaseUser firebaseUser;

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private  String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        lan = intent.getIntExtra("lan", 0); // here 0 is the default value
        typeOfLevelAddBaseTmp = intent.getIntExtra("addProgI",0);
        typeOfLevelSubBaseTmp = intent.getIntExtra("subProgI",0);
        typeOfLevelMultiBaseTmp = intent.getIntExtra("multiProgI",0);
        typeOfLevelDivideBaseTmp = intent.getIntExtra("divideProgI",0);
        ifItFrom = intent.getIntExtra("ifItFrom",0);
        theName = (TextView)findViewById(R.id.textViewName);
        level = intent.getIntExtra("level",1);
        Log.d("tamer", "onCreate: "+ level);
        buttonAdd = (Button) findViewById(R.id.button3);
        buttonSub = (Button) findViewById(R.id.button4);
        buttonMulti = (Button) findViewById(R.id.button5);
        buttonDivide = (Button) findViewById(R.id.button6);
        buttonSignout = (Button) findViewById(R.id.button7);
        buttonTest = (Button) findViewById(R.id.button10);
        textViewTitle = (TextView) findViewById(R.id.textView8);

        progressBarAdd = (ProgressBar)findViewById(R.id.progressBar2) ;
        progressBarSub = (ProgressBar)findViewById(R.id.progressBar3) ;
        progressBarMulti = (ProgressBar)findViewById(R.id.progressBar4) ;
        progressBarDivide = (ProgressBar)findViewById(R.id.progressBar5) ;

        typeOfLevelAdd = progressBarAdd.getProgress();


        typeOfLevelAdd = typeOfLevel(typeOfLevelAdd);
        typeOfLevelSub = typeOfLevel(typeOfLevelSub);
        typeOfLevelMulti = typeOfLevel(typeOfLevelMulti);
        typeOfLevelDivide = typeOfLevel(typeOfLevelDivide);

        typeOfLevelAddBase = progressBarAdd.getProgress();
        typeOfLevelSubBase = progressBarSub.getProgress();
        typeOfLevelMultiBase = progressBarMulti.getProgress();
        typeOfLevelDivideBase = progressBarDivide.getProgress();

        typeOfLevelAddBase = whoIsLarger(typeOfLevelAddBase , typeOfLevelAddBaseTmp);
        typeOfLevelSubBase = whoIsLarger(typeOfLevelSubBase , typeOfLevelSubBaseTmp);
        typeOfLevelMultiBase = whoIsLarger(typeOfLevelMultiBase , typeOfLevelMultiBaseTmp);
        typeOfLevelDivideBase = whoIsLarger(typeOfLevelDivideBase , typeOfLevelDivideBaseTmp);

        progressBarAdd.setProgress(typeOfLevelAdd);
        progressBarSub.setProgress(typeOfLevelSubBase);
        progressBarMulti.setProgress(typeOfLevelMultiBase);
        progressBarDivide.setProgress(typeOfLevelDivideBase);

        Context context;
        Resources resources;
        switch (lan) {
            case 0:
                Log.d("en", lan + "");
                context = LocaleHelper.setLocale(Main2Activity.this, "en");
                resources = context.getResources();
                buttonAdd.setText(resources.getString(R.string.mainactivity2_Add));
                buttonSub.setText(resources.getString(R.string.mainactivity2_Subtract));
                buttonMulti.setText(resources.getString(R.string.mainactivity2_Multiply));
                buttonDivide.setText(resources.getString(R.string.mainactivity2_Divide));
                buttonSignout.setText(resources.getString(R.string.mainactivity2_Signout));
                textViewTitle.setText(resources.getString(R.string.mainactivity2_profile));
                // logInTheHomePage.setText(resources.getString(R.string.ok_setting));
                break;
            case 1:
                context = LocaleHelper.setLocale(Main2Activity.this, "iw");
                resources = context.getResources();
                buttonAdd.setText(resources.getString(R.string.mainactivity2_Add));
                buttonSub.setText(resources.getString(R.string.mainactivity2_Subtract));
                buttonMulti.setText(resources.getString(R.string.mainactivity2_Multiply));
                buttonDivide.setText(resources.getString(R.string.mainactivity2_Divide));
                buttonSignout.setText(resources.getString(R.string.mainactivity2_Signout));
                textViewTitle.setText(resources.getString(R.string.mainactivity2_profile));
                break;
            case 2:
                Log.d("ar", lan + "");
                context = LocaleHelper.setLocale(Main2Activity.this, "ar");
                resources = context.getResources();
                buttonAdd.setText(resources.getString(R.string.mainactivity2_Add));
                buttonSub.setText(resources.getString(R.string.mainactivity2_Subtract));
                buttonMulti.setText(resources.getString(R.string.mainactivity2_Multiply));
                buttonDivide.setText(resources.getString(R.string.mainactivity2_Divide));
                buttonSignout.setText(resources.getString(R.string.mainactivity2_Signout));
                textViewTitle.setText(resources.getString(R.string.mainactivity2_profile));
                break;
        }
       // firebaseAuth = FirebaseAuth.getInstance();
       // firebaseUser = firebaseAuth.getCurrentUser();

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("Users");
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {



                    // User is signed in
                    Log.d("", "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(Main2Activity.this,"Successfully signed in with: " + user.getEmail(),Toast.LENGTH_LONG).show();
                } else {
                    // User is signed out
                    Log.d("", "onAuthStateChanged:signed_out");
                    Toast.makeText(Main2Activity.this,"Successfully signed out.",Toast.LENGTH_LONG).show();
                }
                // ...
            }
        };
        Log.d(TAG, "onCreate:updateFCM ");

        FirebaseUtils.updateFCM(getApplicationContext(),    FirebaseInstanceId.getInstance().getToken(),null);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        setUpClick();
    }

    private static final String TAG = "Main2Activity";

    private void showData(DataSnapshot dataSnapshot){
        for (DataSnapshot ds : dataSnapshot.getChildren()) {

            Members member = ds.child(userID).getValue(Members.class);
            if(member != null) {
                theName.setText(member.getName());
                progressBarAdd.setProgress(member.getStatusOfAdd());
                progressBarSub.setProgress(member.getStatusOfSub());
                progressBarMulti.setProgress(member.getStatusOfMulti());
                progressBarDivide.setProgress(member.getStatusOfDivide());
//            members.setName(ds.child(userID).getValue(Members.class).getName()); //set the name
                // members.setEmail(ds.child(userID).getValue(Members.class).getEmail()); //set the email
           /* members.setStatusOfSub(ds.child(userID).getValue(Members.class).getStatusOfSub());
            members.setStatusOfMulti(ds.child(userID).getValue(Members.class).getStatusOfMulti());
            members.setStatusOfDivide(ds.child(userID).getValue(Members.class).getStatusOfDivide());
            members.setStatusOfAdd(ds.child(userID).getValue(Members.class).getStatusOfAdd());*/

                Log.d("", "showData: name: " + member.getName());
                //display all the information
                //Log.d("", "showData: name: " + members.getName());
                // Log.d("", "showData: email: " + members.getEmail());


            }
            /// to presntate
        }
    }

    private void setUpClick() {
        buttonAdd.setOnClickListener(this);
        buttonSub.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonMulti.setOnClickListener(this);
        buttonSignout.setOnClickListener(this);
        buttonTest.setOnClickListener(this);
    }

    private int typeOfLevel(int tmp){
        if(tmp < 33)
            return  1;
        if(tmp > 33 && tmp < 66)
            return 2;
        if(tmp > 66 && tmp <= 100)
            return 3;
        return 0;
    }

    private int whoIsLarger(int base , int tmp){
        if(tmp > base)
            return tmp;
        return base;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                Intent intent = new Intent(this, opertion.class);
                intent.putExtra("lan",lan);
                intent.putExtra("haveAAdd",1);
                intent.putExtra("haveASub",0);
                intent.putExtra("haveAMulti",0);
                intent.putExtra("haveADivide",0);
                intent.putExtra("typeOfLevel",typeOfLevelAdd);
                startActivity(intent);
                finish();
                break;
            case R.id.button4:
                Intent intent2 = new Intent(this, opertion.class);
                intent2.putExtra("lan",lan);
                intent2.putExtra("haveAAdd",0);
                intent2.putExtra("haveASub",1);
                intent2.putExtra("haveAMulti",0);
                intent2.putExtra("haveADivide",0);
                intent2.putExtra("typeOfLevel",typeOfLevelSub);
                startActivity(intent2);
                finish();
                break;
            case R.id.button5:
                Intent intent1 = new Intent(this, opertion.class);
                intent1.putExtra("lan",lan);
                intent1.putExtra("haveAAdd",0);
                intent1.putExtra("haveASub",0);
                intent1.putExtra("haveAMulti",1);
                intent1.putExtra("haveADivide",0);
                intent1.putExtra("typeOfLevel",typeOfLevelMulti);
                startActivity(intent1);
                finish();
                break;
            case R.id.button6:
                Intent intent3 = new Intent(this, opertion.class);
                intent3.putExtra("lan",lan);
                intent3.putExtra("haveAAdd",0);
                intent3.putExtra("haveASub",0);
                intent3.putExtra("haveAMulti",0);
                intent3.putExtra("haveADivide",1);
                intent3.putExtra("typeOfLevel",typeOfLevelDivide);
                startActivity(intent3);
                finish();
                break;
            case R.id.button7:
                /*FireMissilesDialogFragment fireMissilesDialogFragment = new FireMissilesDialogFragment();
                fireMissilesDialogFragment.onStart();*/
                FirebaseAuth.getInstance().signOut();
                Intent intent4 = new Intent(this, MainActivity.class);
                intent4.putExtra("lan",lan);
                intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent4);
                finish();

                break;

            case R.id.button10 :
                Intent intent5 = new Intent(this, Test.class);
                intent5.putExtra("lan",lan);
                intent5.putExtra("userID",userID);
                startActivity(intent5);
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        openDialog();
    }

    public void shareText(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBodyText = "Your shearing message goes here";
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject/Title");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(intent, "Choose sharing method"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBodyText = "Check it out. Your message goes here";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(sharingIntent, "Shearing Option"));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openDialog() {
        informationDialog tamerDialog = new informationDialog();
        tamerDialog.show(getSupportFragmentManager(), "tamer dialog");
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
