package com.example.mrcorbin.testing;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomCuisineAdapter extends BaseAdapter {

    private Activity context;
    private List<MenuItem> menu;
    //String count;

    private LayoutInflater inflater;
    DatabaseHelper databaseHelper;




    public CustomCuisineAdapter(Activity context, List<MenuItem> menu) {
        this.context = context;
        this.menu = menu;


       // databaseHelper = new DatabaseHelper(this);

    }

    @Override
    public int getCount() {
        return menu.size();
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
            convertView = inflater.inflate(R.layout.cuisine_list_layout, parent, false);
        }




        // find view by id
        TextView menuItemName = convertView.findViewById(R.id.cuisine_names);
        TextView total = convertView.findViewById(R.id.total);

        // get name of cuisine
        String menuName = menu.get(position).getCuisineName();
//
//        if(menuName.equals("american")){
//
//        }

        String count = menu.get(position).getTotalRest();

        menuItemName.setText(menuName);
        total.setText(count);


        return convertView;


    }
}
