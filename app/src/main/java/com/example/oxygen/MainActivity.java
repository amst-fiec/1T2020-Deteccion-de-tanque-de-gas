package com.example.oxygen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.oxygen.Fragments.EstacionsFragment;
import com.example.oxygen.Fragments.ProfileFragment;
import com.example.oxygen.ObjetosNat.Tanque;
import com.example.oxygen.ObjetosNat.Ubicacion;
import com.example.oxygen.ObjetosNat.VariablesUnicas;
import com.example.oxygen.ObjetosNat.Usuario;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Variables
    public static final int GOOGLE_SIGN_IN =123;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private Button btn_login;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceTanques;

    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Ubicacion> ubicaciones = new ArrayList<>();


    private static Usuario usuario;
    private static ArrayList<Tanque> tanques;

    //private static Usuario usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReferenceTanques = FirebaseDatabase.getInstance().getReference().child(VariablesUnicas.TANQUES_FI);

        iniciarParametros();
        usuarios = solicitarUsuarios();
        ubicaciones = solicitarUbicaciones();
       // tanques = solicitarTanque();


    }


    private void cerrarSesion(){
        mGoogleSignInClient.signOut().addOnCompleteListener(this, task -> updateUI(null));
    }


    /**
     * Metodo para iniciar sesión con google
     * @param v: vista
     */
    public void iniciarsesion(View v){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,GOOGLE_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GOOGLE_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if(account != null){
                    firebaseAuthWithGoogle(account);
                }
            }catch (ApiException e){
                Log.w("TAG", "Fallo el inicio de sesion con google.", e);
                Context context = getApplicationContext();
                CharSequence text = "No se realizó el inicio de sesión con google.";
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(context, text, duration).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){
        Log.d("TAG", "firebaseAuthWithGoogle: "+acct.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this,task ->{
            FirebaseUser user = mAuth.getCurrentUser();
            updateUI(user);
        });
    }

    private void updateUI(FirebaseUser user){
        if(user != null){

            HashMap<String, String> info_user=cargarUsuario(user);

            Intent intentPro = new Intent(this, PrincipalActivity.class);
            Intent i = new Intent(this,ProfileFragment.class);
            i.putExtra("info_user",info_user);
            Intent intentEstacion = new Intent(this, EstacionsFragment.class);
            intentEstacion.putExtra("info_user",info_user);
            intentPro.putExtra("info_user",info_user);
            finish();
            startActivity(intentPro);


        }else{
            System.out.println("sin registrarse");
        }



    }

    public  HashMap<String,String> cargarUsuario(FirebaseUser user){
        HashMap<String,String> info_user = new HashMap<>();
        info_user.put("user_name",user.getDisplayName());
        info_user.put("user_email",user.getEmail());
        info_user.put("user_photo",String.valueOf(user.getPhotoUrl()));
        info_user.put("user_id",user.getUid());
        String uiDUser = user.getUid();
        String correo = user.getEmail();
        String imagen = String.valueOf(user.getPhotoUrl());
        String nombreUsuario = user.getDisplayName();
        usuario = new Usuario(correo,uiDUser,imagen,nombreUsuario);
        databaseReference.child(VariablesUnicas.USUARIO_FI).child(uiDUser).setValue(usuario);

        for (Usuario u: usuarios){
            if(u.equals(usuario)){
                System.out.println("Usuario ya agregado");
            }else{
                databaseReference.child(VariablesUnicas.USUARIO_FI).child(uiDUser).setValue(usuario);
            }
        }


        //System.out.println("Longitud: " + usuarios.size());
        return info_user;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public List<String> obtenerIdUsuarios(){
        List<String> idsUsuarios = new ArrayList<>();
        for(Usuario u: usuarios){
            idsUsuarios.add(u.getIdUser());
        }
        return idsUsuarios;
    }

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }



    public ArrayList<Usuario> solicitarUsuarios(){
        final ArrayList<Usuario> usuariosSistema = new ArrayList<>();
        databaseReference.child(VariablesUnicas.USUARIO_FI).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    //Usuario usuario = snapshot.getValue(Usuario.class);
                    String nombre = String.valueOf(snapshot.child("nombreUsuario").getValue());
                    String idUser = String.valueOf(snapshot.child("idUser").getValue());
                    String imagen = String.valueOf(snapshot.child("imagen").getValue());
                    String correo = String.valueOf(snapshot.child("correo").getValue());
                    DataSnapshot d = snapshot.child("Estaciones");
                    System.out.println("Hay estaciones: " + d.toString());
                    Usuario u = new Usuario(correo,idUser,imagen,nombre);
                    usuariosSistema.add(u);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }); return usuariosSistema;
    }

    public void iniciarParametros(){
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        btn_login = findViewById(R.id.btn_iniciarsesion);

        Intent i = getIntent();
        String msg = i.getStringExtra("msg");
        if(msg != null){
            if(msg.equals("cerrarSesion")){
                cerrarSesion();
            }
        }


    }

    public ArrayList<Ubicacion> solicitarUbicaciones(){
        final ArrayList<Ubicacion> ubicaciones = new ArrayList<>();
        databaseReference.child(VariablesUnicas.USUARIO_FI).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    Ubicacion ubicacion = data.getValue(Ubicacion.class);
                    System.out.println("Ubicacion: " + ubicacion.toString());
                    ubicaciones.add(ubicacion);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return ubicaciones;
    }

    /*
    public ArrayList<Tanque> solicitarTanque(){
        final ArrayList<Tanque> tanquesEncontrados = new ArrayList<>();

        databaseReferenceTanques.child(usuario.getIdUser()).addValueEventListener(new ValueEventListener() {
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
        }); return tanquesEncontrados;

    }*/
    public static ArrayList<Tanque> getTanques() {
        return tanques;

    }






    public static ArrayList<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

}