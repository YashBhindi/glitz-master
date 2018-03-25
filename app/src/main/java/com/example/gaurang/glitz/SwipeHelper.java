package com.example.gaurang.glitz;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by BHIMANI585 on 3/12/2018.
 */

public class SwipeHelper extends ItemTouchHelper.SimpleCallback {

    UserAdapter adapter;



    public SwipeHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    public SwipeHelper(UserAdapter adapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN , ItemTouchHelper.LEFT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //adapter.remove(viewHolder.getAdapterPosition());



    }
}
