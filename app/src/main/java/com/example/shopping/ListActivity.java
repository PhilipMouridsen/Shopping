package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {

    private TextView items;
    private static ItemsDB itemsDB;
    private Button delete;
    private Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        itemsDB = ItemsDB.get(this);

        items = findViewById(R.id.items);
        items.setBackgroundColor(Color.parseColor("#FFFFFF"));
        items.setText("Shopping List:" + itemsDB.listItems());

        delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, DeleteActivity.class);
                startActivity(intent);
            }
        });

        back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, ShoppingActivity.class);
                startActivity(intent);
            }
        });
    }
}