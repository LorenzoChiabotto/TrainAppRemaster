package com.lorenzoch.trainappremaster.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lorenzoch.trainappremaster.R;
import com.lorenzoch.trainappremaster.model.Ejercicio;
import com.lorenzoch.trainappremaster.model.EjercicioEstatico;

import java.util.LinkedList;
import java.util.List;

public class RutinaAdapter extends ArrayAdapter {

    Context context;

    public RutinaAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    public RutinaAdapter(Context context, int resource, LinkedList items) {
        super(context, resource, items);
        this.context = context;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup parent) {
        // Declare Variables
        TextView txtTitle;
        TextView txtCabecera;
        LayoutInflater inflater;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.layout_row_rutina, parent, false);

        ((TextView)itemView.findViewById(R.id.txtTipo)).setText(getItem(posicion).getClass().getSimpleName());
        ((TextView)itemView.findViewById(R.id.txtTipoEjercicio)).setText(((Ejercicio)(getItem(posicion))).toStringArray()[0]);
        ((TextView)itemView.findViewById(R.id.txtRepeticionesXseries)).setText(((Ejercicio)(getItem(posicion))).toStringArray()[1]);

        return itemView;

    }
}
