package com.rvitemtouch.sun.recyclerviewitemtouch;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

/**
 * Created by walkingMen on 2016/5/24.
 */
public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private ItemTouchListener mItemTouchListener;

    public MyItemTouchHelperCallback(ItemTouchListener itemTouchListener) {
        mItemTouchListener = itemTouchListener;
    }

    /**
     * 支持长按进入拖动
     *
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    /**
     * 指定拖动和滑动支持的方向
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //List部分功能
//        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//拖动支持向下和向上
//        int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END;//滑动支持向左和向右
        //Grid部分功能
        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END;
        int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlag, swipeFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        Log.e("Test", "onMove--------------------------------");
        mItemTouchListener.onItemMove(source.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.e("Test", "onSwiped--------------------------------");
        mItemTouchListener.onItemDismiss(viewHolder.getAdapterPosition());
    }

    /**
     * 在每次View Holder的状态变成拖拽 (ACTION_STATE_DRAG) 或者 滑动 (ACTION_STATE_SWIPE)的时候被调用。
     *
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            ItemStatusListener listener = (ItemStatusListener) viewHolder;
            listener.onItemSelected();
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 在一个view被拖拽然后被放开的时候被调用，
     *
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        ItemStatusListener listener = (ItemStatusListener) viewHolder;
        listener.onItemClear();
    }
}

