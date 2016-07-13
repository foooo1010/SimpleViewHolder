package com.lianggekeji.library;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View v) {
        super(v);
        ButterKnife.bind(this,v);
    }

    /**
     * 绑定ViewHolder
     *
     * @return
     */
    public abstract void onBindViewHolder(View view, T obj);

}
