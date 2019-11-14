package com.weix.charts_xcl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.weix.R;
import com.weix.commonUtils.HttpHelper;
import com.weix.treeViews.PourPositionData;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainAcitvityChartX extends AppCompatActivity {

    List<ChartData.DateXY> dd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_blank);
        String url = "http://zlyj.vipgz2.idcfengye.com/xjwkqms3.6.6/zlsynewController.do?getZLsxqLists&shebeibianhao=hhdqzl01&holeId=390085";
        HttpHelper.getInstance().initService().getZhoubiao(url).enqueue(new Callback<ChartData>() {
            @Override
            public void onResponse(Call<ChartData> call, Response<ChartData> rib) {
                Log.e("sucess","sucess");
                if(rib.isSuccessful()){
                    ChartData xx = rib.body();
                    dd = xx.getRows();
//                    setTree();
//                    setTree1();
                }
            }
            @Override
            public void onFailure(Call<ChartData> call, Throwable t) {
                Log.e("fail",t.getMessage());
            }
        });
    }
}
