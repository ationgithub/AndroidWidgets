package com.weix.charts_xcl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import org.xclcharts.chart.AreaChart;
import org.xclcharts.chart.AreaData;
import org.xclcharts.chart.LineChart;
import org.xclcharts.chart.LineData;
import org.xclcharts.chart.PieChart;
import org.xclcharts.chart.PieData;
import org.xclcharts.chart.PointD;
import org.xclcharts.chart.SplineChart;
import org.xclcharts.chart.SplineData;
import org.xclcharts.common.DensityUtil;
import org.xclcharts.event.click.PointPosition;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.plot.PlotGrid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MultiAxisChart03View extends DemoView {
	private String TAG = "MultiAxisChart03View";
	private LineChart chart = new LineChart();
	private LinkedList<String> mLabels = new LinkedList<String>();
	private LinkedList<LineData> mDataset = new LinkedList<LineData>();
	private LineChart chartLn = new LineChart();
	private LinkedList<LineData> chartData = new LinkedList<LineData>();
	private SplineChart chartLnAxes = new SplineChart();
	private LinkedList<SplineData> chartDataAxes = new LinkedList<SplineData>();
	private LinkedList<String> mLabelsAxes = new LinkedList<String>();
	LinkedList<Double> dataSeries1= new LinkedList<Double>();
	LinkedList<Double> dataSeries2= new LinkedList<Double>();
	LinkedList<Double> dataSeries3= new LinkedList<Double>();

	// 分割段数，默认为5    dataRange开启值域漫游，会默认分100分，所以，最大值和最小值见在精度范围内要能分成100分
//    y轴动态设定steps，   <  stepsize = (max - min)/listsize    <

//    Collections.max(input))  Collections.min(input)) 6 7 8 9 10 细粒度的算法
	double a = 100;
	double b = 1;

	private Paint mPaintTooltips = new Paint(Paint.ANTI_ALIAS_FLAG);

	public MultiAxisChart03View(Context context,LinkedList<String> zhanglaTime, LinkedList<Double> shengchangliang, LinkedList<Double> youya, LinkedList<Double> dingxingcheng,LinkedList<Double> zhanglali) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();

	}	 
	
	public MultiAxisChart03View(Context context, AttributeSet attrs){
        super(context, attrs);   
        initView();
	 }
	 
	 public MultiAxisChart03View(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initView();
	 }
	 
	 private void initView()
	 {

		 //在点击处显示tooltip
		 mPaintTooltips.setColor(Color.rgb(240, 73, 119));
		 mLabels.add("1h46'");
		 mLabels.add("3h46'");
		 mLabels.add("5h46'");
		 mLabels.add("7h46'");
		 mLabels.add("8h46'");

		 dataSeries1.add(40d);
		 dataSeries1.add(35d);
		 dataSeries1.add(50d);
		 dataSeries1.add(60d);
		 dataSeries1.add(55d);

		 dataSeries2.add((double)50);
		 dataSeries2.add((double)42);
		 dataSeries2.add((double)55);
		 dataSeries2.add((double)65);
		 dataSeries2.add((double)58);

		 dataSeries3.add(0.3*a);
		 dataSeries3.add(0.5*a);
		 dataSeries3.add(0.7*a);
		 dataSeries3.add(0.9*a);

		 chartDataSetLn();
	 	 chartDataSetAxes();
		
		chartRender();
		chartRenderLn();
		chartRenderLnAxes();
		//綁定手势滑动事件
		this.bindTouch(this,chart);
		this.bindTouch(this,chartLn);
		// this.bindTouch(this,chartLnAxes);
	 }
	
	@Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
        super.onSizeChanged(w, h, oldw, oldh);  
       //图所占范围大小
        chart.setChartRange(w ,h);
        chartLn.setChartRange(w ,h);
//        chartLn1.setChartRange(w ,h);
        chartLnAxes.setChartRange(w ,h);
    }
	@Override
	public void render(Canvas canvas) {
		try{
			chart.render(canvas);
			chartLn.render(canvas);
			chartLnAxes.render(canvas);
//            chartLnAxes1.render(canvas);
		} catch (Exception e){
			Log.e(TAG, e.toString());
		}
	}
			 
	private void chartRender()
	{
		List<Double> dataSeries2 = new LinkedList<Double>();
		dataSeries2.add((double)540);
		dataSeries2.add((double)122);
		dataSeries2.add((double)330);
		dataSeries2.add((double)435);
		dataSeries2.add((double)215);
		LineData line2 = new LineData("张拉力",dataSeries2, Color.RED);
		line2.setDotStyle(XEnum.DotStyle.RING);
		line2.getPlotLine().getDotPaint().setColor(Color.RED);
		line2.setLabelVisible(false);
		line2.getPlotLine().getPlotDot().setRingInnerColor(Color.RED);
		line2.getLabelOptions().setLabelBoxStyle(XEnum.LabelBoxStyle.CAPRECT);
		mDataset.add(line2);
		try{
				int [] ltrb = getBarLnDefaultSpadding();
				float left = DensityUtil.dip2px(getContext(), 40); //left 40	
				float right = DensityUtil.dip2px(getContext(), 40); //right	20			
				chart.setPadding(left, ltrb[1],right, ltrb[3]);	//ltrb[2]
				chart.setCategories(mLabels);
				chart.setDataSource(mDataset);
				chart.setPlotPanMode(XEnum.PanMode.HORIZONTAL);
				chart.getDataAxis().setAxisMax(1000);
				chart.getDataAxis().setAxisMin(0);

				chart.getDataAxis().setAxisSteps(200);
				chart.getPlotGrid().showHorizontalLines();
				chart.getPlotGrid().showVerticalLines();
				chart.getDataAxis().hideTickMarks();
				chart.getCategoryAxis().hideTickMarks();
				chart.getDataAxis().setTickLabelRotateAngle(-90);
				chart.getDataAxis().getTickLabelPaint().setColor(Color.RED);
				chart.getCategoryAxis().getTickLabelPaint().setColor(Color.RED);
				PlotGrid plot = chart.getPlotGrid();	
				chart.getDataAxis().getAxisPaint().setStrokeWidth(plot.getHorizontalLinePaint().getStrokeWidth());
				chart.getCategoryAxis().getAxisPaint().setStrokeWidth(plot.getHorizontalLinePaint().getStrokeWidth());
				chart.getDataAxis().getAxisPaint().setColor(plot.getHorizontalLinePaint().getColor());
				chart.getCategoryAxis().getAxisPaint().setColor(plot.getHorizontalLinePaint().getColor());
				chart.getDataAxis().getTickMarksPaint().setColor(plot.getHorizontalLinePaint().getColor());
				chart.getCategoryAxis().getTickMarksPaint().setColor(plot.getHorizontalLinePaint().getColor());
				plot.hideHorizontalLines();
				chart.getPlotLegend().setVerticalAlign(XEnum.VerticalAlign.TOP);
				chart.getPlotLegend().setHorizontalAlign(XEnum.HorizontalAlign.LEFT);
				//激活点击监听
				chart.ActiveListenItemClick();
				chart.extPointClickRange(10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, e.toString());
		}
	}
			
	private void chartRenderLn()
	{
		try {
			//设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....		
			int [] ltrb = getBarLnDefaultSpadding();
			float left = DensityUtil.dip2px(getContext(), 40);
			float right = DensityUtil.dip2px(getContext(), 40);
			chartLn.setPadding(left, ltrb[1],right, ltrb[3]);	//ltrb[2]
			chartLn.setCategories(mLabels);								
			chartLn.setDataSource(chartData);
			chartLn.getDataAxis().setAxisMax(1.0);
			chartLn.getDataAxis().setAxisSteps(0.2);
			chartLn.setPlotPanMode(XEnum.PanMode.HORIZONTAL);
			chartLn.getPlotGrid().showHorizontalLines();
			chartLn.getPlotGrid().showVerticalLines();
			chartLn.getDataAxis().hideTickMarks();
			chartLn.getCategoryAxis().hideTickMarks();
			chartLn.getDataAxis().setTickLabelRotateAngle(-90);
			chartLn.getDataAxis().getTickLabelPaint().setColor(Color.RED);
			chartLn.getCategoryAxis().getTickLabelPaint().setColor(Color.RED);
			PlotGrid plot = chartLn.getPlotGrid();
			chartLn.getDataAxis().getAxisPaint().setStrokeWidth(plot.getHorizontalLinePaint().getStrokeWidth());
			chartLn.getCategoryAxis().getAxisPaint().setStrokeWidth(plot.getHorizontalLinePaint().getStrokeWidth());
			chartLn.getDataAxis().getAxisPaint().setColor(plot.getHorizontalLinePaint().getColor());
			chartLn.getCategoryAxis().getAxisPaint().setColor(plot.getHorizontalLinePaint().getColor());
			chartLn.getDataAxis().getTickMarksPaint().setColor(plot.getHorizontalLinePaint().getColor());
			chartLn.getCategoryAxis().getTickMarksPaint().setColor(plot.getHorizontalLinePaint().getColor());
			plot.hideHorizontalLines();
			chartLn.setDataAxisLocation(XEnum.AxisLocation.RIGHT);
			chartLn.setCategoryAxisLocation(XEnum.AxisLocation.TOP);
			chartLn.getDataAxis().setHorizontalTickAlign(Align.RIGHT);
			chartLn.getDataAxis().getTickLabelPaint().setTextAlign(Align.LEFT);
			chartLn.getPlotLegend().setVerticalAlign(XEnum.VerticalAlign.TOP);
			chartLn.getPlotLegend().setHorizontalAlign(XEnum.HorizontalAlign.CENTER);
			chartLn.ActiveListenItemClick();
			chartLn.extPointClickRange(10);
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.toString());
		}
	}
	
	
	private void chartDataSetLn()
	{
		LineData lineData1 = new LineData("伸长量",dataSeries1, Color.rgb(106, 218, 92));
		lineData1.setDotStyle(XEnum.DotStyle.RING);//hide
		lineData1.getPlotLine().getDotPaint().setColor(Color.BLUE);
		lineData1.setLabelVisible(false);
		lineData1.getPlotLine().getPlotDot().setRingInnerColor(Color.GREEN);
		lineData1.getLabelOptions().setLabelBoxStyle(XEnum.LabelBoxStyle.CAPRECT);

		LineData lineData2 = new LineData("油压",dataSeries2, Color.rgb(48, 145, 255));
		lineData2.setDotStyle(XEnum.DotStyle.RING);
		lineData2.getPlotLine().getDotPaint().setColor(Color.RED);
		lineData2.setLabelVisible(false);
		lineData2.getPlotLine().getPlotDot().setRingInnerColor(Color.GREEN);
		lineData2.getLabelOptions().setLabelBoxStyle(XEnum.LabelBoxStyle.CAPRECT);

		LineData lineData3 = new LineData("顶行程",dataSeries3, Color.rgb(199, 64, 219));
		lineData3.setDotStyle(XEnum.DotStyle.RING);
		lineData3.getPlotLine().getDotPaint().setColor(Color.RED);
		lineData3.setLabelVisible(false);
		lineData3.getPlotLine().getPlotDot().setRingInnerColor(Color.GREEN);
		lineData3.getLabelOptions().setLabelBoxStyle(XEnum.LabelBoxStyle.CAPRECT);

		chartData.add(lineData3);
		chartData.add(lineData1);
		chartData.add(lineData2);

	}
	
	
	private void chartRenderLnAxes()
	{
		try {
			//设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....		
			int [] ltrb = getBarLnDefaultSpadding();
			float left = DensityUtil.dip2px(getContext(), 20); //left 40	
			float right = DensityUtil.dip2px(getContext(), 20); //right	20			
			chartLnAxes.setPadding(left, ltrb[1],right, ltrb[3]);	//ltrb[2]
			chartLnAxes.setCategories(mLabelsAxes);								
			chartLnAxes.setDataSource(chartDataAxes);
			chartLnAxes.getDataAxis().setAxisMax(53);
			chartLnAxes.getDataAxis().setAxisSteps(5);
			chartLnAxes.setCategoryAxisMax(53);
			chartLnAxes.setCategoryAxisMin(0.2);
			chartLnAxes.getPlotLegend().hide();
			chartLnAxes.getPlotGrid().hideEvenRowBgColor();
			chartLnAxes.getPlotGrid().hideHorizontalLines();
			chartLnAxes.getPlotGrid().hideOddRowBgColor();
			chartLnAxes.getPlotGrid().hideVerticalLines();
			chartLnAxes.getDataAxis().hideAxisLine();
			chartLnAxes.getDataAxis().hideTickMarks();
			chartLnAxes.getCategoryAxis().hideAxisLine();
			chartLnAxes.getCategoryAxis().hideTickMarks();
			chartLnAxes.getCategoryAxis().setTickLabelRotateAngle(-90);
			chartLnAxes.getDataAxis().setTickLabelRotateAngle(-90);
			chartLnAxes.getDataAxis().getTickLabelPaint().setColor(Color.rgb(48, 145, 255));
			chartLnAxes.getCategoryAxis().getTickLabelPaint().setColor(Color.rgb(199, 64, 219));
			chartLnAxes.getDataAxis().setHorizontalTickAlign(Align.RIGHT);
			chartLnAxes.getDataAxis().getTickLabelPaint().setTextAlign(Align.LEFT);
			chartLnAxes.setDataAxisLocation(XEnum.AxisLocation.RIGHT);
			chartLnAxes.setCategoryAxisLocation(XEnum.AxisLocation.LEFT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.toString());
		}
	}

	
	private void chartDataSetAxes()
	{	mLabelsAxes.add("0");
		mLabelsAxes.add("0.2");
		mLabelsAxes.add("0.4");
		mLabelsAxes.add("0.6");
		mLabelsAxes.add("0.8");
		mLabelsAxes.add("1.0");
		//线1的数据集
		List<PointD> linePoint1 = new ArrayList<PointD>();
		linePoint1.add(new PointD(0d, 0d));
		SplineData dataSeries1 = new SplineData("",linePoint1, Color.rgb(54, 141, 238) );
		dataSeries1.setDotStyle(XEnum.DotStyle.HIDE);
		//设定数据源		
		chartDataAxes.add(dataSeries1);		
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		super.onTouchEvent(event);
		if(event.getAction() == MotionEvent.ACTION_UP)
		{
			triggerClick(event.getX(),event.getY());
		}
		return true;
	}
	//触发监听
	private void triggerClick(float x,float y)
	{

		PointPosition record_chart = chart.getPositionRecord(x,y);
		PointPosition record = chartLn.getPositionRecord(x,y);
		if( null == record_chart&&null == record)
		{ return;
		}else if(null != record_chart){
			LineData lData_chart = mDataset.get(record_chart.getDataID());
			Double lValue_chart = lData_chart.getLinePoint().get(record_chart.getDataChildID());
			chart.getToolTip().getBackgroundPaint().setColor(Color.GREEN);
			chart.getToolTip().setCurrentXY(x,y);
			chart.getToolTip().setAlign(Align.LEFT);
			chart.getToolTip().addToolTip(" 张拉力:" + Double.toString(lValue_chart),mPaintTooltips);
		}else if(null != record){
			LineData lData = chartData.get(record.getDataID());
			Double lValue = lData.getLinePoint().get(record.getDataChildID());
			chartLn.getToolTip().getBackgroundPaint().setColor(Color.GREEN);
			chartLn.getToolTip().setCurrentXY(x,y);
			if(record.getDataID()==0){ // dingxingcheng
				chartLn.getToolTip().addToolTip("顶行程:" + Double.toString(lValue/a),mPaintTooltips);
			}else if(record.getDataID()==1){ // dingxingcheng
				chartLn.getToolTip().addToolTip("伸长量:" + Double.toString(lValue/b),mPaintTooltips);
			}else if(record.getDataID()==2){ // dingxingcheng
				chartLn.getToolTip().addToolTip("油压:" + Double.toString(lValue/b),mPaintTooltips);
			}

			chartLn.getToolTip().setAlign(Align.LEFT);
		}
		this.invalidate();
	}
}

