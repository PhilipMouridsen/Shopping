package com.example.shopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shopping.database.ItemBaseHelper;
import com.example.shopping.database.ItemCursorWrapper;
import com.example.shopping.database.ItemsDbSchema;

import java.util.ArrayList;
import java.util.Observable;

public class ItemsDB extends Observable {
    //Field variables
    private static ItemsDB sItemsDB;
    private static SQLiteDatabase mDatabase;

    //Constructor
    private ItemsDB(Context context) {
        if (getItemsDB().size() == 0) {
            addItem("Peanut Butter", "Netto");
            addItem("æbler", "Netto");

        }
    }

    //Methods
    static private ItemCursorWrapper queryItems(String whereClause,
                                                String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ItemsDbSchema.ItemTable.NAME,
                null, // Columns - null selects all columns
                whereClause, whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new ItemCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Item item) {
        ContentValues values = new ContentValues();
        values.put(ItemsDbSchema.ItemTable.Cols.WHAT, item.getWhat());
        values.put(ItemsDbSchema.ItemTable.Cols.WHERE, item.getWhere());
        return values;
    }

    public static synchronized ItemsDB get(Context context) {
        if (sItemsDB == null) {
            mDatabase = new ItemBaseHelper(context.getApplicationContext()).getWritableDatabase();
            sItemsDB = new ItemsDB(context);
        }
        return sItemsDB;
    }

    public void addItem(String what, String where) {
        Item newItem = new Item(what, where);
        ContentValues values = getContentValues(newItem);
        mDatabase.insert(ItemsDbSchema.ItemTable.NAME, null, values);
        this.setChanged();
        notifyObservers();
    }

    public ArrayList<Item> getItemsDB() {
        ArrayList<Item> items = new ArrayList<Item>();
        ItemCursorWrapper cursor = queryItems(null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            items.add(cursor.getItem());
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }

    public void deleteItem(String what) {
        mDatabase.delete("Items", "what = ?", new String[]{what});
        this.setChanged();
        notifyObservers();
    }
}
