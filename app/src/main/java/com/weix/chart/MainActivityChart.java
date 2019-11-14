package com.weix.chart;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.BarLineChartTouchListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.weix.R;
import com.weix.chart.marker.DetailsMarkerView;
import com.weix.chart.marker.PositionMarker;
import com.weix.chart.marker.RoundMarker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivityChart extends AppCompatActivity {

    LineChart mLineChart;
    LineChart mLineChart1;
    List<String> xDataList = new ArrayList<>();
    List<Entry> yDataList = new ArrayList<>();
    List<Entry> yDataList1 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chart);
        mLineChart = findViewById(R.id.chart);

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ILineDataSet> dataSets = mLineChart.getLineData().getDataSets();
                for (ILineDataSet set : dataSets)
                    set.setVisible(!set.isVisible());
                mLineChart.animateXY(500, 500);
                mLineChart.invalidate();
            }
        });

        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1,准备要更换的数据
                List<Entry> entries = new ArrayList<>();
                for (int i = 0; i < 12; i++)
                    entries.add(new Entry(i, new Random().nextInt(300)));
                //2. 获取LineDataSet线条数据集
                List<ILineDataSet> dataSets = mLineChart.getLineData().getDataSets();
                //是否存在
                if (dataSets != null && dataSets.size() > 0) {
                    //直接更换数据源
                    for (ILineDataSet set : dataSets) {
                        LineDataSet data = (LineDataSet) set;
                        data.setValues(entries);
                    }
                } else {
                    //重新生成LineDataSet线条数据集
                    LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
                    dataSet.setDrawCircles(false);
                    dataSet.setColor(Color.parseColor("#7d7d7d"));//线条颜色
                    dataSet.setCircleColor(Color.parseColor("#7d7d7d"));//圆点颜色
                    dataSet.setLineWidth(1f);//线条宽度

                    LineData lineData = new LineData(dataSet);
                    //是否绘制线条上的文字
                    lineData.setDrawValues(false);
                    mLineChart.setData(lineData);
                }
                //更新
                mLineChart.invalidate();
            }
        });

        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空数据
                mLineChart.getLineData().clearValues();
                mLineChart.highlightValues(null);
                mLineChart.invalidate();
            }
        });

        findViewById(R.id.btn_slide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float scaleX = mLineChart.getScaleX();
                if (scaleX == 1)
                    mLineChart.zoomToCenter(5, 1f);
                else {
                    BarLineChartTouchListener barLineChartTouchListener = (BarLineChartTouchListener) mLineChart.getOnTouchListener();
                    barLineChartTouchListener.stopDeceleration();
                    mLineChart.fitScreen();
                }
                mLineChart.invalidate();
            }
        });

        //1.设置x轴和y轴的点
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 12; i++)
            entries.add(new Entry(i, new Random().nextInt(300)));
        //2.把数据赋值到你的线条
        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset

        //显示圆圈
        dataSet.setDrawCircles(true);//散点以圆圈形式显示
        dataSet.setColor(Color.parseColor("#7d7d7d"));//线条颜色
        dataSet.setCircleColor(Color.parseColor("#7d7d7d"));//圆点颜色
        dataSet.setLineWidth(1f);//线条宽度

        XAxis xAxis = mLineChart.getXAxis();//拿到X轴
        for (int i = 0; i < 1000; i++) {
            xDataList.add(String.valueOf(i + 1).concat("天"));
        }
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xDataList));//X轴赋值
        for (int i = 0; i < 1000; i++)
            yDataList.add(new Entry(i, new Random().nextFloat()*10*(float) Math.pow(-1,i)));//左侧Y轴赋值
        for (int i = 0; i < 1000; i++)
            yDataList1.add(new Entry(i, new Random().nextFloat()*10));//右侧Y轴赋值
        showChart(this,mLineChart,xDataList,yDataList,yDataList1);//调用函数绘图


//        dataSet.setDrawCircles(false);
//        dataSet.setColor(Color.parseColor("#7d7d7d"));//线条颜色
//        dataSet.setCircleColor(Color.parseColor("#7d7d7d"));//圆点颜色
//        dataSet.setLineWidth(1f);//线条宽度
//        mLineChart.setScaleEnabled(false);
//        //mLineChart.getLineData().getDataSets().get(0).setVisible(true);
//        //设置样式
//        YAxis rightAxis = mLineChart.getAxisRight();
//        //设置图表右边的y轴禁用
//        rightAxis.setEnabled(false);
//        YAxis leftAxis = mLineChart.getAxisLeft();
//        //设置图表左边的y轴禁用
////        leftAxis.setEnabled(false);
//        rightAxis.setAxisMaximum(dataSet.getYMax() * 2);
//        leftAxis.setAxisMaximum(dataSet.getYMax() * 2);
//        //设置x轴
//        XAxis xAxis = mLineChart.getXAxis();
//        xAxis.setTextColor(Color.parseColor("#333333"));
//        xAxis.setTextSize(11f);
//        xAxis.setAxisMinimum(0f);
//        xAxis.setDrawAxisLine(true);//是否绘制轴线
//        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
//        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
//        xAxis.setGranularity(1f);//禁止放大x轴标签重绘
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 12; i++) {
//            list.add(String.valueOf(i + 1).concat("月"));
//        }
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(list));
//        //透明化图例
//        Legend legend = mLineChart.getLegend();
//        legend.setForm(Legend.LegendForm.NONE);
//        legend.setTextColor(Color.WHITE);
//        //legend.setYOffset(-2);
//        //点击图表坐标监听
//        mLineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, Highlight h) {
//                //查看覆盖物是否被回收
//                if (mLineChart.isMarkerAllNull()) {
//                    //重新绑定覆盖物
//                    createMakerView();
//                    //并且手动高亮覆盖物
//                    mLineChart.highlightValue(h);
//                }
//            }
//            @Override
//            public void onNothingSelected() {
//
//            }
//        });


        //隐藏x轴描述
//        Description description = new Description();
//        description.setEnabled(false);
//        mLineChart.setDescription(description);
        //创建覆盖物
//        createMakerView();
        //3.chart设置数据
//        LineData lineData = new LineData(dataSet);
        //是否绘制线条上的文字
//        lineData.setDrawValues(false);
//        mLineChart.setData(lineData);
//        mLineChart.invalidate(); // refresh
    }


    public  void showChart(Context context,LineChart mLineChart, List<String> xDataList,
                           List<Entry> yDataList, List<Entry> yDataList1){
        mLineChart.setDrawBorders(true);
        //设置标题
        mLineChart.getDescription().setText("我滴图表");
        //设置标题字体大小
        mLineChart.getDescription().setTextSize(16f);
        // 标题字体颜色
        mLineChart.getDescription().setTextColor(context.getApplicationContext().getResources().getColor(R.color.material_textBlack_black));
        mLineChart.setScaleEnabled(false);
        //mLineChart.getLineData().getDataSets().get(0).setVisible(true);
        //设置右边y轴  只能用一边的  MMP的
        YAxis rightAxis = mLineChart.getAxisRight();
        rightAxis.setTextColor(Color.RED);//设置颜色
        rightAxis.setAxisMaximum(10);//设置最大值
        rightAxis.setAxisMinimum(-20);//设置最小值
        rightAxis.setDrawGridLines(true);//是否绘制网格
        rightAxis.setDrawZeroLine(true);//是否绘制0刻度线
        rightAxis.setGranularityEnabled(true);//等间距


        //设置左边y轴
        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setEnabled(true);
        leftAxis.setAxisMaximum(10);
        leftAxis.setAxisMinimum(-20);
        //设置x轴
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setSpaceMax(50);
        xAxis.setTextColor(Color.parseColor("#333333"));
        xAxis.setTextSize(11f);
        xAxis.setAxisMinimum(0f);
        xAxis.setLabelRotationAngle(-60);//x轴标签倾斜
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setGranularity(1f);//间隔为1
        //透明化图例
        Legend legend = mLineChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setFormSize(8f);
        legend.setTextColor(context.getApplicationContext().getResources().getColor(R.color.light_blue));
        legend.setTextSize(12f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        // 图例字体
        // mLegend.setTypeface(mTf);
        // 图例的显示和隐藏
        legend.setEnabled(true);
        //屏幕左右平移
        float scaleX = mLineChart.getScaleX();
        if (scaleX == 1)
            mLineChart.zoomToCenter(5, 1f);
        else {
            BarLineChartTouchListener barLineChartTouchListener = (BarLineChartTouchListener) mLineChart.getOnTouchListener();
            barLineChartTouchListener.stopDeceleration();
            mLineChart.fitScreen();
        }
        //动画添加
        mLineChart.animateX(2500);//图像缓冲时间
        // 设置是否启动触摸响应
        mLineChart.setTouchEnabled(true);
        // 是否可以拖拽
        mLineChart.setDragEnabled(true);
        // 是否可以缩放
        mLineChart.setScaleEnabled(true);
        mLineChart.invalidate();

        //隐藏x轴描述
        Description description = new Description();
        description.setEnabled(false);
        mLineChart.setDescription(description);

        //3.chart设置数据
        LineDataSet set1, set2;//数据类型
        if (mLineChart.getData() != null &&
                mLineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mLineChart.getData().getDataSetByIndex(1);
            set1.setValues(yDataList);//坐标轴赋值
            set2.setValues(yDataList1);
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            // 创建一个数据集,并给它一个类型
            set1 = new LineDataSet(yDataList, "原始数据");
            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            //set1.isDrawValuesEnabled();
            set1.setDrawValues(false);//是否显示数据
            set1.setColor(Color.BLACK);//曲线颜色
            set1.setCircleColor(Color.WHITE);
            set1.setLineWidth(2f);//线宽
            set1.setCircleRadius(1f);
            // set1.setFillAlpha(65);
            //set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setDrawCircleHole(true);

            //创建一个数据集,并给它一个类型
            set2 = new LineDataSet(yDataList1, "拟合数据");
            //set2.isDrawValuesEnabled();
            set2.setDrawValues(false);
            set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set2.setColor(Color.RED);
            set2.setCircleColor(Color.BLACK);
            set2.setLineWidth(2f);
            set2.setCircleRadius(1f);
            //set2.setFillAlpha(65);
            //set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(true);
            set2.setHighLightColor(Color.rgb(244, 117, 117));

            // 创建一个数据集的数据对象
            LineData data = new LineData(set1, set2);
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(9f);

            //设置数据
            mLineChart.setData(data);
        }
        //是否绘制线条上的文字
        mLineChart.invalidate(); // refresh
    }


    /**
     * 创建覆盖物
     */
//    public void createMakerView() {
//        DetailsMarkerView detailsMarkerView = new DetailsMarkerView(this);
//        detailsMarkerView.setChartView(mLineChart);
//        mLineChart.setDetailsMarkerView(detailsMarkerView);
//        mLineChart.setPositionMarker(new PositionMarker(this));
//        mLineChart.setRoundMarker(new RoundMarker(this));
//    }

}
