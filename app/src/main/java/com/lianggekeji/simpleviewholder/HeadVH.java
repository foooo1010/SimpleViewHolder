package com.lianggekeji.simpleviewholder;

import android.view.View;
import android.widget.TextView;

import com.lianggekeji.library.BaseViewHolder;
import com.lianggekeji.library.Layout;

import butterknife.Bind;

/**
 * Created by Yi on 16/5/16.
 */
public class HeadVH extends BaseViewHolder<Head> {
    @Bind(R.id.head_text)
    TextView headText;

    @Layout(R.layout.head)
    public HeadVH(View v) {
        super(v);
    }


    @Override
    public void onBindViewHolder(View view, Head obj) {
        if (obj == null) return;
        headText.setText(obj.toString());
    }


}
