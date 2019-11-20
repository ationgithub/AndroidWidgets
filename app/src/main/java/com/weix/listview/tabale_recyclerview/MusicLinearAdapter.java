package com.weix.listview.tabale_recyclerview;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.weix.R;

public class MusicLinearAdapter extends RecyclerView.Adapter<MusicLinearAdapter.ViewHolder> {
    private Context context;
    private View mItemView;
    private RecyclerView mRv;
    private boolean isCalHeight = false;
    int itemCount;

    public MusicLinearAdapter(Context context, RecyclerView mRv) {
        this.context = context;
        this.mRv = mRv;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mItemView = LayoutInflater.from(context).inflate(R.layout.activity_table_recyclerview, viewGroup, false);
        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        setRecyclerViewHeight();
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    /**
     * 手动计算RecyclerView的高度 * 步骤： * 1.获取ItemView的高度 * 2.ItemView的数量 *
     * 3.两者相乘得出RecyclerView的高度
     */
    private void setRecyclerViewHeight() { //避免多次计算
        if (isCalHeight) {
            return;
        }
        isCalHeight = true; //获取ItemView的高度
        RecyclerView.LayoutParams itemview_layoutParams = (RecyclerView.LayoutParams) mItemView.getLayoutParams();
        //获取ItemView的数量 int
        itemCount = getItemCount(); //两者相乘得出RecyclerView的高度
       int recyclerViewHeight = itemCount * itemview_layoutParams.height; //设置RecyclerView的高度
        LinearLayout.LayoutParams rvmview_ayoutParams = (LinearLayout.LayoutParams) mRv.getLayoutParams();
        rvmview_ayoutParams.height = recyclerViewHeight;
        mRv.setLayoutParams(rvmview_ayoutParams);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
