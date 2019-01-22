package com.lorenzoch.trainappremaster.pantallas;

import com.lorenzoch.trainappremaster.model.EjercicioDinamico;
import com.lorenzoch.trainappremaster.model.EjercicioEstatico;
import com.lorenzoch.trainappremaster.model.EjercicioEstaticoRepeticiones;
import com.lorenzoch.trainappremaster.model.EjercicioEstaticoTiempo;

public interface IPrincipal {


    public void addEjercicio(EjercicioDinamico ej);
    public void addEjercicio(EjercicioEstaticoRepeticiones ej);
    public void addEjercicio(EjercicioEstaticoTiempo ej);
}
