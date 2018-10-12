package com.example.mrcorbin.testing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.List;

public class CustomGalleryAdapter extends BaseAdapter {

    private Context context;
    private List<gallery> images;
    private LayoutInflater inflater;


    public CustomGalleryAdapter(Context c, List<gallery> images) {
        context = c;
        this.images = images;
    }

    // returns the number of images
    public int getCount() {
        return images.size();
    }

    // returns the ID of an item
    public Object getItem(int position) {
        return null;
    }

    // returns the ID of an item
    public long getItemId(int position) {
        return 0;
    }

    // returns an ImageView view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        byte[] f_img = images.get(position).getImagePhoto();
        Bitmap bitmap = BitmapFactory.decodeByteArray(f_img, 0, f_img.length);
        //img.setImageBitmap(bitmap);

        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(bitmap);
        //imageView.setImageResource(); // set image in ImageView
        imageView.setLayoutParams(new Gallery.LayoutParams(300, 350)); // set ImageView param
        return imageView;
    }
}
