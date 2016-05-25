package com.rvitemtouch.sun.recyclerviewitemtouch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.DragEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;

    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 1; i <= 520; i++) {
            data.add("这是伟大的item" + i + "");
        }
        rv = (RecyclerView) findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this));
//设置adapter
        HomeAdapter homeAdapter = new HomeAdapter(MainActivity.this, data, new HomeAdapter.OnDragListener() {
            @Override
            public void onStartDrag(HomeAdapter.MyViewHolder holder) {
                
            }

            @Override
            public void onStartSwipe(HomeAdapter.MyViewHolder holder) {

            }
        });
        rv.setAdapter(homeAdapter);
//设置Item增加、移除动画
        rv.setItemAnimator(new DefaultItemAnimator());
//添加分割线
        rv.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.HORIZONTAL_LIST));

        ItemTouchHelper.Callback helperCallback = new MyItemTouchHelperCallback(homeAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(helperCallback);
        helper.attachToRecyclerView(rv);
    }
}
