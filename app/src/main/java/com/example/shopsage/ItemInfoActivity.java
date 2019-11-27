package com.example.shopsage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ItemInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        // Get components

        final EditText textName = findViewById(R.id.editName);
        final EditText textPrice = findViewById(R.id.editPrice);
        final EditText textLocation = findViewById(R.id.editLocation);
        final EditText textCategory = findViewById(R.id.editCategory);
        Button editButton = findViewById(R.id.editItemButton);

        Item item = (Item) getIntent().getSerializableExtra("MyItem");
        final int position = getIntent().getIntExtra("position", 0);

        textName.setText(item.getName());
        textPrice.setText(item.getPrice());
        textLocation.setText(item.getLocation());
        textCategory.setText(item.getCategory());
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = textName.getText().toString();
                String price = textPrice.getText().toString();
                String location = textLocation.getText().toString();
                String category = textCategory.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("item_name", name);
                intent.putExtra("item_price", price);
                intent.putExtra("item_location", location);
                intent.putExtra("item_category", category);
                intent.putExtra("position", position);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
    }
