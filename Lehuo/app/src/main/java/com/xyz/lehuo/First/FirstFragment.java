package com.xyz.lehuo.First;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.xyz.lehuo.R;
import com.xyz.lehuo.view.MySwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xyz on 15/12/24.
 */
public class FirstFragment extends Fragment {

    private MySwipeRefreshLayout refreshLayout;
    private ListView listView;
    private List<com.xyz.lehuo.bean.Activity> activities = new ArrayList<com.xyz.lehuo.bean.Activity>();
    private FirstAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initView(View v) {
        refreshLayout = (MySwipeRefreshLayout) v.findViewById(R.id.refresh_layout);
        listView = (ListView) v.findViewById(R.id.list);
        refreshLayout.setListView(listView);
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
        }
        adapter.setData(activities);
    }

    private void addData() {
        for (int i = 0; i < 1; i++) {
            com.xyz.lehuo.bean.Activity activity = new com.xyz.lehuo.bean.Activity();
            activity.setTitle("奔跑奔跑｜奔跑奔跑奔跑奔跑奔跑奔跑");
            activity.setEndDate("12.25");
            activity.setReadNum(1000);
            activity.setOrganizer("中山大学东校区数据学院发展中心");
            activity.setImgUrl("http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg");
            activities.add(activity);
        }
        adapter.addData(activities);
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

        refreshLayout.setOnLoadListener(new MySwipeRefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (activities.size() > 10) {
                            Toast.makeText(getActivity(), "no more data", Toast.LENGTH_SHORT).show();
                            refreshLayout.setLoading(false);
                            return;
                        }
                        addData();
                        refreshLayout.setLoading(false);
                    }
                }, 3000);
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
