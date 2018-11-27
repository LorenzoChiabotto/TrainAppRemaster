package com.lorenzoch.trainappremaster.model;

import com.lorenzoch.trainappremaster.EnumTipoEstatico;

public class EjercicioEstatico extends Ejercicio{

    private int series;
    private EnumTipoEstatico tipo;

    EjercicioEstatico(){}

    EjercicioEstatico(int id, int series, EnumTipoEstatico tipo){
        super(id);
        setSeries(series);
        setTipo(tipo);
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
