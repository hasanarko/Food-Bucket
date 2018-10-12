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

public class SearchViewAdapter extends BaseAdapter {

    private Activity context;
    private List<Restaurants> rest;

    private LayoutInflater inflater;

    ImageView restImage;
    TextView restName,restDetails,restRating,foodPrice;
    private String type;

    public SearchViewAdapter(Activity context, List<Restaurants> rest,String type) {
        this.context = context;
        this.rest = rest;
        this.type = type;
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
            convertView = inflater.inflate(R.layout.search_list_layout, parent, false);
        }

        foodPrice= convertView.findViewById(R.id.food_price);
        restImage = convertView.findViewById(R.id.r_image_id);
        restRating = convertView.findViewById(R.id.r_rating_id);
        restDetails = convertView.findViewById(R.id.r_desc_id);
        restName = convertView.findViewById(R.id.r_name_id);


//
//        restImage.setImageResource(R.drawable.rest);
//        restRating.setText("0.0");
//        restName.setText("rest");
//        restDetails.setText("....");



        if(type.equals(" food")){
            byte[] image = rest.get(position).getRestImage();
            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            Bitmap restImg = BitmapFactory.decodeStream(imageStream);
            restImage.setImageBitmap(restImg);


            restName.setText(rest.get(position).getRestName());
            restDetails.setText(rest.get(position).getRestAddress());

            if (rest.get(position).getRestRating() == 0.0) {
                restRating.setText("new");
                int color = Color.rgb(164, 164, 164);
                restRating.setBackgroundColor(color);
                restRating.setBackgroundResource(R.drawable.new_restaurants_badge);
            }
            else {
                restRating.setText(String.valueOf(new DecimalFormat("0.0").format(rest.get(position).getRestRating())));
                restRating.setBackgroundResource(R.drawable.rating_badge);
            }

            foodPrice.setText(String.valueOf(rest.get(position).getFoodPrice())+" BDT");
        }
        else if(type.equals("restaurant")){

            byte[] image = rest.get(position).getRestImage();
            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            Bitmap restImg = BitmapFactory.decodeStream(imageStream);
            restImage.setImageBitmap(restImg);

            restName.setText(rest.get(position).getRestName());
            restDetails.setText(rest.get(position).getRestAddress());

            if (rest.get(position).getRestRating() == 0.0) {
                restRating.setText("new");

                int color = Color.rgb(164, 164, 164);
                restRating.setBackgroundColor(color);
                restRating.setBackgroundResource(R.drawable.new_restaurants_badge);
            }
            else {
                restRating.setText(String.valueOf(new DecimalFormat("0.0").format(rest.get(position).getRestRating())));
                restRating.setBackgroundResource(R.drawable.rating_badge);
            }
            foodPrice.setVisibility(View.INVISIBLE);
        }else if(type.equals("cuisine")){

            byte[] image = rest.get(position).getRestImage();
            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            Bitmap restImg = BitmapFactory.decodeStream(imageStream);
            restImage.setImageBitmap(restImg);

            restName.setText(rest.get(position).getRestName());
            restDetails.setText(rest.get(position).getRestAddress());

            if (rest.get(position).getRestRating() == 0.0) {
                restRating.setText("new");

                int color = Color.rgb(164, 164, 164);
                restRating.setBackgroundColor(color);
                restRating.setBackgroundResource(R.drawable.new_restaurants_badge);
            }
            else {
                restRating.setText(String.valueOf(new DecimalFormat("0.0").format(rest.get(position).getRestRating())));
                restRating.setBackgroundResource(R.drawable.rating_badge);
            }
            foodPrice.setVisibility(View.INVISIBLE);
        }



        return convertView;


    }
}
