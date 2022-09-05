package com.example.Labo2;

import android.os.Parcel;
import android.os.Parcelable;

public class Exercice implements Parcelable {

    private Integer _id;
    private String title;
    private String img;
    private String repeat;
    private String categorie;
    private String sets;
    private String duree;
    private String description;
    private String pause;
    private String favorite;

    //**************\\
    //  CONSTRUCTOR  \\
    //*****************************************************************************************************************************
    Exercice() {
        this._id = 0;
        this.title = "";
        this.img = "";
        this.repeat = "";
        this.categorie = "";
        this.sets = "";
        this.duree = "";
        this.description = "";
        this.pause = "";
        this.favorite = "";

    }

    Exercice(Integer _id, String title, String img, String repeat, String categorie, String sets, String duree, String description, String pause, String favorite) {
        this._id = _id;
        this.title = title;
        this.img = img;
        this.repeat = repeat;
        this.categorie = categorie;
        this.sets = sets;
        this.duree = duree;
        this.description = description;
        this.pause = pause;
        this.favorite = favorite;
    }


    //*************\\
    //  PARCELABLE  \\
    //*****************************************************************************************************************************


    protected Exercice(Parcel in) {
        _id = in.readInt();
        title = in.readString();
        img = in.readString();
        repeat = in.readString();
        categorie = in.readString();
        sets = in.readString();
        duree = in.readString();
        description = in.readString();
        pause = in.readString();
        favorite = in.readString();
    }

    public static final Creator<Exercice> CREATOR = new Creator<Exercice>() {
        @Override
        public Exercice createFromParcel(Parcel in) {
            return new Exercice(in);
        }

        @Override
        public Exercice[] newArray(int size) {
            return new Exercice[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(_id);
        parcel.writeString(title);
        parcel.writeString(img);
        parcel.writeString(repeat);
        parcel.writeString(categorie);
        parcel.writeString(sets);
        parcel.writeString(duree);
        parcel.writeString(description);
        parcel.writeString(pause);
        parcel.writeString(favorite);
    }

    //**********\\
    //  GETTERS  \\
    //*****************************************************************************************************************************

    public Integer get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getImg() {
        return img;
    }

    public String getRepeat() {
        return repeat;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getSets() {
        return sets;
    }

    public String getDuree() {
        return duree;
    }

    public String getDescription() {
        return description;
    }

    public String getPause() {
        return pause;
    }

    public String getFavorite() {
        return favorite;
    }


    //**********\\
    //  SETTERS  \\
    //*****************************************************************************************************************************


    public void set_id(Integer _id) {
        this._id = _id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPause(String pause) {
        this.pause = pause;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
}