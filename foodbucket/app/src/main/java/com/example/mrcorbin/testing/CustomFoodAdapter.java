package com.example.mrcorbin.testing;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class CustomFoodAdapter extends BaseAdapter {

    private Activity context;
    private List<Food> foodItem;
    private LayoutInflater inflater;

    public CustomFoodAdapter(Activity context, List<Food> foodItem) {
        this.context = context;
        this.foodItem = foodItem;
    }

    @Override
    public int getCount() {
        return foodItem.size();
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
            convertView = inflater.inflate(R.layout.food_desc, parent, false);
        }

        TextView textViewFoodName = convertView.findViewById(R.id.food_name_id);
        TextView textViewFoodPrice = convertView.findViewById(R.id.price_id);
        TextView textViewFoodRating = convertView.findViewById(R.id.rating_id);
        TextView textViewFoodDetails = convertView.findViewById(R.id.details_id);

        textViewFoodName.setText(foodItem.get(position).getFoodName());
        textViewFoodPrice.setText(String.valueOf(foodItem.get(position).getFoodPrice())+" BDT");
        double rating = (foodItem.get(position).getFoodRating());


        textViewFoodRating.setText(String.valueOf(new DecimalFormat("0.0").format(rating)));
        textViewFoodDetails.setText(foodItem.get(position).getFoodDescription());

        return convertView;
    }
}
