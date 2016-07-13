package com.lianggekeji.simpleviewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lianggekeji.library.BaseViewHolder;
import com.lianggekeji.library.Layout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Yi on 16/5/16.
 */
public class UserItemVH extends BaseViewHolder<User> {

    @Bind(R.id.img_avatar)
    ImageView imgAvatar;
    @Bind(R.id.text_name)
    TextView textName;

    @Layout(R.layout.user_item)
    public UserItemVH(View v) {
        super(v);
    }


    @Override
    public void onBindViewHolder(View view, User obj) {
        if (obj == null) return;
        textName.setText(obj.toString());
    }

}
