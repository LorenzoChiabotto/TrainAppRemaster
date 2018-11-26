package com.lorenzoch.trainappremaster.model;

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

    public void setSeries(int series) {
        this.series = series;
    }

    public EnumTipoEstatico getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipoEstatico tipo) {
        this.tipo = tipo;
    }
}
