package com.example.babar.ekel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.ArrayList;


public class RowAdapter extends ArrayAdapter<DataModel>  {

    private Context context;
    private int layoutResourceId;
    private ArrayList<DataModel> data = null;


    RowAdapter(Context context, int layoutResourceId, ArrayList<DataModel> data) {
        super(context,layoutResourceId,data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        RowBeanHolder rowBeanHolder;

        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,parent,false);

            rowBeanHolder = new RowBeanHolder();
            rowBeanHolder.imgIcon = row.findViewById(R.id.imgIcon);
            rowBeanHolder.name = row.findViewById(R.id.name);
            rowBeanHolder.price = row.findViewById(R.id.price);

            row.setTag(rowBeanHolder);
        } else {
            rowBeanHolder = (RowBeanHolder) row.getTag();
        }
        DataModel object = data.get(position);
        rowBeanHolder.name.setText(object.getName());
        rowBeanHolder.imgIcon.setImageResource(object.ImageID);
        rowBeanHolder.price.setText(String.valueOf(object.getPrice()));
        if (object.isAvailable()) {
            rowBeanHolder.price.setTextColor(Color.GREEN);
        }else {
            rowBeanHolder.price.setTextColor(Color.RED);
        }
        return row;
    }

    private static class RowBeanHolder
    {
        ImageView imgIcon;
        TextView name;
        TextView price;
    }
}
