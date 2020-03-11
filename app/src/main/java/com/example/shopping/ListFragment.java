package com.example.shopping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ListFragment extends Fragment implements Observer {

    private Button delete;
    private RecyclerView mShoppingRecyclerView;
    private ItemAdapter mAdapter;

    private static ItemsDB itemsDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemsDB = ItemsDB.get(getActivity());
        itemsDB.addObserver(this);
    }

    public void update(Observable observable, Object data) {
        updateUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        mShoppingRecyclerView = (RecyclerView) v.findViewById(R.id.shopping_recycler_view);
        mShoppingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }

    private void updateUI() {
        List<Item> items = itemsDB.getItemsDB();
        mAdapter = new ItemAdapter(items);
        mShoppingRecyclerView.setAdapter(mAdapter);
    }

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mWhatTextView, mWhereTextView, mNoView, mDeleteButton;

        public ItemHolder(View itemView) {
            super(itemView);
            mNoView = itemView.findViewById(R.id.item_no);
            mWhatTextView = itemView.findViewById(R.id.item_what);
            mWhereTextView = itemView.findViewById(R.id.item_where);
            mDeleteButton = itemView.findViewById(R.id.item_delete);

            mDeleteButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String item = mWhatTextView.getText().toString().replace("item:", "").trim();
            itemsDB.deleteItem(item);
        }

        public void bind(Item item, int position) {
            mNoView.setText(" " + position + " ");
            mWhatTextView.setText(item.getWhat());
            mWhereTextView.setText(item.getWhere());
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
        private List<Item> mItems;

        public ItemAdapter(List<Item> data) {
            mItems = data;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.one_row, parent, false);
            return new ItemHolder(v);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            final Item item = mItems.get(position);
            holder.bind(item, position);
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }
}
