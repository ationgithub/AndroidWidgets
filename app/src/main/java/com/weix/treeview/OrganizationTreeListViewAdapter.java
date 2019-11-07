package com.weix.treeview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.weix.R;
import java.util.List;

public class OrganizationTreeListViewAdapter<T> extends TreeListViewAdapter<T> {
    public OrganizationTreeListViewAdapter(ListView tree, Context context, List<T> datas, int defaultExpandLevel) throws IllegalArgumentException, IllegalAccessException {
        super(tree, context, datas, defaultExpandLevel);
    }

    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_tree_listview_organization_activity, parent, false);
            holder = new ViewHolder();
            holder.mIcon = (ImageView) convertView.findViewById(R.id.id_item_icon);
            holder.mText = (TextView) convertView.findViewById(R.id.id_item_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (node.getIcon() == -1) {
            holder.mIcon.setVisibility(View.INVISIBLE);
        } else {
            holder.mIcon.setVisibility(View.VISIBLE);
            holder.mIcon.setImageResource(node.getIcon());
        }
        holder.mText.setText(node.getName());
        return convertView;
    }

    private class ViewHolder {
        ImageView mIcon;
        TextView mText;
    }

    public List<Node> getNodes(){
       return super.getmAllNodes();
    }

    public void notifyChanged(){
        super.notifyDataSetChanged();
    }

    public void expandOne(Node dd){
        super.expandAllOneRoot(dd);
    }
}
