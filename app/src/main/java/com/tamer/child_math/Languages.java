package com.tamer.child_math;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Languages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);

        String[] ninjaList = {"English", "Hebrew", "Arabic"};

        ListAdapter theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ninjaList);

        ListView theListView = (ListView) findViewById(R.id.theListViewLanduages);

        theListView.setAdapter(theAdapter);
    }
}
