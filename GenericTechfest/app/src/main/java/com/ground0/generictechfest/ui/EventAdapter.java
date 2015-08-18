package com.ground0.generictechfest.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ground0.generictechfest.R;
import com.ground0.generictechfest.network.model.WorkshopList;
import com.ground0.generictechfest.network.model.Workshops;

import java.util.List;

/**
 * Created by Arjun on 02-02-2015.
 */
public class EventAdapter extends BaseAdapter {

    List<WorkshopList> workshopList;
    private Context context;
    private LayoutInflater layoutInflater;

    public EventAdapter(Context context,List<WorkshopList> workshopList){
        this.context=context;
        this.workshopList = workshopList;
    }
    @Override
    public int getCount() {
        if(workshopList ==null)
            return 0;
        else
            return workshopList.size();
    }

    @Override
    public Object getItem(int position) {
        if(workshopList ==null)
            return null;
        else
            return workshopList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            if(layoutInflater==null)
                layoutInflater=LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.item_layout,parent,false);

            ViewHolder viewHolder=new ViewHolder();
            viewHolder.title= (TextView) convertView.findViewById(R.id.workshopName);
            viewHolder.description=(TextView)convertView.findViewById(R.id.workshopDescription);
            viewHolder.date=(TextView)convertView.findViewById(R.id.workshopDate);
            convertView.setTag(viewHolder);
        }

        ViewHolder viewHolder=(ViewHolder) convertView.getTag();
        WorkshopList workshopList =(WorkshopList)getItem(position);
        if(workshopList !=null)
        {
            viewHolder.title.setText(workshopList.getName());
            Log.i("com.ground0.generictechfest",workshopList.getName());
            viewHolder.description.setText(workshopList.getDescription());
            viewHolder.date.setText(workshopList.getDate());
        }
        return convertView;

    }


    static class ViewHolder{
        TextView title,description,date;

    }
}
