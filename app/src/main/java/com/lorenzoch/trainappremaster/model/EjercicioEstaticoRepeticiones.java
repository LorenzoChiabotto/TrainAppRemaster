package com.lorenzoch.trainappremaster.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.lorenzoch.trainappremaster.EnumTipoEstatico;

public class EjercicioEstaticoRepeticiones extends EjercicioEstatico implements Parcelable {

    private int repeticiones;

    public EjercicioEstaticoRepeticiones(){}

    public EjercicioEstaticoRepeticiones(int id, int series, EnumTipoEstatico tipo, int repeticiones) {
        super(id, series, tipo);
        setRepeticiones(repeticiones);
    }

    protected EjercicioEstaticoRepeticiones(Parcel in) {
        super(in);
        setRepeticiones(in.readInt());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(repeticiones);
    }

    @Override
    public String[] toStringArray() {
        String[] retorno = new String[2];

        retorno[0] = getTipo().toString();
        retorno[1] = getRepeticiones() +" x "+ getSeries() ;

        return retorno;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EjercicioEstaticoRepeticiones> CREATOR = new Creator<EjercicioEstaticoRepeticiones>() {
        @Override
        public EjercicioEstaticoRepeticiones createFromParcel(Parcel in) {
            return new EjercicioEstaticoRepeticiones(in);
        }

        @Override
        public EjercicioEstaticoRepeticiones[] newArray(int size) {
            return new EjercicioEstaticoRepeticiones[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "EjercicioEstaticoRepeticiones{"+
                "id="+ getId()+
                ",tipo="+getTipo().name()+
                ",series="+getSeries()+
                ", repeticiones="+getRepeticiones()+
                "}";
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    private void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }
}
