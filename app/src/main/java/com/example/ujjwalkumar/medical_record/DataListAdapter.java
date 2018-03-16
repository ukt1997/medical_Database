package com.example.ujjwalkumar.medical_record;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Ujjwal Kumar on 12-03-2018.
 */

public class DataListAdapter extends BaseAdapter {

    private Context context ;
    private int layout;
    private ArrayList<Data> datalist;

    public DataListAdapter(Context context, int layout, ArrayList<Data> datalist) {
        this.context = context;
        this.layout = layout;
        this.datalist = datalist;
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder
    {

        ImageView imageview;
        //TextView nameview,addview,ageview,phoneview;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row=view;
        final ViewHolder holder;
        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            //row=LayoutInflater.from(context).inflate(layout, null);
            holder=new ViewHolder();

            //System.err.print("\n ----------------------if start-----------------");
            //holder.nameview = (TextView) row.findViewById(R.id.grid_name);
           // holder.addview = (TextView) row.findViewById(R.id.grid_add);
           // holder.ageview = (TextView) row.findViewById(R.id.grid_age);
            //holder.phoneview = (TextView) row.findViewById(R.id.grid_phone);
            holder.imageview = (ImageView) row.findViewById(R.id.grid_image);
            row.setTag(holder);
        }
        else {

            //System.err.print("\n ----------------------else -----------------");
            holder = (ViewHolder)row.getTag();
        }


        Data d = datalist.get(position);

        //holder.nameview.setText(d.getName());
       // holder.phoneview.setText(d.getMobile());
       // holder.ageview.setText(d.getAge());
        //holder.addview.setText(d.getAddress());
        //holder.phoneview.setText(d.getMobile());

        byte[] presImage = d.getImage();

        Bitmap bitmap = BitmapFactory.decodeByteArray(presImage, 0, presImage.length);
        holder.imageview.setImageBitmap(bitmap);

        return row;
    }
}
