package com.lorenzoch.trainappremaster.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.lorenzoch.trainappremaster.EnumTipoEstatico;

import java.sql.Time;

public class EjercicioEstaticoTiempo extends EjercicioEstatico implements Parcelable {

    private long tiempoRepeticion, tiempoEntreSeries;

    EjercicioEstaticoTiempo(){}
    EjercicioEstaticoTiempo(int id, int series, EnumTipoEstatico tipo, long tiempo, long descanso){
        super(id,series,tipo);
        setTiempoRepeticion(tiempo);
        setTiempoEntreSeries(descanso);
    }


    protected EjercicioEstaticoTiempo(Parcel in) {
        super(in);
        setTiempoRepeticion(in.readLong());
        setTiempoEntreSeries(in.readLong());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(getTiempoRepeticion());
        dest.writeLong(getTiempoEntreSeries());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EjercicioEstaticoTiempo> CREATOR = new Creator<EjercicioEstaticoTiempo>() {
        @Override
        public EjercicioEstaticoTiempo createFromParcel(Parcel in) {
            return new EjercicioEstaticoTiempo(in);
        }

        @Override
        public EjercicioEstaticoTiempo[] newArray(int size) {
            return new EjercicioEstaticoTiempo[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "EjercicioEstaticoRepeticiones{"+
                "id="+ getId()+
                ", tipo="+getTipo().name()+
                ", series="+getSeries()+
                ", tiemporepeticion="+getTiempoRepeticion()+
                ", tiempoentreseries="+getTiempoEntreSeries()+
                "}";
    }

    public long getTiempoEntreSeries() {
        return tiempoEntreSeries;
    }

    private void setTiempoEntreSeries(long tiempoEntreSeries) {
        this.tiempoEntreSeries = tiempoEntreSeries;
    }

    public long getTiempoRepeticion() {
        return tiempoRepeticion;
    }

    private void setTiempoRepeticion(long tiempoRepeticion) {
        this.tiempoRepeticion = tiempoRepeticion;
    }
}
