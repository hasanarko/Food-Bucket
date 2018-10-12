package com.example.mrcorbin.testing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.List;

public class NewlyOpenedAdapter extends RecyclerView.Adapter<NewlyOpenedAdapter.MyViewHolder> {


    private Activity mContext;
    private List<NewlyOpened> mDate;
    DatabaseHelper databaseHelper;

    public NewlyOpenedAdapter(Activity mContext, List<NewlyOpened> mDate) {
        this.mContext = mContext;
        this.mDate = mDate;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflator = LayoutInflater.from(mContext);
        view = mInflator.inflate(R.layout.newly_opened_res_sample_view,parent,false);
        return new NewlyOpenedAdapter.MyViewHolder(view);
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        try{


            holder.tvNewlyOpenRestName.setText(mDate.get(position).getRestName());
            holder.tvNewlyOpenedRestAddress.setText(mDate.get(position).getRestAddress());
//            holder.tvRestRating.setText(mDate.get(position).getRestRating());
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
        holder.newlyOpenedCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Cursor cursor = databaseHelper.queryOnRestaurantTable()

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

        TextView tvNewlyOpenRestName,tvNewlyOpenedRestAddress;
        ImageView tvRestImage;
        CardView newlyOpenedCardview;
        public MyViewHolder(View itemView) {
            super(itemView);


            tvNewlyOpenRestName = (TextView) itemView.findViewById(R.id.new_res_title_id);
            tvNewlyOpenedRestAddress = (TextView) itemView.findViewById(R.id.new_res_desc_id);
            tvRestImage = (ImageView) itemView.findViewById(R.id.new_res_pic_id);
            newlyOpenedCardview = (CardView) itemView.findViewById(R.id.newly_opened_cardview_id);
        }
    }
}
