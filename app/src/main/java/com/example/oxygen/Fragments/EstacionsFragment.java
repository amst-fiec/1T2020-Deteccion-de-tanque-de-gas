package com.example.oxygen.Fragments;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.oxygen.InforTanque;
import com.example.oxygen.MainActivity;
import com.example.oxygen.ObjetosNat.Tanque;
import com.example.oxygen.ObjetosNat.Ubicacion;
import com.example.oxygen.ObjetosNat.Usuario;
import com.example.oxygen.PrincipalActivity;
import com.example.oxygen.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;


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



        Usuario usuario = MainActivity.getUsuario();
        TreeSet<Tanque> tanquesUsuario = PrincipalActivity.getTanques();

        if(tanquesUsuario !=null){
            for (Tanque tanque: tanquesUsuario) {
                RelativeLayout relativeLayout = crearLayout(tanque,inflater);

                //añadir vistas al linear layout
                linearLayout.addView(relativeLayout);
                TextView txt = new TextView(getActivity());
                linearLayout.addView(txt);

                //direccion
                relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getContext(), InforTanque.class);
                        //i.putExtra("tanque",Tanque);
                        Ubicacion ubicacionTanque = obtenerUbicacionTanque(tanque);
                        i.putExtra("tanque",tanque);
                        i.putExtra("ubicacion",ubicacionTanque);
                        getActivity().startActivity(i);

                    }
                });

            }
        }


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

    public Ubicacion obtenerUbicacionTanque(Tanque t){
        TreeSet<Ubicacion> ubicaciones = PrincipalActivity.getUbicaciones();

        for (Ubicacion u: ubicaciones
        ) {
            if(u.getIdUbicacion() == t.getIdUbicacion()){
                return u;
            }
        }
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

    }

    /*
    Parametros: tanque, inflater
    Retorna: layout que muestra la información del tanque enviando por parametro
    */
    public RelativeLayout crearLayout(Tanque tanque, LayoutInflater inflater){
        RelativeLayout relativeLayout;
        String nombreModulo = tanque.getNombre();
        String volumenActual = tanque.getVolumenMedido();
        String volumenInicial = tanque.getVolumenInicial();

        //añadir los layouts con la información de las vistas
        int id = R.layout.layout_estacion; //layout con datos de la estación

        //Inicializacion relative layout
        relativeLayout = (RelativeLayout)inflater.inflate(id,null,false);
        TextView titulo = (TextView)relativeLayout.findViewById(R.id.titulo_estacion) ;
        String titulo_id = "Tanque: " + nombreModulo;
        titulo.setText(titulo_id);

        int porcentaje = (int)((Integer.parseInt(volumenActual)*100)/(Integer.parseInt(volumenInicial)));
        TextView prctjEstacion = (TextView)relativeLayout.findViewById(R.id.porcentaje);
        String porcentaje_str = porcentaje + "%";
        prctjEstacion.setText(porcentaje_str);

        com.ramijemli.percentagechartview.PercentageChartView pcv = (com.ramijemli.percentagechartview.PercentageChartView)relativeLayout.findViewById(R.id.pcv_oxigeno);
        pcv.setProgress(porcentaje,true);

        //mostrar alerta en caso de porcentaje menores
        if(porcentaje < 25){
            RelativeLayout fondo = (RelativeLayout)relativeLayout.findViewById(R.id.fondo);
            fondo.setBackgroundResource(R.drawable.animation_background);
            AnimationDrawable animationDrawable= (AnimationDrawable)fondo.getBackground();
            animationDrawable.setEnterFadeDuration(1000);
            animationDrawable.setExitFadeDuration(1000);
            animationDrawable.start();
        }

        return relativeLayout;
    }

}