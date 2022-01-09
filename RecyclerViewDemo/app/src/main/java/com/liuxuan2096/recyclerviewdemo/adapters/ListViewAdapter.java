package com.liuxuan2096.recyclerviewdemo.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.liuxuan2096.recyclerviewdemo.R;
import com.liuxuan2096.recyclerviewdemo.beans.ItemBean;

import java.util.List;

public class ListViewAdapter extends RecyclerViewBaseAdapter {

    // 普通的条目类型
    public static final int TYPE_NORMAL = 0;
    // 加载更多
    public static final int TYPE_LOAD_MORE = 1;
    private OnRefreshListener mUpPullRefreshListener;

    public ListViewAdapter(List<ItemBean> data) {
        super(data);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getSubView(parent, viewType);
        if (viewType == TYPE_NORMAL) {
            return new InnerHolder(view);
        } else {
            return new LoadeMoreHolder(view);
        }
    }

    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        View view;
        // 根据类型来创建View

        if (viewType == TYPE_NORMAL) {
            view = View.inflate(parent.getContext(), R.layout.item_list_view, null);
        } else {
            view = View.inflate(parent.getContext(), R.layout.item_list_loader_more, null);
        }

        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL && holder instanceof InnerHolder) {
            ((InnerHolder)holder).setData(mData.get(position), position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_LOAD_MORE;
        }
        return TYPE_NORMAL;
    }

    /**
     * 设置刷的监听的接口
     */
    public void setOnRefreshListener(OnRefreshListener listener) {
        this.mUpPullRefreshListener = listener;
    }

    //定义接口
    public interface OnRefreshListener {
        void onUpPullRefresh(LoadeMoreHolder loaderMoreHolder);
    }

    public class LoadeMoreHolder extends RecyclerView.ViewHolder {

        public static final int LOADER_STATE_LOADING = 0;
        public static final int LOADER_STATE_RELOAD = 1;
        public static final int LOADER_STATE_NORMAL = 2;

        private LinearLayout mLading;
        private TextView mReLoad;

        public LoadeMoreHolder(View itemView) {
            super(itemView);

            mLading = (LinearLayout) itemView.findViewById(R.id.loading);
            mReLoad = (TextView) itemView.findViewById(R.id.reload);

            mReLoad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //这里面要去触发加载数据
                    update(LOADER_STATE_LOADING);
                }
            });
        }


        public void update(int state) {

            //重置控件的状态
            mLading.setVisibility(View.GONE);
            mReLoad.setVisibility(View.GONE);

            switch (state) {
                case LOADER_STATE_LOADING:
                    mLading.setVisibility(View.VISIBLE);
                    //触发加载数据
                    startLoaderMore();
                    break;

                case LOADER_STATE_RELOAD:
                    mReLoad.setVisibility(View.VISIBLE);
                    break;

                case LOADER_STATE_NORMAL:
                    mLading.setVisibility(View.GONE);
                    mReLoad.setVisibility(View.GONE);
                    break;
            }
        }

        private void startLoaderMore() {
            if (mUpPullRefreshListener != null) {
                mUpPullRefreshListener.onUpPullRefresh(this);
            }
        }
    }
}
