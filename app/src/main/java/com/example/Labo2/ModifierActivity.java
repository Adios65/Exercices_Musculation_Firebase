package com.example.Labo2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class ModifierActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    ImageView imageSelector;
    Context context = ModifierActivity.this;
    Exercice exercice_a_modifier;

    TextView formTitre;
    String key;

    //FORM VALUE
    EditText nomInput;
    EditText descriptionInput;
    Spinner spinnerCategorie;
    Spinner spinnerSets;
    Spinner spinnerPause;
    Spinner spinnerRepeat;
    Spinner spinnerDuree;


    FireBaseHelper fireDB = new FireBaseHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_ajouter_exercice);


        //SET OnClickLISTENER TO BUTTONS
        Button btn_update = findViewById(R.id.btn_modifier);
        Button btn_delete = findViewById(R.id.btn_supprimer);
        Button btn_cancel = findViewById(R.id.btn_annuler);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

        //CHANGER TITRE
        formTitre = findViewById(R.id.form_Titre);
        formTitre.setText("Modifer Exercice");

        //GET EXERCICE A MODIFIER
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            exercice_a_modifier = extras.getParcelable("ModifierCetExercice");
        }
        //SAUVARDER LA CLE POUR DB OPERATIONS [UPDATE--DELETE]
        key = exercice_a_modifier.getKey();


        //ENLEVER TOP ACTION BAR
        Objects.requireNonNull(getSupportActionBar()).hide();


        //CHERHCE ELEMENTS IN VIEW
        //*******************************************************************************************************************************************
        //SET UNFOCUS FOR EDITTEXT, THIS WILL ENABLE TO CLICK OUTSIDE THE KEYBOARD TO CLOSE IT_______________________________________________________________
        nomInput = findViewById(R.id.input_title);
        descriptionInput = findViewById(R.id.input_description);

        ArrayList<EditText> editTextList = new ArrayList<>();
        editTextList.add(nomInput);
        editTextList.add(descriptionInput);

        //SETUP EDITTEXT UNFOCUS KEYBOARD FUNCTIONNALITY
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

        //Get Spinner Layout and Set Array Strings______________________________________________________________________________________________________________________
        spinnerCategorie = findViewById(R.id.spinnerCategorie);
        spinnerSets = findViewById(R.id.spinnerSets);
        spinnerPause = findViewById(R.id.spinnerPause);
        spinnerRepeat = findViewById(R.id.spinnerRepeat);
        spinnerDuree = findViewById(R.id.spinnerDuree);

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


        //SET SPINNER BY EXERCICE SELECTION
        spinnerSets.setSelection(setSpinnerPosition(exercice_a_modifier.getSets()));
        spinnerPause.setSelection(setSpinnerPosition(exercice_a_modifier.getPause()));
        spinnerRepeat.setSelection(setSpinnerPosition(exercice_a_modifier.getRepeat()));
        spinnerDuree.setSelection(setSpinnerPosition(exercice_a_modifier.getDuree()));
        spinnerCategorie.setSelection(setSpinnerPosition(exercice_a_modifier.getCategorie()));
    }


    //*************************************************\\
    //  GET EXERCICE SPINNER POSITION TO POPULATE FORM  \\
    //*******************************************************************************************************************************************
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

    //*******************************************\\
    //  BUTTON ACTION :: UPDATE--DELETE--CANCEL   \\
    //*******************************************************************************************************************************************
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_modifier:
                updateExercice(exercice_a_modifier);
                this.finish();
                break;
            case R.id.btn_supprimer:
                deleteExercice(exercice_a_modifier);
                this.finish();
                break;
            case R.id.btn_annuler:
                this.finish();
                break;

        }
    }

    //*******************\\
    //  UPDATE EXERCICE   \\
    //*******************************************************************************************************************************************
    public void updateExercice(Exercice exercice_a_modifier) {

        //Modifier Exercice
        exercice_a_modifier.setTitle(nomInput.getText().toString());
        exercice_a_modifier.setDescription(descriptionInput.getText().toString());
        exercice_a_modifier.setPause(spinnerPause.getSelectedItem().toString());
        exercice_a_modifier.setSets(spinnerSets.getSelectedItem().toString());
        exercice_a_modifier.setDuree(spinnerDuree.getSelectedItem().toString());
        exercice_a_modifier.setRepeat(spinnerRepeat.getSelectedItem().toString());


        Map<String, Object> hashExercice = exercice_a_modifier.toMap();
        fireDB.modifier(key, hashExercice);

    }

    //*******************\\
    //  DELETE EXERCICE   \\
    //*******************************************************************************************************************************************
    public void deleteExercice(Exercice exercice_a_effacer) {
        fireDB.supprimer(key);
    }

}