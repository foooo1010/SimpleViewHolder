package com.lianggekeji.library;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yi on 16/5/16.
 */
public class SimpleBaseAdapter<T, K, L> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int count = 10;
    private List<T> itemData = new ArrayList<>();
    private Class<? extends BaseViewHolder> itemViewHolder;
    private int itemType;

    private K headData;
    private Class<? extends BaseViewHolder> headViewHolder;
    private int headType;

    private L loadMoreData;
    private Class<? extends BaseViewHolder> moreViewHolder = DefaultFooterVH.class;
    private int moreType = R.layout.default_load_more;
    private boolean canMore = false;//是否支持上拉加载更多
    private boolean hasMore = true;//是否还有更多数据

    public void addItemView(Class<? extends BaseViewHolder> itemViewHolder) {
        this.itemViewHolder = itemViewHolder;
        this.itemType = getHolderType(itemViewHolder);
    }

    public void addHeadItemView(Class<? extends BaseViewHolder> headViewHolder) {
        this.headViewHolder = headViewHolder;
        this.headType = getHolderType(headViewHolder);
    }

    //可以自定义尾部,有默认值
    public void addLoadMoreItemView(Class<? extends BaseViewHolder> loadMoreViewHolder) {
        this.moreViewHolder = loadMoreViewHolder;
        this.moreType = getHolderType(moreViewHolder);
    }

    public void setItemData(List<T> itemData) {
        this.itemData = itemData;
        canMore = itemData.size() > count;//重新设置数据后自动设置canMore
        notifyDataSetChanged();
    }

    //得到layoutId(layoutId作为Type)
    public int getHolderType(Class<? extends BaseViewHolder> holder) {
        try {
            return holder.getConstructor(View.class).getAnnotation(Layout.class).value();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean isCanMore() {
        return canMore;
    }

    public void setHeadData(K headData) {
        this.headData = headData;
        notifyDataSetChanged();
    }

    public void setLoadMoreData(L data) {
        this.loadMoreData = data;
        notifyDataSetChanged();
    }

    public void setCanMore(boolean canMore) {
        this.canMore = canMore;
        notifyDataSetChanged();
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
        notifyDataSetChanged();
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getItemViewType(int position) {
        if (headType != 0 && position == 0) {
            return headType;
        } else if (canMore && getItemCount() == position + 1) {
            return moreType;
        } else {
            return itemType;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            Class<? extends BaseViewHolder> tempHolder = itemViewHolder;
            if (viewType == headType) tempHolder = headViewHolder;
            else if (viewType == moreType) tempHolder = moreViewHolder;
            return tempHolder.getConstructor(View.class).newInstance(
                    LayoutInflater.from(parent.getContext()).inflate(
                            viewType, parent, false
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int tempType = getItemViewType(position);
        Object obj;
        if (tempType == itemType) obj = itemData.get(position - (headType == 0 ? 0 : 1));
        else if (tempType == headType) obj = headData;
        else obj = hasMore ? (loadMoreData == null ? new Object() : loadMoreData) : null;
        ((BaseViewHolder) holder).onBindViewHolder(
                holder.itemView, obj
        );
    }

    @Override
    public int getItemCount() {
        return itemData.size() + (headType == 0 ? 0 : 1) + (canMore ? 1 : 0);
    }
}



