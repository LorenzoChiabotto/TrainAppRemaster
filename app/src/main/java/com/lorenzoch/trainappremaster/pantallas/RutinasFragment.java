package com.lorenzoch.trainappremaster.pantallas;

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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lorenzoch.trainappremaster.Adapters.RutinaAdapter;
import com.lorenzoch.trainappremaster.EnumDiasSemana;
import com.lorenzoch.trainappremaster.R;
import com.lorenzoch.trainappremaster.dialogs.CreacionDinamicoDialogFragment;
import com.lorenzoch.trainappremaster.dialogs.CreacionEstaticoRepeticionDialogFragment;
import com.lorenzoch.trainappremaster.dialogs.CreacionEstaticoTiempoDialogFragment;
import com.lorenzoch.trainappremaster.model.Ejercicio;

import java.util.LinkedList;

public class RutinasFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "RutinasFragment";

    EnumDiasSemana selectedDay;
    ListView lista;


    FirebaseFirestore db;

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

        if(selectedDay == null){

        }

        getEjerciciosHoy();

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case(R.id.fbEstaticoRep):{
                //Toast.makeText(getContext(),"Estatico repeticiones", Toast.LENGTH_SHORT).show();

                DialogFragment newFragment = new CreacionEstaticoRepeticionDialogFragment();
                String tag = getResources().getString(R.string.estaticorepeticiones);
                newFragment.show(getFragmentManager(), tag);
                break;}
            case(R.id.fbEstaticoTie):{
                //Toast.makeText(getContext(),"Estatico Tiempo", Toast.LENGTH_SHORT).show();

                DialogFragment newFragment = new CreacionEstaticoTiempoDialogFragment();
                String tag = getResources().getString(R.string.estaticotiempo);
                newFragment.show(getFragmentManager(), tag);
                break;}
            case(R.id.fbDinamico):{
                //Toast.makeText(getContext(),"Dinamico", Toast.LENGTH_SHORT).show();

                DialogFragment newFragment = new CreacionDinamicoDialogFragment();
                String tag = getResources().getString(R.string.dinamico);
                newFragment.show(getFragmentManager(), tag);
                break;
            }


            case(R.id.rbD):{
                selectedDay = EnumDiasSemana.DOMINGO;break;
            }
            case(R.id.rbL):{
                selectedDay = EnumDiasSemana.LUNES;break;
            }
            case(R.id.rbMA):{
                selectedDay = EnumDiasSemana.MARTES;break;
            }
            case(R.id.rbMIE):{
                selectedDay = EnumDiasSemana.MIERCOLES;break;
            }
            case(R.id.rbJ):{
                selectedDay = EnumDiasSemana.JUEVES;break;
            }
            case(R.id.rbV):{
                selectedDay = EnumDiasSemana.VIERNES;break;
            }
            case(R.id.rbS):{
                selectedDay = EnumDiasSemana.SABADO;break;
            }
        }
    }

    public void getEjerciciosHoy(){


        data.clear();
        data = new RutinaAdapter(getContext(),R.layout.layout_row_rutina,ejerciciosHoy);

        lista.setAdapter(data);
    }

}
