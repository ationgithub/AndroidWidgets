package com.weix.scroll_flipper;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.app.Activity;

import com.weix.R;

public class Activity01 extends Activity implements OnGestureListener{

    public String[] teStrings = {"第一首诗","第二首诗"};     //这是想分别显示两个不同界面标题
    public String[] poemStrings = {"君不见黄河之水天上来，\n奔流到海不复回；" +
            "\n君不见高堂明镜悲白发，\n朝如青丝暮成雪。" +
            "\n人生得意须尽欢，\n莫使金樽空对月。" +
            "\n天生我材必有用，\n千金散尽还复来。" +
            "\n烹羊宰牛且为乐，\n会须一饮三百杯。" +
            "\n岑夫子，丹丘生，\n将进酒，君莫停。" +
            "\n\n与君歌一曲，\n请君为我侧耳听。" +
            "\n钟鼓馔玉不足贵，\n但愿长醉不愿醒。" +
            "\n古来圣贤皆寂寞，\n惟有饮者留其名。" +
            "\n陈王昔时宴平乐，\n斗酒十千恣欢谑。" +
            "\n主人何为言少钱，\n径须沽取对君酌。" +
            "\n五花马，千金裘，\n呼儿将出换美酒，" + "\n与尔同消万古愁。",
            "渡远荆门外，来从楚国游。" +
                    "\n山随平野尽，江入大荒流。" +
                    "\n月下飞天镜，云生结海楼。" +
                    "\n仍怜故乡水，万里送行舟。"};  //这是想分别显示两个不同界面内容

    private ViewFlipper viewFlipper;
    private GestureDetector gestureDetector;    //手势侦测对象，用来侦测不同手势的，在下面的代码中可以看到

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //首先需要使用一个仅有ViewFlipper对象的布局文件，从中获取ViewFlipper。详情见下面布局文件代码
        viewFlipper = (ViewFlipper) getLayoutInflater().inflate(R.layout.main, null);

        // 生成GestureDetector对象，用于检测手势事件
        gestureDetector = new GestureDetector(this);
        //由于ViewFlipper是实现View对象之间的切换，因此需要把每个显示界面的内容均是作为View对象，并让其在ViewFlipper对象中进行载入
        //对于多个类似的View对象，此时可以用View对象数组进行循环载入。
        View view[] = new View[2];

        for (int i = 0; i < 2; i++)
        {
            //我采用的是较为简便的方法，对每个View对象都使用xml布局文件来进行界面布局，因此需要用inflate方法来对每个View载入布局文件
            view[i] = getLayoutInflater().inflate(R.layout.active01, null);
            //所有的View均使用同一个activity01布局文件

            viewFlipper.addView(view[i]);  //添加每个View对象

            TextView titleTextView = (TextView)view[i].findViewById(R.id.Poem_title);
            TextView poemTextView = (TextView)view[i].findViewById(R.id.Poem);
            titleTextView.setText(teStrings[i]);
            Log.v("报告！", "设置标题成功!");
            poemTextView.setText(poemStrings[i]);
            Log.v("报告！", "设置内容成功!");

        }
        setContentView(viewFlipper);
    }
    @Override
    public boolean onDown(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        //e1参数是手指离开时的MotionEvent，e2参数是手指接触时的MotionEvent
        //对手指滑动的距离进行了计算，如果滑动距离大于50像素，就做切换动作，否则不做任何切换动作。
        // 从左向右滑动
        if (e1.getX() - e2.getX() > 150)
        {
            // 添加动画
            this.viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
                    R.anim.push_left_in));
            this.viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
                    R.anim.push_left_out));
            this.viewFlipper.showNext();
            Log.v("从左向右滑动", "成功");
            return true;
        }// 从右向左滑动
        else if (e1.getX() - e2.getX() < -150)
        {
            this.viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
                    R.anim.push_right_in));
            this.viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
                    R.anim.push_right_out));
            this.viewFlipper.showPrevious();
            Log.v("从右往左滑动", "成功");
            return true;
        }
        return true;
    }
    @Override
    public void onLongPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public void onShowPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        return this.gestureDetector.onTouchEvent(event);   //将当前的手势返回到当前的手势监听器中，以触发相应手势处理事件
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        gestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }



}