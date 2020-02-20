package com.example.shopping;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemsDBUnitTest {

    private static ItemsDB IDB;
    private Context context;

    public ItemsDBUnitTest() {
        context = null;
        IDB = ItemsDB.get(context);
        IDB.fillItemsDB("mad", "butik");
        IDB.fillItemsDB("Bananer", "Netto");
        IDB.fillItemsDB("Peanut Butter (meget vigtigt)", "Wherever");
    }

    @Test
    public void deleteItemTest() {
        IDB.deleteItem("mad");
        assertEquals(IDB.listItems(),
                "\n Buy bananer in: netto" +
                        "\n Buy peanut butter (meget vigtigt) in: wherever");
    }
}
