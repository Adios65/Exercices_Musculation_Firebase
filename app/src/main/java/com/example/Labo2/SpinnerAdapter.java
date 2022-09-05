package com.example.Labo2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class SpinnerAdapter extends ArrayAdapter<String> {

    Context context;
    String[] listeNomImage;

    public SpinnerAdapter(Context context, int textViewResourceId, String[] listeNomImage) {
        super(context, textViewResourceId, listeNomImage);
        this.context = context;
        this.listeNomImage = listeNomImage;
    }

    // Override these methods and instead return our custom view (with image and text)
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return getCustomView(position, convertView, parent);
    }

    // Function to return our custom View (View with an image and text)
    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.spinner_content_layout, parent, false);
        TextView label = (TextView) row.findViewById(R.id.text);
        label.setText(listeNomImage[position]);


        return row;
    }
}
