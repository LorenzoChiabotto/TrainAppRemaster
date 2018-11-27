package com.lorenzoch.trainappremaster.model;

public class Ejercicio {

    private int id;

    Ejercicio(){}
    Ejercicio(int id){
        setId(id);
    }

    private void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}
