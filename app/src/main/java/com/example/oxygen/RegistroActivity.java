package com.example.oxygen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oxygen.ObjetosNat.Usuario;
import com.example.oxygen.ObjetosNat.VariablesUnicas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity {
    private EditText edtUser;
    private EditText edtCorreo;
    private EditText edtContrasena;

    private String user;
    private String password;
    private String correo;
    private static Usuario usuario;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtUser = findViewById(R.id.usuario);
        edtCorreo = findViewById(R.id.correo);
        edtContrasena = findViewById(R.id.clave);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void cancelar(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void ingresarRegistro(View view){
        user = edtUser.getText().toString();
        password = edtContrasena.getText().toString();
        correo = edtCorreo.getText().toString();

        if(!user.isEmpty() && !correo.isEmpty() && !password.isEmpty()){

            if(password.length() >= 6){
                 usuario = new Usuario(correo, "","https://www.pngkey.com/png/detail/230-2301779_best-classified-apps-default-user-profile.png",user);
                registrarUser(usuario);


            }else{
                Toast.makeText(RegistroActivity.this, "La contrasena debe tener al menos 6 caracteres",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(RegistroActivity.this,"Debe completrar los campos", Toast.LENGTH_SHORT).show();
        }
    }

    private void registrarUser(Usuario usuario){
        mAuth.createUserWithEmailAndPassword(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String id = mAuth.getCurrentUser().getUid();
                    usuario.setIdUser(id);
                    mDatabase.child(VariablesUnicas.USUARIO_FI).child(id).setValue(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                //startActivity(new Intent(RegistroActivity.class, PrincipalActivity.class));
                                Intent i = new Intent(RegistroActivity.this, PrincipalActivity.class);
                                //i.putExtra("usuario",usuario);
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(RegistroActivity.this,"NO se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(RegistroActivity.this, "No se ah registrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static Usuario getUsuario() {
        return usuario;
    }
}