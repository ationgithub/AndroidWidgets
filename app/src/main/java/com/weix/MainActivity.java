package com.weix;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import com.weix.commonUtils.HttpHelper;
import com.weix.treeViews.PourPositionData;
import com.weix.treeview.Node;
import com.weix.treeview.OrganizationBean;
import com.weix.treeview.OrganizationTreeListViewAdapter;
import com.weix.treeview.TreeListViewAdapter;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import weix.xjp.searchtips.wideght.OnItemClick;

public class MainActivity extends AppCompatActivity implements OnItemClick {

//    private SearchTipsGroupView view;
//    private String items[] = {"视频", "么么哒", "动画", "音乐", "猜你喜欢", "最近热门", "影院", "游戏", "好得多"};
    List<PourPositionData.DataBean> dd;
    private OrganizationTreeListViewAdapter<OrganizationBean> mAdapter;
    private ListView treeListView;
    private List<OrganizationBean> treeNodes;
    SearchView searchView;
//    List<Node> searchNodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        SearchViewFragment fragment=new SearchViewFragment(this);
//        getSupportFragmentManager().beginTransaction().replace(R.id.root_layout_common,fragment).commit();
//        view = (SearchTipsGroupView) findViewById(R.id.search_tips);
//        view.initViews(items, this);
        treeListView = findViewById(R.id.lv_tree_organization_activity);
        searchView = findViewById(R.id.msearchs);
        int closeImagId = getResources().getIdentifier("android:id/search_close_btn", null, null);
        ImageView closeImag =  searchView.findViewById(closeImagId);
        closeImag.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(), "You have clicked the Submit Button!", Toast.LENGTH_SHORT).show();
                // 展开某个叶子
                List<Node> ddd = mAdapter.getNodes();
                Log.e("onenode",ddd.size()+":d:"+ddd.get(0).getName());
                for(Node d:ddd) {
//                    if (d.getId().equals("A01A04A0100001000090000100004000010000100001")) {
                    if (d.getName().contains(query)) {
//                        searchNodes.add(d) ;
                        mAdapter.expandOne(d);
                        Log.e("onenode",d.getId()+":d:"+d.getParent().getName());
                    }
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        String url = "http://192.168.1.144:8080/jeecg/appWZproject.do?AppjzbwList&departId=8a8cb3a867c55ac30167c55b74590002&page=&rows=&keyword=";
        HttpHelper.getInstance().initService().getTongzhiren(url).enqueue(new Callback<PourPositionData>() {
            @Override
            public void onResponse(Call<PourPositionData> call, Response<PourPositionData> rib) {
                Log.e("sucess","sucess");
                if(rib.isSuccessful()){
                    PourPositionData xx = rib.body();
                    dd = xx.getData();
//                    setTree();
                    setTree1();
                }
            }
            @Override
            public void onFailure(Call<PourPositionData> call, Throwable t) {
                Log.e("fail",t.getMessage());
            }
        });

    }

    void setTree1(){
        treeNodes = new ArrayList<>();
//        searchNodes = new ArrayList<>();
//        for(int i = 0;i<100;i++){// dd.size()
        for(PourPositionData.DataBean d:dd){
            OrganizationBean ob = new OrganizationBean(d.getProjectNo(),
                    d.getParentNo(),
                    d.getProjectname(),
                    d.getId());
            treeNodes.add(ob);
        }
        try {
            mAdapter = new OrganizationTreeListViewAdapter<>(treeListView, this, treeNodes, 10);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        treeListView.setAdapter(mAdapter);
        mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
            @Override
            public void onClick(Node node, int position) {
                if (node.isLeaf()) {
                    Log.e("node",node.getId()+":d:"+node.getpId());
                    //  A01A04A0100001000090000100004000010000100001:d:A01A04A01000010000900001000040000100001
                }
            }
        });
    }

    @Override
    public void onClick(int position) {
//        Toast.makeText(this, items[position], Toast.LENGTH_SHORT).show();
    }
}
