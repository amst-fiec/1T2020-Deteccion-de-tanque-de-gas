package com.example.oxygen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.oxygen.Fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PrincipalActivity extends AppCompatActivity {

    //argumentos a enviar entre activities
    Bundle args;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        BottomNavigationView bottomNav = findViewById(R.id.botton_menu);


        //agregar elementos a enviar al fragment----------------------------------------------------
        args = new Bundle();
        //args.putString("ejemplo", ejemplo);


        //inicio del fragment por defecto-----------------------------------------------------------
        Fragment fragmentUno = new EstacionsFragment();
        fragmentUno.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.contenedor_fragment, fragmentUno).commit();

        //-----------------------------------------------------------------------------------------

        bottomNav.setOnNavigationItemSelectedListener(navListener);





    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new EstacionsFragment();
                            break;
                        case R.id.nav_add:
                            selectedFragment = new AgregarEstacionFragment();
                            break;
                        case R.id.nav_cuenta:
                            selectedFragment = new ProfileFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_fragment,selectedFragment).commit();
                    return true;

                }
            };

}