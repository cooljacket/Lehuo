package com.xyz.lehuo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xyz.lehuo.global.BaseFragActivity;
import com.xyz.lehuo.util.HttpUtil;


public class MainActivity extends BaseFragActivity {

    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SlidingMenu menu = new SlidingMenu(this);
        btn = (Button) findViewById(R.id.test);
        tv = (TextView) findViewById(R.id.text);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpUtil().create(HttpUtil.GET, "http://42.121.129.102:8001/?r=user/prelogin", null, new HttpUtil.HttpCallBallListener() {
                    @Override
                    public void onStart() {
                        Toast.makeText(MainActivity.this, "Start", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError() {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(String result) {
                        tv.setText(result);
                    }
                });
            }
        });
    }


}
