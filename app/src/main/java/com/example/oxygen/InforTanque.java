package com.example.oxygen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.oxygen.ObjetosNat.Tanque;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.TreeSet;

public class InforTanque extends AppCompatActivity {
    private TextView txt_volumenInicial;
    private TextView txt_porcentajeBateria;
    private TextView txt_volumenActual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_tanque);
        txt_porcentajeBateria = findViewById(R.id.txt_porcentBateriaEst);
        txt_volumenInicial =  findViewById(R.id.txt_vInicialEstacion);
        txt_volumenActual = findViewById(R.id.txt_vActualEstacion);

        Intent i = getIntent();
        Tanque TanqueMostrar = (Tanque)i.getSerializableExtra("tanque");

        String volumenInicial = TanqueMostrar.getVolumenInicial();
        String volumenActual = TanqueMostrar.getVolumenMedido();
        String porcentajeBateria = TanqueMostrar.getPorcentajeBateria();

        txt_porcentajeBateria.setText(porcentajeBateria);
        txt_volumenInicial.setText(volumenInicial);
        txt_volumenActual.setText(volumenActual);
        
    }


    //TreeSet<Tanque> tanques = PrincipalActivity.getTanques();



}