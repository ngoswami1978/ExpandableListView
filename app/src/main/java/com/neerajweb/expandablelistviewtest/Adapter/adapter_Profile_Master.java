package com.neerajweb.expandablelistviewtest.Adapter;

/**
 * Created by Admin on 07/03/2016.
 */
import java.util.List;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.neerajweb.expandablelistviewtest.Model.modelMemberProfile;
import com.neerajweb.expandablelistviewtest.R;
import com.neerajweb.expandablelistviewtest.Maintainance.ApartmentApplicationController;
import com.neerajweb.expandablelistviewtest.utils.Const;
import com.neerajweb.expandablelistviewtest.utils.ExpandablePanel;
import com.neerajweb.expandablelistviewtest.utils.Utility;

public class adapter_Profile_Master extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<modelMemberProfile> profileItems;
    ImageLoader imageLoader = ApartmentApplicationController.getInstance().getImageLoader();
    Context myContext;
    Drawable[] myTextViewCompoundDrawables;

    NetworkImageView thumbNail;

    public adapter_Profile_Master(Activity activity, List<modelMemberProfile> profileItems) {
        this.activity = activity;
        this.profileItems = profileItems;
        this.myContext=activity;
    }

    @Override
    public int getCount() {
        return profileItems.size();
    }

    @Override
    public Object getItem(int location) {
        return profileItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.profile_master_list_row, null);


        if (position % 2 == 1) {
            convertView.setBackgroundColor(myContext.getResources().getColor(R.color.reportheadercolorwhite));
        } else {
            convertView.setBackgroundColor(myContext.getResources().getColor(R.color.reportheadercolorwhite));
        }

        if (imageLoader == null)
            imageLoader = ApartmentApplicationController.getInstance().getImageLoader();

        thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);

        TextView ownername=(TextView) convertView.findViewById(R.id.OwnerName);
        TextView ownerflatno=(TextView) convertView.findViewById(R.id.OwnerFlatNo);
        TextView owneraddress=(TextView) convertView.findViewById(R.id.OwnerAddress);
        TextView ownerlocation=(TextView) convertView.findViewById(R.id.OwnerLocation);
        TextView ownerphone=(TextView) convertView.findViewById(R.id.OwnerPhone);
        TextView owneremail=(TextView) convertView.findViewById(R.id.OwnerEmail);

        TextView renterheader=(TextView) convertView.findViewById(R.id.Renterheader);
        TextView rentername=(TextView) convertView.findViewById(R.id.RenterName);
        TextView renteraddress=(TextView) convertView.findViewById(R.id.RenterAddress);
        TextView renterlocation=(TextView) convertView.findViewById(R.id.RenterLocation);
        TextView renterphone=(TextView) convertView.findViewById(R.id.RenterPhone);
        TextView renteremail=(TextView) convertView.findViewById(R.id.RenterEmail);

        TextView maintainanceheader = (TextView) convertView.findViewById(R.id.maintainanceHeader);
        TextView lstpaymentamountheader     = (TextView) convertView.findViewById(R.id.lstpaymentamountHeader);
        TextView lstpaymentmonthyearheader  = (TextView) convertView.findViewById(R.id.lstpaymentmonthyearHeader);
        TextView lstpaymentdateheader        = (TextView) convertView.findViewById(R.id.lstpaymentdateHeader);

        TextView lastpaidamount=(TextView) convertView.findViewById(R.id.lstPaidAmount);
        TextView lastpaidmonthyear=(TextView) convertView.findViewById(R.id.lstPaidMonthYear);
        TextView lastpaiddate=(TextView) convertView.findViewById(R.id.lstPaidDate);


        // getting movie data for the row
        modelMemberProfile m = profileItems.get(position);

        // thumbnail image
        if (!m.getThumbnailUrl().isEmpty()){
            thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
        }
        else  {
            thumbNail.setImageUrl(Const.URL_WS_EMPTYUSERPROFILE, imageLoader);
        }

        // Owner Name
        ownername.setText(m.getOwner_name());
        setCustomFontface(m.getCustomFontName(), ownername);

        //remove drawable image from Owner Name TextView i.e greentick mark//
        myTextViewCompoundDrawables = ownername.getCompoundDrawables();

        if(m.getapprove_status().equals("0"))
        {
            // code later on
            convertView.setBackgroundColor(myContext.getResources().getColor(R.color.list_row_notapprovedcolor));
        }

        ownerflatno.setText(m.getflt_no() +"\n" +"[" + m.getflt_type() + "]");
        setCustomFontface(m.getCustomFontName(), ownerflatno);

        owneraddress.setText(m.getOwner_Address());
        setCustomFontface(m.getCustomFontName(), owneraddress);

        ownerlocation.setText(m.getOwner_Location());
        setCustomFontface(m.getCustomFontName(), ownerlocation);

        ownerphone.setText(m.getOwner_contact());
        setCustomFontface(m.getCustomFontName(), ownerphone);

        owneremail.setText(m.getemail());
        setCustomFontface(m.getCustomFontName(), owneremail);

        if ((m.getRenter_name().trim().equals("")) || (m.getRenter_name().trim().equals(" ")))
        {
            renterheader.setVisibility(convertView.GONE);
            rentername.setVisibility(convertView.GONE);
            renteraddress.setVisibility(convertView.GONE);
            renterlocation.setVisibility(convertView.GONE);
            renterphone.setVisibility(convertView.GONE);
            renteremail.setVisibility(convertView.GONE);
        }
        else
        {
            renterheader.setVisibility(convertView.VISIBLE);
            rentername.setVisibility(convertView.VISIBLE);
            renteraddress.setVisibility(convertView.VISIBLE);
            renterlocation.setVisibility(convertView.VISIBLE);
            renterphone.setVisibility(convertView.VISIBLE);
            renteremail.setVisibility(convertView.VISIBLE);

            setCustomFontface(m.getCustomFontName(), renterheader);

            rentername.setText(m.getRenter_name());
            setCustomFontface(m.getCustomFontName(), rentername);

            renteraddress.setText(m.getRenter_Address());
            setCustomFontface(m.getCustomFontName(), renteraddress);

            renterlocation.setText(m.getRenter_Location());
            setCustomFontface(m.getCustomFontName(), renterlocation);

            renterphone.setText(m.getRenter_contact());
            setCustomFontface(m.getCustomFontName(), renterphone);

            renteremail.setText(m.getRenter_email());
            setCustomFontface(m.getCustomFontName(), renteremail);
        }

        setCustomFontface(m.getCustomFontName(), maintainanceheader);
        setCustomFontface(m.getCustomFontName(), lstpaymentamountheader);
        setCustomFontface(m.getCustomFontName(), lstpaymentmonthyearheader);
        setCustomFontface(m.getCustomFontName(), lstpaymentdateheader);

        if (m.getLastPaidAmount().equals(""))
            lastpaidamount.setText("");
        else
            lastpaidamount.setText(myContext.getResources().getString(R.string.Rs) + "." + m.getLastPaidAmount());


        setCustomFontface(m.getCustomFontName(), lastpaidamount);

        if (m.getLastPaidMonth().equals("")){
            lastpaidmonthyear.setText("");
        }
        else{
            lastpaidmonthyear.setText(m.getLastPaidMonth()+","+m.getLastPaidYear());
            setCustomFontface(m.getCustomFontName(), lastpaidmonthyear);
        }

        lastpaiddate.setText(m.getLastPaidEntrydt());
        setCustomFontface(m.getCustomFontName(), lastpaiddate);

        return convertView;
    }
    private void setCustomFontface(String fontName,TextView tview)
    {
        try
        {
            if (!fontName.isEmpty()) {
                // Font path
                //String fontPath = "fonts/gtw.ttf";

                String fontPath = "fonts/" + fontName;

                // Loading Font Face
                Typeface tf = Typeface.createFromAsset(myContext.getAssets(), fontPath);

                // Applying font
                tview.setTypeface(tf);
            }
        }
        catch(Exception Ex)
        {
            //Toast.makeText(this, Ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}