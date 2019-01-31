package com.lorenzoch.trainappremaster.pantallas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lorenzoch.trainappremaster.EnumDiasSemana;
import com.lorenzoch.trainappremaster.R;
import com.lorenzoch.trainappremaster.TrainApp;
import com.lorenzoch.trainappremaster.model.Ejercicio;
import com.lorenzoch.trainappremaster.model.EjercicioDinamico;
import com.lorenzoch.trainappremaster.model.EjercicioEstaticoRepeticiones;
import com.lorenzoch.trainappremaster.model.EjercicioEstaticoTiempo;

public class MainActivity extends AppCompatActivity implements IPrincipal {
    private static final String TAG = "MainActivity";

    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_hoy:{
                    transaction.replace(R.id.layoutPrincipal, new HoyFragment());
                    break;
                }
                case R.id.navigation_estadisticas:{
                    transaction.replace(R.id.layoutPrincipal, new EstadisticasFragment());
                    break;
                }
                case R.id.navigation_configuracion:{

                    break;
                }
                case R.id.navigation_planes:{
                    transaction.replace(R.id.layoutPrincipal, new RutinasFragment());
                    break;
                }
                default:
                    return false;
            }
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigation.setSelectedItemId(R.id.navigation_hoy);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.desconectar:
                signOut();
                Intent intent = new Intent(this, LoginActivity.class);

                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void signOut(){
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    public void onBackPressed() {
        if (navigation.getSelectedItemId() == R.id.navigation_hoy){
            moveTaskToBack(true);
        }
        navigation.setSelectedItemId(R.id.navigation_hoy);
    }





    //      INTERFACE

    @Override
    public void addEjercicio(Ejercicio ejercicio, EnumDiasSemana diaSemana) {
        TrainApp.addEjercicio(ejercicio,diaSemana);
    }


    public void handleDialogEjerciciosResponse(String tag, Ejercicio ejerciciosHoy, EnumDiasSemana diaSemana){


        FirebaseFirestore db;

        db = FirebaseFirestore.getInstance();

        db.collection().document().collection(getString(R.string.RutinaColection))
                .add(ejerciciosHoy)
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



        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();

        txtTitulo.setText(tag);

        Bundle bundle = new Bundle();
        bundle.putSerializable(getString(R.string.intent_message), ejercicios);
        fragment.setArguments(bundle);

        transaction.replace(R.id.frameLayout, new RutinasFragment(), tag);


        transaction.commit();
    }
}
