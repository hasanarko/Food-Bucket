package com.example.mrcorbin.testing;

import android.content.Context;
import android.content.Intent;
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

import java.io.ByteArrayInputStream;
import java.util.List;

public class NearByAdapter extends RecyclerView.Adapter<NearByAdapter.MyViewHolder>{

    private Context mContext;
    private List<NearbyRestaurants> mDate;
    Bitmap image;

    public NearByAdapter(Context mContext, List<NearbyRestaurants> mDate) {
        this.mContext = mContext;
        this.mDate = mDate;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflator = LayoutInflater.from(mContext);
        view = mInflator.inflate(R.layout.nearby_sample_view,parent,false);
        return new NearByAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
       // holder.tvNearbyRestName.setText(mDate.get(position).getRestName());
       // holder.tvRestImage.setImageResource(mDate.get(position).getRestImage());
        try{


            holder.tvNearbyRestName.setText(mDate.get(position).getRestName());
            holder.tvNearbyRestAddress.setText(mDate.get(position).getRestAddress());
//            holder.tvRestRating.setText(mDate.get(position).getRestRating());
            byte[] byteArray = mDate.get(position).getRestImage();

            ByteArrayInputStream imageStream = new ByteArrayInputStream(byteArray);
           image = BitmapFactory.decodeStream(imageStream);
            holder.tvRestImage.setImageBitmap(image);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        //set click listener
        //passing data to the book activity
        holder.nearByCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,BookActivity.class);
                intent.putExtra("Title",mDate.get(position).getRestName());
                intent.putExtra("Description",mDate.get(position).getRestAddress());
                intent.putExtra("thumbnail",mDate.get(position).getRestImage());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDate.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView tvNearbyRestName,tvNearbyRestAddress;
        ImageView tvRestImage;
        CardView nearByCardview;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvNearbyRestName = (TextView) itemView.findViewById(R.id.nearby_rest_title_id);
            tvRestImage = (ImageView) itemView.findViewById(R.id.nearby_rest_pic_id);
            tvNearbyRestAddress = (TextView) itemView.findViewById(R.id.nearby_res_desc_id);
            nearByCardview = (CardView) itemView.findViewById(R.id.nearby_cardview_id);
        }
    }
}
