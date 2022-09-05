package com.example.Labo2;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    ArrayList<Exercice> exercice = new ArrayList<>();

    Context context = DetailActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        exercice = extras.getParcelableArrayList("detailExercice");


        //SETUP BACK BUTTON
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        afficherDetail();
    }

    private void afficherDetail() {



        getSupportActionBar().setTitle(exercice.get(0).getTitle());

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

}