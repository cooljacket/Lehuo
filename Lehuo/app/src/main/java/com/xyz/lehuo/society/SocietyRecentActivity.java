package com.xyz.lehuo.society;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.xyz.lehuo.R;
import com.xyz.lehuo.bean.Activity;
import com.xyz.lehuo.first.FirstAdapter;
import com.zy.zlistview.view.ZListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xyz on 15/12/30.
 */
public class SocietyRecentActivity extends Fragment {

    private List<Activity> allActivities = new ArrayList<Activity>();
    private android.app.Activity activity;
    private FirstAdapter adapter;
    private ZListView listView;

    @Override
    public void onAttach(android.app.Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_society_all, null);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initView(View view) {
        listView = (ZListView) view.findViewById(R.id.listview);
        ((TextView)view.findViewById(R.id.content)).setText("社团近期活动内容");
    }

    private void initData() {
        adapter = new FirstAdapter(allActivities, getActivity());
        listView.setAdapter(adapter);
    }

    private void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

}
