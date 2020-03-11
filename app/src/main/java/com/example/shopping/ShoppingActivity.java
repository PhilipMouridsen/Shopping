package com.example.shopping;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class ShoppingActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragmentUI = fm.findFragmentById(R.id.container_ui_portrait);
        Fragment fragmentList = fm.findFragmentById(R.id.container_list);

        if (fragmentList == null) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                fragmentUI = new UIFragment();
                fragmentList = new ListFragment();
                fm.beginTransaction().add(R.id.container_ui_landscape, fragmentUI).add(R.id.container_list, fragmentList).commit();
            }
        }
        if (fragmentUI == null) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                fragmentUI = new UIFragment();
                fm.beginTransaction().add(R.id.container_ui_portrait, fragmentUI).commit();
            }
        }
    }
}