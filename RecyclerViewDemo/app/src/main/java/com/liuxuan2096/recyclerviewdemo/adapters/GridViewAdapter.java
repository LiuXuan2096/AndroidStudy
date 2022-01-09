package com.liuxuan2096.recyclerviewdemo.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.liuxuan2096.recyclerviewdemo.R;
import com.liuxuan2096.recyclerviewdemo.beans.ItemBean;

import java.util.List;

public class GridViewAdapter extends RecyclerViewBaseAdapter {

    public GridViewAdapter(List<ItemBean> data) {
        super(data);
    }

    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        // 在这里创建item的UI
        View view = View.inflate(parent.getContext(), R.layout.item_grid_view, null);
        return view;
    }
}
