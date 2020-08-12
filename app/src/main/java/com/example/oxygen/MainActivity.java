package com.example.oxygen;

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

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    //Variables
    public static final int GOOGLE_SIGN_IN =123;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private Button btn_login;

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
           // String name = user.getDisplayName();
            //String email = user.getEmail();
            //String photo = String.valueOf(user.getPhotoUrl());
            //System.out.println("nombre");
            //System.out.println(name);
            HashMap<String,String> info_user = new HashMap<>();
            info_user.put("user_name",user.getDisplayName());
            info_user.put("user_email",user.getEmail());
            info_user.put("user_photo",String.valueOf(user.getPhotoUrl()));
            info_user.put("user_id",user.getUid());
            finish();
            Intent intentPro = new Intent(this, PrincipalActivity.class);
           //Intent i = new Intent(this,ProfileFragment.class);
            //i.putExtra("info_user",info_user);
            intentPro.putExtra("info_user",info_user);
            //Intent intent = new Intent(this,EstacionsFragment.class);
            startActivity(intentPro);


        }else{
            System.out.println("sin registrarse");
        }
    }

    /**
     * Metodo para iniciar todos los parametros de esta clase
     *
     */

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