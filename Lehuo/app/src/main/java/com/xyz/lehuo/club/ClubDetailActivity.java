package com.xyz.lehuo.club;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xyz.lehuo.R;
import com.xyz.lehuo.bean.Activity;
import com.xyz.lehuo.bean.Club;
import com.xyz.lehuo.global.BaseFragActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xyz on 15/12/30.
 */
public class ClubDetailActivity extends BaseFragActivity implements View.OnClickListener {

    private ViewPager vp;
    private ImageView back;
    private SimpleDraweeView societyLogo;
    private TextView name;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private ClubIntroFragment introFragment;
    private ClubAllFragment allActivityFragment;
    private ClubAllFragment recentActivityFragment;
    private FragmentPagerAdapter adapter;
    private List<TextView> tvIndicators = new ArrayList<TextView>();
    private List<View> vIndicators = new ArrayList<View>();
    private Club club;

    private ProgressDialog pd;

    String intro = "";
    List<Activity> allActivities = new ArrayList<Activity>();
    List<Activity> recentActivities = new ArrayList<Activity>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_detail);
        initData();
        initView();
    }

    private void initData() {
        introFragment = new ClubIntroFragment();
        allActivityFragment = new ClubAllFragment();
        recentActivityFragment = new ClubAllFragment();
        fragments.add(introFragment);
        fragments.add(allActivityFragment);
        fragments.add(recentActivityFragment);
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        club = (Club) getIntent().getSerializableExtra("club");

    }

    private void initView() {
        societyLogo = (SimpleDraweeView) findViewById(R.id.club_img);
        societyLogo.setImageURI(Uri.parse(club.getImgUrl()));
        name = (TextView) findViewById(R.id.club_name);
        name.setText(club.getName());
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setOffscreenPageLimit(3);
        LinearLayout llOne = (LinearLayout) findViewById(R.id.intro_ll);
        LinearLayout llTwo = (LinearLayout) findViewById(R.id.all_ll);
        LinearLayout llThree = (LinearLayout) findViewById(R.id.recent_ll);
        TextView tvOne = (TextView) findViewById(R.id.intro);
        TextView tvTwo = (TextView) findViewById(R.id.all);
        TextView tvThree = (TextView) findViewById(R.id.recent);
        View vOne = findViewById(R.id.intro_line);
        View vTwo = findViewById(R.id.all_line);
        View vThree = findViewById(R.id.recent_line);
        tvIndicators.add(tvOne);
        tvIndicators.add(tvTwo);
        tvIndicators.add(tvThree);
        vIndicators.add(vOne);
        vIndicators.add(vTwo);
        vIndicators.add(vThree);
        llOne.setOnClickListener(this);
        llTwo.setOnClickListener(this);
        llThree.setOnClickListener(this);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setIndicators(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setAdapter(adapter);
    }

    private void setIndicators(int pos) {
        switch (pos) {
            case 0:
                tvIndicators.get(0).setTextColor(Color.parseColor("#222222"));
                vIndicators.get(0).setVisibility(View.VISIBLE);
                tvIndicators.get(1).setTextColor(Color.parseColor("#14aa81"));
                vIndicators.get(1).setVisibility(View.INVISIBLE);
                tvIndicators.get(2).setTextColor(Color.parseColor("#14aa81"));
                vIndicators.get(2).setVisibility(View.INVISIBLE);
                break;
            case 1:
                tvIndicators.get(0).setTextColor(Color.parseColor("#14aa81"));
                vIndicators.get(0).setVisibility(View.INVISIBLE);
                tvIndicators.get(1).setTextColor(Color.parseColor("#222222"));
                vIndicators.get(1).setVisibility(View.VISIBLE);
                tvIndicators.get(2).setTextColor(Color.parseColor("#14aa81"));
                vIndicators.get(2).setVisibility(View.INVISIBLE);
                break;
            case 2:
                tvIndicators.get(0).setTextColor(Color.parseColor("#14aa81"));
                vIndicators.get(0).setVisibility(View.INVISIBLE);
                tvIndicators.get(1).setTextColor(Color.parseColor("#14aa81"));
                vIndicators.get(1).setVisibility(View.INVISIBLE);
                tvIndicators.get(2).setTextColor(Color.parseColor("#222222"));
                vIndicators.get(2).setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.intro_ll:
                vp.setCurrentItem(0, false);
                setIndicators(0);
                break;
            case R.id.all_ll:
                vp.setCurrentItem(1, false);
                setIndicators(1);
                break;
            case R.id.recent_ll:
                vp.setCurrentItem(2, false);
                setIndicators(2);
                break;
        }
    }
}