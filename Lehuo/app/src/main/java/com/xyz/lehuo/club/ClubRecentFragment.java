package com.xyz.lehuo.club;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xyz.lehuo.R;
import com.xyz.lehuo.WebActivity;
import com.xyz.lehuo.bean.Activity;
import com.xyz.lehuo.first.FirstAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xyz on 15/12/30.
 */
public class ClubRecentFragment extends Fragment {

    public static final String TAG = "ClubRecentFragment";

    private List<Activity> allActivities = new ArrayList<Activity>();
    private FirstAdapter adapter;
    private ListView listView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_all, null);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
    }

    private void initData() {
        adapter = new FirstAdapter(allActivities, getActivity());
        listView.setAdapter(adapter);
    }

    private void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("activity", allActivities.get(position));
                startActivity(intent);
            }
        });
    }

    public void setData(List<Activity> activities) {
        allActivities = activities;
        adapter.setData(activities);
        Log.i(TAG, "setData");
    }

}
