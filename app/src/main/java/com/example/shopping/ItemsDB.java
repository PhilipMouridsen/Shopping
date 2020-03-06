package com.example.shopping;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ItemsDB extends Observable {
    //Field variables
    private static ItemsDB sItemsDB;
    private List<Item> itemsDB;

    //Constructor
    private ItemsDB(Context context) {
        itemsDB = new ArrayList<>();
    }

    //Methods
    public static synchronized ItemsDB get(Context context) {
        if (sItemsDB == null) {
            sItemsDB = new ItemsDB(context);
            sItemsDB.fillItemsDB("banan", "netto");
            sItemsDB.fillItemsDB("æble", "netto");
            sItemsDB.fillItemsDB("Peanut Butter", "netto");

        }
        return sItemsDB;
    }

    public synchronized String listItems() {
        String r = "";
        for (int i = 0; i < itemsDB.size(); i++) {
            r = r + "\n Buy " + itemsDB.get(i).toString();
        }
        return r;
    }

    public synchronized void fillItemsDB(String what, String where) {
        itemsDB.add(new Item(what, where));
        this.setChanged();
        notifyObservers();
    }

    public synchronized List<Item> getItemsDB() {
        return itemsDB;
    }

    public synchronized void deleteItem(String what) {
        for (Item item : itemsDB) {
            if (item.getWhat().equals(what.toLowerCase().trim())) {
                itemsDB.remove(item);
                break;
            }
        }
        this.setChanged();
        notifyObservers();
    }

    public synchronized int getSize() {
        return itemsDB.size();
    }

    public synchronized void emptyList(){
        itemsDB.clear();
    }
}
