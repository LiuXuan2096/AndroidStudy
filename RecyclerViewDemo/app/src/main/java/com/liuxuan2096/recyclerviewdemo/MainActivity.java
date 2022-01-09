package com.liuxuan2096.recyclerviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.liuxuan2096.recyclerviewdemo.adapters.GridViewAdapter;
import com.liuxuan2096.recyclerviewdemo.adapters.ListViewAdapter;
import com.liuxuan2096.recyclerviewdemo.adapters.RecyclerViewBaseAdapter;
import com.liuxuan2096.recyclerviewdemo.beans.ItemBean;
import com.liuxuan2096.recyclerviewdemo.beans.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 总结：
 * 1.添加依赖
 * 2.通过findViewById找到控件
 * 3.准备好数据
 * 4.设置布局管理器
 * 5.创建适配器
 * 6.设置适配器
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView mList;
    private List<ItemBean> mData;
    private RecyclerViewBaseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 找到控件
        mList = (RecyclerView) this.findViewById(R.id.recycler_view);
        // 准备数据
        initData();
        //设置默认的显示样式为ListView的效果
        showList(true, false);
    }

    /**
     * 用于模拟数据
     */
    private void initData() {
        mData = new ArrayList<>();

        // 创建模拟数据
        for (int i = 0; i < Data.icons.length; i++) {
            // 创建数据对象
            ItemBean data = new ItemBean();
            data.icon = Data.icons[i];
            data.title = "我是第 " + i + " 个条目";
            // 添加到集合里
            mData.add(data);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {

            //ListView的部分
            case R.id.list_view_vertical_stander:
                Log.d(TAG, "点击了ListView里头的垂直标准");
                showList(true, false);
                break;
            case R.id.list_view_vertical_reverse:
                Log.d(TAG, "点击了ListView里头的垂直反向");
                showList(true, true);
                break;
            case R.id.list_view_horizontal_stander:
                Log.d(TAG, "点击了ListView里头的水平标准");
                showList(false, false);
                break;
            case R.id.list_view_horizontal_reverse:
                Log.d(TAG, "点击了ListView里头的水平反向");
                showList(false, true);
                break;

            //GridView部分
            case R.id.grid_view_vertical_stander:
                showGrid(true, false);
                break;
            case R.id.grid_view_vertical_reverse:
                showGrid(true, true);
                break;
            case R.id.grid_view_horizontal_stander:
                showGrid(false, false);
                break;
            case R.id.grid_view_horizontal_reverse:
                showGrid(false, true);
                break;


        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 这个方法用于实现和GridView一样的效果
     */
    private void showGrid(boolean isVertical, boolean isReverse) {

        //创建布局管理器
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        //设置水平还是垂直
        layoutManager.setOrientation(isVertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL);
        //设置标准(正向)还是反向的
        layoutManager.setReverseLayout(isReverse);

        //设置布局管理器
        mList.setLayoutManager(layoutManager);

        //创建适配器
        mAdapter = new GridViewAdapter(mData);

        //设置适配器
        mList.setAdapter(mAdapter);

        //初始化事件
        initListener();
    }

    private void showList(boolean isVertical, boolean isReverse) {
        // RecyclerView需要设置样式，其实就是设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 设置布局控制器来控制
        // 设置水平还是垂直
        layoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
        // 设置标准正或反
        layoutManager.setReverseLayout(isReverse);

        mList.setLayoutManager(layoutManager);
        // 创建适配器
        mAdapter = new ListViewAdapter(mData);
        mList.setAdapter(mAdapter);
        initListener();
    }

    private void initListener() {

        mAdapter.setOnItemClickListener(new RecyclerViewBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //这里处理条目的点击事件,该干嘛就干嘛,跳转的就跳转...
                Toast.makeText(MainActivity.this, "您点击的是第" + position + "个条目", Toast.LENGTH_SHORT).show();
            }
        });
    }
}