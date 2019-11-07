package com.weix.treeview;

import com.weix.treeview.annotation.TreeNodeId;
import com.weix.treeview.annotation.TreeNodeLabel;
import com.weix.treeview.annotation.TreeNodePid;

public class OrganizationBean {
    @TreeNodeId
    private String _id;
    @TreeNodePid
    private String parentId;
    @TreeNodeLabel
    private String name;

    private String idName;

    public OrganizationBean(String _id, String parentId, String name, String idName) {
        this._id = _id;
        this.parentId = parentId;
        this.name = name;
        this.idName = idName;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
