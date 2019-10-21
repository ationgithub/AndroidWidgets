package com.weix.bottomnavigation;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.weix.R;
import com.weix.textview.BadgeView;

public class RelativeLayoutTextviewBottomNavigation extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout ly_one,ly_two,ly_three,ly_four;
//    RadioButton mTextView1;
    private TextView mTextView1,mTextView2,mTextView3,mTextView4;
    private TextView mTextNum1,mTextNum2,mTextNum3;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_bottom_navigation);

        bindView();
        ly_one.performClick();
        FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        BlankFragment xunFragment =  BlankFragment.newInstance("");
        transaction.add(R.id.fragment_container1, xunFragment);
        transaction.commit();
    }

    private void bindView() {

        ly_one = (LinearLayout)findViewById(R.id.ly_tab_menu_deal);
        ly_two = (LinearLayout)findViewById(R.id.ly_tab_menu_poi);
        ly_three = (LinearLayout)findViewById(R.id.ly_tab_menu_more);
        ly_four = (LinearLayout)findViewById(R.id.ly_tab_menu_user);

        mTextView1 = findViewById(R.id.tab_menu_deal);
        mTextView2 = findViewById(R.id.tab_menu_poi);
        mTextView3 = findViewById(R.id.tab_menu_more);
        mTextView4 = findViewById(R.id.tab_menu_user);

        mTextNum1 = (TextView)findViewById(R.id.tab_menu_deal_num);
        mTextNum2 = (TextView)findViewById(R.id.tab_menu_poi_num);
        mTextNum3 = (TextView)findViewById(R.id.tab_menu_more_num);

        mImageView = (ImageView)findViewById(R.id.tab_menu_setting_partner);

        ly_one.setOnClickListener(this);
        ly_two.setOnClickListener(this);
        ly_three.setOnClickListener(this);
        ly_four.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    private void setSelected() {
        mTextView1.setSelected(false);
        mTextView2.setSelected(false);
        mTextView3.setSelected(false);
        mTextView4.setSelected(false);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ly_tab_menu_deal:
                setSelected();
                mTextView1.setSelected(true);
//                Drawable drawable = null;
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                    drawable = this.getResources().getDrawable(R.drawable.layer_list_red_dot,null);
//                }
//                drawable.setBounds(10,1,40,60);//40 60
//                mTextView1.setCompoundDrawables(null,null,drawable,null);

                BadgeView badgeView2 = new BadgeView(this);
                badgeView2.setTargetView(mTextNum1);
                badgeView2.setBadgeCount(9);

//                BadgeView badgeView = new BadgeView(this);
//                badgeView.setTargetView(findViewById(R.id.rl));
//                badgeView.setBadgeGravity(Gravity.RIGHT | Gravity.CENTER);
//                badgeView.setBadgeMargin(0, 0, 10, 0);
//                badgeView.setText("NEW");
//                BadgeView badgeView2 = new BadgeView(this);
//                badgeView2.setTargetView(findViewById(R.id.rl2));
//                badgeView2.setBadgeCount(9);
                break;
            case R.id.ly_tab_menu_poi:
                setSelected();
                mTextView2.setSelected(true);
                mTextNum2.setVisibility(View.INVISIBLE);
                break;
            case R.id.ly_tab_menu_more:
                setSelected();
                mTextView3.setSelected(true);
                mTextNum3.setVisibility(View.INVISIBLE);
                break;
            case R.id.ly_tab_menu_user:
                setSelected();
                mTextView4.setSelected(true);
                mImageView.setVisibility(View.INVISIBLE);
                break;
        }
    }

}
