package com.weix.bottomnavigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.socks.library.KLog;
import com.weix.R;
import com.weix.commonUtils.HttpHelper;
import com.weix.treeViews.Nodes;
import com.weix.treeViews.PourPositionData;
import com.weix.treeViews.TreeListView;
import com.weix.treeview.Node;
import com.weix.treeview.OrganizationBean;
import com.weix.treeview.OrganizationTreeListViewAdapter;
import com.weix.treeview.TreeListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlankFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    private String mContentText;
//    private RelativeLayout relativeLayout;
    private TreeListView listView;
    List<PourPositionData.DataBean> dd;
    List<Nodes> list = new ArrayList<Nodes>();
    private OrganizationTreeListViewAdapter<OrganizationBean> mAdapter;
    private ListView treeListView;
    private List<OrganizationBean> treeNodes;

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance(String param1) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }
        String url = "http://192.168.1.144:8080/jeecg/appWZproject.do?AppjzbwList&departId=8a8cb3a867c55ac30167c55b74590002&page=&rows=&keyword=";
        HttpHelper.getInstance().initService().getTongzhiren(url).enqueue(new Callback<PourPositionData>() {
            @Override
            public void onResponse(Call<PourPositionData> call, Response<PourPositionData> rib) {
                Log.e("sucess","sucess");
                if(rib.isSuccessful()){
                    PourPositionData xx = rib.body();
                    dd = xx.getData();
//                    setTree();
//                    setTree1();
                }
            }
            @Override
            public void onFailure(Call<PourPositionData> call, Throwable t) {
                Log.e("fail",t.getMessage());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
//        TextView contentTv = rootView.findViewById(R.id.content_tv);
//        relativeLayout = (RelativeLayout) rootView.findViewById(R.id.main_relative_layout);
//        treeListView = (ListView) rootView.findViewById(R.id.lv_tree_organization);
//        contentTv.setText(mContentText);
        return rootView;
    }

//    void setTree(){  这个多选树，数据量一大内存直接卡死
//            for(PourPositionData.DataBean d:dd){
//                Nodes ob = new Nodes(d.getParentNo(),d.getProjectNo(),d.getProjectname(),d.getId());
//                list.add(ob);
//            }
//        listView = new TreeListView(getActivity(), list);
//        listView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
//        relativeLayout.addView(listView);
//        List<Nodes> dd = listView.get();
//        KLog.e(String.valueOf(dd.size()));
//        listView.setOnTreeNodeClickListener(new TreeListView.OnTreeNodeClickListener() {
//            @Override
//            public void onClick(Nodes node, int position) {
////                    Toast.makeText(getApplicationContext(), node.getName()+String.valueOf(position),Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    void setTree1(){
//        treeNodes = new ArrayList<>();
//        for(PourPositionData.DataBean d:dd){
//            OrganizationBean ob = new OrganizationBean(d.getProjectNo(),
//                    d.getParentNo(),
//                    d.getProjectname(),
//                    d.getId());
//            treeNodes.add(ob);
//        }
//        try {
//            mAdapter = new OrganizationTreeListViewAdapter<>(treeListView, getActivity(), treeNodes, 10);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
//            @Override
//            public void onClick(Node node, int position) {
//                if (node.isLeaf()) {
//                }
//            }
//        });
//    }
}
