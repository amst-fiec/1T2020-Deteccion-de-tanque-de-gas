package com.example.oxygen;

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

import org.w3c.dom.Text;


public class EstacionsFragment extends Fragment {

    private ViewGroup linearLayout;

    public EstacionsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment----------------------------------------------------------
        View v = inflater.inflate(R.layout.fragment_estacions, container, false);

        linearLayout = (ViewGroup)v.findViewById(R.id.lista_estaciones) ; //LinearLayout que contendrá las estaciones

        //añadir los layouts con la información de las vistas
        int id = R.layout.layout_estacion; //layout con datos de la estación


        //añadir layout normal------------------------------------------------------------------------------------
        RelativeLayout relativeLayout1 = (RelativeLayout)inflater.inflate(id,null,false);
        linearLayout.addView(relativeLayout1);
        TextView txt = new TextView(getActivity()); //Textview para generar un espacio entre los elementos del linearLayout):
        linearLayout.addView(txt);


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


        //añadir layout normal----------------------------------------------------------------------------------------
        RelativeLayout relativeLayout3 = (RelativeLayout)inflater.inflate(id,null,false);
        linearLayout.addView(relativeLayout3);
        TextView txt3 = new TextView(getActivity());
        linearLayout.addView(txt3);


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

    }

}