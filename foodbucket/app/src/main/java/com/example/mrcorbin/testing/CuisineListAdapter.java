package com.example.mrcorbin.testing;

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

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.util.List;

public class CuisineListAdapter extends BaseAdapter {


    List<Restaurants> res_name;
    Context context;
    private LayoutInflater inflater;

    public CuisineListAdapter(List<Restaurants> res_name, Context context) {

        this.res_name = res_name;
        this.context = context;
    }

    @Override
    public int getCount() {
        return res_name.size();
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
            convertView = inflater.inflate(R.layout.cuisinelist_sample_view, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.cuisineimageViewId);
        TextView textView_restaurant_title = convertView.findViewById(R.id.restaurant_list_id);
        TextView textView_rest_address = convertView.findViewById(R.id.restaurant_description_id);
        TextView textView_rest_rating = convertView.findViewById(R.id.restaurant_number_rating);
        //TextView textView_restaurant_description = convertView.findViewById(R.id.restaurant_list_DescriptionId);

        //imageView.setImageResource(res_pic[position]);
        //setting image for list
        byte[] image = res_name.get(position).getRestImage();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
        Bitmap restImage = BitmapFactory.decodeStream(imageStream);
        imageView.setImageBitmap(restImage);

        textView_restaurant_title.setText(res_name.get(position).getRestName());
        textView_rest_address.setText(res_name.get(position).getRestAddress());

        if(res_name.get(position).getRestRating() == 0.0){
            textView_rest_rating.setText("new");
            int color = Color.rgb(164,164,164);
            textView_rest_rating.setBackgroundColor(color);
            textView_rest_rating.setBackgroundResource(R.drawable.new_restaurants_badge);




        }else{
            //restRating.setText();
            textView_rest_rating.setText(String.valueOf(new DecimalFormat("0.0").format(res_name.get(position).getRestRating())));
            textView_rest_rating.setBackgroundResource(R.drawable.rating_badge);
        }



        return convertView;
    }
}
