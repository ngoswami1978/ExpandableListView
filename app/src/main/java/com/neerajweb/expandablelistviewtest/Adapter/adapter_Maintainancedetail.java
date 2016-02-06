package com.neerajweb.expandablelistviewtest.Adapter;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.neerajweb.expandablelistviewtest.Model.modelMaintainancedetail;
import com.neerajweb.expandablelistviewtest.R;

/**
 * Created by Admin on 14/01/2016.
 */

public class adapter_Maintainancedetail extends BaseAdapter implements View.OnClickListener {

    private ArrayList<modelMaintainancedetail> listData;
    private LayoutInflater layoutInflater;
    Context mcontext;
    private Dialog dialog;


    public adapter_Maintainancedetail(Context context, ArrayList<modelMaintainancedetail> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        mcontext = context;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_rptmaintainance_detail_row_view, null);
            holder = new ViewHolder();

            holder.Dtl_id = (TextView) convertView.findViewById(R.id.mta_dtl_id);
            holder.Srno= (TextView) convertView.findViewById(R.id.mta_SrNo);
            holder.Flatnumber= (TextView) convertView.findViewById(R.id.mta_FlatNo);
            holder.Flattype= (TextView) convertView.findViewById(R.id.mta_FlatType);
            holder.Ownername= (TextView) convertView.findViewById(R.id.mta_FlatOwnerName) ;
            holder.Ownercontact= (TextView) convertView.findViewById(R.id.mta_FlatOwnerContact);
            holder.Rentername= (TextView) convertView.findViewById(R.id.mta_FlatRenterName);
            holder.Rentercontact= (TextView) convertView.findViewById(R.id.mta_FlatRenterContact);
            holder.PaidAmount= (TextView) convertView.findViewById(R.id.mta_AmtPaid);
            holder.PaidEntrydt= (TextView) convertView.findViewById(R.id.mta_paiddatetime);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.Dtl_id.setText(String.valueOf(listData.get(position).getdtlid()));
        holder.Srno.setText(String.valueOf(listData.get(position).getSrno()));
        holder.Flatnumber.setText(listData.get(position).getflatnumber());
        holder.Flattype.setText(listData.get(position).getflattype());
        holder.Ownername.setText(listData.get(position).getownername());
        holder.Ownercontact.setText(listData.get(position).getOwnercontact());
        holder.Rentername.setText(listData.get(position).getrentername());
        holder.Rentercontact.setText(listData.get(position).getRentercontact());
        holder.PaidAmount.setText(listData.get(position).getPaidAmount());
        holder.PaidEntrydt.setText(listData.get(position).getPaidEntrydt());

        convertView.setOnClickListener(new OnItemClickListener(position));

        return convertView;
    }

    @Override
    public void onClick(View v) {
        Log.v("CustomAdapter", "=====Row button clicked=====");
    }

    /********* Called when Item click in ListView ************/
    private class OnItemClickListener  implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
//            Toast.makeText(mcontext, "You Clicked position : " + position, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onClick(View arg0) {
            // Call when click on row rptmaintainancedetail
//            CustomDialogMaintainance sct = (CustomDialogMaintainance) dialog;
//            /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/
//            sct.onItemClick(mPosition);
        }
    }

    static class ViewHolder {
        TextView Dtl_id;
        TextView Srno;
        TextView Flatnumber;
        TextView Flattype;
        TextView Ownername ;
        TextView Ownercontact;
        TextView Rentername;
        TextView Rentercontact;
        TextView PaidAmount;
        TextView PaidEntrydt;

    }
}
