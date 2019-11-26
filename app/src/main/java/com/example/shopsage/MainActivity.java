package com.example.shopsage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
This

 */


public class MainActivity extends AppCompatActivity {

    ArrayList<List> lists;
    private final String KEY_RECYCLER_STATE = "recycler_state";
    private RecyclerView mRecyclerView;
    private static Bundle mBundleRecyclerViewState;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This will set the content view
        setContentView(R.layout.activity_main);
        RecyclerView rvLists = (RecyclerView) findViewById(R.id.listView);
        Button newListButton = (Button) findViewById(R.id.newListButton);
        newListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewListActivity.class);
                startActivityForResult(intent, 2);
            }
        }) ;

        // Initialize list
        if(lists == null) {
            lists = new ArrayList<List>();
        }


        // Create adapter passing in the sample user data
        adapter = new ListAdapter(lists, this);
        // Attach the adapter to the recyclerview to populate items
        rvLists.setAdapter(adapter);
        // Set layout manager to position the items
        rvLists.setLayoutManager(new LinearLayoutManager(this));
        // That's all!

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (2) : {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    String returnValue = data.getStringExtra("newlist");
                    adapter.add(returnValue);
                }
                break;
            }
            case (3) : {
                if (resultCode == Activity.RESULT_OK) {
                    System.out.println("Inside case 3");
                    List list = (List) data.getSerializableExtra("MyList");
                    int position = data.getIntExtra("position", 0);
                    adapter.add(list, position);
                }
            }
        }
    }
}
