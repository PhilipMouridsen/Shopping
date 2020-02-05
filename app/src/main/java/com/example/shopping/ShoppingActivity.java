package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingActivity extends AppCompatActivity {
    // GUI variables
    private Button listItems;
    private Button addNewItem;
    private TextView items;
    private EditText what;
    private EditText where;
    // Model: Database of items
    private ItemsDB itemsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemsDB = new ItemsDB();

        items = findViewById(R.id.items);
        what = findViewById(R.id.what_edit);
        where = findViewById(R.id.where_edit);


        listItems = findViewById(R.id.List_Button);
        listItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.setBackgroundColor(Color.parseColor("#FFFFFF"));
                items.setText("Shopping List:" + itemsDB.listItems());
            }
        });
        addNewItem = findViewById(R.id.Add_Button);
        addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (what.getText().toString().trim().length() > 0 && where.getText().toString().trim().length() > 0) {
                    itemsDB.fillItemsDB(what.getText().toString(), where.getText().toString());
                    Toast.makeText(ShoppingActivity.this, R.string.add_toast, Toast.LENGTH_SHORT).show();
                    what.getText().clear();
                    where.getText().clear();
                } else if (what.getText().toString().trim().length() == 0 || where.getText().toString().trim().length() == 0) {
                    Toast.makeText(ShoppingActivity.this, R.string.none_toast, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}