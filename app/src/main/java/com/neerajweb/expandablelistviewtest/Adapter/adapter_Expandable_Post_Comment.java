package com.neerajweb.expandablelistviewtest.Adapter;

/**
 * Created by Admin on 15/09/2015.
 */
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.neerajweb.expandablelistviewtest.Expandable_Post_Comment_HeaderInfo;
import com.neerajweb.expandablelistviewtest.Model.modelPostComment;
import com.neerajweb.expandablelistviewtest.R;

public class adapter_Expandable_Post_Comment extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Expandable_Post_Comment_HeaderInfo> deptList;

    public adapter_Expandable_Post_Comment(Context context, ArrayList<Expandable_Post_Comment_HeaderInfo> deptList) {
        this.context = context;
        this.deptList = deptList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<modelPostComment> productList = deptList.get(groupPosition).getCommentList();
        return productList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        modelPostComment modelPostComment = (modelPostComment) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.expandable_post_comment_child_row, null);
        }

        TextView PostcommentId = (TextView) view.findViewById(R.id.childItemId);//PostCommentID
        PostcommentId.setText(modelPostComment.getPostcommentId().trim());

        TextView sequence = (TextView) view.findViewById(R.id.sequence);//PostComments Sequence No
        sequence.setText("through Reply " + modelPostComment.getSequence().trim() + ": ");

        TextView childItem = (TextView) view.findViewById(R.id.childItem);//Post Comment message
        childItem.setText(modelPostComment.getPostcomment().trim());

        TextView childItemUserName = (TextView) view.findViewById(R.id.childItemUserName);//LogedIn UserName
        childItemUserName.setText(modelPostComment.getLogedInUserName().trim());

        TextView childItemDatetime = (TextView) view.findViewById(R.id.childItemDatetime);//Message datetime
        childItemDatetime.setText(modelPostComment.getpostDatetime().trim());

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<modelPostComment> productList = deptList.get(groupPosition).getCommentList();
        return productList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return deptList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return deptList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        Expandable_Post_Comment_HeaderInfo expandablePostCommentHeaderInfo = (Expandable_Post_Comment_HeaderInfo) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.expandable_post_comment_group_heading, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(expandablePostCommentHeaderInfo.getName().trim());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
