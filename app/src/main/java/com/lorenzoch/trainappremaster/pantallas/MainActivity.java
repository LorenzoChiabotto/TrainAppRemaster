package com.lorenzoch.trainappremaster.pantallas;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.lorenzoch.trainappremaster.R;

public class MainActivity extends AppCompatActivity {


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
                case R.id.navigation_rutinas:{
                    transaction.replace(R.id.layoutPrincipal, new RutinasFragment());
                    break;
                }
                case R.id.navigation_configuracion:{
                    transaction.replace(R.id.layoutPrincipal, new HoyFragment());
                    break;
                }
                case R.id.navigation_planes:{
                    transaction.replace(R.id.layoutPrincipal, new PlanFragment());
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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigation.setSelectedItemId(R.id.navigation_hoy);
    }

}
