package com.example.mrcorbin.testing;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class cusineAdapter extends RecyclerView.Adapter<cusineAdapter.MyViewHolder> {

    private Context mContext;
    private List<Cusine> mDate;

    public cusineAdapter(Context mContext, List<Cusine> mDate) {
        this.mContext = mContext;
        this.mDate = mDate;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflator = LayoutInflater.from(mContext);
        view = mInflator.inflate(R.layout.cuisine_sample_view,parent,false);
        return new cusineAdapter.MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {


        holder.cusine_image_tumbnail.setImageResource(mDate.get(position).getCuisinePics());

        //set click listener
        //passing data to the book activity
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(mContext,BookActivity.class);
                //intent.putExtra("Title",mDate.get(position).getTitle());
                //intent.putExtra("Description",mDate.get(position).getDescription());
                //intent.putExtra("thumbnail",mDate.get(position).getThumbnail());

               // mContext.startActivity(intent);

                //Toast.makeText(mContext, mDate.get(position).getCuisineName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext,CuisineActivity.class);
                intent.putExtra("cuisine_name",mDate.get(position).getCuisineName());
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mDate.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{


        ImageView cusine_image_tumbnail;
        CardView cardview;

        public MyViewHolder(View itemView) {
            super(itemView);


            cusine_image_tumbnail = (ImageView) itemView.findViewById(R.id.cusine_image_id);
            cardview = (CardView) itemView.findViewById(R.id.cusine_cardview_id);
        }
    }
}
