package com.example.mrcorbin.testing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.util.List;

public class CustomTotalListAdapter extends BaseAdapter {

    private Activity context;
    private List<Restaurants> rest;
    private LayoutInflater inflater;

    ImageView restImage;
    TextView restName,restDetails,restRating,foodPrice;
    private String type;

    public CustomTotalListAdapter(Activity context, List<Restaurants> rest) {
        this.context = context;
        this.rest = rest;

    }

    @Override
    public int getCount() {
        return rest.size();
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
        if (convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.total_rest_list_layout, parent, false);
        }

        //foodPrice= convertView.findViewById(R.id.food_price);
        restImage = convertView.findViewById(R.id.image_id);
        restRating = convertView.findViewById(R.id.rating_id);
        restDetails = convertView.findViewById(R.id.desc_id);
        restName = convertView.findViewById(R.id.name_id);


        byte[] image = rest.get(position).getRestImage();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
        Bitmap rest_img = BitmapFactory.decodeStream(imageStream);
        restImage.setImageBitmap(rest_img);


        restName.setText(rest.get(position).getRestName());
        restDetails.setText(rest.get(position).getRestAddress());

        if(rest.get(position).getRestRating() == 0.0){
            restRating.setText("new");
            int color = Color.rgb(164,164,164);
            restRating.setBackgroundColor(color);
            restRating.setBackgroundResource(R.drawable.new_restaurants_badge);




        }else{
            restRating.setText(String.valueOf(new DecimalFormat("0.0").format(rest.get(position).getRestRating())));
            restRating.setBackgroundResource(R.drawable.rating_badge);
        }
           // foodPrice.setText(String.valueOf(rest.get(position).getFoodPrice()));


        return convertView;


    }
}
