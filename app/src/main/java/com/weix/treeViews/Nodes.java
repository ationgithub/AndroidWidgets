package com.weix.treeViews;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Nodes implements Serializable {
	private Nodes parent = null; // 父节点
	private List<Nodes> childrens = new ArrayList<Nodes>();//子节点
	private String value;//节点显示值
	private boolean isChecked = false; //是否被选中
	private boolean isExpand = true;//是否处于扩展状态
	private boolean hasCheckBox = true;//是否有复选框
	private String parentId = null;
	private String curId = null;
	private String hasId = null;


	//父节点集合
	private List<Nodes> parents = new ArrayList<>();

	/**
	 * 设置节点值
	 *
	 * @param parentId
	 *            TODO
	 * @param curId
	 *            TODO
	 */
	public Nodes(String parentId, String curId, String value,String hasId) {
		// TODO Auto-generated constructor stub

		this.value = value;
		this.parentId = parentId;
		this.curId = curId;
		this.hasId = hasId;

	}

	public List<Nodes> getParents() {
		return parents;
	}

	public void setParents(Nodes node) {
		if(node != null) {
			if (!parents.contains(node)) {
				parents.add(node);
			}
		}
	}

	/**
	 * 得到父节点
	 */
	public Nodes getParent() {
		return parent;
	}
	/**
	 * 设置父节点
	 * @param parent
	 */
	public void setParent(Nodes parent) {
		this.parent = parent;
	}
	/**
	 * 得到子节点
	 * @return
	 */
	public List<Nodes> getChildrens() {
		return childrens;
	}
	/**
	 * pandu是否根节点
	 * @return
	 *
	 */
	public boolean isRoot(){
		return parent ==null?true:false;
	}

	/**
	 * 是否被选中
	 * @return
	 *
	 */
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	/**
	 * 是否是展开状态
	 * @return
	 *
	 */
	public boolean isExplaned() {
		return isExpand;
	}
	/**
	 * 设置展开状态
	 * @param isExplaned
	 *
	 */
	public void setExplaned(boolean isExplaned) {
		this.isExpand = isExplaned;
	}
	/**
	 * 是否有复选框
	 * @return
	 *
	 */
	public boolean hasCheckBox() {
		return hasCheckBox;
	}
	/**
	 * 设置是否有复选框
	 * @param hasCheckBox
	 *
	 */
	public void setHasCheckBox(boolean hasCheckBox) {
		this.hasCheckBox = hasCheckBox;
	}


	public String getHasId() {
		return hasId;
	}

	public void setHasId(String hasId) {
		this.hasId = hasId;
	}

	/**
	 * 得到节点值
	 * @return
	 *
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置节点值
	 * @param value
	 *
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 增加一个子节点
	 * @param node
	 *
	 */
	public void addNode(Nodes node){
		if(!childrens.contains(node)){
			childrens.add(node);
		}
	}
	/**
	 * 移除一个子节点
	 * @param node
	 *
	 */
	public void removeNode(Nodes node){
		if(childrens.contains(node))
			childrens.remove(node);
	}
	/**
	 * 移除指定位置的子节点
	 * @param location
	 *
	 */
	public void removeNode(int location){
		childrens.remove(location);
	}
	/**
	 * 清除所有子节点
	 *
	 */
	public void clears(){
		childrens.clear();
	}
	/**
	 * 判断给出的节点是否当前节点的父节点
	 * @param node
	 * @return
	 *
	 */
	public boolean isParent(Nodes node){
		if(parent == null)return false;
		if(parent.equals(node))return true;
		return parent.isParent(node);
	}
	/**
	 * 递归获取当前节点级别
	 * @return
	 *
	 */
	public int getLevel(){
		return parent ==null?0:parent.getLevel()+1;
	}
	/**
	 * 父节点是否处于折叠的状态
	 * @return
	 *
	 */
	public boolean isParentCollapsed(){
		if(parent ==null)return false;
		if(!parent.isExplaned())return true;
		return parent.isParentCollapsed();
	}
	/**
	 * 是否叶节点（没有展开下级的几点）
	 * @return
	 *
	 */
	public boolean isLeaf(){
		return childrens.size()<1?true:false;
	}
	/**
	 * 返回自己的id
	 * @return
	 **/
	public String getCurId() {
		// TODO Auto-generated method stub
		return curId;
	}
	/**
	 * 返回的父id
	 * @return
	 **/
	public String getParentId() {
		// TODO Auto-generated method stub
		return parentId;
	}


}
