package com.xyz.lehuo.user;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xyz.lehuo.R;
import com.xyz.lehuo.bean.Activity;
import com.zy.zlistview.adapter.BaseSwipeAdapter;
import com.zy.zlistview.enums.DragEdge;
import com.zy.zlistview.enums.ShowMode;
import com.zy.zlistview.view.ZSwipeItem;

import java.util.List;

/**
 * Created by xyz on 15/12/30.
 */
public class CollectionAdapter extends BaseSwipeAdapter {

    private Context context;
    private List<Activity> activities;

    public CollectionAdapter(Context context, List<Activity> activities) {
        this.context = context;
        this.activities = activities;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_item;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_listview_collection, parent, false);
    }

    @Override
    public void fillValues(final int position, View convertView) {
        final ZSwipeItem swipeItem = (ZSwipeItem) convertView.findViewById(R.id.swipe_item);
        LinearLayout ll = (LinearLayout) convertView.findViewById(R.id.ll);
        swipeItem.setShowMode(ShowMode.PullOut);
        swipeItem.setDragEdge(DragEdge.Right);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activities.remove(position);
                notifyDataSetChanged();
                swipeItem.close();
            }
        });
        ((TextView) convertView.findViewById(R.id.activity_title)).setText(activities.get(position).getTitle());
        ((TextView) convertView.findViewById(R.id.organizer)).setText(activities.get(position).getOrganizer());
        ((TextView) convertView.findViewById(R.id.read_num)).setText(activities.get(position).getReadNum() + "");
        ((TextView) convertView.findViewById(R.id.end_date)).setText(activities.get(position).getEndDate());
        Uri uri = Uri.parse(activities.get(position).getImgUrl());
        ((SimpleDraweeView)convertView.findViewById(R.id.activity_img)).setImageURI(uri);

    }

    @Override
    public int getCount() {
        return activities.size();
    }

    @Override
    public Object getItem(int position) {
        return activities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setData(List<Activity> activities) {
        this.activities = activities;
        notifyDataSetChanged();
    }
}
