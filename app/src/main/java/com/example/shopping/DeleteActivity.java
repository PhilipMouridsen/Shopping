package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DeleteActivity extends AppCompatActivity {

    private EditText what_delete;
    private Button delete;
    private static ItemsDB itemsDB;
    private TextView items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        itemsDB = ItemsDB.get(this);

        what_delete = findViewById(R.id.delete_text);

        items = findViewById(R.id.items);
        items.setBackgroundColor(Color.parseColor("#FFFFFF"));
        items.setText("Shopping List:" + itemsDB.listItems());

        delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsDB.deleteItem(what_delete.getText().toString());
                Intent intent = new Intent(DeleteActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

    }
}
