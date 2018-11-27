package com.lorenzoch.trainappremaster.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class Ejercicio implements Parcelable{

    private int id;

    Ejercicio(){}
    Ejercicio(int id){
        setId(id);
    }

    protected Ejercicio(Parcel in) {
        setId(in.readInt());
    }

    public static final Creator<Ejercicio> CREATOR = new Creator<Ejercicio>() {
        @Override
        public Ejercicio createFromParcel(Parcel in) {
            return new Ejercicio(in);
        }

        @Override
        public Ejercicio[] newArray(int size) {
            return new Ejercicio[size];
        }
    };

    private void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }


    @NonNull
    @Override
    public String toString() {
        return "Ejercicio{"+
                "id="+ getId()+
                "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }
}
