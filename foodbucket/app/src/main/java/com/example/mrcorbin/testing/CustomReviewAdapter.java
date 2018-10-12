package com.example.mrcorbin.testing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomReviewAdapter extends BaseAdapter {

    int personImg;
    Context context;
    List<Review> reviewList;
    LayoutInflater inflater;

    public CustomReviewAdapter( List<Review> reviewList, int personImg, Context context) {

        this.reviewList = reviewList;
        this.personImg = personImg;
        this.context = context;
    }

    @Override
    public int getCount() {
        return reviewList.size();
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
            convertView = inflater.inflate(R.layout.sample_review_view, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageViewId);
        TextView textView = convertView.findViewById(R.id.countryNameId);

        TextView textViewFollow = convertView.findViewById(R.id.followId);
        TextView textRating = convertView.findViewById(R.id.rated_badge);
        TextView textReviewMessage = convertView.findViewById(R.id.review);



        textViewFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "follow", Toast.LENGTH_SHORT).show();
            }
        });

        imageView.setImageResource(personImg);
        textView.setText(reviewList.get(position).getReviewerName());
        textRating.setText(String.valueOf(reviewList.get(position).getRatingNumber()));
        textReviewMessage.setText(reviewList.get(position).getReview());

        return convertView;
    }
}
