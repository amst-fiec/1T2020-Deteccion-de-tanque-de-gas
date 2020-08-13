package com.example.oxygen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.oxygen.Fragments.ProfileFragment;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    //Variables
    public static final int GOOGLE_SIGN_IN =123;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private Button btn_login;

    private ArrayList<Estacion> estacionesUsuario;
    private Tanque tanque;
    private Estacion estacion;
    private DatabaseReference db_reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarParametros();
        iniciarLecturaDeBaseDatos();

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

            // AQUI TENGO QUE AGG UNA FUNCION QUE ME ENVIE LA IFORMACION DEL USUARIO LOGIADO
            //Y comparar si ya fue registrado o aun no ah sido registrado

            /*
            //DATOS DE PRUEBA PARA ESTACIÓN

            //primera estacion
            Tanque tanque1 = new Tanque("Descripcion tanque 1",1,8.2,6.2,90);
            Estacion estacion1 = new Estacion(tanque1,"guayaquil",505,1,1);

            //segunda estacion
            Tanque tanque2 = new Tanque("Descripcion tanque 2",2,8,5,90);
            Estacion estacion2 = new Estacion(tanque2,"Quito",300,2,2);

            //lista con las estaciones propias del usuario
            ArrayList<Estacion> estaciones = new ArrayList<>();
            estaciones.add(estacion1);
            estaciones.add(estacion2);

           //Intent i = new Intent(this,ProfileFragment.class);
            //i.putExtra("info_user",info_user);
            //Intent intent = new Intent(this,EstacionsFragment.class);

            //envio de arraylist con estaciones
            intentPro.putExtra("estaciones", estaciones);
            */
            finish();
            Intent intentPro = new Intent(this, PrincipalActivity.class);
            intentPro.putExtra("info_user",info_user);
            startActivity(intentPro);


        }else{
            System.out.println("sin registrarse");
        }
    }
    //Medoto para leer la base de datos

    public void iniciarLecturaDeBaseDatos(){
        db_reference = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        System.out.println(db_reference);
        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    obtenerDatosUser(snapshot);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Hay un error al leer la base de datos");;
            }
        });
    }

    public void obtenerDatosUser(DataSnapshot snap){
        String nameU = String.valueOf(snap.child("Nombre").getValue());
        String correo = String.valueOf(snap.child("correo").getValue());

        System.out.println("Hola Mundo");
        System.out.println(nameU);
        System.out.println(correo);
        DataSnapshot snapChildEstaciones = snap.child("Estaciones");
        DatabaseReference refenciaGeneral = snapChildEstaciones.getRef();
        System.out.println(refenciaGeneral);
        DatabaseReference referenceEstaciones = refenciaGeneral.getParent();
        System.out.println(referenceEstaciones);
        /*referenceEstaciones.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snEstacion: dataSnapshot.getChildren()) {
                    ObtenerDatoPorEstacion(snEstacion);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("ALgo salio mal");
            }
        }); */
        referenceEstaciones.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for(DataSnapshot dat: dataSnapshot.getChildren()){
                    System.out.println("Ahora sii con feee");
                    System.out.println(dat.child("idEstacion"));
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void ObtenerDatoPorEstacion(DataSnapshot snapshot){
        //String id = String.valueOf(snapshot.child("idEstacion").getValue());
        System.out.println("Vamos que si sale");
        //System.out.println(id);
        System.out.println(snapshot.child("Tanque"));
        System.out.println(snapshot.child("idEstacion"));
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