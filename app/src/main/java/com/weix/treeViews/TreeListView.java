package com.weix.treeViews;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.weix.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TreeListView extends ListView {
	ListView treelist = null;
	TreeAdapter ta = null;
	public List<Nodes> mNodeList;
	private List<Nodes> checkList;
	private OnTreeNodeClickListener mListener;
	public interface OnTreeNodeClickListener {
		void onClick(Nodes node, int position);
	}
	public void setOnTreeNodeClickListener(OnTreeNodeClickListener mListener) {
		this.mListener = mListener;
	}

	public TreeListView(final Context context, List<Nodes> res) {
		super(context);
		treelist = this;
		treelist.setFocusable(false);
		treelist.setBackgroundColor(0xffffff);
		treelist.setFadingEdgeLength(0);
		treelist.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

		treelist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
				((TreeAdapter) parent.getAdapter()).ExpandOrCollapse(position);
				if (mListener != null) {
					mListener.onClick(mNodeList.get(position), position);
				}
			}
		});
		initNode(context, initNodRoot(res), true, -1, -1, 0);
	}

	// 使用 onMeasure 方法，来解决尺寸高度的问题，以及事件冲突的问题；
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		heightMeasureSpec = MeasureSpec.makeMeasureSpec(
				Integer.MAX_VALUE>>2,
				MeasureSpec.AT_MOST
		);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
//	/**
//	 *
//	 * @param context
//	 *            响应监听的上下文
//	 * @param root
//	 *            已经挂好树的根节点
//	 * @param hasCheckBox
//	 *            是否整个树有复选框
//	 * @param tree_ex_id
//	 *            展开iconid -1会使用默认的
//	 * @param tree_ec_id
//	 *            收缩iconid -1会使用默认的
//	 * @param expandLevel
//	 *            初始展开等级
//	 *
//	 */
	public List<Nodes> initNodRoot(List<Nodes> res) {
		ArrayList<Nodes> list = new ArrayList<Nodes>();
		ArrayList<Nodes> roots = new ArrayList<Nodes>();
		Map<String, Nodes> nodemap = new LinkedHashMap<String, Nodes>();
		for (int i = 0; i < res.size(); i++) {
			Nodes nr = res.get(i);
			Nodes n = new Nodes( nr.getParentId(), nr.getCurId(), nr.getValue(),nr.getHasId());
			nodemap.put(n.getCurId(), n);// 生成map树
		}
		Set<String> set = nodemap.keySet();
		Collection<Nodes> collections = nodemap.values();
		Iterator<Nodes> iterator = collections.iterator();
		while (iterator.hasNext()) {// 添加所有根节点到root中
			Nodes n = iterator.next();
			if (!set.contains(n.getParentId()))
				roots.add(n);
			list.add(n);
		}
		for (int i = 0; i < list.size(); i++) {
			Nodes n = list.get(i);
			for (int j = i + 1; j < list.size(); j++) {
				Nodes m = list.get(j);
				if (m.getParentId() .equals( n.getCurId())) {
					n.addNode(m);
					m.setParent(n);
					m.setParents(n);
				} else if (m.getCurId() .equals( n.getParentId())) {
					m.addNode(n);
					n.setParent(m);
					m.setParents(m);
				}
			}
		}
		return roots;
	}

	public void initNode(Context context, List<Nodes> root, boolean hasCheckBox,
                         int tree_ex_id, int tree_ec_id, int expandLevel) {
		ta = new TreeAdapter(context, root);
		//获取
		mNodeList = ta.all;
		// 设置整个树是否显示复选框
		ta.setCheckBox(true);
		// 设置展开和折叠时图标
		int tree_ex_id_ = (tree_ex_id == -1) ? R.drawable.tree_ex : tree_ex_id;
		int tree_ec_id_ = (tree_ec_id == -1) ? R.drawable.tree_ec : tree_ec_id;
		ta.setCollapseAndExpandIcon(tree_ex_id_, tree_ec_id_);
		// 设置默认展开级别
		ta.setExpandLevel(expandLevel);
		this.setAdapter(ta);
	}
	/* 返回当前所有选中节点的List数组 */
	public List<Nodes> get() {
		Log.e("getTreeListView", ta.getSelectedNode().size() + "");
		return ta.getSelectedNode();
	}
	public  List<Nodes> getLeaf(){
		List<Nodes> selectNode = ta.getSelectedNode();
		List<Nodes> leafNodes = new ArrayList<>();
		for(Nodes node:selectNode){
			if(node.isLeaf()){
				leafNodes.add(node);
			}
		}
		Log.e("getTreeListViewLeaf", leafNodes.size() + "");
		return leafNodes;
	}

public void setSelect(List<String> allSelect){
	ta.setSelectedNode(allSelect);
}}
