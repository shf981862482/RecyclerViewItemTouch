package com.rvitemtouch.sun.recyclerviewitemtouch;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;

    private List<String> data = new ArrayList<>();
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = this.getSharedPreferences("config", MODE_PRIVATE);
        if (sp.contains("content")) {
            String content = sp.getString("content", "none");
            List<String> strings = JSONArray.parseArray(content, String.class);
            data.clear();
            data.addAll(strings);
        } else {
            for (int i = 1; i <= 520; i++) {
                data.add("这是伟大的item" + i + "");
            }
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
        //设置Item增加、移除动画
        rv.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
//        rv.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
/*        rv.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL,
                10, Color.parseColor("#333333")));*/
        rv.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL_LIST));

        ItemTouchHelper.Callback helperCallback = new MyItemTouchHelperCallback(homeAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(helperCallback);
        helper.attachToRecyclerView(rv);
        rv.setAdapter(homeAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("content", JSON.toJSONString(data));
        editor.commit();
    }
}
