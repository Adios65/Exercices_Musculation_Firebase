package com.example.Labo2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ExerciceArrayAdapter extends ArrayAdapter<Exercice> {

    private static final String TAG = "ExerciceListAdapter";

    private final Context mContext;
    int mResource;
    ArrayList<Exercice> listeExercice;

    public ExerciceArrayAdapter(Context context, int resource, ArrayList<Exercice> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {


        int _id = getItem(position).get_id();
        String title = getItem(position).getTitle();
        String img = getItem(position).getImg();
        String repeat = getItem(position).getRepeat();
        String categorie = getItem(position).getCategorie();
        String sets = getItem(position).getSets();
        String duree = getItem(position).getDuree();
        String description = getItem(position).getDescription();
        String pause = getItem(position).getPause();
        String favorite = getItem(position).getFavorite();

        LayoutInflater inflater = LayoutInflater.from(mContext);


        convertView = inflater.inflate(mResource, parent, false);

//        TextView tv_ref = convertView.findViewById(R.id.textView_ref);
        TextView tv_title = convertView.findViewById(R.id.textView_title);
        ImageView iv_img = convertView.findViewById(R.id.imageView_img);
        int imgDR = mContext.getResources().getIdentifier(img, "drawable", mContext.getPackageName());
        TextView tv_repeat = convertView.findViewById(R.id.textView_repeat);
//        TextView tv_categorie = convertView.findViewById(R.id.textView_categorie);
//        TextView tv_sets = convertView.findViewById(R.id.textView_sets);
//        TextView tv_duree = convertView.findViewById(R.id.textView_duree);
//        TextView tv_description = convertView.findViewById(R.id.textView_description);
//        TextView tv_pause = convertView.findViewById(R.id.textView_pause);
//        TextView tv_favorite = convertView.findViewById(R.id.textView_favorite);


//        ListView listView =
//        tv_ref.setText(ref);
        tv_title.setText(title);
        iv_img.setImageResource(imgDR);
        tv_repeat.setText(repeat);
//        tv_categorie.setText(categorie);
//        tv_sets.setText(sets);
//        tv_duree.setText(duree);
//        tv_description.setText(description);
//        tv_pause.setText(pause);
//        tv_favorite.setText(favorite);

        ///****** POUR AJOUTER l'IMAGE DRAWABLE SUR LA LISTVIEW PAR ITEM
//        int drawableID = context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
//        imageSelector.setImageResource(drawableID);

        return convertView;
    }
}