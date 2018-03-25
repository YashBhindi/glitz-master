package com.example.gaurang.glitz;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.AdapterView;

/**
 * Created by yash on 25/3/18.
 */

class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public RecyclerItemClickListener(Object p0, RecyclerView recyclerView, AdapterView.OnItemClickListener onItemClickListener) {



    }
}
