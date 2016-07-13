package com.lianggekeji.library;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;


/**
 * Created by Yi on 16/5/18.
 */
public class SimpleRecycleView<T,K,L> extends FrameLayout {
    RecyclerView recycleView;
    SwipeRefreshLayout refreshLayout;
    OnScrollListener scrollListener;
    SimpleBaseAdapter adapter;

    public SimpleRecycleView(Context context) {
        this(context, null);
    }

    public SimpleRecycleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleRecycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.refresh_recycler_layout, null);
        recycleView = (RecyclerView) view.findViewById(R.id.recycle_view);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        scrollListener = new OnScrollListener() {
            @Override
            public void loadMore() {
                if (loadListener!=null) loadListener.onLoadMore();
            }
        };
        recycleView.addOnScrollListener(scrollListener);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (loadListener!=null) loadListener.onRefresh();
            }
        });
        adapter = new SimpleBaseAdapter();
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);
        addView(view);
    }
    //完成加载
    public void completeLoading() {
        refreshLayout.setRefreshing(false);
        scrollListener.complete();
    }
    public void completeRefresh() {
        refreshLayout.setRefreshing(false);
    }
    public void completeLoadMore() {
        scrollListener.complete();
    }

    public void addHeadView(Class<? extends BaseViewHolder> viewHolder) {
        adapter.addHeadItemView(viewHolder);
    }

    public void addItemView(Class<? extends BaseViewHolder> viewHolder) {
        adapter.addItemView(viewHolder);
    }

    public void addLoadMoreView(Class<? extends BaseViewHolder> viewHolder) {
        adapter.addLoadMoreItemView(viewHolder);
    }

    public SwipeRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }

    public interface LoadListener {
        void onLoadMore();

        void onRefresh();
    }

    private LoadListener loadListener;
    //刷新和下拉的监听
    public void setLoadListener(LoadListener loadListener) {
        this.loadListener = loadListener;
    }
    //设置item数据
    public void setItemData(List<T> data) {
        adapter.setItemData(data);
    }
    //设置头部数据
    public void setHeadData(K data) {
        adapter.setHeadData(data);
    }

    public void setLoadMoreData(L data) {
        setHasMore(true);
        adapter.setLoadMoreData(data);
    }
    //是否可以上拉加载更多
    public void setCanLoadMore(boolean is) {
        adapter.setCanMore(is);
    }
    //是否有跟过数据,默认效果设置有用
    public void setHasMore(boolean hasMore) {
        adapter.setHasMore(hasMore);
    }
}
