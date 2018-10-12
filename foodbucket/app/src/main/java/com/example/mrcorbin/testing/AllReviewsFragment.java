package com.example.mrcorbin.testing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class AllReviewsFragment extends Fragment {
    private int restId;
    List<Review> listReview;
    DatabaseHelper databaseHelper;

    public AllReviewsFragment(){

    }


    @SuppressLint("ValidFragment")
    public AllReviewsFragment(int restId){
            this.restId = restId;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_reviews, container, false);

        databaseHelper = new DatabaseHelper(getContext());
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();


        ListView listView = view.findViewById(R.id.allreviewId);
        final int personImg = R.drawable.p_icon;
        final String[] personName = {"John", "Michael", "Mary", "Robin"};


        listReview = databaseHelper.findReviews(restId);


         listView.setAdapter(new CustomReviewAdapter(listReview,personImg,getActivity()));







        return view;
    }


}
