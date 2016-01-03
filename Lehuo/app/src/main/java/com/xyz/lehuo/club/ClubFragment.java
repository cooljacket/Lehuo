package com.xyz.lehuo.club;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xyz.lehuo.R;
import com.xyz.lehuo.bean.Club;
import com.xyz.lehuo.datebase.DatabaseManager;
import com.xyz.lehuo.global.Constant;
import com.xyz.lehuo.util.HttpUtil;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClubFragment extends Fragment {

    public static final String TAG = "ClubFragment";

    private SwipeRefreshLayout refreshLayout;
    private ListView list;
    private MyAdapter adapter;
    private List<Club> clubs;
    private DatabaseManager databaseManager = new DatabaseManager(getActivity());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club, container, false);
        initView(view);
        return view;
    }

    private void initView(View v) {
        refreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.refresh_layout);
        list = (ListView) v.findViewById(R.id.listview);
        clubs = new ArrayList<Club>();
        adapter = new MyAdapter(getActivity(), clubs);
        list.setAdapter(adapter);
        initData();
        initListener();
    }

    private void initData() {
        new HttpUtil().create(HttpUtil.POST, Constant.GET_ALL_CLUBS, new ArrayList<NameValuePair>(), new HttpUtil.HttpCallBallListener() {
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
                clubs = databaseManager.getAllClubs();
                adapter.setData(clubs);
            }

            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getInt("code") == 1) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject jo = data.optJSONObject(i);
                            Club club = new Club();
                            club.setName(jo.getString("name"));
                            club.setIntro(jo.getString("intro"));
                            club.setId(jo.getString("_id").substring(9, jo.getString("_id").length() - 2));
                            club.setImgUrl(jo.getString("img_url"));
                            clubs.add(club);
                        }
                        databaseManager.clearClubTable();
                        databaseManager.addClubs(clubs);
                        adapter.setData(clubs);
                    } else {
                        Toast.makeText(getActivity(), "服务器错误", Toast.LENGTH_SHORT).show();
                        clubs = databaseManager.getAllClubs();
                        adapter.setData(clubs);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initListener() {
        refreshLayout.setColorSchemeResources(android.R.color.holo_green_dark,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ClubDetailActivity.class);
                intent.putExtra("club", clubs.get(position));
                startActivity(intent);
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        Context context;
        List<Club> clubs;

        MyAdapter(Context context, List<Club> clubs) {
            this.context = context;
            this.clubs = clubs;
        }

        @Override
        public int getCount() {
            return clubs.size();
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
                convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_club, null);
                viewHolder = new ViewHolder();
                viewHolder.img = (SimpleDraweeView) convertView.findViewById(R.id.img);
                viewHolder.name = (TextView) convertView.findViewById(R.id.name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Uri uri = Uri.parse(clubs.get(position).getImgUrl());
            viewHolder.img.setImageURI(uri);
            viewHolder.name.setText(clubs.get(position).getName());
            return convertView;
        }

        class ViewHolder {
            SimpleDraweeView img;
            TextView name;
        }

        public void setData(List<Club> clubs) {
            this.clubs = clubs;
            this.notifyDataSetChanged();
        }
    }

}