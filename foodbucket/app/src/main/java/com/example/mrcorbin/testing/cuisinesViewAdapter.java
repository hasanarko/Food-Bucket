package com.example.mrcorbin.testing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class cuisinesViewAdapter extends BaseAdapter {

    Context context;
    String[] cuisines_name;
    int[] cuisines_pic;
    LayoutInflater inflater;

    public cuisinesViewAdapter(Context context, String[] cuisines_name, int[] cuisines_pic) {
        this.context = context;
        this.cuisines_name = cuisines_name;
        this.cuisines_pic = cuisines_pic;
    }

    @Override
    public int getCount() {
        return cuisines_name.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cuisine_sample_view, parent, false);
        }

        ImageView img = convertView.findViewById(R.id.cusine_image_id);
        img.setImageResource(cuisines_pic[position]);

        return convertView;
    }
}
