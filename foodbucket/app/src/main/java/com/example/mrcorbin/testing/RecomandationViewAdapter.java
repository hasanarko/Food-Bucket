package com.example.mrcorbin.testing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.util.List;

public class RecomandationViewAdapter extends RecyclerView.Adapter<RecomandationViewAdapter.MyViewHolder> {




    private Context mContext;
    private List<Restaurants> mDate;

    public RecomandationViewAdapter(Context mContext, List<Restaurants> mDate) {
        this.mContext = mContext;
        this.mDate = mDate;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflator = LayoutInflater.from(mContext);
        view = mInflator.inflate(R.layout.recomandation_sample_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecomandationViewAdapter.MyViewHolder holder, final int position) {
      //  holder.tvRestImage.setImageResource(mDate.get(position).getRestImage());



        try{


            holder.tvRestName.setText(mDate.get(position).getRestName());
            holder.tvRestAddress.setText(mDate.get(position).getRestAddress());
           holder.tvRestRating.setText(String.valueOf(new DecimalFormat("0.0").format(mDate.get(position).getRestRating())));




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
        holder.recomandCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        TextView tvRestName,tvRestAddress,tvRestRating;
        ImageView tvRestImage;
        CardView recomandCardview;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvRestName = (TextView) itemView.findViewById(R.id.recomand_rest_title_id);
            tvRestRating = (TextView) itemView.findViewById(R.id.recomand_rest_rating_id);
            tvRestImage = (ImageView) itemView.findViewById(R.id.recomand_rest_pic_id);
            tvRestAddress = (TextView) itemView.findViewById(R.id.rest_address_id);
            recomandCardview = (CardView) itemView.findViewById(R.id.recomand_cardview_id);
        }
    }
}
