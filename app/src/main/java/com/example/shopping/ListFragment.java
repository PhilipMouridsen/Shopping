package com.example.shopping;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Observable;
import java.util.Observer;

public class ListFragment extends Fragment implements Observer {

    private TextView items;
    private Button delete;
    private Button back_button;

    private static ItemsDB itemsDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemsDB = ItemsDB.get(getActivity());
        itemsDB.addObserver(this);
    }

    public void update(Observable observable, Object data) {
        items.setText("Shopping List"+itemsDB.listItems());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        items = v.findViewById(R.id.items);
        items.setBackgroundColor(Color.parseColor("#FFFFFF"));
        items.setText("Shopping List:" + itemsDB.listItems());

        delete = v.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DeleteActivity.class);
                startActivity(intent);
            }
        });

        back_button = v.findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShoppingActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
