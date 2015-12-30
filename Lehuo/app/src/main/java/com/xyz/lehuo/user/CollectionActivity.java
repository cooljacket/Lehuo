package com.xyz.lehuo.user;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.xyz.lehuo.R;
import com.xyz.lehuo.bean.Activity;
import com.xyz.lehuo.global.BaseActivity;
import com.zy.zlistview.view.ZListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xyz on 15/12/30.
 */
public class CollectionActivity extends BaseActivity {

    ZListView listView;
    List<Activity> activities = new ArrayList<Activity>();
    CollectionAdapter adapter = new CollectionAdapter(this, activities);
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        initView();
        initData();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        listView = (ZListView) findViewById(R.id.listview);
        listView.setXListViewListener(new ZListView.IXListViewListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });
        listView.setPullLoadEnable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        listView.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionActivity.this.finish();
            }
        });
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
}
