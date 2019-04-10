package com.lorenzoch.trainappremaster.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.lorenzoch.trainappremaster.EnumDiasSemana;
import com.lorenzoch.trainappremaster.R;
import com.lorenzoch.trainappremaster.model.Ejercicio;
import com.lorenzoch.trainappremaster.model.EjercicioEstaticoTiempo;
import com.lorenzoch.trainappremaster.pantallas.IPrincipal;

import java.util.LinkedList;

public class CreacionEstaticoTiempoDialogFragment extends DialogFragment {
    private static final String TAG = "CreacionEstaticoTiempoD";
    IPrincipal iPrincipal;

    EnumDiasSemana dia;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_creacion_estatico_tiempo, container, false);

        Bundle bundle = getArguments();
        if (bundle!= null){
            dia = (EnumDiasSemana) bundle.getSerializable(getString(R.string.bundleDia));
        }

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> data=new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.arrayTest));
        spinner.setAdapter(data);


        view.findViewById(R.id.btnAceptar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EjercicioEstaticoTiempo ej =new  EjercicioEstaticoTiempo();
                iPrincipal.handleDialogEjerciciosResponse(TAG, ej,dia);
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iPrincipal = (IPrincipal) getActivity();
    }
}
