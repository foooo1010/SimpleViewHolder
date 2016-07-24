##SimpeViewHolder
a SimpleRecyleView can refresh , loadMore and have head or footer

An email <yiflandre@gmail.com> link

##setup
gradle

```
compile 'compile 'com.lianggekeji.yhj:viewholder:2.0.1''
	
```

##usage
in xml:

```

    <com.lianggekeji.library.SimpleRecycleView
        android:layout_width="..."
        android:id="..."
        android:layout_height="...t"/>
```
 


in java:
 
```
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

```

