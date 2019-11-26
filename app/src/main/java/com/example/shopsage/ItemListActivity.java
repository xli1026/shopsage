package com.example.shopsage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ItemListActivity extends AppCompatActivity {

    List list;
    int position;
    ItemListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        RecyclerView rvItems = (RecyclerView) findViewById(R.id.itemListView);
        Button newItemButton = (Button) findViewById(R.id.newItemButton);
        Button backButton = (Button) findViewById(R.id.backButton);
        TextView listName = (TextView) findViewById(R.id.listName);
        newItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewItemActivity.class);
                startActivityForResult(intent, 3);
            }
        }) ;
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                List list = adapter.getList();
                intent.putExtra("MyList", adapter.getList());
                intent.putExtra("position", position);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        list = (List) getIntent().getSerializableExtra("MyList");
        position = getIntent().getIntExtra("position", 0);
        listName.setText(list.getName());
        // Create adapter passing in the sample user data
        adapter = new ItemListAdapter(list);
        rvItems.setAdapter(adapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (3) : {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    String name = data.getStringExtra("item_name");
                    String price = data.getStringExtra("item_price");
                    String location = data.getStringExtra("item_location");
                    String category = data.getStringExtra("item_category");
                    adapter.add(name, price, location, category);
                }
                break;
            }
        }
    }
}
