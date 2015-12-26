package com.xyz.lehuo;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.xyz.lehuo.First.FirstFragment;
import com.xyz.lehuo.bean.User;
import com.xyz.lehuo.discover.DiscoverFragment;
import com.xyz.lehuo.global.BaseFragActivity;
import com.xyz.lehuo.global.Conf;
import com.xyz.lehuo.global.MyApplication;
import com.xyz.lehuo.society.SocietyFragment;
import com.xyz.lehuo.user.LoginActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseFragActivity {

    List<Fragment> fragments = new ArrayList<Fragment>();
    FirstFragment firstFragment;
    DiscoverFragment discoverFragment;
    SocietyFragment societyFragment;

    List<ImageView> ivIndicators = new ArrayList<ImageView>();
    List<TextView> tvIndicators = new ArrayList<TextView>();

    SimpleDraweeView mainUserLogol;
    ImageView scan;
    TextView titleText;

    SlidingMenu menu;
    SlidingMenu.CanvasTransformer mTransformer;
    TextView username;
    RelativeLayout userLayout;
    SimpleDraweeView userLogol;
    RelativeLayout colLayout;
    RelativeLayout focusLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        initSlidingMenu();
        initView();
        initFragment();
    }

    private void initView() {
        mainUserLogol = (SimpleDraweeView) findViewById(R.id.user_img);
        scan = (ImageView) findViewById(R.id.scan);
        titleText = (TextView) findViewById(R.id.title_text);
        mainUserLogol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.toggle();
            }
        });
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "main", Toast.LENGTH_SHORT).show();
            }
        });
        String url =  "http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg";
        Uri uri = Uri.parse(url);
        mainUserLogol.setImageURI(uri);

        TextView tvFirst = (TextView) findViewById(R.id.first_tv);
        ImageView ivFirst = (ImageView) findViewById(R.id.first);
        TextView tvSociety = (TextView) findViewById(R.id.society_tv);
        ImageView ivSociety = (ImageView) findViewById(R.id.society);
        TextView tvDiscover = (TextView) findViewById(R.id.discover_tv);
        ImageView ivDiscover = (ImageView) findViewById(R.id.discover);
        ivIndicators.add(ivFirst);
        ivIndicators.add(ivSociety);
        ivIndicators.add(ivDiscover);
        tvIndicators.add(tvFirst);
        tvIndicators.add(tvSociety);
        tvIndicators.add(tvDiscover);

        LinearLayout firstLayout = (LinearLayout) findViewById(R.id.first_layout);
        LinearLayout societyLayout = (LinearLayout) findViewById(R.id.society_layout);
        LinearLayout discoverLayout = (LinearLayout) findViewById(R.id.discover_layout);
        firstLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTab(0);
            }
        });
        societyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTab(1);
            }
        });
        discoverLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTab(2);
            }
        });
    }

    private void initFragment() {
        fragments = new ArrayList<Fragment>();
        if (firstFragment == null) {
            firstFragment = new FirstFragment();
        }
        if (discoverFragment == null) {
            discoverFragment = new DiscoverFragment();
        }
        if (societyFragment == null) {
            societyFragment = new SocietyFragment();
        }
        fragments.add(firstFragment);
        fragments.add(discoverFragment);
        fragments.add(societyFragment);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if (!fragments.get(i).isAdded()) {
                ft.add(R.id.content, fragments.get(i));
            }
            if (i == 0) {
                ft.show(fragments.get(i));
            } else {
                ft.hide(fragments.get(i));
            }
        }
        ft.commit();

    }

    private void showTab(int idx) {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (i == idx) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
            ft.commit();
        }
        switch (idx) {
            case 0:
                ivIndicators.get(0).setImageResource(R.mipmap.first_press);
                tvIndicators.get(0).setTextColor(Color.parseColor("#5479b8"));
                ivIndicators.get(1).setImageResource(R.mipmap.society);
                tvIndicators.get(1).setTextColor(Color.parseColor("#a2a3a3"));
                ivIndicators.get(2).setImageResource(R.mipmap.discover);
                tvIndicators.get(2).setTextColor(Color.parseColor("#a2a3a3"));
                titleText.setText("抢鲜看");
                break;
            case 1:
                ivIndicators.get(0).setImageResource(R.mipmap.first);
                tvIndicators.get(0).setTextColor(Color.parseColor("#a2a3a3"));
                ivIndicators.get(1).setImageResource(R.mipmap.society_press);
                tvIndicators.get(1).setTextColor(Color.parseColor("#5479b8"));
                ivIndicators.get(2).setImageResource(R.mipmap.discover);
                tvIndicators.get(2).setTextColor(Color.parseColor("#a2a3a3"));
                titleText.setText("社团");
                break;
            case 2:
                ivIndicators.get(0).setImageResource(R.mipmap.first);
                tvIndicators.get(0).setTextColor(Color.parseColor("#a2a3a3"));
                ivIndicators.get(1).setImageResource(R.mipmap.society);
                tvIndicators.get(1).setTextColor(Color.parseColor("#a2a3a3"));
                ivIndicators.get(2).setImageResource(R.mipmap.discover_press);
                tvIndicators.get(2).setTextColor(Color.parseColor("#5479b8"));
                titleText.setText("发现");
                break;
            default:
                break;
        }

    }

    private void initSlidingMenu() {
        menu = new SlidingMenu(this);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        menu.setFadeDegree(0.35f);
        menu.setMenu(R.layout.activity_user);
        mTransformer = new SlidingMenu.CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen) {
                float scale = (float) (percentOpen*0.25 + 0.75);
                canvas.scale(scale, scale, canvas.getWidth()/2, canvas.getHeight()/2);
            }
        };
        menu.setBehindCanvasTransformer(mTransformer);
        userLayout = (RelativeLayout) menu.findViewById(R.id.user_logol_layout);
        username = (TextView) menu.findViewById(R.id.user_name);
        userLogol = (SimpleDraweeView) menu.findViewById(R.id.user_logol);
        colLayout = (RelativeLayout) menu.findViewById(R.id.col_layout);
        focusLayout = (RelativeLayout) menu.findViewById(R.id.focus_layout);
        initUserData();
    }

    private void initUserData() {
        if (Conf.isLogin == true) {
            User user = ((MyApplication)getApplication()).getUser();
            username.setText(user.getName());

        } else {
            userLogol.setImageResource(R.mipmap.mine);
            username.setText("未登录");
            userLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });
            colLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
            });
            focusLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
