package com.rvitemtouch.sun.recyclerviewitemtouch;

/**
 * Created by walkingMen on 2016/5/24.
 */
public interface ItemTouchListener {
    void onItemMove(int sourcePosition, int targetPostion);
    void onItemDismiss(int adapterPosition);
}
