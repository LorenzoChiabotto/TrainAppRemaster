package com.lorenzoch.trainappremaster.model;

public class EjercicioDinamico extends Ejercicio{

    private EnumTipoDinamico tipo;

    EjercicioDinamico(){}
    EjercicioDinamico(int id, EnumTipoDinamico tipo){
        super(id);
        setTipo(tipo);
    }

    public void setTipo(EnumTipoDinamico tipo) {
        this.tipo = tipo;
    }

    public EnumTipoDinamico getTipo() {
        return tipo;
    }
}
