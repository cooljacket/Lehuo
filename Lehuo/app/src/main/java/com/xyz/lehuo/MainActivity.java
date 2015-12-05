package com.xyz.lehuo;

import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.xyz.lehuo.global.BaseFragActivity;


public class MainActivity extends BaseFragActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SlidingMenu menu = new SlidingMenu(this);
    }


}
