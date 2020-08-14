package com.example.oxygen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.oxygen.Objetos.Tanque;
import com.example.oxygen.Objetos.Usuario;
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
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Variables
    public static final int GOOGLE_SIGN_IN =123;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private Button btn_login;
    private ArrayList<Usuario> usuarios;

    //public Intent inteP = new Intent(this,PrincipalActivity.class);
    //static Usuario usuario = null;

    private ArrayList<Estacion> estacionesUsuario;
    private Tanque tanque;
    private Estacion estacion;
    private DatabaseReference db_reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarParametros();
       // iniciarLecturaDeBaseDatos();

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

            HashMap<String,String> info_user = new HashMap<>();
            info_user.put("user_name",user.getDisplayName());
            info_user.put("user_email",user.getEmail());
            info_user.put("user_photo",String.valueOf(user.getPhotoUrl()));
            info_user.put("user_id",user.getUid());
            //System.out.println(user.getDisplayName().toString());
            //Crear objeto Usuario

            db_reference = FirebaseDatabase.getInstance().getReference().child("Usuarios");
            System.out.println(db_reference);
            //Obtener usuario O crear usuario
           //crearUsuario(db_reference);

            crearUsuario(db_reference);
            System.out.println("Viendo array");
            //System.out.println(usuarios.toString());
        }

    }

    //Intent intentPro = new Intent(this, PrincipalActivity.class);
    public void crearUsuario(DatabaseReference db_reference){
        //Usuario u1;
        HashMap<String,Usuario> usuarioMapa = new HashMap<>();
        Intent inteP = new Intent(MainActivity.this,PrincipalActivity.class);
        //usuarios = new ArrayList<>();
        db_reference.addValueEventListener(new ValueEventListener() {
            Usuario usuario;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataSnapshot> parametros = new ArrayList<>();
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                while(items.hasNext()){
                    DataSnapshot item = items.next();
                    parametros.add(item);
                    //System.out.println(item.getValue());
                }

                DataSnapshot estaciones = parametros.get(0).child("Estaciones");
                System.out.println(estaciones.toString());

               // System.out.println(estaciones.getValue());
                System.out.println(parametros.get(0).child("correo").getValue());
                String correo = String.valueOf(parametros.get(0).child("imagen").getValue());
                String imagen = String.valueOf(parametros.get(0).child("imagen").getValue());
                String nombreU = String.valueOf(parametros.get(0).child("nombreUsuario").getValue());
                String idUsuario = String.valueOf(parametros.get(0).getKey().toString());
                System.out.println(idUsuario);
                Usuario usuarioOb =  new Usuario(nombreU,idUsuario,imagen,correo,estaciones);
                System.out.println(usuarioOb.toString());

                usuarioMapa.put("usuarioA", usuarioOb);
                System.out.println(usuarioMapa.toString());
                inteP.putExtra("usuario",usuarioMapa );
                Bundle enviar = new Bundle();
                enviar.putSerializable("envia", usuarioMapa);
                startActivity(inteP);


                //inteP.putExtra("hola", "hola");
                //inteP.putExtra
               // startActivity(inteP);


              //  startActivity(inteP);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
            //Intent i = new Intent(MainActivity.this,PrincipalActivity.class);

        });
        finish();
        inteP.putExtra("usuario",usuarioMapa );


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