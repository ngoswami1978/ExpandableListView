package com.neerajweb.expandablelistviewtest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.neerajweb.expandablelistviewtest.Adapter.adapter_Expandable_Post_Comment;
import com.neerajweb.expandablelistviewtest.JSONfunctions.resultJSON;
import com.neerajweb.expandablelistviewtest.Maintainance.ApartmentApplicationController;
import com.neerajweb.expandablelistviewtest.Model.modelPostComment;
import com.neerajweb.expandablelistviewtest.Model.modelPostCommentHeader;
import com.neerajweb.expandablelistviewtest.utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Expandable_Post_Comment_MainActivity extends Activity implements OnClickListener {

    private LinkedHashMap<String, Expandable_Post_Comment_HeaderInfo> mypostTitle = new LinkedHashMap<String, Expandable_Post_Comment_HeaderInfo>();
    private ArrayList<Expandable_Post_Comment_HeaderInfo> titleList = new ArrayList<Expandable_Post_Comment_HeaderInfo>();
    private adapter_Expandable_Post_Comment listAdapter;
    private ExpandableListView myList;
    public Context mContext;
    public long lngTitleId;
    Calendar objCalender ;
    SimpleDateFormat df;

    //private modelPostCommentHeader model_PostCommentheder;
    ArrayList<modelPostCommentHeader> model_PostCommentheder;
    ArrayList<modelPostComment> model_PostComment;
    private ArrayList<resultJSON> resultNewPosts;

    ProgressDialog PD;
    ProgressDialog PDPostCommentDetail;

    Spinner  spinnerPostCommentHeader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandable_post_comment_activity_main);

        mContext = this;
        try {
            objCalender = Calendar.getInstance();
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            // Spinner element
            spinnerPostCommentHeader= (Spinner) findViewById(R.id.postTitle);

            // Load Spinner data from database table
            //loadHeaderData();

            //Just add some data to start with
//            loadData();

            //get reference to the ExpandableListView
            myList = (ExpandableListView) findViewById(R.id.myList);
            //create the adapter by passing your ArrayList data
            listAdapter = new adapter_Expandable_Post_Comment(Expandable_Post_Comment_MainActivity.this, titleList);
            //attach the adapter to the list
            myList.setAdapter(listAdapter);

            //expand all Groups
            expandAll();

            //add new item to the List
            Button add = (Button) findViewById(R.id.addPost);
            add.setOnClickListener(this);

            //listener for child row click
            myList.setOnChildClickListener(myListItemClicked);
            //listener for group heading click
            myList.setOnGroupClickListener(myListGroupClicked);
        }
        catch (Exception Ex)
        {
            Toast.makeText(this,Ex.getMessage() , Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            loadJSONPostCommentHeaderInfo();
            loadJSONPostCommentDetailInfo();
        }
        catch  (Exception Ex)
        {
            Toast.makeText(this, Ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // LoadJSON with post comment details
    private void loadJSONPostCommentDetailInfo() {
        // JSON Node names
        final String ITEM_ID="id";
        final String ITEM_TITLEID="TitleId";
        final String ITEM_USERNAME="Username";
        final String ITEM_SEQUENCE="Sequence";
        final String ITEM_TITLE="title";
        final String ITEM_POSTCOMMENTDATETIME="PostcommentDateTime";
        final String ITEM_POSTCOMMENT="Postcomment";

        PDPostCommentDetail = new ProgressDialog(this);
        PDPostCommentDetail.setMessage("Refreshing your posts " + "\n" + "please wait.....");
        PDPostCommentDetail.setCancelable(false);

        try {
            PDPostCommentDetail.show();
            StringRequest postRequest = new StringRequest(Request.Method.POST, Const.URL_WS_POST_COMMENT_DETAILS,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                // prepare the list of all records--------
                                model_PostComment  = new ArrayList<modelPostComment>();
                                ArrayList<String> Arraylstdetail=new ArrayList<String>();
                                //----------------------------------------
                                JSONObject jsonObject = new JSONObject(response);
                                int success =jsonObject.getInt("success");
                                if (success == 1) {
                                    JSONArray ja = jsonObject.getJSONArray("postdetail");

                                    for (int i = 0; i < ja.length(); i++) {
                                        modelPostComment itemrpop = new modelPostComment();
                                        JSONObject jobj = ja.getJSONObject(i);

                                        itemrpop.setPostcommentId(jobj.getString(ITEM_ID));
                                        itemrpop.setPostcommentTitleId(jobj.isNull(ITEM_TITLEID) ? "" : jobj.getString(ITEM_TITLEID));
                                        itemrpop.setLogedInUserName(jobj.isNull(ITEM_USERNAME) ? "" : jobj.getString(ITEM_USERNAME));
                                        itemrpop.setSequence(jobj.isNull(ITEM_SEQUENCE) ? "" : jobj.getString(ITEM_SEQUENCE));
                                        itemrpop.setPostcommentTitle(jobj.isNull(ITEM_TITLE) ? "" : jobj.getString(ITEM_TITLE));
                                        itemrpop.setpostDatetime(jobj.isNull(ITEM_POSTCOMMENTDATETIME) ? "" : jobj.getString(ITEM_POSTCOMMENTDATETIME));
                                        itemrpop.setPostcomment(jobj.isNull(ITEM_POSTCOMMENT) ? "" : jobj.getString(ITEM_POSTCOMMENT));

                                        model_PostComment.add(itemrpop);
                                        Arraylstdetail.add(jobj.isNull(ITEM_TITLE) ? "" : jobj.getString(ITEM_TITLE));
                                    } // for loop ends
                                    PDPostCommentDetail.dismiss();
                                    loadDataToDevice(model_PostComment, Arraylstdetail);
                                } // if ends
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    PDPostCommentDetail.dismiss();
                    Toast.makeText(mContext,
                            "failed to retrive infomations please check your network connection...", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("_id",hashmapMonthYear.get("ID"));
//                    params.put("title",hashmapMonthYear.get("TITLE"));
                    return params;
                }
            };

            // Adding request to request queue
            ApartmentApplicationController.getInstance().addToReqQueue(postRequest);
        }
        catch (Exception Ex) {
            Toast.makeText(this, Ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    // LoadJSON with post comment headers
    private void loadJSONPostCommentHeaderInfo() {
        // JSON Node names
        final String ITEM_ID="_id";
        final String ITEM_TITLE="title";

        PD = new ProgressDialog(this);
        PD.setMessage("Loading " + "\n" + "please wait.....");
        PD.setCancelable(false);

        try {
            PD.show();
            StringRequest postRequest = new StringRequest(Request.Method.POST, Const.URL_WS_POST_COMMENT_HEADERS,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                // prepare the list of all records--------
                                model_PostCommentheder  = new ArrayList<modelPostCommentHeader>();
                                ArrayList<String> Arraylstheader=new ArrayList<String>();
//                                ArrayList<modelPostCommentHeader> objModelMaintainancerptPeriods =new ArrayList<modelPostCommentHeader>();

                                //----------------------------------------
                                JSONObject jsonObject = new JSONObject(response);
                                int success =jsonObject.getInt("success");
                                if (success == 1) {
                                    JSONArray ja = jsonObject.getJSONArray("headerdetail");

                                    for (int i = 0; i < ja.length(); i++) {
                                        modelPostCommentHeader itemrpop = new modelPostCommentHeader();
                                        JSONObject jobj = ja.getJSONObject(i);

                                        itemrpop.setId(Integer.parseInt(jobj.getString(ITEM_ID)));
                                        itemrpop.setTitle(jobj.isNull(ITEM_TITLE) ? "" : jobj.getString(ITEM_TITLE));

                                        model_PostCommentheder.add(itemrpop);
                                        Arraylstheader.add(jobj.isNull(ITEM_TITLE) ? "" : jobj.getString(ITEM_TITLE));
                                    } // for loop ends
                                    PD.dismiss();
                                    reLoadHeader(model_PostCommentheder, Arraylstheader);
                                } // if ends
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    PD.dismiss();
                    Toast.makeText(mContext,
                            "failed to retrive infomations please check your network connection...", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("_id",hashmapMonthYear.get("ID"));
//                    params.put("title",hashmapMonthYear.get("TITLE"));
                    return params;
                }
            };

            // Adding request to request queue
            ApartmentApplicationController.getInstance().addToReqQueue(postRequest);
        }
        catch (Exception Ex) {
            Toast.makeText(this, Ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void reLoadHeader( final ArrayList<modelPostCommentHeader> objmodel_PostCommentheder,ArrayList<String> Arraylstheader)
    {
        try
        {
            if (!(Arraylstheader.isEmpty()))
            {
                try{
                    // Setting Flat Spinner adapter
                    spinnerPostCommentHeader.setAdapter(new ArrayAdapter<String>(mContext , android.R.layout.simple_spinner_dropdown_item, Arraylstheader));

                    // Spinner on item click listener
                    spinnerPostCommentHeader.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            // TODO Auto-generated method stub
//                            Toast.makeText(mContext, objmodel_PostCommentheder.get(position).getId(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(mContext, objmodel_PostCommentheder.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                            lngTitleId = objmodel_PostCommentheder.get(position).getId();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            // TODO Auto-generated method stub
                        }
                    });
                }
                catch(Exception Ex)
                {
                    Toast.makeText(mContext,Ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch(Exception Ex)
        {
            Toast.makeText(this, Ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View v)
    {
    try
        {
            switch (v.getId()) {

                //add entry to the List
                case R.id.addPost:

                    Spinner spinner = (Spinner) findViewById(R.id.postTitle);
                    TextView textView = (TextView)spinner.getSelectedView();
                    String title = textView.getText().toString();
                    String userName= "N";// set Logged in User Name later on

                    //String title = spinner.getSelectedItem().toString();
                    EditText editText = (EditText) findViewById(R.id.etPostOrComments);
                    String postcomments = editText.getText().toString();
                    editText.setText("");
                    savePostAndCommentsIntoServer(lngTitleId, title, postcomments, userName);
                    break;
            }
        }
        catch(Exception Ex)
        {
            Toast.makeText(this,Ex.getMessage() , Toast.LENGTH_LONG).show();
        }
    }

    //method to expand all groups
    private void expandAll() {
        try
        {
            int count = listAdapter.getGroupCount();
            for (int i = 0; i < count; i++){
                myList.expandGroup(i);
            }
        }
        catch(Exception Ex)
        {
            Toast.makeText(this,Ex.getMessage() , Toast.LENGTH_LONG).show();
        }

    }

    //method to collapse all groups
    private void collapseAll() {
        try
        {
            int count = listAdapter.getGroupCount();
            for (int i = 0; i < count; i++){
                myList.collapseGroup(i);
            }
        }
        catch   (Exception Ex)
        {
            Toast.makeText(this,Ex.getMessage() , Toast.LENGTH_LONG).show();
        }
    }

    //load data into expandable list from server
    private void loadDataToDevice(ArrayList<modelPostComment> objPostArray,ArrayList<String> Arraylstdetail){
        long lgTitleid;
        try
        {
            if (objPostArray.size()>0)
            {
                for(modelPostComment dtls: objPostArray)
                {
                   lgTitleid = Long.parseLong(dtls.getPostcommentTitleId());
                   addTitleAndPostsIntoExpandableList(lgTitleid, dtls.getPostcommentTitle()
                           , dtls.getPostcomment(), dtls.getLogedInUserName(), dtls.getpostDatetime(), dtls.getSequence()
                           ,dtls.getPostcommentId());
                }
            }
        }
        catch (Exception Ex)
        {
            Toast.makeText(this,Ex.getMessage() , Toast.LENGTH_LONG).show();
        }
    }

    //load some initial data into out list
//    private void loadData(){
//        long lgTitleid;
//        try
//        {
//            // Connect to database and Load Cursor Object
//            // database handler
//            DatabaseHandler db = new DatabaseHandler(getApplicationContext());
//
//            // get all List of Post and comments from database and update the List View
//            ArrayList<modelPostComment> objPostArray = db.getAllPostsComments();
//
//            if (objPostArray.size()>0)
//            {
//                for(modelPostComment dtls: objPostArray)
//                {
//                    lgTitleid = Long.parseLong(dtls.getPostcommentTitleId());
//                    addTitleAndPostsInListView(lgTitleid,dtls.getPostcommentTitle(),dtls.getPostcomment());
//                    //Toast.makeText(this,titles.indexOf(dtls) , Toast.LENGTH_LONG).show();
//                }
//            }
//            else
//            {
//                addTitleAndPostsInListView(1,"MaintainanceMain?", "hey guys ! pls let me know what is the MaintainanceMain cost per month.");
//                addTitleAndPostsInListView(1,"MaintainanceMain?", "1K for 1 BHK");
//                addTitleAndPostsInListView(1,"MaintainanceMain?", "1.5K for 2 BHK");
//
//                addTitleAndPostsInListView(2,"Laundry Facilities?", "Who is doing Laundry work can anybody le me know?");
//                addTitleAndPostsInListView(2,"Laundry Facilities?", "Upper Shopper Stopper");
//
//                addTitleAndPostsInListView(6,"Parking?", "hi! please clear the bikes from ground floor");
//                addTitleAndPostsInListView(6,"Parking?","hi ! can anyone let me know who is th owner of white alto?");
//            }
//        }
//        catch (Exception Ex)
//        {
//            Toast.makeText(this,Ex.getMessage() , Toast.LENGTH_LONG).show();
//        }
//    }


    //our child listener
    private OnChildClickListener myListItemClicked =  new OnChildClickListener() {
        public boolean onChildClick(ExpandableListView parent, View v,
                                    int groupPosition, int childPosition, long id) {
            try
            {
                //get the group header
                Expandable_Post_Comment_HeaderInfo expandablePostCommentHeaderInfo = titleList.get(groupPosition);
                //get the child info
                modelPostComment modelPostComment =  expandablePostCommentHeaderInfo.getCommentList().get(childPosition);
                //display it or do something with it
                Toast.makeText(getBaseContext(), "Clicked on Detail " + expandablePostCommentHeaderInfo.getName()
                        + "/" + modelPostComment.getPostcomment(), Toast.LENGTH_LONG).show();
            }
            catch(Exception Ex)
            {
                Toast.makeText(getBaseContext(),Ex.getMessage() , Toast.LENGTH_LONG).show();
            }

            return false;
        }

    };

    //our group listener
    private OnGroupClickListener myListGroupClicked =  new OnGroupClickListener() {

        public boolean onGroupClick(ExpandableListView parent, View v,
                                    int groupPosition, long id) {
            try
            {
                //get the group header
                Expandable_Post_Comment_HeaderInfo expandablePostCommentHeaderInfo = titleList.get(groupPosition);
                //display it or do something with it
                Toast.makeText(getBaseContext(), "Child on Header " + expandablePostCommentHeaderInfo.getName(),
                        Toast.LENGTH_LONG).show();
            }
            catch(Exception Ex)
            {
                Toast.makeText(getBaseContext(), Ex.getMessage() ,Toast.LENGTH_LONG).show();
            }

            return false;
        }

    };

    //Load initial values and Add the same Title and Posts into Expandable View
    private int addTitleAndPostsIntoExpandableList(long lngTitleId, String title, String postcomments, String username,String datetime,String sequence,String postCommentId ){
        int groupPosition = 0;
        try
        {
            //check the hash map if the group already exists
            Expandable_Post_Comment_HeaderInfo expandablePostCommentHeaderInfo = mypostTitle.get(title);
            //add the group if doesn't exists
            if(expandablePostCommentHeaderInfo == null){
                expandablePostCommentHeaderInfo = new Expandable_Post_Comment_HeaderInfo();
                expandablePostCommentHeaderInfo.setName(title);
                mypostTitle.put(title, expandablePostCommentHeaderInfo);
                titleList.add(expandablePostCommentHeaderInfo);
            }

            //get the children for the group
            ArrayList<modelPostComment> postcommentList = expandablePostCommentHeaderInfo.getCommentList();
            //size of the children list
            int listSize = postcommentList.size();
            //add to the counter
            listSize++;

            //String formattedDate = df.format(objCalender.getInstance().getTime());
            String formattedDate = datetime;

            //create a new child and add that to the group
            modelPostComment modelPostComment = new modelPostComment();
            modelPostComment.setPostcommentId(postCommentId);
            modelPostComment.setPostcommentTitleId(String.valueOf(lngTitleId));
            modelPostComment.setPostcommentTitle(title);
            modelPostComment.setSequence(String.valueOf(sequence));
            modelPostComment.setPostcomment(postcomments);
            modelPostComment.setLogedInUserName(username); // default Neeraj later on implement the Login User Name
            modelPostComment.setpostDatetime(formattedDate);

            postcommentList.add(0, modelPostComment);
            expandablePostCommentHeaderInfo.setCommentList(postcommentList);

            //find the group position inside the list
            groupPosition = titleList.indexOf(expandablePostCommentHeaderInfo);
            //return groupPosition;
        }
        catch   (Exception Ex)
        {
            Toast.makeText(this,Ex.getMessage() , Toast.LENGTH_LONG).show();
        }
        return groupPosition;
    }

    //save Post and comment into database server using PHP Web Service
    private int savePostAndCommentsIntoServer(final long intTitleId, final String title, final String postcomments,final String username){
        int groupPosition=0;
        // JSON Node names
        final String ITEM_SEQUENCE="sequence";
        final String ITEM_TITLEID="titleid";
        final String ITEM_USERNAME="username";
        final String ITEM_POSTCOMMENTDATETIME="postcommentdatetime";
        final String ITEM_POSTCOMMENT="postcomment";

        try
        {
//            check the hash map if the group already exists
            Expandable_Post_Comment_HeaderInfo expandablePostCommentHeaderInfo = mypostTitle.get(title);

            //add the group if doesn't exists
            if(expandablePostCommentHeaderInfo == null){
                expandablePostCommentHeaderInfo = new Expandable_Post_Comment_HeaderInfo();
                expandablePostCommentHeaderInfo.setName(title);
                mypostTitle.put(title, expandablePostCommentHeaderInfo);
                titleList.add(expandablePostCommentHeaderInfo);
            }

//            get the children for the group
            ArrayList<modelPostComment> postcommentList = expandablePostCommentHeaderInfo.getCommentList();
//            size of the children list
            int listSize = postcommentList.size();
//            add to the counter
            listSize++;

            //int listSize = 5; //manually entered it will later on updated by server only
            String formattedDate = df.format(objCalender.getInstance().getTime()); //manually entered it will later on updated by server only

            //create a new child and add that to the group
            final modelPostComment modelPostComment = new modelPostComment();
            modelPostComment.setPostcommentTitleId(String.valueOf(intTitleId));
            modelPostComment.setPostcommentTitle(title);
            modelPostComment.setSequence(String.valueOf(listSize));
            modelPostComment.setPostcomment(postcomments);
            modelPostComment.setLogedInUserName(username); // default Neeraj later on implement the Login User Name
            modelPostComment.setpostDatetime(formattedDate);// Server date is updated through PHP webservice, so here no use of formattedDate

            PDPostCommentDetail = new ProgressDialog(this);
            PDPostCommentDetail.setMessage("posting " + "\n" + "please wait.....");
            PDPostCommentDetail.setCancelable(false);

            try {
                PDPostCommentDetail.show();
                StringRequest postRequest = new StringRequest(Request.Method.POST, Const.URL_WS_CRUD_POST_COMMENT,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                try {
                                    // prepare the list of all records--------
                                    model_PostComment  = new ArrayList<modelPostComment>();
                                    //----------------------------------------
                                    JSONObject jsonObject = new JSONObject(response);
                                    ArrayList<resultJSON> resultjsonobj = new ArrayList<resultJSON>();
                                    resultjsonobj = parseJsonResult(jsonObject);

                                    int success =resultjsonobj.get(0).getSuccess();
                                    int groupPosition = 0;
                                    long retid;
                                    if (success == 1) {
                                        PDPostCommentDetail.dismiss();
                                        retid = resultjsonobj.get(0).getId();
                                        modelPostComment.setPostcommentId(String.valueOf(retid));
                                        groupPosition=  displayPostToExpandableView(modelPostComment);
                                        modelPostComment.setGroupPosition(groupPosition);
                                    } // if ends
                                } catch (JSONException e) {
                                    PDPostCommentDetail.dismiss();
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    PDPostCommentDetail.dismiss();
                                    Toast.makeText(mContext,
                                            "failed to retrive infomations please check your network connection...", Toast.LENGTH_SHORT).show();
                                }
                            })
                            {
                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> params = new HashMap<String, String>();
                                    params.put(ITEM_SEQUENCE,modelPostComment.getSequence());
                                    params.put(ITEM_TITLEID,modelPostComment.getPostcommentTitleId());
                                    params.put(ITEM_USERNAME,modelPostComment.getLogedInUserName());
                                    params.put(ITEM_POSTCOMMENTDATETIME,modelPostComment.getpostDatetime());
                                    params.put(ITEM_POSTCOMMENT,modelPostComment.getPostcomment());
                                    return params;
                                }
                            };
                // Adding request to request queue
                ApartmentApplicationController.getInstance().addToReqQueue(postRequest);
            }
            catch (Exception Ex) {
                Toast.makeText(this, Ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        catch   (Exception Ex)
        {
            Toast.makeText(this,Ex.getMessage() , Toast.LENGTH_LONG).show();
        }
        return groupPosition;
    }

    private int displayPostToExpandableView(modelPostComment modelPostComment)
    {
        int groupPosition = 0;
        int resultNewItemsSize = 0;
        try
        {
//            check the hash map if the group already exists
            Expandable_Post_Comment_HeaderInfo expandablePostCommentHeaderInfo = mypostTitle.get(modelPostComment.getPostcommentTitle());

            //add the group if doesn't exists
            if(expandablePostCommentHeaderInfo == null){
                expandablePostCommentHeaderInfo = new Expandable_Post_Comment_HeaderInfo();
                expandablePostCommentHeaderInfo.setName(modelPostComment.getPostcommentTitle());
                mypostTitle.put(modelPostComment.getPostcommentTitle(), expandablePostCommentHeaderInfo);
                titleList.add(expandablePostCommentHeaderInfo);
            }

//            get the children for the group
            ArrayList<modelPostComment> postcommentList = expandablePostCommentHeaderInfo.getCommentList();
//            size of the children list
            int listSize = postcommentList.size();
//            add to the counter
            listSize++;
            String formattedDate = df.format(objCalender.getInstance().getTime());


            //** The New Post and Comment append into postcommentList
            // so that in every post we will get the updated records fromm all the Users
            //**
            try
            {
                resultNewItemsSize = resultNewPosts.get(0).getCommentList().size();
                if (resultNewItemsSize>=1)
                {
                    //for (int i=0; i< resultNewItemsSize ;i++)
                    for (int i=resultNewItemsSize-1; i >= 0 ;i--)
                    {
                        //postcommentList.add(0, modelPostComment);
                        postcommentList.add(0, resultNewPosts.get(0).getCommentList().get(i));
                        expandablePostCommentHeaderInfo.setCommentList(postcommentList);
                    }
                }
            }
            catch(Exception Ex)
            {
                //If any error comes we will set the last post in 0 position.
                postcommentList.add(0, modelPostComment);
                expandablePostCommentHeaderInfo.setCommentList(postcommentList);
            }

//            postcommentList.add(0, modelPostComment);
//            expandablePostCommentHeaderInfo.setCommentList(postcommentList);


//            find the group position inside the list
            groupPosition = titleList.indexOf(expandablePostCommentHeaderInfo);

//            notify the list so that changes can take effect
            listAdapter.notifyDataSetChanged();
//            collapse all groups
            collapseAll();
//            expand the group where item was just added
            myList.expandGroup(groupPosition);
//            set the current group to be selected so that it becomes visible
            myList.setSelectedGroup(groupPosition);
        }
        catch(Exception Ex)
        {
            Toast.makeText(this,Ex.getMessage() , Toast.LENGTH_LONG).show();
        }
        return groupPosition;
    }

    /**
     * Parsing json reponse and passing the data to feed view list adapter
     * */
    private ArrayList<resultJSON> parseJsonResult(JSONObject response) {
        resultJSON item= new resultJSON();
        resultNewPosts = new ArrayList<resultJSON>();
        //get the children for the group
        ArrayList<modelPostComment> postcommentList = item.getCommentList();

        String  ITEM_ID = "id";
        String  ITEM_TITLEID = "TitleId";
        String  ITEM_USERNAME = "Username";
        String  ITEM_SEQUENCE = "Sequence";
        String  ITEM_TITLE = "title";
        String  ITEM_POSTCOMMENTDATETIME = "PostcommentDateTime";
        String  ITEM_POSTCOMMENT = "Postcomment";

        try {
            JSONArray feedArray = response.getJSONArray("result");
            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);
                if (i==0)
                {
                    item.setId(feedObj.getInt("postId"));
                }
                else if(i==1)
                {
                    item.setSuccess(feedObj.getInt("success"));
                }
                else if(i==2)
                {
                    item.setMessage(feedObj.getString("message"));
                }
                else if(i>=3)
                {
                    Iterator<String> iter = feedObj.keys();
                    while (iter.hasNext()) {
                        String key = iter.next();
                        try {
                            JSONObject jobj = (JSONObject) feedObj.get(key);

                            modelPostComment itemrpop = new modelPostComment();
                            itemrpop.setPostcommentId(String.valueOf(jobj.get(ITEM_ID)));
                            itemrpop.setPostcommentTitleId(jobj.isNull(ITEM_TITLEID) ? "" : jobj.getString(ITEM_TITLEID));
                            itemrpop.setLogedInUserName(jobj.isNull(ITEM_USERNAME) ? "" : jobj.getString(ITEM_USERNAME));
                            itemrpop.setSequence(jobj.isNull(ITEM_SEQUENCE) ? "" : jobj.getString(ITEM_SEQUENCE));
                            itemrpop.setPostcommentTitle(jobj.isNull(ITEM_TITLE) ? "" : jobj.getString(ITEM_TITLE));
                            itemrpop.setpostDatetime(jobj.isNull(ITEM_POSTCOMMENTDATETIME) ? "" : jobj.getString(ITEM_POSTCOMMENTDATETIME));
                            itemrpop.setPostcomment(jobj.isNull(ITEM_POSTCOMMENT) ? "" : jobj.getString(ITEM_POSTCOMMENT));
                            postcommentList.add(0, itemrpop);
                            item.setCommentList(postcommentList);

                        } catch (JSONException e) {
                            // Something went wrong!
                        }
                    }
                }
            }
            resultNewPosts.add(item);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultNewPosts;
    }

    //here we maintain our Comments and post in various Titles
//    private int addTitleAndPostsInListView(long intTitleId, String title, String postcomments){
//        int groupPosition = 0;
//        try
//        {
//            //check the hash map if the group already exists
//            Expandable_Post_Comment_HeaderInfo expandablePostCommentHeaderInfo = mypostTitle.get(title);
//            //add the group if doesn't exists
//            if(expandablePostCommentHeaderInfo == null){
//                expandablePostCommentHeaderInfo = new Expandable_Post_Comment_HeaderInfo();
//                expandablePostCommentHeaderInfo.setName(title);
//                mypostTitle.put(title, expandablePostCommentHeaderInfo);
//                titleList.add(expandablePostCommentHeaderInfo);
//
//                /* No need to Add Title into Database table
//                * we already inserted all the default values of titles into database on OncreateAvtivity method
//                * in main activity
//                */
//            }
//
//            //get the children for the group
//            ArrayList<modelPostComment> postcommentList = expandablePostCommentHeaderInfo.getCommentList();
//            //size of the children list
//            int listSize = postcommentList.size();
//            //add to the counter
//            listSize++;
//
//            String formattedDate = df.format(objCalender.getInstance().getTime());
//
//            /* * Write here code to Insert comments and post into databaase */
//            // database handler
//            DatabaseHandler db = new DatabaseHandler(getApplicationContext());
//            lngTitleId = intTitleId;
//            long retid = db.insertPostComment(String.valueOf(listSize),lngTitleId, "Neeraj Goswami",formattedDate, postcomments);
//            /* * */
//
//            //create a new child and add that to the group
//            modelPostComment modelPostComment = new modelPostComment();
//            modelPostComment.setPostcommentId(String.valueOf(retid));
//            modelPostComment.setSequence(String.valueOf(listSize));
//            modelPostComment.setPostcomment(postcomments);
//            modelPostComment.setLogedInUserName("N"); // default Neeraj later on implement the Login User Name
//            modelPostComment.setpostDatetime(formattedDate);
//
//            postcommentList.add(0, modelPostComment);
//            expandablePostCommentHeaderInfo.setCommentList(postcommentList);
//
//            //find the group position inside the list
//            groupPosition = titleList.indexOf(expandablePostCommentHeaderInfo);
//            //return groupPosition;
//            db.close();
//        }
//        catch   (Exception Ex)
//        {
//            Toast.makeText(this,Ex.getMessage() , Toast.LENGTH_LONG).show();
//        }
//        return groupPosition;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadHeaderData() {
        try {
        // database handler
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        // Spinner Drop down elements
        List<String> lables = db.getAllLabels();

        // get a cursor from the database with an "_id" field
        Cursor c = db.getAllTitles();

        if (lables.size()==0)
        {
            // set Default valus of Title for this activity
            db.insertTitle("MaintainanceMain?");
            db.insertTitle("Laundry Facilities?");
            db.insertTitle("Safety and Security?");
            db.insertTitle("Common Areas?");
            db.insertTitle("Home Improvement?");
            db.insertTitle("Parking?");
            db.insertTitle("Fire?");
            db.insertTitle("Renters?");
            db.insertTitle("basic Rules?");
            db.insertTitle("Misc?");

            loadHeaderData();
        }

        // make an adapter from the cursor
        String[] from = new String[] {"title"};
        int[] to = new int[] {android.R.id.text1};
        SimpleCursorAdapter sca = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, c, from, to);

        // set layout for activated adapter
        sca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        // Spinner element
//        Spinner  spinner = (Spinner) this.findViewById(R.id.postTitle);
        // get xml file spinner and set adapter
            spinnerPostCommentHeader.setAdapter(sca);

        // set spinner listener to display the selected item id
//        mContext = this;
            spinnerPostCommentHeader.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                    Toast.makeText(mContext, "Selected ID=" + id, Toast.LENGTH_LONG).show();
                    lngTitleId = id;
                }
                public void onNothingSelected(AdapterView<?> parent) {}
            });

        db.close();
        }
        catch(Exception Ex)
        {
            Toast.makeText(this,Ex.getMessage() ,Toast.LENGTH_LONG).show();
        }
    }
}