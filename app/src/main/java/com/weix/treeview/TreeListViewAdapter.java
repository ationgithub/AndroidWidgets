package com.weix.treeview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public abstract class TreeListViewAdapter<T> extends BaseAdapter {
    private static final String TAG = "TreeListViewAdapter";
    protected Context mContext;
    protected List<Node> mAllNodes;
    protected List<Node> mVisibleNodes;
    protected LayoutInflater mInflater;
    protected ListView mTree;
//    boolean hasCheckBox;


    public List<Node> getmAllNodes() {
        return mAllNodes;
    }

    /**
     * 设置Node的点击回调
     */
    public interface OnTreeNodeClickListener {
        void onClick(Node node, int position);
    }
    /**
     * 设置是否有复选框
     * @param hasCheckBox
     *
     */
//    public void setCheckBox(boolean hasCheckBox){
//        this.hasCheckBox = hasCheckBox;
//    }

    private OnTreeNodeClickListener mListener;

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener mListener) {
        this.mListener = mListener;
    }

    public TreeListViewAdapter(ListView tree, Context context, List<T> datas, int defaultExpandLevel) throws IllegalArgumentException,
            IllegalAccessException {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mAllNodes = TreeHelper.getSortedNodes(datas, defaultExpandLevel);
        mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
        mTree = tree;
        mTree.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                switch (view.getId()) {
//                    //全选、反选
//                    case R.id.checkbb:
                        expandOrCollapse(position);
                        if (mListener != null) {
                            mListener.onClick(mVisibleNodes.get(position), position);
                        }
//                        break;
//                    case R.id.id_item_text:
//                        expandOrCollapse(position);
//                        if (mListener != null) {
//                            mListener.onClick(mVisibleNodes.get(position), position);
//                        }
//                        break;
//                }
            }
        });
    }

    // 展开某个叶子所有的根节点
    public void expandAllOneRoot(Node node){
        if(node.getParent()!=null){
            Node n = node.getParent();
            if (n != null) {
                if (n.isLeaf())
                    return;
                n.setExpand(true);
                mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
                notifyDataSetChanged();
            }
            expandAllOneRoot(n);
        }
    }

    /**
     * 点击折叠或者展开
     *
     * @param position
     */
    private void expandOrCollapse(int position) {
        Node n = mVisibleNodes.get(position);
        if (n != null) {
            if (n.isLeaf())
                return;
            n.setExpand(!n.isExpand());
            mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mVisibleNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return mVisibleNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Node node = mVisibleNodes.get(position);
        convertView = getConvertView(node, position, convertView, parent);
        // 设置内边距
        convertView.setPadding(node.getLevel() * 60, 3, 3, 3);
        return convertView;
    }

    public abstract View getConvertView(Node node, int position, View convertView, ViewGroup parent);

}
