package com.neerajweb.expandablelistviewtest.Model;

/**
 * Created by Admin on 12/02/2016.
 */
public class modelPostCommentHeader {
    private int _id;
    private String title;

    public modelPostCommentHeader() {}

    public modelPostCommentHeader(int mid, String mtitle)
    {
        this._id=mid;
        this.title=mtitle;
    }

    public void setId(int _sid)    {        this._id = _sid;    }
    public int getId()    {        return this._id;    }

    public void setTitle(String _title)    {        this.title = _title;    }
    public String getTitle()    {        return this.title;    }

}
