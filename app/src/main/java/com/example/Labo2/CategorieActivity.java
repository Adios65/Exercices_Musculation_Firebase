package com.example.Labo2;

import static android.widget.AdapterView.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
    ExerciceArrayAdapter arrayAdapter;
    Context context = CategorieActivity.this;

    //FORM UI
    ImageView imageSelector;

    //DATABASEHELPER FOR DATABASE
    protected MyDataBaseHelper maDB;
    protected ExerciceDataBase fireDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //DEFAULT
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);

        //SETUP BACK BUTTON
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //GET DATABASEHELPER TO ACCESS DATABASE
        maDB = new MyDataBaseHelper(this);

        fireDB = new ExerciceDataBase();


        //GET CategorieChoisie
        Bundle extras = getIntent().getExtras();
        String categorieChoisie = "";
        if (extras != null) {
            categorieChoisie = extras.getString("categorieChoisie");
            getSupportActionBar().setTitle(categorieChoisie);
        }


        //LISTER LE ARRAYLIST
        listerExerciceSelonCategorie(categorieChoisie);


        //TEST****************************************
        Button btn_form = findViewById(R.id.btn_form);
        btn_form.setOnClickListener(this);
        //TEST****************************************



    }

//    private void loadData(String categorie) {
//
//        fireDB.lireParCategorie(categorie).addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(!snapshot.getChildren().iterator().hasNext()){
//                    fireDB.ajouterDonnees();
//
//                };
//                    listeExercices = new ArrayList<>();
//                for (DataSnapshot data : snapshot.getChildren()) {
//                    Exercice exercice = data.getValue(Exercice.class);
//                    if (!listeExercices. contains(exercice)) {
//                        listeExercices.add(exercice);
//                    }
//                }
//                listViewExercicesCategorie = findViewById(R.id.liste_Exercices_Categorie);
//                arrayAdapter = new ExerciceArrayAdapter(context, R.layout.exercices_list_layout, listeExercices);
//                listViewExercicesCategorie.setAdapter(arrayAdapter);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//    }


    //************************************\\
    // GET LIST OF EXERCICE BY CATEGORIE   \\
    //*******************************************************************************************************************************************************
    private void listerExerciceSelonCategorie(String categorieChoisie) {

        if (categorieChoisie.equals("Favoris")) {

        } else if (!categorieChoisie.equals("")) {
            fireDB.lireParCategorie(categorieChoisie).addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(!snapshot.getChildren().iterator().hasNext() ){
                        fireDB.ajouterDonnees();
                    }
                    listeExercices = new ArrayList<>();
                    for (DataSnapshot data : snapshot.getChildren()) {
                        Exercice exercice = data.getValue(Exercice.class);
                        listeExercices.add(exercice);
                    }
                    listViewExercicesCategorie = findViewById(R.id.liste_Exercices_Categorie);
                    arrayAdapter = new ExerciceArrayAdapter(context, R.layout.exercices_list_layout, listeExercices);
                    listViewExercicesCategorie.setAdapter(arrayAdapter);


                    listViewExercicesCategorie.setOnItemClickListener((adapterView, view, i, l) -> {
                        ArrayList<Exercice> exercice = new ArrayList<>();
                        exercice.add((Exercice) adapterView.getItemAtPosition(i));
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putParcelableArrayListExtra("detailExercice", exercice);
                        startActivity(intent);
                        //Send CategorieChoisie to CategorieActivity
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        //New Exercice List
//        listeExercices = new ArrayList<>();

        //Get Exercices par Categorie ou Favoris
//        if (categorieChoisie.equals("Favoris")) {
//            listeExercices = maDB.obtenirFavoris();
//        } else {
//            listeExercices = maDB.obtenirExerciceParCategorie(categorieChoisie);
//        }

        //Get ListView to show Exercices
//        listViewExercicesCategorie = findViewById(R.id.liste_Exercices_Categorie);
//        arrayAdapter = new ExerciceArrayAdapter(this, R.layout.exercices_list_layout, listeExercices);
//        listViewExercicesCategorie.setAdapter(arrayAdapter);
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
                    maDB.insertExercice(nomForm, imageForm, repeatForm, categorieForm, setsForm, dureeForm, descriptionForm, pauseForm, favoriteForm);
                    Exercice exercice = new Exercice(1,nomForm, imageForm, repeatForm, categorieForm, setsForm, dureeForm, descriptionForm, pauseForm, favoriteForm);
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
        //TEST-----------------------------------------------------------------------------
        String toastmsg = "ca marche";
        Toast.makeText(CategorieActivity.this, toastmsg, Toast.LENGTH_SHORT).show();

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
        formAjouterExercice();
    }

}