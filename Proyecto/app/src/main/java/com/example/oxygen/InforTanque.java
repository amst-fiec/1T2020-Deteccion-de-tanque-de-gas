package com.example.oxygen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oxygen.ObjetosNat.Tanque;
import com.example.oxygen.ObjetosNat.Ubicacion;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class InforTanque extends AppCompatActivity {
    private TextView txt_volumenInicial, txt_volumenActual;
    private TextView txt_porcentajeBateria,txt_porcentaje;
    private TextView  txt_pisoEstacion,txt_habitacionEstacion,txt_ubicacionEstacion;
    private com.ramijemli.percentagechartview.PercentageChartView pcv;
    private Ubicacion ubicacion;
    private double latitud,longitud;
    Button btn_verUbicacion;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_tanque);
        txt_porcentajeBateria = findViewById(R.id.txt_porcentBateriaEst);
        txt_volumenInicial =  findViewById(R.id.txt_vInicialEstacion);
        txt_volumenActual = findViewById(R.id.txt_vActualEstacion);

        txt_porcentajeBateria = findViewById(R.id.txt_porcentBateriaEst);
        txt_volumenInicial =  findViewById(R.id.txt_vInicialEstacion);
        txt_volumenActual = findViewById(R.id.txt_vActualEstacion);
        pcv = findViewById(R.id.pcv_volumen);
        txt_porcentaje = findViewById(R.id.txt_porcentaje);
        txt_pisoEstacion = findViewById(R.id.txt_pisoEstacion);
        txt_habitacionEstacion = findViewById(R.id.txt_habitacionEstacion);
        txt_ubicacionEstacion = findViewById(R.id.txt_ubicacionEstacion);
        btn_verUbicacion = findViewById(R.id.btn_verUbicacion);

        Intent i = getIntent();
        Tanque TanqueMostrar = (Tanque)i.getSerializableExtra("tanque");
         ubicacion = (Ubicacion)i.getSerializableExtra("ubicacion");

        String volumenInicial = TanqueMostrar.getVolumenInicial();
        String volumenActual = TanqueMostrar.getVolumenMedido();
        String porcentajeBateria = TanqueMostrar.getPorcentajeBateria();
        int porcentaje = (int)((Integer.parseInt(volumenActual)*100)/(Integer.parseInt(volumenInicial)));
        String piso = String.valueOf(ubicacion.getPiso());
        String habitacion = String.valueOf(ubicacion.getHabitacion());
        String ciudad = String.valueOf(ubicacion.getCiudad());
        String porcentaje_str = porcentaje + "%";
         latitud = ubicacion.getLatitud();
         longitud = ubicacion.getLongitud();

        txt_porcentajeBateria.setText(porcentajeBateria);
        txt_volumenInicial.setText(volumenInicial);
        txt_volumenActual.setText(volumenActual);
        pcv.setProgress(porcentaje,true);
        txt_porcentaje.setText(porcentaje_str);
        txt_pisoEstacion.setText(piso);
        txt_habitacionEstacion.setText(habitacion);
        txt_ubicacionEstacion.setText(ciudad);
        btn_verUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InforTanque.this, MapsActivity.class);
                //ubicacion = (Ubicacion)i.getSerializableExtra("ubicacion");
                i.putExtra("ciudad", ubicacion.getCiudad());
                i.putExtra("latitud", ubicacion.getLatitud());
                i.putExtra("longitud", ubicacion.getLongitud());
                startActivity(i);
            }
        });
        
    }






 /*public List<Address> obtenerCoordenadas(String s){
     Intent i = new Intent(InforTanque.this, MapsActivity.class);
     i.putExtra("ciudad", ubicacionTanque);
     List<Address> ubicacion = obtenerCoordenadas(ubicacionTanque);
     Address address = ubicacion.get(0);
     double latitud = address.getLatitude();
     double longitud = address.getLongitude();
     i.putExtra("latitud", latitud);
     i.putExtra("longitud", longitud);
     //i.putExtra("ubicacion",ubicacion);
     startActivity(i);
     Geocoder geocoder = new Geocoder(InforTanque.this);
     List<Address> list = new ArrayList<>();
     try {
         list = geocoder.getFromLocationName(s,2);
     } catch (IOException e) {
         e.printStackTrace();
     }
     if(list.size() > 0){
         return list;
     }
     else{
         Toast.makeText(this, "No se encontró ningún resultado, intente con otro lugar", Toast.LENGTH_SHORT).show();
     }
     return null;
 }*/


}