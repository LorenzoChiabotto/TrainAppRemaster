package com.lorenzoch.trainappremaster.pantallas;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lorenzoch.trainappremaster.R;
import com.lorenzoch.trainappremaster.dialogs.CreacionDinamicoDialogFragment;
import com.lorenzoch.trainappremaster.dialogs.CreacionEstaticoRepeticionDialogFragment;
import com.lorenzoch.trainappremaster.dialogs.CreacionEstaticoTiempoDialogFragment;

public class PlanFragment extends Fragment implements View.OnClickListener {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan, container,false);

        view.findViewById(R.id.fbEstaticoRep).setOnClickListener(this);
        view.findViewById(R.id.fbEstaticoTie).setOnClickListener(this);
        view.findViewById(R.id.fbDinamico).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        DialogFragment newFragment = null;
        String tag;

        switch (v.getId()){
            case(R.id.fbEstaticoRep):{
                //Toast.makeText(getContext(),"Estatico repeticiones", Toast.LENGTH_SHORT).show();

                newFragment = new CreacionEstaticoRepeticionDialogFragment();
                tag = getResources().getString(R.string.estaticorepeticiones);
                newFragment.show(getFragmentManager(), tag);
                break;}
            case(R.id.fbEstaticoTie):{
                //Toast.makeText(getContext(),"Estatico Tiempo", Toast.LENGTH_SHORT).show();

                newFragment = new CreacionEstaticoTiempoDialogFragment();
                tag = getResources().getString(R.string.estaticotiempo);
                newFragment.show(getFragmentManager(), tag);
                break;}
            case(R.id.fbDinamico):{
                //Toast.makeText(getContext(),"Dinamico", Toast.LENGTH_SHORT).show();

                newFragment = new CreacionDinamicoDialogFragment();
                tag = getResources().getString(R.string.dinamico);
                newFragment.show(getFragmentManager(), tag);
                break;
            }
        }
    }
}
