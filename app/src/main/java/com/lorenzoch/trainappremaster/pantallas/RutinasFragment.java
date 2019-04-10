package com.lorenzoch.trainappremaster.pantallas;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lorenzoch.trainappremaster.Adapters.RutinaAdapter;
import com.lorenzoch.trainappremaster.EnumDiasSemana;
import com.lorenzoch.trainappremaster.R;
import com.lorenzoch.trainappremaster.dialogs.CreacionDinamicoDialogFragment;
import com.lorenzoch.trainappremaster.dialogs.CreacionEstaticoRepeticionDialogFragment;
import com.lorenzoch.trainappremaster.dialogs.CreacionEstaticoTiempoDialogFragment;
import com.lorenzoch.trainappremaster.model.Ejercicio;
import com.lorenzoch.trainappremaster.model.EjercicioDinamico;
import com.lorenzoch.trainappremaster.model.EjercicioEstatico;
import com.lorenzoch.trainappremaster.model.EjercicioEstaticoTiempo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RutinasFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "RutinasFragment";

    IPrincipal iPrincipal;

    EnumDiasSemana selectedDay;
    ListView lista;

    Ejercicio ejercicio;
    RutinaAdapter data;
    LinkedList<Ejercicio> ejerciciosHoy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rutinas, container,false);

        view.findViewById(R.id.fbEstaticoRep).setOnClickListener(this);
        view.findViewById(R.id.fbEstaticoTie).setOnClickListener(this);
        view.findViewById(R.id.fbDinamico).setOnClickListener(this);

        view.findViewById(R.id.rbD).setOnClickListener(this);
        view.findViewById(R.id.rbL).setOnClickListener(this);
        view.findViewById(R.id.rbMA).setOnClickListener(this);
        view.findViewById(R.id.rbMIE).setOnClickListener(this);
        view.findViewById(R.id.rbJ).setOnClickListener(this);
        view.findViewById(R.id.rbV).setOnClickListener(this);
        view.findViewById(R.id.rbS).setOnClickListener(this);

        lista = view.findViewById(R.id.lista);

        Bundle bundle = getArguments();
        if(bundle != null){
            ejercicio = (Ejercicio) bundle.getParcelable(getString(R.string.bundleEj));
            selectedDay = (EnumDiasSemana) bundle.getSerializable(getString(R.string.bundleDia));

            guardarEjercicio(ejercicio);
        }else{
            selectedDay = EnumDiasSemana.DOMINGO;
            ejerciciosHoy = new LinkedList<>();
        }
        getEjerciciosHoy();
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case(R.id.fbEstaticoRep):{
                //Toast.makeText(getContext(),"Estatico repeticiones", Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putSerializable(getString(R.string.bundleEj),ejerciciosHoy);
                bundle.putSerializable(getString(R.string.bundleDia),selectedDay);

                DialogFragment newFragment = new CreacionEstaticoRepeticionDialogFragment();
                String tag = getResources().getString(R.string.estaticorepeticiones);
                newFragment.setArguments(bundle);
                newFragment.show(getFragmentManager(), tag);
                break;}
            case(R.id.fbEstaticoTie):{
                //Toast.makeText(getContext(),"Estatico Tiempo", Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putSerializable(getString(R.string.bundleEj),ejerciciosHoy);
                bundle.putSerializable(getString(R.string.bundleDia),selectedDay);

                DialogFragment newFragment = new CreacionEstaticoTiempoDialogFragment();
                String tag = getResources().getString(R.string.estaticotiempo);

                newFragment.setArguments(bundle);
                newFragment.show(getFragmentManager(), tag);
                break;}
            case(R.id.fbDinamico):{
                //Toast.makeText(getContext(),"Dinamico", Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putSerializable(getString(R.string.bundleEj),ejerciciosHoy);
                bundle.putSerializable(getString(R.string.bundleDia),selectedDay);

                DialogFragment newFragment = new CreacionDinamicoDialogFragment();
                String tag = getResources().getString(R.string.dinamico);

                newFragment.setArguments(bundle);
                newFragment.show(getFragmentManager(), tag);
                break;
            }


            case(R.id.rbD):{
                selectedDay = EnumDiasSemana.DOMINGO;getEjerciciosHoy();break;
            }
            case(R.id.rbL):{
                selectedDay = EnumDiasSemana.LUNES;getEjerciciosHoy();break;
            }
            case(R.id.rbMA):{
                selectedDay = EnumDiasSemana.MARTES;getEjerciciosHoy();break;
            }
            case(R.id.rbMIE):{
                selectedDay = EnumDiasSemana.MIERCOLES;getEjerciciosHoy();break;
            }
            case(R.id.rbJ):{
                selectedDay = EnumDiasSemana.JUEVES;getEjerciciosHoy();break;
            }
            case(R.id.rbV):{
                selectedDay = EnumDiasSemana.VIERNES;getEjerciciosHoy();break;
            }
            case(R.id.rbS):{
                selectedDay = EnumDiasSemana.SABADO;getEjerciciosHoy();break;
            }
        }
    }

    public void getEjerciciosHoy(){
        FirebaseFirestore db;

        db = FirebaseFirestore.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            db.collection(user.getEmail()).document(getString(R.string.RutinaColection)).collection((selectedDay.toString()))
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    Log.d(TAG, "onComplete: "+ document);
                                    Log.d(TAG, "onComplete: "+ document.getData().values().toString());
                                    Log.d(TAG, "onComplete: "+ document.getData().get("id") + "-"+document.getData().get("tipo")+"--"+document.getData().get("repeticiones"));
                                    Log.d(TAG, "onComplete: "+ (document.getData().values().toArray()).length);
                                 //   ejerciciosHoy.add(document.toObject(Ejercicio.class));
                                }
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });
/*
            if (data != null) {
                data.clear();
            }
            data = new RutinaAdapter(getContext(), R.layout.layout_row_rutina, ejerciciosHoy);

            lista.setAdapter(data);
            lista.refreshDrawableState();*/
        }
    }

    private void guardarEjercicio(Ejercicio ejercicio) {
        Log.d(TAG, "guardarRutina: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        FirebaseFirestore db;

        db = FirebaseFirestore.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in

            db.collection(user.getEmail()).document(getString(R.string.RutinaColection)).collection(selectedDay.toString())
                    .add(ejercicio)
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
}
