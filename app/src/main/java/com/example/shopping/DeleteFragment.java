package com.example.shopping;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class DeleteFragment extends Fragment {

    private EditText what_delete;
    private TextView items;
    private Button back_button;
    private Button delete;

    private static ItemsDB itemsDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemsDB = ItemsDB.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_delete, container, false);
        what_delete = v.findViewById(R.id.delete_text);

        items = v.findViewById(R.id.items);
        items.setBackgroundColor(Color.parseColor("#FFFFFF"));
        items.setText("Shopping List:" + itemsDB.listItems());

        back_button = v.findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListActivity.class);
                startActivity(intent);
            }
        });

        delete = v.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int listSize = itemsDB.getSize();
                itemsDB.deleteItem(what_delete.getText().toString());
                if (itemsDB.getSize() == listSize) {
                    Toast.makeText(getActivity(), R.string.no_delete_toast, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), R.string.delete_toast, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), ListActivity.class);
                    startActivity(intent);
                }

            }
        });

        return v;
    }
}