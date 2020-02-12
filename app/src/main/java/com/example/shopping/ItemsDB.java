package com.example.shopping;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ItemsDB {
    //Field variables
    private static ItemsDB sItemsDB;
    private List<Item> itemsDB;

    //Constructor
    private ItemsDB(Context context) {
        itemsDB = new ArrayList<>();
    }

    //Methods
    public static ItemsDB get(Context context) {
        if (sItemsDB == null) {
            sItemsDB = new ItemsDB(context);
        }
        return sItemsDB;
    }

    public String listItems() {
        String r = "";
        for (int i = 0; i < itemsDB.size(); i++) {
            r = r + "\n Buy " + itemsDB.get(i).toString();
        }
        return r;
    }

    public void fillItemsDB(String what, String where) {
        itemsDB.add(new Item(what, where));
        itemsDB.add(new Item("coffee", "Irma"));
        itemsDB.add(new Item("carrots", "Netto"));
        itemsDB.add(new Item("milk", "Netto"));
        itemsDB.add(new Item("bread", "bakery"));
        itemsDB.add(new Item("butter", "Irma"));
    }

    public void deleteItem(String what) {
        for (Item item : itemsDB) {
            if (item.getWhat().equals(what.toLowerCase().trim())) {
                itemsDB.remove(item);
                break;
            }
        }
    }
}
