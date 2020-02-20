package com.example.shopping;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ItemUnitTest {
    Item t;

    @Test
    public void itemsDBTest(){
        t = new Item("aa", "bb");
        assertEquals(t.getWhat(), "aa");
        assertEquals(t.getWhere(), "bb");
    }
}