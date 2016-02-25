package com.neerajweb.expandablelistviewtest.JSONfunctions;

import com.neerajweb.expandablelistviewtest.Model.modelMember;
import com.neerajweb.expandablelistviewtest.Model.modelPostComment;

import java.util.ArrayList;

/**
 * Created by Admin on 25/02/2016.
 */
public class result_MemberJSON {

    private String Owner_id = "";
    private int success;
    private String message;
    private ArrayList<modelMember> memberList = new ArrayList<modelMember>();

    public result_MemberJSON() {}

    public result_MemberJSON(String mOwner_id, String musr, String memail, String mregIP, String mdt, String mgcmregid, String mapprove_status, String mflt_id, String mflt_type, String mflt_no, String mOwner_name, String mOwner_contact, String mAge, String mRenter_name,
                       String mRenter_contact, String mcustomFont, String mOwner_LastName, String mRenter_LastName, String mOwner_Address, String mRenter_Address,
                       String mRenter_Location, String mOwner_Location, String mRenter_age, String mRenter_email, String mOwner_email,
                             int _success, String _message,ArrayList<modelMember> memberList )
    {
        this.Owner_id = mOwner_id;
        this.success = _success;
        this.message = _message;
        this.memberList=memberList;
    }

    public void setOwner_id(String _Owner_id) {
        this.Owner_id = _Owner_id;
    }

    public String getOwner_id() {
        return this.Owner_id;
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

    public ArrayList<modelMember> getMemberList()
    {
        return memberList ;
    }

    public void setMemberList(ArrayList<modelMember> _memberList)
    {
        this.memberList  = _memberList;
    }
}
