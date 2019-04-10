package com.lorenzoch.trainappremaster.pantallas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
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

import java.util.LinkedList;

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


    public void handleDialogEjerciciosResponse(String tag, Ejercicio ejercicio, EnumDiasSemana diaSemana){
        Log.d(TAG, "handleDialogEjerciciosResponse: BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.bundleEj),ejercicio);
        bundle.putSerializable(getString(R.string.bundleDia),diaSemana);
        
        RutinasFragment fragment=new RutinasFragment();
        fragment.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        transaction.replace(R.id.layoutPrincipal, fragment, tag);

        transaction.commit();

    }
}
