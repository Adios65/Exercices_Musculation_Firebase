package com.example.Labo2;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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

    protected MyDataBaseHelper maDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SETUP LA NAVIGATION DRAWER TOGGLE FEATURE
        setupNavigationToggle();
        //SETUP LA NAVIGATION DRAWER ITEMS OnCLICK LISTENER
        setupItemOnClick();

        maDB = new MyDataBaseHelper(this);
        maDB.getWritableDatabase();


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


        //TEST-----------------------------------------------------------------------------
        String toastmsg = "ca marche";
        Toast.makeText(MainActivity.this, toastmsg, Toast.LENGTH_SHORT).show();
        //TEST-----------------------------------------------------------------------------
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
                    drawerLayout.closeDrawers();
                    break;
                case R.id.questionB:
                    //CONTACT PHONE
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