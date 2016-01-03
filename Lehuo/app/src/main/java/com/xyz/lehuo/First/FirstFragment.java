package com.xyz.lehuo.first;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xyz.lehuo.R;
import com.xyz.lehuo.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xyz on 15/12/24.
 */
public class FirstFragment extends Fragment {

    private SwipeRefreshLayout refreshLayout;
    private ListView listView;
    private List<com.xyz.lehuo.bean.Activity> activities = new ArrayList<com.xyz.lehuo.bean.Activity>();
    private FirstAdapter adapter;
    private ViewPager vp;
    private View headView;
    private BannerAdapter bannerAdapter;
    private List<String> bannerUrls = new ArrayList<String>();
    private ProgressDialog pd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        initView(view);
        initListener();
        initData();
        return view;
    }

    private void initView(View v) {
        headView = LayoutInflater.from(getActivity()).inflate(R.layout.banner_headview, null);
        vp = (ViewPager) headView.findViewById(R.id.vp);
        refreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.refresh_layout);
        listView = (ListView) v.findViewById(R.id.list);
        listView.addHeaderView(headView);
        adapter = new FirstAdapter(activities, getActivity());
        listView.setAdapter(adapter);
    }

    private void initData() {
        activities.clear();
        for (int i = 0; i < 2; i++) {
            com.xyz.lehuo.bean.Activity activity = new com.xyz.lehuo.bean.Activity();
            activity.setTitle("奔跑奔跑｜奔跑奔跑奔跑奔跑奔跑奔跑");
            activity.setEndDate("12.25");
            activity.setReadNum(1000);
            activity.setOrganizer("中山大学东校区数据学院发展中心");
            activity.setImgUrl("http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg");
            activities.add(activity);
            bannerUrls.add("http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg");
        }
        bannerAdapter = new BannerAdapter(getActivity(), bannerUrls);
        vp.setAdapter(bannerAdapter);
        adapter.setData(activities);
    }

    private void initListener() {
        refreshLayout.setColorSchemeResources(android.R.color.holo_green_dark,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                        initData();
                    }
                }, 1000);
            }
        });

    }

    private void dealResult(String result) {

    }

    HttpUtil.HttpCallBallListener getData = new HttpUtil.HttpCallBallListener() {
        @Override
        public void onStart() {
            pd = ProgressDialog.show(getActivity(), "提示", "加载中，请稍后");
        }

        @Override
        public void onFinish() {
            pd.cancel();
        }

        @Override
        public void onError() {
            Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSuccess(String result) {
            dealResult(result);
        }
    };

    HttpUtil.HttpCallBallListener refreshData = new HttpUtil.HttpCallBallListener() {
        @Override
        public void onStart() {

        }

        @Override
        public void onFinish() {
            refreshLayout.setRefreshing(false);
        }

        @Override
        public void onError() {
            Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSuccess(String result) {
            dealResult(result);
        }
    };

    class BannerAdapter extends PagerAdapter {

        private Context context;
        private List<String> urls;
        private List<SimpleDraweeView> imageViews;

        public BannerAdapter(Context context, List<String> urls) {
            this.context = context;
            initUrls(urls);
        }

        void initUrls(List<String> urls) {
            this.urls = urls;
            imageViews = new ArrayList<SimpleDraweeView>();
            for (int i = 0; i < urls.size(); i++) {
                Uri uri = Uri.parse(urls.get(i));
                SimpleDraweeView view = new SimpleDraweeView(context, null, R.style.banner_image);
                view.setImageURI(uri);
                imageViews.add(view);
            }
        }

        @Override
        public int getCount() {
            return urls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            SimpleDraweeView image = imageViews.get(position);
            ViewParent vp = image.getParent();
            if (vp != null) {
                ((ViewGroup)vp).removeView(image);
            }
            container.addView(image);
            return image;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }

        public void setData(List<String> urls) {
            this.urls = urls;
            notifyDataSetChanged();
        }
    }
}
