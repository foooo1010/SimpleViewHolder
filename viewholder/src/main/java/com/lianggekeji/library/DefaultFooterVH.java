package com.lianggekeji.library;


import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DefaultFooterVH extends BaseViewHolder<Object> {
    ProgressBar progress;
    TextView tvState;

    public DefaultFooterVH(View v) {
        super(v);
    }

    @Override
    public void onBindViewHolder(View view, Object o) {
        boolean isLoading = (o == null ? false : true);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        tvState = (TextView) view.findViewById(R.id.tv_state);
        tvState.setText(isLoading ? "正在加载" : "没有更多");
        progress.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }


}