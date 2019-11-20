package com.weix.listview.tabale_recyclerview;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.weix.R;

import java.util.List;

public class RecyclerAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<TensionProcessData.RowsEntity> itemsData;
    private Resources mResources;

    private View mItemView;
    private RecyclerView mRv;
    private boolean isCalHeight = false;
    int itemCount;
    public RecyclerAdpter(Context context, RecyclerView mRv,List<TensionProcessData.RowsEntity> itemsData) {
        super();
        this.context = context;
        this.itemsData = itemsData;
        this.mRv = mRv;
        mResources = context.getResources();
    }

    @Override
    public int getItemCount() {
        if (itemsData != null && itemsData.size() > 0) {
            return itemsData.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        setRecyclerViewHeight();
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder mItemViewHolder = (ItemViewHolder) holder;
            TensionProcessData.RowsEntity item = itemsData.get(position);

            mItemViewHolder.tv_order.setText(position +"");
            mItemViewHolder.tv_jltime.setText(item.getJlsj());
            mItemViewHolder.tv_zlcs.setText(item.getZlcs());
            mItemViewHolder.tv_zt1.setText(item.getZt1());
            mItemViewHolder.tv_zll1.setText(item.getZll1());

            mItemViewHolder.tv_yy1.setText(item.getYy1());
            mItemViewHolder.tv_dxc1.setText(item.getDxc1());
            mItemViewHolder.tv_scl1.setText(item.getScl1());
            mItemViewHolder.tv_zt2.setText(item.getZt2());
            mItemViewHolder.tv_zll2.setText(item.getZll2());
            mItemViewHolder.tv_yy2.setText(item.getYy2());
            mItemViewHolder.tv_dxc2.setText(item.getDxc2());
            mItemViewHolder.tv_scl2.setText(item.getScl2());

        }
    }
    private void setRecyclerViewHeight() { //避免多次计算
        if (isCalHeight) {
            return;
        }
        isCalHeight = true; //获取ItemView的高度
        RecyclerView.LayoutParams itemview_layoutParams = (RecyclerView.LayoutParams) mItemView.getLayoutParams();
        //获取ItemView的数量 int
        itemCount = getItemCount(); //两者相乘得出RecyclerView的高度
        int recyclerViewHeight = itemCount * itemview_layoutParams.height; //设置RecyclerView的高度
        FrameLayout.LayoutParams rvmview_ayoutParams = (FrameLayout.LayoutParams) mRv.getLayoutParams();
        rvmview_ayoutParams.height = recyclerViewHeight;
        mRv.setLayoutParams(rvmview_ayoutParams);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mItemView = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_tablelayout, parent, false);
        return new ItemViewHolder(mItemView);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_order;
        TextView tv_jltime;
        TextView tv_zlcs;
        TextView tv_zt1;
        TextView tv_zll1;

        TextView tv_yy1;
        TextView tv_dxc1;
        TextView tv_scl1;
        TextView tv_zt2;
        TextView tv_zll2;
        TextView tv_yy2;
        TextView tv_dxc2;
        TextView tv_scl2;

        public ItemViewHolder(View view) {
            super(view);
            tv_order = (TextView) view.findViewById(R.id.tv_order);
            tv_jltime = (TextView) view.findViewById(R.id.tv_jltime);
            tv_zlcs = (TextView) view.findViewById(R.id.tv_zlcs);
            tv_zt1 = (TextView) view.findViewById(R.id.tv_zt1);
            tv_zll1 = (TextView) view.findViewById(R.id.tv_zll1);

            tv_yy1 = (TextView) view.findViewById(R.id.tv_yy1);
            tv_dxc1 = (TextView) view.findViewById(R.id.tv_dxc1);
            tv_scl1 = (TextView) view.findViewById(R.id.tv_scl1);
            tv_zt2 = (TextView) view.findViewById(R.id.tv_zt2);
            tv_zll2 = (TextView) view.findViewById(R.id.tv_zll2);
            tv_yy2 = (TextView) view.findViewById(R.id.tv_yy2);
            tv_dxc2 = (TextView) view.findViewById(R.id.tv_dxc2);
            tv_scl2 = (TextView) view.findViewById(R.id.tv_scl2);
        }
    }
}
