package com.weix.listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import com.weix.R;
import com.weix.charts_xcl.MultiAxisChart03View;
import com.weix.commonUtils.HttpHelper;
import com.weix.listview.tabale_recyclerview.RecyclerAdpter;
import com.weix.listview.tabale_recyclerview.TensionProcessData;
import java.util.LinkedList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * RecyclerView三种展示（列表，表格，瀑布）
 */

public class TableRecyclerviewActivity extends AppCompatActivity{
    List<TensionProcessData.RowsEntity> mList;
    RecyclerView mRecyclerView;
    RecyclerAdpter ra;
    LinearLayout ll ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_recyclerview);
        ll = findViewById(R.id.chart11);
         mRecyclerView = (RecyclerView) findViewById(R.id.rv_recycler);
//        http://zlyj.vipgz2.idcfengye.com/xjwkqms3.6.6/zlsynewController.do?getZLsxqLists&shebeibianhao=hhdqzl01&holeId=390085
        String url = "http://114.55.94.198:8082/hhdqqms3.6.6/zlsynewController.do?getZLsxqLists&shebeibianhao=hhdqzl01&holeId=390085&tdsourcetag=s_pctim_aiomsg";
        HttpHelper.getInstance().initService().getMessage(url).enqueue(new Callback<TensionProcessData>() {
            @Override
            public void onResponse(Call<TensionProcessData> call, Response<TensionProcessData> rib) {
                Log.e("sucess","sucess");
                if(rib.isSuccessful()){
                    TensionProcessData xx = rib.body();
                    mList = xx.getRows();
                    TensionProcessData.RowsEntity dd =   new TensionProcessData.RowsEntity();
                    dd.setJlsj("记录时间");
                    dd.setZlcs("张拉次数");
                    dd.setZll1("张拉力1");
                    dd.setYy1("油压1");
                    dd.setDxc1("顶行程1");
                    dd.setScl1("伸长量1");
                    dd.setZll2("张拉力2");
                    dd.setYy2("油压2");
                    dd.setDxc2("顶行程2");
                    dd.setScl2("伸长量2");
                    mList.add(0,dd);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(TableRecyclerviewActivity.this));
                    ra = new RecyclerAdpter(getApplicationContext(),mRecyclerView, mList.subList(0,11));
                    mRecyclerView.setAdapter(ra);

                    final LinkedList<String> zhanglaTime = new LinkedList<>();
                    final LinkedList<Double> shengchangliang= new LinkedList<>();
                    final LinkedList<Double> youya= new LinkedList<>();
                    final LinkedList<Double> dingxingcheng= new LinkedList<>();
                    final LinkedList<Double> zhanglali= new LinkedList<>();
                    final LinkedList<Double> shengchangliang2= new LinkedList<>();
                    final LinkedList<Double> youya2= new LinkedList<>();
                    final LinkedList<Double> dingxingcheng2= new LinkedList<>();
                    final LinkedList<Double> zhanglali2= new LinkedList<>();
                    List<TensionProcessData.RowsEntity> mList1 = mList.subList(1,11);
                    for (TensionProcessData.RowsEntity re:mList1){
                        zhanglaTime.add(re.getJlsj());
                        shengchangliang.add(Double.valueOf(re.getScl1()));
                        youya.add(Double.valueOf(re.getYy1()));
                        dingxingcheng.add(Double.valueOf(re.getDxc1()));
                        zhanglali.add(Double.valueOf(re.getZll1()));

                        shengchangliang2.add(Double.valueOf(re.getScl2()));
                        youya2.add(Double.valueOf(re.getYy2()));
                        dingxingcheng2.add(Double.valueOf(re.getDxc2()));
                        zhanglali2.add(Double.valueOf(re.getZll2()));
                    }
                    MultiAxisChart03View mc = new MultiAxisChart03View(getApplicationContext(),zhanglaTime,shengchangliang,youya,dingxingcheng,zhanglali);
                    mc.setLayoutParams(ll.getLayoutParams());
                    ll.addView(mc);
                }
            }
            @Override
            public void onFailure(Call<TensionProcessData> call, Throwable t) {
                Log.e("fail",t.getMessage());
            }
        });

    }
}
