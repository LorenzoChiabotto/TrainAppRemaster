package com.lorenzoch.trainappremaster;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lorenzoch.trainappremaster.model.Ejercicio;
import com.lorenzoch.trainappremaster.model.EjercicioDinamico;
import com.lorenzoch.trainappremaster.model.EjercicioEstaticoRepeticiones;
import com.lorenzoch.trainappremaster.model.EjercicioEstaticoTiempo;
import com.lorenzoch.trainappremaster.model.Rutina;
import com.lorenzoch.trainappremaster.model.Usuario;

public class TrainApp {

    private Usuario user;

    TrainApp(){
    }

    public void Login(){

    }

    public static void addEjercicio(Ejercicio ejercicio, EnumDiasSemana diaSemana){

        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        CollectionReference restaurants = mFirestore.collection("rutinas")
                .document(diaSemana.toString())
                .collection("ejercicios");
        restaurants.add(ejercicio);
    }

    public EjercicioEstaticoRepeticiones altaEstatico(EjercicioEstaticoRepeticiones ejercicio){
        return ejercicio;
    }
    public EjercicioEstaticoTiempo altaEstatico(EjercicioEstaticoTiempo ejercicio){
        return ejercicio;
    }
    public EjercicioDinamico altaEjercicioDinamico(EjercicioDinamico ejercicio){
        return ejercicio;
    }

}
