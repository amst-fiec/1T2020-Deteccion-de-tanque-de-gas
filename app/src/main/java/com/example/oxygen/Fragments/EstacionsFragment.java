package com.example.oxygen.Fragments;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.oxygen.Fragments.ProfileFragment;
import com.example.oxygen.ObjetosNat.Estacion;
import com.example.oxygen.ObjetosNat.FirebaseDatos;
import com.example.oxygen.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;


public class EstacionsFragment extends Fragment {

    private ViewGroup linearLayout;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EstacionsFragment() {
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EstacionsFragment newInstance(String param1, String param2) {
        EstacionsFragment fragment = new EstacionsFragment();
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
            LayoutInflater inflater = LayoutInflater.from(getContext());
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment----------------------------------------------------------
        View v = inflater.inflate(R.layout.fragment_estacions, container, false);

        linearLayout = (ViewGroup)v.findViewById(R.id.lista_estaciones) ; //LinearLayout que contendrá las estaciones

        //añadir los layouts con la información de las vistas
        int id = R.layout.layout_estacion; //layout con datos de la estación

        //datos obtenidos del usuario
        HashMap<String,String> info_user = (HashMap<String, String>) getArguments().getSerializable("info_user");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        //System.out.println(info_user.get("idModulo"));
        //databaseReference.child(FirebaseDatos.ESTACIONES_FI).setValue(info_user.get("idModulo"));
        Estacion estacion = new Estacion(2,2,"50",5,"45");

//        System.out.println(info_user.get("user_name"));//databaseReference.child(info_user.get("idModulo")).setValue(estacion);
        /*ArrayList<Estacion> estaciones = (ArrayList<Estacion>) getArguments().getSerializable("estaciones_user");
        if (estaciones!= null){
            int numEstaciones = estaciones.size();
            for (int i = 0;i <numEstaciones; i++){
                Estacion estacion = estaciones.get(i);

                //se asignan los valores correspondientes a la estacion
                String descripcion = estacion.getTanque().getDescripcion();
                int estacion_id = estacion.getId();
                double volActual = estacion.getTanque().getVolActual();
                double volInicial = estacion.getTanque().getVolInicial();


                RelativeLayout relativeLayout = (RelativeLayout)inflater.inflate(id,null,false);
                TextView titulo = (TextView)relativeLayout.findViewById(R.id.titulo_estacion) ;
                String titulo_id = "Estacion" + estacion_id;
                titulo.setText(titulo_id);

                TextView subtitulo = (TextView)relativeLayout.findViewById(R.id.descripcion_estacion) ;
                subtitulo.setText(descripcion);

                int porcentaje = (int)((volActual*100)/(volInicial));
                TextView prctjEstacion = (TextView)relativeLayout.findViewById(R.id.porcentaje);
                String porcentaje_str = porcentaje + "%";
                prctjEstacion.setText(porcentaje_str);

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
                })


            }

        }*/


        /*
        //añadir layout warning------------------------------------------------------------------------------------
        RelativeLayout relativeLayout2 = (RelativeLayout)inflater.inflate(id,null,false);
        //asignacion fondo intermitente
        RelativeLayout fondo = (RelativeLayout)relativeLayout2.findViewById(R.id.fondo);
        fondo.setBackgroundResource(R.drawable.animation_background);
        AnimationDrawable animationDrawable= (AnimationDrawable)fondo.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(1000);
        animationDrawable.start();
        //propiedades del layout
        TextView porcentaje = (TextView)relativeLayout2.findViewById(R.id.porcentaje);
        porcentaje.setText("");
        linearLayout.addView(relativeLayout2);
        TextView txt2 = new TextView(getActivity());
        linearLayout.addView(txt2);

/*
        //añadir layout normal----------------------------------------------------------------------------------------
        RelativeLayout relativeLayout3 = (RelativeLayout)inflater.inflate(id,null,false);
        linearLayout.addView(relativeLayout3);
        TextView txt3 = new TextView(getActivity());
        linearLayout.addView(txt3);

    */
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

    }

}