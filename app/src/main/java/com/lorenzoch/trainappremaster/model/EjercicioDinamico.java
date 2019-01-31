package com.lorenzoch.trainappremaster.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.lorenzoch.trainappremaster.EnumTipoDinamico;

public class EjercicioDinamico extends Ejercicio implements Parcelable {

    private EnumTipoDinamico tipo;

    public EjercicioDinamico(){}
    public EjercicioDinamico(int id, EnumTipoDinamico tipo){
        super(id);
        setTipo(tipo);
    }

    protected EjercicioDinamico(Parcel in) {
        super(in);
        setTipo(EnumTipoDinamico.valueOf(in.readString()));
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.tipo.name());
    }

    @Override
    public String[] toStringArray() {
        String[] retorno = new String[2];

        retorno[0] = getTipo().toString();
        retorno[1]= " ";

        return retorno;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EjercicioDinamico> CREATOR = new Creator<EjercicioDinamico>() {
        @Override
        public EjercicioDinamico createFromParcel(Parcel in) {
            return new EjercicioDinamico(in);
        }

        @Override
        public EjercicioDinamico[] newArray(int size) {
            return new EjercicioDinamico[size];
        }
    };


    @NonNull
    @Override
    public String toString() {
        return "EjercicioDinamico{"+
                ", id="+ getId()+
                ", tipo="+getTipo().name()+
                "}";
    }

    private void setTipo(EnumTipoDinamico tipo) {
        this.tipo = tipo;
    }

    public EnumTipoDinamico getTipo() {
        return tipo;
    }
}
