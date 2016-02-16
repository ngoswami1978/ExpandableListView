package com.neerajweb.expandablelistviewtest;

/**
 * Created by Admin on 15/09/2015.
 */
import com.neerajweb.expandablelistviewtest.Model.modelPostComment;

import java.util.ArrayList;

public class Expandable_Post_Comment_HeaderInfo {

    private String name;
    private ArrayList<modelPostComment> commentList = new ArrayList<modelPostComment>();;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<modelPostComment> getCommentList()
    {
        return commentList ;
    }

    public void setCommentList(ArrayList<modelPostComment> commentList)
    {
        this.commentList  = commentList;
    }

}