package com.xyz.lehuo.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;

import com.xyz.lehuo.R;

/**
 * Created by xyz on 15/12/26.
 */
public class MySwipeRefreshLayout extends SwipeRefreshLayout implements AbsListView.OnScrollListener {

    public static final String Tag = "MySwipeRefreshLayout";

    private int mTouchSlop;
    private ListView mListView;
    private View mListViewFooter;
    private int mYDown;
    private int mLastY;
    private boolean isLoading = false;
    private OnLoadListener mOnLoadListener;

    public MySwipeRefreshLayout(Context context) {
        super(context);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.listview_footer, null, false);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(Tag, "onLayout=========>" + getChildCount());
//        for (int i = 0; i < getChildCount(); i++) {
//            Log.i(Tag, "child: " + i + " ===>" + getChildAt(i).toString());
//        }
        Log.i(Tag, "mListView=======>" + mListView);
        if (mListView == null) {
            getListView();
            if (mListView != null && mListView.getFooterViewsCount() <= 0) {
                mListView.addFooterView(mListViewFooter);
            }
        }
    }

    private void getListView() {
        Log.i(Tag, "child count=========>" + getChildCount());
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof  ListView) {
                mListView = (ListView) getChildAt(i);
                mListView.setOnScrollListener(this);
                break;
            }
        }
    }

    public void setListView(ListView listView) {
        this.mListView = listView;
        this.mListView.setOnScrollListener(this);
        mListView.addFooterView(mListViewFooter);
        //mListViewFooter.setVisibility(View.GONE);
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
//        Log.i(Tag, "getFooterViewsCount=======>" + mListView.getFooterViewsCount());
        if (mListView != null && mListView.getFooterViewsCount() > 0) {
            mListView.removeFooterView(mListViewFooter);
            //mListViewFooter.setVisibility(View.GONE);
        }
        if (isLoading) {
            if (mListView != null && mListView.getFooterViewsCount() <= 0) {
                mListView.addFooterView(mListViewFooter);
                //mListViewFooter.setVisibility(View.VISIBLE);
            }
            //mListViewFooter.setVisibility(View.VISIBLE);
        } else {
            //mListViewFooter.setVisibility(View.GONE);
            mYDown = 0;
            mLastY = 0;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mYDown = (int)ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                mLastY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                if (canLoad()) {
                    loadData();
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean canLoad() {
        return isBottom() && !isLoading && isPullUp();
    }

    private boolean isBottom() {
        if (mListView != null && mListView.getAdapter() != null) {
            return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
        }
        return false;
    }

    private boolean isPullUp() {
        return (mYDown - mLastY) >= mTouchSlop;
    }

    private void loadData() {
        if (mOnLoadListener != null) {
            mOnLoadListener.onLoad();
        }
        setLoading(true);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (isFastDoubleClick(100)) {
            return;
        }
        isLoading = false;
        if (canLoad()) {
            loadData();
        }
    }

    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

    public interface OnLoadListener {
        void onLoad();
    }

    private long lastClickTime = 0;

    public boolean isFastDoubleClick(long times) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < times) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
