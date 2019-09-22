package com.tamer.child_math.Firebase;


import android.app.DownloadManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class FirebaseUtils  {

    public static final String KEY_FCM = "fcm";
    public static final String USERS = "Users";


    public static void updateFCM(Context context,String  token,  OnCompleteListener completeListener) {

        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        Log.d(TAG, "updateFCM:currentUser "+currentUser);
        Log.d(TAG, "updateFCM:token "+token);


        if (currentUser != null && token !=null) {
            Log.d(TAG, "updateFCM: "+currentUser.getUid());

            Map<String, Object> taskMap = new HashMap<>();
            taskMap.put(KEY_FCM, token);
            FirebaseDatabase.getInstance().getReference().child(USERS)
                    .child(String.valueOf(currentUser.getUid()))
                    .updateChildren(taskMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Log.d(TAG, "onComplete:updateFCM "+task.isSuccessful());

                    Log.d(TAG, "onComplete: updateFCM");
                }
            });

        }

    }

    public static void sendRequestToUser(final Context context, String  email) {

FirebaseDatabase.getInstance().getReference().child(USERS).addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        Log.d(TAG, "onDataChange:sendRequestToUser  "+dataSnapshot);
        boolean thereIsUser=false;
        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        for (DataSnapshot child:dataSnapshot.getChildren()){

            Log.d(TAG, "onDataChange: "+child.getKey()+ " currrent user "+ currentUserId);
            if (!child.getKey().equals(currentUserId)&&child.hasChildren()&&child.hasChild(KEY_FCM)){
                final String value = child.child(KEY_FCM).getValue(String.class);


                Log.d(TAG, "onDataChange:KEY_FCM  "+value);

                NotficationData notficationData =new NotficationData();

                notficationData.setContent("There is a new contest request");
                notficationData.setTitle("Request a game");
                notficationData.setId((int) System.currentTimeMillis());
                notficationData.setType(-1);
                notficationData.setReferId(currentUserId);
thereIsUser=true;


                sendNotification(value,notficationData);
                break;

            }
        }

        if (!thereIsUser)
            Toast.makeText(context, "user not exist", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});




    }
    public static void sendResponseToUser(final Context mContext, String referId , final int status) {

        FirebaseDatabase.getInstance()
                .getReference()
                .child(USERS).child(referId)
               .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()&&dataSnapshot.hasChild(KEY_FCM)){
                    final String value = dataSnapshot.child(KEY_FCM).getValue(String.class);


                    Log.d(TAG, "onDataChange:KEY_FCM  "+value);

                    String format=String.format("Application accepted" );
                    if (status!=1)
                        format=String.format( "reques has been rejected" );

                    NotficationData notficationData =new NotficationData();
                    notficationData.setContent(format);
                    notficationData.setTitle("Request a game");
                    notficationData.setId((int) System.currentTimeMillis());
                    notficationData.setType(status);
                    notficationData.setReferId(FirebaseAuth.getInstance().getCurrentUser().getUid());



                    sendNotification(value,notficationData);


                }else {
                    Toast.makeText(mContext, "user not exist", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }



    public static void sendNotification(final String to, final NotficationData notficationData) {

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    OkHttpClient client = new OkHttpClient();
                    JSONObject json=new JSONObject();
                    JSONObject dataJson=new JSONObject();
                    dataJson.put("body",notficationData.getContent());
                    dataJson.put("title",notficationData.getTitle());
                    dataJson.put("referId",notficationData.getReferId());
                    dataJson.put("type",notficationData.getType());
                    json.put("notification",dataJson);
                    json.put("data",dataJson);
                    json.put("to",to);
                    RequestBody body = RequestBody.create(JSON, json.toString());
                    Request request = new Request.Builder()
                            .header("Authorization","key=AIzaSyBGNh8pXgAritVtW9zsJ72usnQzOfRmSEI")
                            .url("https://fcm.googleapis.com/fcm/send")
                            .post(body)
                            .build();
                    Response response = client.newCall(request).execute();
                    String finalResponse = response.body().string();

                }catch (Exception e){
                    //Log.d(TAG,e+"");
                }
                return null;
            }
        }.execute();



    }
}