package com.xyz.lehuo.club;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xyz.lehuo.R;

/**
 * Created by xyz on 15/12/30.
 */
public class ClubIntroFragment extends Fragment {

    private TextView intro;
    private Activity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_intro, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        intro = (TextView) view.findViewById(R.id.intro);
        intro.setText(((ClubDetailActivity)activity).club.getIntro());
    }
}
