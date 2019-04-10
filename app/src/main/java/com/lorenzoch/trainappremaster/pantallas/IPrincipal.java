package com.lorenzoch.trainappremaster.pantallas;

import com.lorenzoch.trainappremaster.EnumDiasSemana;
import com.lorenzoch.trainappremaster.model.Ejercicio;
import com.lorenzoch.trainappremaster.model.EjercicioDinamico;
import com.lorenzoch.trainappremaster.model.EjercicioEstatico;
import com.lorenzoch.trainappremaster.model.EjercicioEstaticoRepeticiones;
import com.lorenzoch.trainappremaster.model.EjercicioEstaticoTiempo;

import java.util.LinkedList;

public interface IPrincipal {


    public void addEjercicio(Ejercicio ejercicio, EnumDiasSemana diaSemana);
    public void handleDialogEjerciciosResponse(String tag, Ejercicio ejercicio, EnumDiasSemana diaSemana);
}
