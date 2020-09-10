package com.example.oxygen.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.oxygen.ObjetosNat.Tanque;
import com.example.oxygen.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EstacionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EstacionesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private DatabaseReference databaseReference;
    //private ArrayList<Estacion> estaciones = new ArrayList<>();
    private ArrayAdapter<Tanque> adapterEstacion;
    private ListView  vistaEstaciones;
    private Intent i;
    private ViewGroup linearLayout;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EstacionesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EstacionesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EstacionesFragment newInstance(String param1, String param2) {
        EstacionesFragment fragment = new EstacionesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View principal =  inflater.inflate(R.layout.fragment_estaciones, container, false);

      //  System.out.println("HolaMundo");
        HashMap<String,String> info_user = (HashMap<String, String>) getArguments().getSerializable("info_user");
      //  System.out.println(info_user);
       // System.out.println(info_user.get("user_name"));
       // System.out.println(info_user.get("idModulo"));
        linearLayout = (ViewGroup)principal.findViewById(R.id.lista_estaciones);
        int id = R.layout.layout_estacion;

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
/*
        databaseReference.child(VariablesUnicas.ESTACIONES_FI).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //estaciones.clear();
                for (DataSnapshot objSnap: dataSnapshot.getChildren()){
                    Estacion es = objSnap.getValue(Estacion.class);
                    System.out.println(es.toString());
                    System.out.println(objSnap.getKey());

                   // adapterEstacion = new ArrayAdapter<Estacion>(principal.getContext(),android.R.layout.simple_dropdown_item_1line,estaciones);
                    // vistaEstaciones.setAdapter(adapterEstacion);


                }

                //Recorro el array de los objetos estacion;
               for (Estacion estacionU: estaciones) {
                    String nombreEstacion = estacionU.getNombre();
                    String porcentajeBateria = estacionU.getPorcentajeBateria();

                    RelativeLayout relativeLayout = (RelativeLayout)inflater.inflate(id,null,false);
                    TextView titulo = (TextView)relativeLayout.findViewById(R.id.titulo_estacion);
                    titulo.setText(nombreEstacion);

                    TextView prctjEstacion = (TextView)relativeLayout.findViewById(R.id.porcentaje);
                    String porcentaje_str = porcentajeBateria + "%";
                    prctjEstacion.setText(porcentaje_str);
                    int porcentaje = Integer.parseInt(porcentajeBateria);
                    com.ramijemli.percentagechartview.PercentageChartView pcv = (com.ramijemli.percentagechartview.PercentageChartView)relativeLayout.findViewById(R.id.pcv_oxigeno);
                    pcv.setProgress(porcentaje,true);

                    linearLayout.addView(relativeLayout);
                    TextView txt = new TextView(getActivity()); //Textview para generar un espacio entre los elementos del linearLayout):
                    linearLayout.addView(txt);

                    relativeLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getContext(), InforTanque.class);
                            i.putExtra("numero_piscina",1);
                            getActivity().startActivity(i);

                        }
                    });


                }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });*/
        return principal;


    }



}