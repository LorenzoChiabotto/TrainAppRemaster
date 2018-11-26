package com.lorenzoch.trainappremaster.model;

import java.sql.Time;

public class EjercicioEstaticoTiempo extends EjercicioEstatico {
    Time tiempoRepeticion, tiempoEntreSeries;

    EjercicioEstaticoTiempo(){}
    EjercicioEstaticoTiempo(int id, int series,EnumTipoEstatico tipo, Time tiempo, Time descanso){

        super(id,series,tipo);
        setTiempoEntreSeries(descanso);
        setTiempoRepeticion(tiempo);
    }


    public Time getTiempoEntreSeries() {
        return tiempoEntreSeries;
    }

    public void setTiempoEntreSeries(Time tiempoEntreSeries) {
        this.tiempoEntreSeries = tiempoEntreSeries;
    }

    public Time getTiempoRepeticion() {
        return tiempoRepeticion;
    }

    public void setTiempoRepeticion(Time tiempoRepeticion) {
        this.tiempoRepeticion = tiempoRepeticion;
    }
}
