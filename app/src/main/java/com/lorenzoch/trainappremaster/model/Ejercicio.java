package com.lorenzoch.trainappremaster.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public abstract class Ejercicio implements Parcelable{

    private int id;

    Ejercicio(){}
    Ejercicio(int id){
        setId(id);
    }

    protected Ejercicio(Parcel in) {
        setId(in.readInt());
    }

    private void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public static final Creator<Ejercicio> CREATOR = new ClassLoaderCreator<Ejercicio>() {
        @Override
        public Ejercicio createFromParcel(Parcel source, ClassLoader loader) {
            return null;
        }

        @Override
        public Ejercicio createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public Ejercicio[] newArray(int size) {
            return new Ejercicio[0];
        }
    };

    @NonNull
    @Override
    public abstract String toString();

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }
}
