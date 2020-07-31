package com.example.oxygen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * Metodo para iniciar sesión con google
     * @param v: vista
     */
    public void iniciarsesion(View v){
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }
}