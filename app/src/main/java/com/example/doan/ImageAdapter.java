package com.example.doan;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
public class ImageAdapter extends ArrayAdapter<Integer> {
    public ImageAdapter(Context context, Integer[] imageIds) {
        super(context, 0, imageIds);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(getContext());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }
        // Load image into ImageView using the ID
        imageView.setImageResource(getItem(position));

        return imageView;
    }
}