package com.example.mrcorbin.testing;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.util.List;

public class TopRestaurantsAdapter extends RecyclerView.Adapter<TopRestaurantsAdapter.MyViewHolder> {


    private Context mContext;
    private List<TopRestaurants> mDate;

    public TopRestaurantsAdapter(Context mContext, List<TopRestaurants> mDate) {
        this.mContext = mContext;
        this.mDate = mDate;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflator = LayoutInflater.from(mContext);
        view = mInflator.inflate(R.layout.top_rated_res_cardview,parent,false);
        return new TopRestaurantsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {



        try{


            holder.tvTopRestName.setText(mDate.get(position).getRestName());
            holder.tvTopRestAddress.setText(mDate.get(position).getRestAddress());
            holder.tvToprestRating.setText(String.valueOf(new DecimalFormat("0.0").format(mDate.get(position).getRestRating())));


            byte[] byteArray = mDate.get(position).getRestImage();

            ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
            Bitmap image = BitmapFactory.decodeStream(imageStream);
            holder.tvRestImage.setImageBitmap(image);
        }
        catch(Exception e){
            e.printStackTrace();
        }


        //set click listener
        //passing data to the book activity
        holder.topRestCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(mContext,BookActivity.class);
                Intent intent = new Intent(mContext,BookActivity.class);
                intent.putExtra("res_id",mDate.get(position).getRestId());
                intent.putExtra("Title",mDate.get(position).getRestName());
                intent.putExtra("Email", mDate.get(position).getRestEmail());
                intent.putExtra("Details",mDate.get(position).getRestAddress());
                intent.putExtra("thumbnail",mDate.get(position).getRestImage());
                intent.putExtra("phoneNumber",mDate.get(position).getRestPhone());
                intent.putExtra("restStatus",mDate.get(position).getRestStatus());
                intent.putExtra("openingTime",mDate.get(position).getRestOpeningTime());
                intent.putExtra("closingTime",mDate.get(position).getRestClosingTime());
                intent.putExtra("Badge",mDate.get(position).getRestRating());

                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mDate.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvTopRestName,tvTopRestAddress,tvToprestRating;
       // TextView tv_top_res_desc;
        ImageView tvRestImage;
        CardView topRestCardview;


        public MyViewHolder(View itemView) {
            super(itemView);


            tvRestImage = (ImageView) itemView.findViewById(R.id.res_pic_id);

            tvTopRestName = (TextView) itemView.findViewById(R.id.res_title_id);
            tvTopRestAddress = (TextView) itemView.findViewById(R.id.res_desc_id);
            topRestCardview = (CardView) itemView.findViewById(R.id.top_rest_cardview_id);
            tvToprestRating = (TextView) itemView.findViewById(R.id.top_res_rating_id);

        }
    }



}
