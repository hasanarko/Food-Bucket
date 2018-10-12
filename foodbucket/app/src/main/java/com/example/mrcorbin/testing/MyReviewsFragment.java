package com.example.mrcorbin.testing;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MyReviewsFragment extends Fragment {

    private int restId;
    private int foodiesId;
    DatabaseHelper databaseHelper;

    List<Review> listMyReview;


    public MyReviewsFragment(){

    }


    @SuppressLint("ValidFragment")
    public MyReviewsFragment(int restId,int foodiesId){
        this.restId = restId;
        this.foodiesId = foodiesId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_reviews, container, false);

        ListView listView = view.findViewById(R.id.myreviewId);
        final int personImg = R.drawable.p_icon;


        databaseHelper = new DatabaseHelper(getContext());
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();


        listMyReview = databaseHelper.findMyReviews(restId,foodiesId);
        listView.setAdapter(new CustomMyReviewAdapter(listMyReview,personImg,getActivity()));
        //Toast.makeText(getContext(), String.valueOf(foodiesId), Toast.LENGTH_SHORT).show();




        return view;
    }
}
