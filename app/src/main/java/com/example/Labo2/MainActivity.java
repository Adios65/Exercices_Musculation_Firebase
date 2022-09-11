package com.example.Labo2;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //NAVIGATION DRAWER
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;

    //MainActivity Context
    Context context = MainActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SETUP LA NAVIGATION DRAWER TOGGLE FEATURE
        setupNavigationToggle();
        //SETUP LA NAVIGATION DRAWER ITEMS OnCLICK LISTENER
        setupItemOnClick();
        //ACTION BAR BACKGROUND COLOR
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#363F93"));
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(colorDrawable);

        //ACTION BAR TITLE
        Objects.requireNonNull(getSupportActionBar()).setTitle("Gorilla Gym");



        //SETUP BUTTON MAIN MENU
        ArrayList<ImageView> buttonList = new ArrayList<>();

        buttonList.add(findViewById(R.id.main_btn_favoris));

        buttonList.add(findViewById(R.id.main_btn_abdominaux));
        buttonList.add(findViewById(R.id.main_btn_biceps));

        buttonList.add(findViewById(R.id.main_btn_fessiers));
        buttonList.add(findViewById(R.id.main_btn_dos));

        buttonList.add(findViewById(R.id.main_btn_epaules));
        buttonList.add(findViewById(R.id.main_btn_mollets));

        buttonList.add(findViewById(R.id.main_btn_pectoraux));
        buttonList.add(findViewById(R.id.main_btn_triceps));

        buttonList.add(findViewById(R.id.main_btn_avantbras));

        for (int i = 0; i < buttonList.size(); i++) {
            buttonList.get(i).setOnClickListener(this);
        }


    }


    //**************\\
    //  FONCTIONS    \\
    //*******************************************************************************************************************************************

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        //GET CATEGORY
        String categorieChoisie = "";

        switch (v.getId()) {
            case R.id.main_btn_favoris:
                categorieChoisie = "Favoris";
                break;
            case R.id.main_btn_abdominaux:
                categorieChoisie = "Abdominaux";
                break;
            case R.id.main_btn_biceps:
                categorieChoisie = "Biceps";
                break;
            case R.id.main_btn_fessiers:
                categorieChoisie = "Cuisses-Fessiers";
                break;
            case R.id.main_btn_dos:
                categorieChoisie = "Dos";
                break;
            case R.id.main_btn_epaules:
                categorieChoisie = "Épaules";
                break;
            case R.id.main_btn_mollets:
                categorieChoisie = "Mollets";
                break;
            case R.id.main_btn_pectoraux:
                categorieChoisie = "Pectoraux";
                break;
            case R.id.main_btn_triceps:
                categorieChoisie = "Triceps";
                break;
            case R.id.main_btn_avantbras:
                categorieChoisie = "Avant Bras";
                break;

        }

        //Get CategorieActivity
        Intent intentToCategorie = new Intent(context, CategorieActivity.class);

        //Send CategorieChoisie to CategorieActivity
        intentToCategorie.putExtra("categorieChoisie", categorieChoisie);

        //Go To CategorieActivity
        startActivity(intentToCategorie);



        //SETUP ACTION BAR BACKGROUND COLOR
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#363F93"));
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(colorDrawable);
    }

    //***********************************\\
    //  MENU DRAWER CLICK ITEM SELECTION  \\
    //*******************************************************************************************************************************************
    @SuppressLint("NonConstantResourceId")
    private void setupItemOnClick() {
        navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.questionA:
                    //CONTACT EMAIL
                    composerCourriel();
                    drawerLayout.closeDrawers();
                    break;
                case R.id.questionB:
                    //CONTACT PHONE
                    faireUnAppel();
                    drawerLayout.closeDrawers();
                    break;
//                case R.id.questionC:
//                    drawerLayout.closeDrawers();
//                    break;
//                case R.id.questionD:
//                    drawerLayout.closeDrawers();
//                    break;
//                case R.id.questionE:
//                    drawerLayout.closeDrawers();
//                    break;
            }
            return true;
        });
    }

    //INTENT IMPLICIT POUR ENVOYER UN COURRIEL
    public void composerCourriel() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:support@gorillagym.com")); // only email apps should handle this
        startActivity(Intent.createChooser(intent, "Choisissez un client de messagerie :"));
    }

    //INTENT IMPLICIT POUR PASSER UN APPEL
    public void faireUnAppel() {
        Uri number = Uri.parse("tel:5141234567");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }



    //*********************\\
    // UTILITIES FUNCTIONS  \\  xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //*******************************************************************************************************************************************
    //SETUP :: MENU DRAWER TOGGLE
    private void setupNavigationToggle() {
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    //SETUP :: MENU DRAWER NECESSARY FUNCTION
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}