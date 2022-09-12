package com.example.Labo2;

import static android.widget.AdapterView.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class CategorieActivity extends AppCompatActivity implements OnItemSelectedListener, View.OnClickListener {

    //INTERFACE UI
    ArrayList<Exercice> listeExercices;
    ListView listViewExercicesCategorie;
    ListViewArrayAdapter arrayAdapter;
    Context context = CategorieActivity.this;

    //FORM UI
    ImageView imageSelector;

    //DATABASEHELPER FOR DATABASE
    protected FireBaseHelper fireDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //DEFAULT
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);

        //SETUP BACK BUTTON
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //ACTION BAR BACKGROUND COLOR
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#363F93"));
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(colorDrawable);

        //GET DATABASEHELPER TO ACCESS DATABASE
        fireDB = new FireBaseHelper();


        //GET CategorieChoisie
        Bundle extras = getIntent().getExtras();
        String categorieChoisie = "";
        if (extras != null) {
            categorieChoisie = extras.getString("categorieChoisie");
            getSupportActionBar().setTitle(categorieChoisie);
        }

        //LISTER LE ARRAYLIST
        listerExerciceSelonCategorie(categorieChoisie);

        //SET CLICK EVENT POUR LE BOUTON FORM
        findViewById(R.id.btn_form).setOnClickListener(this);

    }


    //************************************\\
    // GET LIST OF EXERCICE BY CATEGORIE   \\
    //*******************************************************************************************************************************************************
    private void listerExerciceSelonCategorie(String categorieChoisie) {

        if (categorieChoisie.equals("Favoris")) {
            fireDB.lireFavoris().addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    listeExercices = new ArrayList<>();

                    for (DataSnapshot data : snapshot.getChildren()) {
                        Exercice exercice = data.getValue(Exercice.class);
                        assert exercice != null;
                        exercice.setKey(data.getKey());
                        listeExercices.add(exercice);
                    }

                    //Get ListView to show Exercices
                    listViewExercicesCategorie = findViewById(R.id.liste_Exercices_Categorie);
                    arrayAdapter = new ListViewArrayAdapter(context, R.layout.exercices_list_layout, listeExercices);
                    listViewExercicesCategorie.setAdapter(arrayAdapter);

                    listViewExercicesCategorie.setOnItemClickListener((adapterView, view, i, l) -> {
                        //AJOUTER L'EXERCICE SUR LEQUEL ON A CLIQUÉ DANS ARRAYLIST
                        ArrayList<Exercice> exercice = new ArrayList<>();
                        exercice.add((Exercice) adapterView.getItemAtPosition(i));


                        //Send ARRAYLIST avec L'Exercice to DetailActivity
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putParcelableArrayListExtra("detailExercice", exercice);
                        startActivity(intent);
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }


            });
        } else if (!categorieChoisie.equals("")) {
            fireDB.lireParCategorie(categorieChoisie).addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (!snapshot.getChildren().iterator().hasNext()) {
                        fireDB.ajouterDonnees();
                    }

                    listeExercices = new ArrayList<>();

                    for (DataSnapshot data : snapshot.getChildren()) {
                        Exercice exercice = data.getValue(Exercice.class);

                        assert exercice != null;
                        exercice.setKey(data.getKey());


                        listeExercices.add(exercice);
                    }


                    //Get ListView to show Exercices
                    listViewExercicesCategorie = findViewById(R.id.liste_Exercices_Categorie);
                    arrayAdapter = new ListViewArrayAdapter(context, R.layout.exercices_list_layout, listeExercices);
                    listViewExercicesCategorie.setAdapter(arrayAdapter);


                    listViewExercicesCategorie.setOnItemClickListener((parent, view, position, id) -> {
                        //AJOUTER L'EXERCICE SUR LEQUEL ON A CLIQUÉ DANS ARRAYLIST
                        ArrayList<Exercice> exercice = new ArrayList<>();
                        exercice.add((Exercice) parent.getItemAtPosition(position));

                        Log.d("TAG", "CATEGORY2");
                        //Send ARRAYLIST avec L'Exercice to DetailActivity
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putParcelableArrayListExtra("detailExercice", exercice);
                        startActivity(intent);
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }


            });
        }

    }


    //**********************\\
    // FORM AJOUTER EXERCICE   \\
    //*******************************************************************************************************************************************************
    private void formAjouterExercice() {
        //INSTANTIATE THE FORM AND INFLATE IT________________________________________________________________________________________________________________
        AlertDialog.Builder myForm = new AlertDialog.Builder(context, R.style.form_theme);
        LayoutInflater inflater = LayoutInflater.from(context);
        View myFormView = inflater.inflate(R.layout.form_ajouter_exercice, findViewById(R.id.linearLayoutRoot));

        //SET UNFOCUS FOR EDITTEXT, THIS WILL ENABLE TO CLICK OUTSIDE THE KEYBOARD TO CLOSE IT_______________________________________________________________
        EditText nomInput = myFormView.findViewById(R.id.input_title);
        EditText descriptionInput = myFormView.findViewById(R.id.input_description);

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
        imageSelector = myFormView.findViewById(R.id.drawableImage);

        //Get Spinner for the Form____________________________________________________________________________________________________________________________
        Spinner imageSpinner = myFormView.findViewById(R.id.spinnerImage);
        imageSpinner.setAdapter(new SpinnerAdapter(this, R.layout.spinner_content_layout, getResources().getStringArray(R.array.spinnerImage)));
        imageSpinner.setOnItemSelectedListener(this);

        //Set Spinner Layout and Strings______________________________________________________________________________________________________________________
        Spinner spinnerCategorie = myFormView.findViewById(R.id.spinnerCategorie);
        Spinner spinnerSets = myFormView.findViewById(R.id.spinnerSets);
        Spinner spinnerPause = myFormView.findViewById(R.id.spinnerPause);
        Spinner spinnerRepeat = myFormView.findViewById(R.id.spinnerRepeat);
        Spinner spinnerDuree = myFormView.findViewById(R.id.spinnerDuree);

        spinnerCategorie.setAdapter(new SpinnerAdapter(this, R.layout.spinner_content_layout, getResources().getStringArray(R.array.spinnerCategorie)));
        spinnerSets.setAdapter(new SpinnerAdapter(this, R.layout.spinner_content_layout, getResources().getStringArray(R.array.spinnerSets)));
        spinnerPause.setAdapter(new SpinnerAdapter(this, R.layout.spinner_content_layout, getResources().getStringArray(R.array.spinnerPause)));
        spinnerRepeat.setAdapter(new SpinnerAdapter(this, R.layout.spinner_content_layout, getResources().getStringArray(R.array.spinnerRepeat)));
        spinnerDuree.setAdapter(new SpinnerAdapter(this, R.layout.spinner_content_layout, getResources().getStringArray(R.array.spinnerDuree)));


        //REMOVE BUTTONS
        Button btn_update = myFormView.findViewById(R.id.btn_modifier);
        Button btn_delete = myFormView.findViewById(R.id.btn_supprimer);
        Button btn_cancel = myFormView.findViewById(R.id.btn_annuler);
        btn_update.setVisibility(View.GONE);
        btn_delete.setVisibility(View.GONE);
        btn_cancel.setVisibility(View.GONE);

        //FORM ACTION ====CREATE / CANCEL======_______________________________________________________________________________________________________________
        myForm.setView(myFormView)
                .setPositiveButton("Create", (dialog, id) -> {
                    //Get Form Values

                    String nomForm = nomInput.getText().toString();
                    String descriptionForm = descriptionInput.getText().toString();
                    String categorieForm = spinnerCategorie.getSelectedItem().toString();
                    String setsForm = spinnerSets.getSelectedItem().toString();
                    String pauseForm = spinnerPause.getSelectedItem().toString();
                    String repeatForm = spinnerRepeat.getSelectedItem().toString();
                    String dureeForm = spinnerDuree.getSelectedItem().toString();
                    String imageForm = imageSpinner.getSelectedItem().toString();
                    String favoriteForm = "0";

                    //Insert into Database_______________________________________________________________________________________________________________________
                    Exercice exercice = new Exercice(nomForm, imageForm, repeatForm, categorieForm, setsForm, dureeForm, descriptionForm, pauseForm, favoriteForm);
                    fireDB.ajouter(exercice);
                    //TEST_______________________________________________________________________________________________________________________________________
                    arrayAdapter.notifyDataSetChanged();
                    Toast.makeText(context, "Exercice " + nomForm + " sauvegarder correctement!", Toast.LENGTH_LONG).show();
                })

                .setNegativeButton("Cancel", (dialog, id) -> dialog.dismiss());

        //CREATE FORM
        myForm.create();

        //DISPLAY THE FORM
        myForm.show();
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

    //****************\\
    //  HIDE KEYBOARD   \\
    //*******************************************************************************************************************************************
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    //**************************************\\
    //  TEST THE FORM FOR ADDING EXERCICE    \\
    //*******************************************************************************************************************************************
    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_form) {
            formAjouterExercice();
        }
    }
}