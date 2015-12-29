package com.xyz.lehuo.user;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xyz.lehuo.R;
import com.xyz.lehuo.global.BaseActivity;

/**
 * Created by xyz on 15/12/29.
 */
public class UserInfoActivity extends BaseActivity {

    private ImageView back;
    private TextView modify;
    private RelativeLayout logolLayout;
    private SimpleDraweeView userLogol;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
    }

    private void initView() {

    }
}
