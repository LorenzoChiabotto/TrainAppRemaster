package com.lorenzoch.trainappremaster.pantallas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.lorenzoch.trainappremaster.R;

public class MainActivity extends AppCompatActivity {

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

    @Override
    public void onBackPressed() {
        if (navigation.getSelectedItemId() == R.id.navigation_hoy){
            moveTaskToBack(true);
        }
        navigation.setSelectedItemId(R.id.navigation_hoy);
    }

    private void signOut(){
        FirebaseAuth.getInstance().signOut();
    }
}
