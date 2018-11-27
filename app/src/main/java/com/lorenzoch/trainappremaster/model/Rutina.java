package com.lorenzoch.trainappremaster.model;

import com.lorenzoch.trainappremaster.EnumDiasSemana;

import java.util.LinkedList;

public class Rutina {

    private int id;
    private LinkedList<Ejercicio> ejercicios;
    private EnumDiasSemana dia;

    Rutina(){}

    Rutina(int id, LinkedList<Ejercicio> ejercicios, EnumDiasSemana dia){
        setId(id);
        setEjercicios(ejercicios);
        setDia(dia);
    }

    public int getId() {
        return id;
    }
    private void setId(int id) {
        this.id = id;
    }

    public LinkedList<Ejercicio> getEjercicios() {
        return ejercicios;
    }
    private void setEjercicios(LinkedList<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public EnumDiasSemana getDia() {
        return dia;
    }
    private void setDia(EnumDiasSemana dia) {
        this.dia = dia;
    }
}
