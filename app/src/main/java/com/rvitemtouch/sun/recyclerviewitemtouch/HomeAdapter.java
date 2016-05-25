package com.rvitemtouch.sun.recyclerviewitemtouch;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by walkingMen on 2016/5/24.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> implements ItemTouchListener {
    private Context context;
    private List<String> mDatas;
    private OnDragListener dragListener;


    public HomeAdapter(Context context, List<String> mDatas, OnDragListener listener) {
        this.context = context;
        this.mDatas = mDatas;
        dragListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_home, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
        holder.tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEventCompat.getActionMasked(motionEvent)
                        == MotionEvent.ACTION_DOWN) {
                    dragListener.onStartDrag(holder);
                    Log.e("Test", "onTouch.ACTION_DOWN");
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onItemMove(int sourcePosition, int targetPostion) {
        String prev = mDatas.remove(sourcePosition);
        mDatas.add(targetPostion > sourcePosition ? targetPostion - 1 : targetPostion, prev);
        notifyItemMoved(sourcePosition, targetPostion);
    }

    @Override
    public void onItemDismiss(int adapterPosition) {
        mDatas.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements ItemStatusListener {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
        }

        @Override
        public void onItemSelected() {
            Toast.makeText(context, tv.getText().toString() + "--onItemSelected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onItemClear() {
            Toast.makeText(context, tv.getText().toString() + "--onItemClear", Toast.LENGTH_SHORT).show();
        }
    }

    public interface OnDragListener {
        void onStartDrag(MyViewHolder holder);

        void onStartSwipe(MyViewHolder holder);
    }

}

