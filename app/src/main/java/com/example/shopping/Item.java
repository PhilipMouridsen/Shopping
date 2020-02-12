package com.example.shopping;

public class Item {
    private String mWhat = null;
    private String mWhere = null;

    public Item(String what, String where) {
        mWhat = what.toLowerCase().trim();
        mWhere = where.toLowerCase().trim();
    }

    @Override
    public String toString() {
        return oneLine("", " in: ");
    }

    public String getWhat() {
        return mWhat;
    }

    public void setWhat(String what) {
        mWhat = what;
    }

    public String getWhere() {
        return mWhere;
    }

    public void setWhere(String where) {
        mWhere = where;
    }

    public String oneLine(String pre, String post) {
        return pre + mWhat + post + mWhere;
    }
}
