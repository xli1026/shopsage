package com.example.shopsage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        Button createButton = (Button) findViewById(R.id.createItemButton);
        createButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText textName = findViewById(R.id.editName);
                EditText textPrice = findViewById(R.id.editPrice);
                EditText textLocation = findViewById(R.id.editLocation);
                EditText textCategory = findViewById(R.id.editCategory);
                String name = textName.getText().toString();
                String price = textPrice.getText().toString();
                String location = textLocation.getText().toString();
                String category = textCategory.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("item_name", name);
                intent.putExtra("item_price", price);
                intent.putExtra("item_location", location);
                intent.putExtra("item_category", category);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
