package com.xyz.lehuo.society;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xyz.lehuo.R;
import com.xyz.lehuo.bean.Society;

import java.util.ArrayList;
import java.util.List;

public class SocietyFragment extends Fragment {

    SwipeRefreshLayout refreshLayout;
    ListView list;
    MyAdapter adapter;
    List<Society> societies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_society, container, false);
        initView(view);
        return view;
    }

    private void initView(View v) {
        refreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.refresh_layout);
        list = (ListView) v.findViewById(R.id.listview);
        initData();
        initListener();
    }

    private void initData() {
        societies = new ArrayList<Society>();
        for (int i = 0; i < 2; i++) {
            Society society = new Society();
            society.setImgUrl("http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg");
            society.setName("社团");
            societies.add(society);
        }
        adapter = new MyAdapter(getActivity(), societies);
        list.setAdapter(adapter);
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
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class MyAdapter extends BaseAdapter {

        Context context;
        List<Society> societies;

        MyAdapter(Context context, List<Society> societies) {
            this.context = context;
            this.societies = societies;
        }

        @Override
        public int getCount() {
            return societies.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_society, null);
                viewHolder = new ViewHolder();
                viewHolder.img = (SimpleDraweeView) convertView.findViewById(R.id.img);
                viewHolder.name = (TextView) convertView.findViewById(R.id.name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Uri uri = Uri.parse(societies.get(position).getImgUrl());
            viewHolder.img.setImageURI(uri);
            viewHolder.name.setText(societies.get(position).getName());
            return convertView;
        }

        class ViewHolder {
            SimpleDraweeView img;
            TextView name;
        }
    }

}