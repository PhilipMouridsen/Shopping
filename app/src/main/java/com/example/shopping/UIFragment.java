package com.example.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class UIFragment extends Fragment {

    // GUI variables
    private TextView items;
    private EditText what;
    private EditText where;
    private Button listItems;
    private Button addNewItem;
    // Model: Database of items
    private static ItemsDB itemsDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemsDB = ItemsDB.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ui, container, false);

        what = v.findViewById(R.id.what_edit);
        where = v.findViewById(R.id.where_edit);

        listItems = v.findViewById(R.id.List_Button);
        listItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListActivity.class);
                startActivity(intent);
            }
        });
        addNewItem = v.findViewById(R.id.Add_Button);
        addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (what.getText().toString().trim().length() > 0 && where.getText().toString().trim().length() > 0) {
                    itemsDB.addItem(what.getText().toString(), where.getText().toString());
                    Toast.makeText(getActivity(), R.string.add_toast, Toast.LENGTH_SHORT).show();
                    what.getText().clear();
                    where.getText().clear();
                } else if (what.getText().toString().trim().length() == 0 || where.getText().toString().trim().length() == 0) {
                    Toast.makeText(getActivity(), R.string.none_toast, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
}
