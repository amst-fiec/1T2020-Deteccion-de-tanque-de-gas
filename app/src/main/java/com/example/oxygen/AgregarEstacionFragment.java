package com.example.oxygen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oxygen.ObjetosNat.FirebaseDatos;
import com.example.oxygen.ObjetosNat.Tanque;
import com.example.oxygen.ObjetosNat.Ubicacion;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AgregarEstacionFragment extends Fragment {
    Spinner unidad;
    EditText txt_Nombre, txt_descripcion, txt_codigo, txt_ubicacion, txt_habitacion, txt_piso, txt_vol;
    Button btn_agregar;
    private DatabaseReference databaseReference;


    public AgregarEstacionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_agregar_estacion, container, false);
        unidad = (Spinner)v.findViewById(R.id.spn_unidad);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getActivity()),R.array.opciones, R.layout.spinner_item_unidad);
        unidad.setAdapter(adapter);

        HashMap<String,String> info_user = (HashMap<String, String>) getArguments().getSerializable("info_user");
        //System.out.println(info_user);
        //System.out.println(info_user.get("user_name"));
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //databaseReference.child(FirebaseDatos.ESTACIONES_FI).child(info_user.get("idModulo")).setValue(estacion);
        btn_agregar = (Button)v.findViewById(R.id.btn_agregarEstacion);
        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_Nombre = (EditText)v.findViewById(R.id.txt_nombre_estacion);
                txt_descripcion = (EditText)v.findViewById(R.id.txt_descripcion);
                txt_codigo = (EditText)v.findViewById(R.id.txt_descripcion);
                txt_ubicacion = (EditText)v.findViewById(R.id.txt_ubicacion);
                txt_habitacion = (EditText)v.findViewById(R.id.txt_habitacion);
                txt_piso = (EditText)v.findViewById(R.id.txt_piso);
                txt_vol = (EditText)v.findViewById(R.id.txt_volumen);
                String contenidoT = "Oxigeno";
                if(txt_Nombre.getText().toString().isEmpty() || txt_codigo.getText().toString().isEmpty() || txt_ubicacion.getText().toString().isEmpty() || txt_habitacion.getText().toString().isEmpty() ||
                        txt_piso.getText().toString().isEmpty() || txt_vol.getText().toString().isEmpty() || txt_descripcion.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Datos incompletos", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "Estación añadida", Toast.LENGTH_SHORT).show();
                    String idUser = info_user.get("user_id");

                    //Agregar Tanque

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference tanques = databaseReference.child(FirebaseDatos.TANQUES_FI);
                    DatabaseReference idU_reference = tanques.child(idUser);
                    DatabaseReference newId_reference = idU_reference.push();
                    Tanque tanque = new Tanque(contenidoT,txt_vol.getText().toString());
                    newId_reference.setValue(tanque);


                    String reference_newId = newId_reference.getKey();

                    //Agregar Ubicacion
                    DatabaseReference ubicaciones = databaseReference.child(FirebaseDatos.UBICACIONES_FI);
                    String ciudad = "Guayaquil";
                    int habitacion = Integer.parseInt(txt_habitacion.getText().toString());
                    String nombreHospital = "IEES";
                    int piso = Integer.parseInt(txt_piso.getText().toString());
                    Ubicacion ubicacion = new Ubicacion(ciudad,habitacion,nombreHospital,piso);
                    DatabaseReference idU_reference_Hubica = ubicaciones.child(idUser);
                    idU_reference_Hubica.child(reference_newId).setValue(ubicacion);
                }


            }
        });

        return v;
    }

    public void agregarEstacion(View v){

    }
}