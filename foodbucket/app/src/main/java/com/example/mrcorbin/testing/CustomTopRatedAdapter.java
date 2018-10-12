package com.example.mrcorbin.testing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.util.List;

public class CustomTopRatedAdapter extends BaseAdapter {


    List<Restaurants> res;
    Context context;
    private LayoutInflater inflater;

    public CustomTopRatedAdapter(List<Restaurants> res, Context context) {

        this.res = res;
        this.context = context;
    }

    @Override
    public int getCount() {
        return res.size();
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
            convertView = inflater.inflate(R.layout.topratedlist_sample_view, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.toprated_rest_image);
        TextView textView_restaurant_title = convertView.findViewById(R.id.toprated_rest_name);
        TextView textView_rest_address = convertView.findViewById(R.id.toprated_rest_address);
        TextView textView_rest_rating = convertView.findViewById(R.id.toprated_rest_rating);
        //TextView textView_restaurant_description = convertView.findViewById(R.id.restaurant_list_DescriptionId);

        //imageView.setImageResource(res_pic[position]);
        //setting image for list
        byte[] image = res.get(position).getRestImage();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
        Bitmap restImage = BitmapFactory.decodeStream(imageStream);
        imageView.setImageBitmap(restImage);

        textView_restaurant_title.setText(res.get(position).getRestName());
        textView_rest_address.setText(res.get(position).getRestAddress());
        textView_rest_rating.setText(String.valueOf(new DecimalFormat("0.0").format(res.get(position).getRestRating())));

        return convertView;
    }
}
