package com.example.oxygen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.oxygen.Fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class PrincipalActivity extends AppCompatActivity {

    //argumentos a enviar entre activities
    Bundle args;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        BottomNavigationView bottomNav = findViewById(R.id.botton_menu);


        args = new Bundle();
        //args.putString("ejemplo", ejemplo);


        //inicio del fragment por defecto-----------------------------------------------------------
        Fragment fragmentUno = new EstacionsFragment();
        Intent i = getIntent();
        HashMap<String, String> info_user = (HashMap<String, String>)i.getSerializableExtra("info_user");
        ArrayList<Estacion> estaciones_user = (ArrayList<Estacion>)i.getSerializableExtra("estaciones");

        Bundle b = new Bundle();
        b.putSerializable("info_user",info_user);
        b.putSerializable("estaciones_user",estaciones_user);
        fragmentUno.setArguments(b);
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
                            /*Intent intent = getIntent();
                            HashMap<String, String> info_user = (HashMap<String, String>)intent.getSerializableExtra("info_user");
                            //agregar elementos a enviar al fragment----------------------------------------------------
                            Bundle b = new Bundle();
                            b.putSerializable("info_user",info_user);
                            selectedFragment.setArguments(b);*/
                            break;

                    }

                    Intent i = getIntent();
                    HashMap<String, String> info_user = (HashMap<String, String>)i.getSerializableExtra("info_user");
                    ArrayList<Estacion> estaciones_user = (ArrayList<Estacion>)i.getSerializableExtra("estaciones");

                    Bundle b = new Bundle();
                    b.putSerializable("info_user",info_user);
                    b.putSerializable("estaciones_user",estaciones_user);
                    selectedFragment.setArguments(b);

                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_fragment,selectedFragment).commit();
                    return true;

                }
            };

}