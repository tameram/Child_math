package com.tamer.child_math;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FireBaseDataBaseHelperForQuestions {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private List<questionInFireBase> questionInFireBaseList = new ArrayList<>();

    public FireBaseDataBaseHelperForQuestions() {
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("Questions");

    }

    public interface DataStatus{
        void DataIsLoaded(List<questionInFireBase> questionInFireBaseList, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public void readQuestion(final DataStatus dataStatus){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questionInFireBaseList.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    questionInFireBase question = keyNode.getValue(questionInFireBase.class);
                    questionInFireBaseList.add(question);
                }
                dataStatus.DataIsLoaded(questionInFireBaseList,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void addQuestion(questionInFireBase questionInFireBase , final DataStatus dataStatus){
        String key = mReference.push().getKey();
        mReference.child(key).setValue(questionInFireBase).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }
}

