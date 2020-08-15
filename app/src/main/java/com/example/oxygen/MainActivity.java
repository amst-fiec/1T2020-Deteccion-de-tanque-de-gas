package com.example.oxygen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.oxygen.Fragments.EstacionesFragment;
import com.example.oxygen.Fragments.EstacionsFragment;
import com.example.oxygen.Fragments.ProfileFragment;
import com.example.oxygen.ObjetosNat.FirebaseDatos;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    //Variables
    public static final int GOOGLE_SIGN_IN =123;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private Button btn_login;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    //DataSnapshot dataSnapshot = new DataSnapshot();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarParametros();
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
           // Bundle bundleEstacion = new Bundle();
            //bundleEstacion.putSerializable("info_user",info_user);
            //Intent intentProfileF = new Intent(this, EstacionsFragment.class);
           // intentPro.putExtra("info_user",info_user);
            i.putExtra("info_user",info_user);
            Intent intentEstacion = new Intent(this, EstacionsFragment.class);
            intentEstacion.putExtra("info_user",info_user);
            intentPro.putExtra("info_user",info_user);
            //Intent intent = new Intent(this,EstacionsFragment.class);

            //envio de arraylist con estaciones
            //intentPro.putExtra("estaciones", estaciones);
            finish();
            startActivity(intentPro);


        }else{
            System.out.println("sin registrarse");
        }


        //DATOS DE PRUEBA PARA ESTACIÓN

        //primera estacion
        // Tanque tanque1 = new Tanque("Descripcion tanque 1",1,8.2,6.2,90);
        //Estacion estacion1 = new Estacion(tanque1,"guayaquil",505,1,1);

        //segunda estacion
        //Tanque tanque2 = new Tanque("Descripcion tanque 2",2,8,5,90);
        //Estacion estacion2 = new Estacion(tanque2,"Quito",300,2,2);

        //lista con las estaciones propias del usuario
        //ArrayList<Estacion> estaciones = new ArrayList<>();
        //estaciones.add(estacion1);
        //estaciones.add(estacion2);
    }

    /**
     * Metodo para iniciar todos los parametros de esta clase
     *
     */

    public HashMap<String,String> cargarUsuario(FirebaseUser user){
        HashMap<String,String> info_user = new HashMap<>();
        info_user.put("user_name",user.getDisplayName());
        info_user.put("user_email",user.getEmail());
        info_user.put("user_photo",String.valueOf(user.getPhotoUrl()));
        info_user.put("user_id",user.getUid());
        String idModulo = "505050";
        info_user.put("idModulo",idModulo);
        String uiDUser = user.getUid();
        String correo = user.getEmail();
        String imagen = String.valueOf(user.getPhotoUrl());
        String nombreUsuario = user.getDisplayName();
        Usuario usuario = new Usuario(correo,imagen,nombreUsuario);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(FirebaseDatos.USUARIO_FI).child(uiDUser).setValue(usuario);
        databaseReference.child(FirebaseDatos.USUARIO_FI).child(uiDUser).child("Estaciones").child(idModulo).child("Descripcion").setValue("Paciente estable");

        return info_user;
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



}