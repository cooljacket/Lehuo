package com.xyz.lehuo.first;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xyz.lehuo.R;
import com.xyz.lehuo.bean.Activity;

import java.util.List;

/**
 * Created by xyz on 15/12/25.
 */
public class FirstAdapter extends BaseAdapter {

    private List<Activity> activities;
    private Context context;

    public FirstAdapter(List<Activity> activities, Context context) {
        this.activities = activities;
        this.context = context;
    }

    @Override
    public int getCount() {
        return activities.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_listview_first, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.activity_title);
            viewHolder.endDate = (TextView) view.findViewById(R.id.end_date);
            viewHolder.readNum = (TextView) view.findViewById(R.id.read_num);
            viewHolder.organizer = (TextView) view.findViewById(R.id.organizer);
            viewHolder.endTime = (TextView) view.findViewById(R.id.end_time);
            viewHolder.img = (SimpleDraweeView) view.findViewById(R.id.activity_img);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(activities.get(i).getTitle());
        viewHolder.organizer.setText(activities.get(i).getOrganizer());
        viewHolder.readNum.setText(activities.get(i).getReadNum() + "");
        viewHolder.endDate.setText(activities.get(i).getEndDate());
        viewHolder.endTime.setText(activities.get(i).getEndTime());
        Uri uri = Uri.parse(activities.get(i).getImgUrl());
        viewHolder.img.setImageURI(uri);
        return view;
    }

    class ViewHolder {
        TextView title;
        TextView endDate;
        TextView endTime;
        TextView readNum;
        TextView organizer;
        SimpleDraweeView img;
    }

    public void addData(List<Activity> activities) {
        this.activities.addAll(activities);
        notifyDataSetChanged();
    }

    public void setData(List<Activity> activities) {
        this.activities = activities;
        notifyDataSetChanged();
    }
}
