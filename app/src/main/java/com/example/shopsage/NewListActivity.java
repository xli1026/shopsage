package com.example.shopsage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        Button createButton = (Button) findViewById(R.id.createListButton);
        createButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText textbox = findViewById(R.id.editName);
                String name = textbox.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("newlist", name);
                System.out.println("list created name is " + name);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
