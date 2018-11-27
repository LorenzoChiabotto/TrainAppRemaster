package com.lorenzoch.trainappremaster.model;

import com.lorenzoch.trainappremaster.EnumTipoDinamico;

public class EjercicioDinamico extends Ejercicio{

    private EnumTipoDinamico tipo;

    EjercicioDinamico(){}
    EjercicioDinamico(int id, EnumTipoDinamico tipo){
        super(id);
        setTipo(tipo);
    }

    private void setTipo(EnumTipoDinamico tipo) {
        this.tipo = tipo;
    }

    public EnumTipoDinamico getTipo() {
        return tipo;
    }
}
