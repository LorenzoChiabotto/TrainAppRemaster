package com.lorenzoch.trainappremaster.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.lorenzoch.trainappremaster.EnumDiasSemana;

import java.util.LinkedList;

public class Rutina implements Parcelable {

    private int id;
    private LinkedList<Ejercicio> ejercicios;
    private EnumDiasSemana dia;

    public Rutina(){}

    public Rutina(int id, LinkedList<Ejercicio> ejercicios, EnumDiasSemana dia){
        setId(id);
        setEjercicios(ejercicios);
        setDia(dia);
    }

    protected Rutina(Parcel in) {
        setId(in.readInt());
        setDia(EnumDiasSemana.valueOf(in.readString()));
        in.readTypedList(ejercicios,Ejercicio.CREATOR);
    }

    public static final Creator<Rutina> CREATOR = new Creator<Rutina>() {
        @Override
        public Rutina createFromParcel(Parcel in) {
            return new Rutina(in);
        }

        @Override
        public Rutina[] newArray(int size) {
            return new Rutina[size];
        }
    };

    public int getId() {
        return id;
    }
    private void setId(int id) {
        this.id = id;
    }

    public LinkedList<Ejercicio> getEjercicios() {
        return ejercicios;
    }
    private void setEjercicios(LinkedList<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public EnumDiasSemana getDia() {
        return dia;
    }
    private void setDia(EnumDiasSemana dia) {
        this.dia = dia;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
        dest.writeString(getDia().name());
        dest.writeTypedList(getEjercicios());
    }

    @NonNull
    @Override
    public String toString() {
        return "Rutina{"+
                "id="+getId()+
                ", dia="+getDia().name()+
                ", ejercicios="+getEjercicios().toString()+
                "}";
    }
}
