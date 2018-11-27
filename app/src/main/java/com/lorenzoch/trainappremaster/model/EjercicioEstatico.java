package com.lorenzoch.trainappremaster.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.lorenzoch.trainappremaster.EnumTipoEstatico;

public class EjercicioEstatico extends Ejercicio implements Parcelable {

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

    public static final Creator<EjercicioEstatico> CREATOR = new Creator<EjercicioEstatico>() {
        @Override
        public EjercicioEstatico createFromParcel(Parcel in) {
            return new EjercicioEstatico(in);
        }

        @Override
        public EjercicioEstatico[] newArray(int size) {
            return new EjercicioEstatico[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "EjercicioEstatico{"+
                ", id="+ getId()+
                ", tipo="+getTipo().name()+
                ", series="+getSeries()+
                "}";
    }

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
