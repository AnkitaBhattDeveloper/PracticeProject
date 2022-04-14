package com.example.practiceproject.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practiceproject.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableAdapter implements ExpandableListAdapter {
    List<String> groupList;
    HashMap<String, ArrayList<String>> childList;
    Context context;

    public ExpandableAdapter(List<String> groupList, HashMap<String, ArrayList<String>> childList,Context context) {
        this.groupList = groupList;
        this.childList = childList;
        this.context = context;
    }


    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childList.get(groupList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childList.get(groupList.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,viewGroup,false);
        TextView textView = view.findViewById(android.R.id.text1);
        String sGroup = String.valueOf(getGroup(i));
        textView.setText(sGroup);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(Color.BLACK);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(android.R.layout.simple_selectable_list_item,viewGroup,false);
       TextView textView = view.findViewById(android.R.id.text1);
       String sChild = String.valueOf(getChild(i,i1));
       textView.setText(sChild);
       textView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(viewGroup.getContext(), "item clicked", Toast.LENGTH_SHORT).show();
           }
       });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int i) {

    }

    @Override
    public void onGroupCollapsed(int i) {

    }

    @Override
    public long getCombinedChildId(long l, long l1) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long l) {
        return 0;
    }
}
