package com.lianggekeji.simpleviewholder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lianggekeji.library.SimpleRecycleView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    SimpleRecycleView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleView = (SimpleRecycleView) findViewById(R.id.recycle_view);
        recycleView.setLoadListener(new SimpleRecycleView.LoadListener() {
            @Override
            public void onLoadMore() {
                Toast.makeText(getApplicationContext(), "load more...", Toast.LENGTH_LONG).show();
                recycleView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recycleView.completeLoading();
                        recycleView.setHaveMore(false);
                    }
                }, 2000);
            }

            @Override
            public void onRefresh() {
                Toast.makeText(getApplicationContext(), "refresh....", Toast.LENGTH_LONG).show();
                recycleView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recycleView.completeLoading();
                    }
                }, 2000);
            }
        });
        recycleView.addHeadView(HeadVH.class);
        recycleView.addItemView(UserItemVH.class);
        recycleView.setItemData(Arrays.asList(new User(), new User(), new User(), new User()
                , new User(), new User(), new User(), new User(), new User()
                , new User(), new User(), new User(), new User(), new User()));
    }
}
