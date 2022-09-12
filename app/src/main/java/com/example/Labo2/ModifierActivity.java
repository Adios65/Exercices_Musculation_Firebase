package com.example.Labo2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class ModifierActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    ImageView imageSelector;

    Context context = ModifierActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_ajouter_exercice);

        //CHANGER TITRE
        TextView formTitre = findViewById(R.id.form_Titre);
        formTitre.setText("Modifer Exercice");

        //GET CategorieChoisie
        Bundle extras = getIntent().getExtras();
        Exercice exercice_a_modifier = new Exercice();

        if (extras != null) {
            exercice_a_modifier = extras.getParcelable("ModifierCetExercice");
            getSupportActionBar().hide();
        }


        //CHERHCE ELEMENTS IN VIEW
        //*******************************************************************************************************************************************

        //SET UNFOCUS FOR EDITTEXT, THIS WILL ENABLE TO CLICK OUTSIDE THE KEYBOARD TO CLOSE IT_______________________________________________________________
        EditText nomInput = findViewById(R.id.input_title);
        EditText descriptionInput = findViewById(R.id.input_description);

        ArrayList<EditText> editTextList = new ArrayList<>();
        editTextList.add(nomInput);
        editTextList.add(descriptionInput);

        for (int i = 0; i < editTextList.size(); i++) {
            editTextList.get(i).setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            });
        }


        //Get ImageView for the Form__________________________________________________________________________________________________________________________
        imageSelector = findViewById(R.id.drawableImage);

        //Get Spinner for the Form____________________________________________________________________________________________________________________________
        Spinner imageSpinner = findViewById(R.id.spinnerImage);
        imageSpinner.setVisibility(View.GONE);

        //Set Spinner Layout and Strings______________________________________________________________________________________________________________________
        Spinner spinnerCategorie = findViewById(R.id.spinnerCategorie);
        Spinner spinnerSets = findViewById(R.id.spinnerSets);
        Spinner spinnerPause = findViewById(R.id.spinnerPause);
        Spinner spinnerRepeat = findViewById(R.id.spinnerRepeat);
        Spinner spinnerDuree = findViewById(R.id.spinnerDuree);

        spinnerCategorie.setAdapter(new SpinnerAdapter(this, R.layout.spinner_content_layout, getResources().getStringArray(R.array.spinnerCategorie)));
        spinnerSets.setAdapter(new SpinnerAdapter(this, R.layout.spinner_content_layout, getResources().getStringArray(R.array.spinnerSets)));
        spinnerPause.setAdapter(new SpinnerAdapter(this, R.layout.spinner_content_layout, getResources().getStringArray(R.array.spinnerPause)));
        spinnerRepeat.setAdapter(new SpinnerAdapter(this, R.layout.spinner_content_layout, getResources().getStringArray(R.array.spinnerRepeat)));
        spinnerDuree.setAdapter(new SpinnerAdapter(this, R.layout.spinner_content_layout, getResources().getStringArray(R.array.spinnerDuree)));


        //INSERT DATA EXERCICE DANS ELEMENTS
        //*******************************************************************************************************************************************
        //EDITETEXT
        nomInput.setText(exercice_a_modifier.getTitle());
        descriptionInput.setText(exercice_a_modifier.getDescription());

        //GET IMAGE RESOURCE AND SET IMAGE TO IMAGEVIEW
        int drawableID = context.getResources().getIdentifier(exercice_a_modifier.getImg(), "drawable", context.getPackageName());
        imageSelector.setImageResource(drawableID);

        //{"Curl Zottman", "avant_bras_curl_zottman", "x10", "Avant Bras", "3 Set", "5 Minutes", "La position de départ est la même que pour le curl haltères : debout, dos bien droit, genoux légèrement fléchis, deux haltères courtes dans les mains avec une prise en supination. \n\nEn contractant les biceps et en gardant les coudes près du corps, amener les deux haltères en position haute. Une fois dans cette position, faites tourner votre poignet de 180 degrés jusqu'à ce que vous ayez une prise en pronation. Redescendre ensuite les haltères en conservant cette prise. Quand les haltères approchent des cuisses, tournez de nouveau les poignets pour revenir à la position de départ prise en supination.", "30 Secondes", "0"},
        //


//        Log.d("Exercice", "PRE--" + test);


        spinnerSets.setSelection(setSpinnerPosition(exercice_a_modifier.getSets()));
        spinnerPause.setSelection(setSpinnerPosition(exercice_a_modifier.getPause()));
        spinnerRepeat.setSelection(setSpinnerPosition(exercice_a_modifier.getRepeat()));
        spinnerDuree.setSelection(setSpinnerPosition(exercice_a_modifier.getDuree()));
        spinnerCategorie.setSelection(setSpinnerPosition(exercice_a_modifier.getCategorie()));
    }


    public int setSpinnerPosition(String ExerciceSpinnerValue) {
        int position = 0;
        switch (ExerciceSpinnerValue) {
            case "2 Set":
            case "5x":
            case "2 Minutes":
            case "Biceps":
                position = 1;
                break;

            case "3 Set":
            case "10x":
            case "3 Minutes":
            case "Cuisses-Fessiers":
                position = 2;
                break;

            case "4 Set":
            case "12x":
            case "4 Minutes":
            case "Dos":
                position = 3;
                break;

            case "5 Set":
            case "15x":
            case "5 Minutes":
            case "Epaules":
                position = 4;
                break;

            case "Mollets":
                position = 5;
                break;
            case "Pectoraux":
                position = 6;
                break;
            case "Triceps":
                position = 7;
                break;
            case "Avant Bras":
                position = 8;
                break;
        }

        return position;

    }





    //*************************************************\\
    //  OnSELECTION FOR SPINNER AND IMAGEVIEW IN FORM   \\
    //*******************************************************************************************************************************************
    @Override
    public void onItemSelected(AdapterView<?> parentView, View view, int position, long id) {
        //GET IMAGE NAME FROM SPINNER SELECTION
        String drawableName = parentView.getItemAtPosition(position).toString();

        //GET CONTEXT OF THE IMAGE VIEW
        Context context = imageSelector.getContext();

        //GET IMAGE RESOURCE
        int drawableID = context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());

        //SET IMAGE TO IMAGEVIEW
        imageSelector.setImageResource(drawableID);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //NOT NEEDED, JUST NEED TO IMPLEMENT FUNCTION
    }


    //****************\\
    //  HIDE KEYBOARD   \\
    //*******************************************************************************************************************************************
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}