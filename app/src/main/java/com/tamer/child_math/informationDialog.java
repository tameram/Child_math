package com.tamer.child_math;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import static android.content.ContentValues.TAG;
// get inforemation from firebase --> problem is conect string to dialog
public class informationDialog extends AppCompatDialogFragment{

    FirebaseDatabase database;
    DatabaseReference myRef ;
    String value ;
    String [] values = {"The numerical digits we use today such as 1, 2 and 3 are based on the Hindu-Arabic numeral system developed over 1000 years ago.",
            "Different names for the number 0 include zero, nought, naught, nil, zilch and zip.",
            "The smallest ten prime numbers are: 2, 3, 5, 7, 11, 13, 17, 19, 23 and 29.",
            "2 and 5 are the only prime numbers that end with a 2 or a 5.",
            "The golden ratio of approximately 1.618 between two quantities such as lengths often appears in nature (tree branching, uncurling ferns, pine cone arrangements etc) and has been used throughout history to create aesthetically pleasing designs and art works such as Leonardo da Vinci’s Mona Lisa.",
            "Here is Pi written to 100 decimal places:\n" +
                    "3.1415926535897932384626433832795028841971693993751" +
                    "058209749445923078164062862089986280348253421170679",
            "What comes after a million, billion and trillion? A quadrillion, quintillion, sextillion, septillion, octillion, nonillion, decillion and undecillion.",
            "The name of the popular search engine ‘Google’ came from a misspelling of the word ‘googol’, which is a very large number (the number one followed by one hundred zeros to be exact).",
            "The number 142857 is rather special. When you multiply it the digits found in the answer stay the same, just in a different order",
            "One centillion (the number 1 followed by 303 zeros)",
            "111111111 x 111111111 = 12345678987654321",
            "12 + 3 - 4 + 5 + 67 + 8 + 9 = 100"};
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("information").child("1");
        Random r=new Random();
        int randomNumber=r.nextInt(values.length);
        value = values[randomNumber];
        //readFromForebase();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Information")
                .setMessage(value)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }

    public void readFromForebase(){
// Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


}
