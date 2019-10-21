package com.weix;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.weix.search.SearchTipsGroupView;

import weix.xjp.searchtips.wideght.OnItemClick;



public class MainActivity extends AppCompatActivity implements OnItemClick {

    private SearchTipsGroupView view;
    private String items[] = {"视频", "么么哒", "动画", "音乐", "猜你喜欢", "最近热门", "影院", "游戏", "好得多"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SearchViewFragment fragment=new SearchViewFragment(this);
//        getSupportFragmentManager().beginTransaction().replace(R.id.root_layout_common,fragment).commit();

        view = (SearchTipsGroupView) findViewById(R.id.search_tips);
        view.initViews(items, this);
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(this, items[position], Toast.LENGTH_SHORT).show();
    }
}
