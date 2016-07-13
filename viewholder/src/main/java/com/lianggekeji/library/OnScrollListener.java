package com.lianggekeji.library;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Yi on 16/5/18.
 */
public abstract class OnScrollListener extends RecyclerView.OnScrollListener {
    protected int lastVisibleItem;
    private boolean isLoading=false;
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (recyclerView.getAdapter() != null
                && newState == RecyclerView.SCROLL_STATE_IDLE
                &&!isLoading
                && lastVisibleItem + 1 == recyclerView.getAdapter()
                .getItemCount() && ((SimpleBaseAdapter)recyclerView.getAdapter()).isCanMore()) {
            loadMore();
            isLoading = true;
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int arg0, int arg1) {
        super.onScrolled(recyclerView, arg0, arg1);
        lastVisibleItem = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
    }
    public abstract void loadMore();

    public void complete() {
        isLoading = false;
    }
}
