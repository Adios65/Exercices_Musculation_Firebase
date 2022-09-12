package com.example.Labo2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        //GET THE EXERCICE BEING LISTED
        Exercice exercice = getItem(position);

        //OBTENIR LES VALEURS DES ATTRIBUTS DE L'EXERCICE EN QUESTION
        String key = (String) exercice.getKey();
        String title = exercice.getTitle();
        String img = exercice.getImg();
        String repeat = exercice.getRepeat();


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        //OBTENIR LES ELEMENTS DE exercices_list_layout
        TextView tv_title = convertView.findViewById(R.id.textView_title);
        TextView tv_repeat = convertView.findViewById(R.id.textView_repeat);
        ImageView iv_img = convertView.findViewById(R.id.imageView_img);
        TextView btn_modifier = convertView.findViewById(R.id.textView_modifier);
        TextView btn_favorie = convertView.findViewById(R.id.ImageButton_favorite);

        //AFFICHER LES VALEURS DANS LE LISTVIEW
        tv_title.setText(title);
        tv_repeat.setText(repeat);
        int imgDR = mContext.getResources().getIdentifier(img, "drawable", mContext.getPackageName());
        iv_img.setImageResource(imgDR);

        //SET IMAGE FOR FAVORITE
        if (exercice.getFavorite().equals("0")) {
            btn_favorie.setBackgroundResource(R.drawable.non_favoris);
        } else if (exercice.getFavorite().equals("1")) {
            btn_favorie.setBackgroundResource(R.drawable.favoris);
        }

        //OnCLICK LISTENER FOR UPDATE
        btn_favorie.setOnClickListener(view -> {
            //Modifier Favori dans l'exercice
            if (exercice.getFavorite().equals("0")) {
                exercice.setFavorite("1");
                Map<String, Object> hashExercice = exercice.toMap();
                fireDB.modifier(key, hashExercice);
            } else {
                exercice.setFavorite("0");
                Map<String, Object> hashExercice = exercice.toMap();
                fireDB.modifier(key, hashExercice);
            }
        });


        //OnCLICK LISTENER FOR UPDATE
        btn_modifier.setOnClickListener(view -> {
            //Get CategorieActivity
            Intent intentToCategorie = new Intent(mContext, ModifierActivity.class);

            //Send CategorieChoisie to CategorieActivity
            intentToCategorie.putExtra("ModifierCetExercice", exercice);

            //Go To CategorieActivity
            mContext.startActivity(intentToCategorie);


        });


        return convertView;
    }


//
//    private void form_modifier(View convertView) {
//        //INSTANTIATE THE FORM AND INFLATE IT________________________________________________________________________________________________________________
//        AlertDialog.Builder myForm = new AlertDialog.Builder(mContext, R.style.form_theme);
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        View myFormView = inflater.inflate(R.layout.form_ajouter_exercice, convertView.findViewById(R.id.linearLayoutRoot));
//
//        //SET UNFOCUS FOR EDITTEXT, THIS WILL ENABLE TO CLICK OUTSIDE THE KEYBOARD TO CLOSE IT_______________________________________________________________
//        EditText nomInput = myFormView.findViewById(R.id.input_title);
//        EditText descriptionInput = myFormView.findViewById(R.id.input_description);
//
//        ArrayList<EditText> editTextList = new ArrayList<>();
//        editTextList.add(nomInput);
//        editTextList.add(descriptionInput);
//
//        for (int i = 0; i < editTextList.size(); i++) {
//            editTextList.get(i).setOnFocusChangeListener((v, hasFocus) -> {
//                if (!hasFocus) {
//                    hideKeyboard(v);
//                }
//            });
//        }
//
//
//        //Get ImageView for the Form__________________________________________________________________________________________________________________________
//        imageSelector = myFormView.findViewById(R.id.drawableImage);
//
//        //Get Spinner for the Form____________________________________________________________________________________________________________________________
//        Spinner imageSpinner = myFormView.findViewById(R.id.spinnerImage);
//        imageSpinner.setAdapter(new SpinnerAdapter(this, R.layout.spinner_content_layout, getResources().getStringArray(R.array.spinnerImage)));
//        imageSpinner.setOnItemSelectedListener(this);
//
//        //Set Spinner Layout and Strings______________________________________________________________________________________________________________________
//        Spinner spinnerCategorie = myFormView.findViewById(R.id.spinnerCategorie);
//        Spinner spinnerSets = myFormView.findViewById(R.id.spinnerSets);
//        Spinner spinnerPause = myFormView.findViewById(R.id.spinnerPause);
//        Spinner spinnerRepeat = myFormView.findViewById(R.id.spinnerRepeat);
//        Spinner spinnerDuree = myFormView.findViewById(R.id.spinnerDuree);
//
//        spinnerCategorie.setAdapter(new SpinnerAdapter(this, R.layout.spinner_content_layout, getResources().getStringArray(R.array.spinnerCategorie)));
//        spinnerSets.setAdapter(new SpinnerAdapter(this, R.layout.spinner_content_layout, getResources().getStringArray(R.array.spinnerSets)));
//        spinnerPause.setAdapter(new SpinnerAdapter(this, R.layout.spinner_content_layout, getResources().getStringArray(R.array.spinnerPause)));
//        spinnerRepeat.setAdapter(new SpinnerAdapter(this, R.layout.spinner_content_layout, getResources().getStringArray(R.array.spinnerRepeat)));
//        spinnerDuree.setAdapter(new SpinnerAdapter(this, R.layout.spinner_content_layout, getResources().getStringArray(R.array.spinnerDuree)));
//
//        //FORM ACTION ====CREATE / CANCEL======_______________________________________________________________________________________________________________
//        myForm.setView(myFormView)
//                .setPositiveButton("Create", (dialog, id) -> {
//                    //Get Form Values
//
//                    String nomForm = nomInput.getText().toString();
//                    String descriptionForm = descriptionInput.getText().toString();
//                    String categorieForm = spinnerCategorie.getSelectedItem().toString();
//                    String setsForm = spinnerSets.getSelectedItem().toString();
//                    String pauseForm = spinnerPause.getSelectedItem().toString();
//                    String repeatForm = spinnerRepeat.getSelectedItem().toString();
//                    String dureeForm = spinnerDuree.getSelectedItem().toString();
//                    String imageForm = imageSpinner.getSelectedItem().toString();
//                    String favoriteForm = "0";
//
//                    //Insert into Database_______________________________________________________________________________________________________________________
//                    Exercice exercice = new Exercice(nomForm, imageForm, repeatForm, categorieForm, setsForm, dureeForm, descriptionForm, pauseForm, favoriteForm);
//                    fireDB.ajouter(exercice);
//                    //TEST_______________________________________________________________________________________________________________________________________
//                    arrayAdapter.notifyDataSetChanged();
//                    Toast.makeText(context, "Exercice " + nomForm + " sauvegarder correctement!", Toast.LENGTH_LONG).show();
//                })
//
//                .setNegativeButton("Cancel", (dialog, id) -> dialog.dismiss());
//
//        //CREATE FORM
//        myForm.create();
//
//        //DISPLAY THE FORM
//        myForm.show();
//    }


}