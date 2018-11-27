package com.lorenzoch.trainappremaster.model;

import com.lorenzoch.trainappremaster.EnumTipoEstatico;

public class EjercicioEstaticoRepeticiones extends EjercicioEstatico {

    private int repeticiones;

    EjercicioEstaticoRepeticiones(){}

    EjercicioEstaticoRepeticiones(int id, int series, EnumTipoEstatico tipo, int repeticiones) {
        super(id, series, tipo);
        setRepeticiones(repeticiones);
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    private void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }
}
