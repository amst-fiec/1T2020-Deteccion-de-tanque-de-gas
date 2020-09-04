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
import android.widget.Toast;

import com.example.oxygen.ObjetosNat.Tanque;
import com.example.oxygen.ObjetosNat.Ubicacion;
import com.example.oxygen.ObjetosNat.Usuario;
import com.example.oxygen.ObjetosNat.VariablesUnicas;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.TreeSet;

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
                txt_codigo = (EditText)v.findViewById(R.id.txt_codigo);
                txt_ubicacion = (EditText)v.findViewById(R.id.txt_ubicacion);
                txt_habitacion = (EditText)v.findViewById(R.id.txt_habitacion);
                txt_piso = (EditText)v.findViewById(R.id.txt_piso);
                txt_vol = (EditText)v.findViewById(R.id.txt_volumen);
                //String contenidoT = "Oxigeno";
                String nombreT = txt_Nombre.getText().toString();
                String descripcion = txt_descripcion.getText().toString();
                String codigo = txt_codigo.getText().toString();
                String ubicacion = txt_ubicacion.getText().toString();
                String habitacion = txt_habitacion.getText().toString();
                int habitacionA = Integer.parseInt(habitacion);
                int piso = Integer.parseInt(txt_piso.getText().toString());
                String volumenInicial = txt_vol.getText().toString();

               // Tanque estacion = new Tanque(nombreE,volumenInicial,"","",codigo,1,MainActivity.getUsuario().getIdUser());
                if(txt_Nombre.getText().toString().isEmpty() || txt_codigo.getText().toString().isEmpty() || txt_ubicacion.getText().toString().isEmpty() || txt_habitacion.getText().toString().isEmpty() ||
                        txt_piso.getText().toString().isEmpty() || txt_vol.getText().toString().isEmpty() || txt_descripcion.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Datos incompletos", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "Estación añadida", Toast.LENGTH_SHORT).show();
                    String idUser = info_user.get("user_id");

                    //Estacion estacion = new Estacion();
                   // databaseReference.child(VariablesUnicas.ESTACIONES_FI).child(codigo).setValue(estacion);
                    //databaseReference.child(VariablesUnicas.UBICACIONES_FI).child("1").setValue(ubicacion1);
                    //Usuario usuarioEs = MainActivity.getUsuario();
                   // usuarioEs.agregarEstacion(estacion);
                    //databaseReference.child(VariablesUnicas.USUARIO_FI).child(usuarioEs.getIdUser()).child("Estaciones").child("1").setValue(estacion.getIdModulo());

                    //String dataSnapshot = databaseReference.child(VariablesUnicas.UBICACIONES_FI).child(u.getIdUser()).getKey();
                    TreeSet<Integer> idUbicacionUser = PrincipalActivity.getIdUbicacionesUser();
                    System.out.println("Numero de ubicaciones: " + idUbicacionUser.size());
                    if(idUbicacionUser.size()>0){
                        int valor = idUbicacionUser.size() + 1 ;
                        String valorA = String.valueOf(valor);
                        Tanque tanque = new Tanque(codigo,valor,nombreT,"0",volumenInicial,"0");
                        Usuario u = MainActivity.getUsuario();
                        Ubicacion ubicacion1 = new Ubicacion(ubicacion,habitacionA,valor,piso);
                        databaseReference.child(VariablesUnicas.TANQUES_FI).child(u.getIdUser()).child(tanque.getIdModulo()).setValue(tanque);
                        databaseReference.child(VariablesUnicas.TANQUES_USER).child(tanque.getIdModulo()).setValue(u.getIdUser());
                        databaseReference.child(VariablesUnicas.UBICACIONES_FI).child(u.getIdUser()).child(String.valueOf(idUbicacionUser.size()+1)).setValue(ubicacion1);

                    }else{
                        Tanque tanque = new Tanque(codigo,1,nombreT,"0",volumenInicial,"0");
                        Usuario u = MainActivity.getUsuario();
                        Ubicacion ubicacion1 = new Ubicacion(ubicacion,habitacionA,1,piso);
                        databaseReference.child(VariablesUnicas.TANQUES_FI).child(u.getIdUser()).child(tanque.getIdModulo()).setValue(tanque);
                        databaseReference.child(VariablesUnicas.TANQUES_USER).child(tanque.getIdModulo()).setValue(u.getIdUser());
                        databaseReference.child(VariablesUnicas.UBICACIONES_FI).child(u.getIdUser()).child("1").setValue(ubicacion1);

                    }

                }


            }
        });

        return v;
    }

    public void agregarEstacion(View v){

    }
}