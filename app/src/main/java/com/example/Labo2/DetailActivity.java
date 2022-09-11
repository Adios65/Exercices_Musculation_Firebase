package com.example.Labo2;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    ArrayList<Exercice> exercice = new ArrayList<>();

    Context context = DetailActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //GET ARRAYLIST AVEC EXERCICE ENVOYÉ PAR LISTVIEW CategoryActivity
        Bundle extras = getIntent().getExtras();
        exercice = extras.getParcelableArrayList("detailExercice");

        //SETUP ACTION BAR BACKGROUND COLOR
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#363F93"));
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(colorDrawable);

        //SETUP TITLE ACTION BAR
        getSupportActionBar().setTitle(exercice.get(0).getTitle());

        //SETUP BACK BUTTON
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        afficherDetail();

    }

    //OBTENIR LES VUES DE activity_detail ET LES REMPLIR AVEC DETAIL DE L'EXERCICE
    private void afficherDetail() {
        ImageView imgIV = (ImageView) findViewById(R.id.imageView_detail);
        TextView descriptionTV = (TextView) findViewById(R.id.textView_description);
        TextView setsTV = (TextView) findViewById(R.id.textView_sets);
        TextView repeatTV = (TextView) findViewById(R.id.textView_repeat);
        int img_id = context.getResources().getIdentifier(exercice.get(0).getImg(), "drawable", context.getPackageName());
        imgIV.setImageResource(img_id);
        descriptionTV.setText(exercice.get(0).getDescription());
        setsTV.setText(exercice.get(0).getSets());
        repeatTV.setText(exercice.get(0).getRepeat());

    }


    //**********************\\
    //  BACK BUTTON ACTION   \\
    //*******************************************************************************************************************************************
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}