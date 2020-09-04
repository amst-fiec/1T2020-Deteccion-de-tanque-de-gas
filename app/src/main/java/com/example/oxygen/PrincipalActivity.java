package com.example.oxygen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.oxygen.Fragments.EstacionesFragment;
import com.example.oxygen.Fragments.EstacionsFragment;
import com.example.oxygen.Fragments.ProfileFragment;
import com.example.oxygen.ObjetosNat.Tanque;
import com.example.oxygen.ObjetosNat.Ubicacion;
import com.example.oxygen.ObjetosNat.Usuario;
import com.example.oxygen.ObjetosNat.VariablesUnicas;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class PrincipalActivity extends AppCompatActivity {

    //argumentos a enviar entre activities
    Bundle args;
   private static TreeSet<Integer> idUbicacionesUser;
   private static TreeSet<Tanque> tanques;
    private static TreeSet<Ubicacion> ubicaciones;


    DatabaseReference databaseReference;
    DatabaseReference databaseReferenceTanques;
    DatabaseReference databaseReferenceUbicaciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        BottomNavigationView bottomNav = findViewById(R.id.botton_menu);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(VariablesUnicas.UBICACIONES_FI);
        databaseReferenceTanques = FirebaseDatabase.getInstance().getReference().child(VariablesUnicas.TANQUES_FI);
        databaseReferenceUbicaciones = FirebaseDatabase.getInstance().getReference().child(VariablesUnicas.UBICACIONES_FI);
        idUbicacionesUser = new TreeSet<>();
        idUbicacionesUser = solicitarUbicacionesUser();
        tanques = solicitarTanque();
        ubicaciones = solicitarUbicaciones();
        args = new Bundle();
        //args.putString("ejemplo", ejemplo);


        //inicio del fragment por defecto-----------------------------------------------------------
        Fragment fragmentUno = new AgregarEstacionFragment();
        Intent i = getIntent();
        //HashMap<String, String> info_user = (HashMap<String, String>)i.getSerializableExtra("info_user");
        //ArrayList<Estacion> estaciones_user = (ArrayList<Estacion>)i.getSerializableExtra("estaciones");

        Bundle b = new Bundle();
        //b.putSerializable("info_user",info_user);
        //b.putSerializable("estaciones_user",estaciones_user);
        fragmentUno.setArguments(b);
        getSupportFragmentManager().beginTransaction().add(R.id.contenedor_fragment, fragmentUno).commit();

        //-----------------------------------------------------------------------------------------

        bottomNav.setOnNavigationItemSelectedListener(navListener);


    }

    public TreeSet<Integer> solicitarUbicacionesUser(){
        final TreeSet<Integer> idUbicacionesUsuario = new TreeSet<>();
        Usuario u = MainActivity.getUsuario();
        if(u != null){
            databaseReference.child(u.getIdUser()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot d: dataSnapshot.getChildren()){
                        String id = d.getKey().toString();
                        Integer integer = Integer.parseInt(id);
                        idUbicacionesUsuario.add(integer);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else{
            u = RegistroActivity.getUsuario();
            databaseReference.child(u.getIdUser()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot d: dataSnapshot.getChildren()){
                        String id = d.getKey().toString();
                        Integer integer = Integer.parseInt(id);
                        idUbicacionesUsuario.add(integer);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
         return idUbicacionesUsuario;
    }

    public TreeSet<Tanque> solicitarTanque(){
        final TreeSet<Tanque> tanquesEncontrados = new TreeSet<>();
        Usuario u = MainActivity.getUsuario();
        if(u != null){
            databaseReferenceTanques.child(u.getIdUser()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot data: dataSnapshot.getChildren()){
                        Tanque t = data.getValue(Tanque.class);
                        tanquesEncontrados.add(t);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else{
            u = RegistroActivity.getUsuario();
            databaseReferenceTanques.child(u.getIdUser()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot data: dataSnapshot.getChildren()){
                        Tanque t = data.getValue(Tanque.class);
                        tanquesEncontrados.add(t);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
         return tanquesEncontrados;

    }

    public TreeSet<Ubicacion> solicitarUbicaciones(){
        final TreeSet<Ubicacion> ubicacionesEncontradas = new TreeSet<>();
        Usuario u = MainActivity.getUsuario();
        if(u != null){
            databaseReferenceUbicaciones.child(u.getIdUser()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot d: dataSnapshot.getChildren()
                    ) {
                        Ubicacion u = d.getValue(Ubicacion.class);
                        ubicacionesEncontradas.add(u);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else{
            u = RegistroActivity.getUsuario();
            databaseReferenceUbicaciones.child(u.getIdUser()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot d: dataSnapshot.getChildren()
                    ) {
                        Ubicacion u = d.getValue(Ubicacion.class);
                        ubicacionesEncontradas.add(u);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        return ubicacionesEncontradas;
    }

    public static TreeSet<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public static TreeSet<Integer> getIdUbicacionesUser() {
        return idUbicacionesUser;
    }

    public static TreeSet<Tanque> getTanques() {
        return tanques;

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
                   // ArrayList<Estacion> estaciones_user = (ArrayList<Estacion>)i.getSerializableExtra("estaciones");

                    Bundle b = new Bundle();
                    b.putSerializable("info_user",info_user);
                    //b.putSerializable("estaciones_user",estaciones_user);
                    selectedFragment.setArguments(b);

                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_fragment,selectedFragment).commit();
                    return true;

                }
            };




}