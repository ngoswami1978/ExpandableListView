package com.neerajweb.expandablelistviewtest.JSONfunctions;

import com.neerajweb.expandablelistviewtest.Model.modelPostComment;
import java.util.ArrayList;

/**
 * Created by Admin on 16/02/2016.
 */
public class resultJSON {
    private int id;
    private int success;
    private String message;
    private ArrayList<modelPostComment> commentList = new ArrayList<modelPostComment>();

    public resultJSON() {}

    public resultJSON(int id,int success, String message,ArrayList<modelPostComment> commentList ) {
        super();
        this.id = id;
        this.success = success;
        this.message = message;
        this.commentList=commentList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
