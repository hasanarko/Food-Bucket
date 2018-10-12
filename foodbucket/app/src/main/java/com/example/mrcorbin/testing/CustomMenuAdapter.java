package com.example.mrcorbin.testing;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomMenuAdapter extends BaseAdapter {

    private Activity context;
    private List<MenuItem> menu;
    private LayoutInflater inflater;

    public CustomMenuAdapter(Activity context, List<MenuItem> menu) {
        this.context = context;
        this.menu = menu;
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
            convertView = inflater.inflate(R.layout.menu_list_layout, parent, false);
        }

        // find view by id
        TextView menuItemName = convertView.findViewById(R.id.textViewMenuName);

        // get name of cuisine
        String menuName = menu.get(position).getCuisineName();
        menuItemName.setText(menuName);

        return convertView;


    }
}
