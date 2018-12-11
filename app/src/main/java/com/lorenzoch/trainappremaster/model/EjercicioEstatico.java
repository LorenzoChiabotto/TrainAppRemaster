package com.lorenzoch.trainappremaster.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.lorenzoch.trainappremaster.EnumTipoEstatico;

public abstract class EjercicioEstatico extends Ejercicio implements Parcelable {

    private int series;
    private EnumTipoEstatico tipo;

    EjercicioEstatico(){}

    EjercicioEstatico(int id, int series, EnumTipoEstatico tipo){
        super(id);
        setSeries(series);
        setTipo(tipo);
    }


    protected EjercicioEstatico(Parcel in) {
        super(in);
        setSeries(in.readInt());
        setTipo(EnumTipoEstatico.valueOf(in.readString()));
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(series);
        dest.writeString(this.tipo.name());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @NonNull
    @Override
    public abstract String toString();

    public int getSeries() {
        return series;
    }

    private void setSeries(int series) {
        this.series = series;
    }

    public EnumTipoEstatico getTipo() {
        return tipo;
    }

    private void setTipo(EnumTipoEstatico tipo) {
        this.tipo = tipo;
    }
}
