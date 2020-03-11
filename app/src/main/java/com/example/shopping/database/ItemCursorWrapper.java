package com.example.shopping.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.shopping.Item;

public class ItemCursorWrapper extends CursorWrapper {
    public ItemCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Item getItem() {
        String what = getString(getColumnIndex(ItemsDbSchema.ItemTable.Cols.WHAT));
        String where = getString(getColumnIndex(ItemsDbSchema.ItemTable.Cols.WHERE));
        return new Item(what, where);
    }
}