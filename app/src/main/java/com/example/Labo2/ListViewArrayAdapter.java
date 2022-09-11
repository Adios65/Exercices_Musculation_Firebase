package com.example.Labo2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewArrayAdapter extends ArrayAdapter<Exercice> {

    private final Context mContext;
    int mResource;
    FireBaseHelper fireDB = new FireBaseHelper();

    public ListViewArrayAdapter(Context context, int resource, ArrayList<Exercice> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {


        //OBTENIR LES VALEURS DES ATTRIBUTS DE L'EXERCICE EN QUESTION
        String key = (String) getItem(position).getKey();
        String title = getItem(position).getTitle();
        String img = getItem(position).getImg();
        String repeat = getItem(position).getRepeat();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        //OBTENIR LES VUES DE exercices_list_layout
        TextView tv_title = convertView.findViewById(R.id.textView_title);
        ImageView iv_img = convertView.findViewById(R.id.imageView_img);
        int imgDR = mContext.getResources().getIdentifier(img, "drawable", mContext.getPackageName());
        TextView tv_repeat = convertView.findViewById(R.id.textView_repeat);

        //AFFICHER LES VALEURS DANS LE LISTVIEW
        tv_title.setText(title);
        iv_img.setImageResource(imgDR);
        tv_repeat.setText(repeat);

        TextView btn_Modifier_item  = convertView.findViewById(R.id.textView_menu);
        btn_Modifier_item.setOnClickListener(view -> {

        });


        TextView btn_favorite = convertView.findViewById(R.id.textView_favorite);
        btn_favorite.setOnClickListener(view -> {
            HashMap<String, Object> itemClicked = new HashMap<>();
            Exercice exercice = getItem(position);
            itemClicked.put("_id",getItem(position).get_id());
            itemClicked.put(key,exercice);
            System.out.println("____________"+fireDB.modifier(key, itemClicked));
        });


        ///****** POUR AJOUTER l'IMAGE DRAWABLE SUR LA LISTVIEW PAR ITEM
//        int drawableID = context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
//        imageSelector.setImageResource(drawableID);

        return convertView;
    }
}