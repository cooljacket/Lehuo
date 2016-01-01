package com.xyz.lehuo.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.xyz.lehuo.R;
import com.xyz.lehuo.bean.Activity;
import com.xyz.lehuo.first.FirstAdapter;
import com.zy.zlistview.view.ZListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xyz on 15/12/24.
 */
public class DiscoverFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup typeGroup;
    private ZListView list;
    private List<Activity> activities = new ArrayList<Activity>();
    private FirstAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initView(View v) {
        typeGroup = (RadioGroup) v.findViewById(R.id.type_group);
        typeGroup.setOnCheckedChangeListener(this);
        list = (ZListView) v.findViewById(R.id.listview);
        adapter = new FirstAdapter(activities, getActivity());
        list.setAdapter(adapter);
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.display:
                Toast.makeText(getActivity(), "展览", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lecture:
                break;
            case R.id.recruit:
                break;
            case R.id.benefit:
                break;
        }
    }
}
