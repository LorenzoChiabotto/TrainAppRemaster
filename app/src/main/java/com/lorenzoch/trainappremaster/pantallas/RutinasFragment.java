package com.lorenzoch.trainappremaster.pantallas;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lorenzoch.trainappremaster.EnumDiasSemana;
import com.lorenzoch.trainappremaster.R;
import com.lorenzoch.trainappremaster.model.Ejercicio;
import com.lorenzoch.trainappremaster.model.EjercicioDinamico;
import com.lorenzoch.trainappremaster.model.EjercicioEstatico;
import com.lorenzoch.trainappremaster.model.EjercicioEstaticoRepeticiones;
import com.lorenzoch.trainappremaster.model.EjercicioEstaticoTiempo;
import com.lorenzoch.trainappremaster.model.Rutina;

import java.util.LinkedList;

public class RutinasFragment extends Fragment {
    private static final String TAG = "RutinasFragment";
    FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_button, container,false);

        view.findViewById(R.id.fbAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altaRutina();
            }
        });

        return view;
    }


    public void altaRutina(){
        LinkedList<Ejercicio> ret = new LinkedList<>();
        ret.add(new EjercicioEstaticoRepeticiones());
        ret.add(new EjercicioEstaticoTiempo());
        ret.add(new EjercicioDinamico());
        Rutina rutina = new Rutina(0, ret, EnumDiasSemana.LUNES);

        db = FirebaseFirestore.getInstance();
        db.collection(getString(R.string.FSrutinas))
                .add(rutina)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }
}
