package com.example.oxygen.Fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oxygen.MainActivity;
import com.example.oxygen.ObjetosNat.FirebaseDatos;
import com.example.oxygen.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
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
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View mainF = inflater.inflate(R.layout.fragment_profile, container, false);
        Button btn = mainF.findViewById(R.id.btn_CerrarS);
        TextView txt_name = mainF.findViewById(R.id.txt_user);
         TextView txt_email = mainF.findViewById(R.id.txt_email);
        ImageView imv_photo = mainF.findViewById(R.id.imv_foto);
        //DatabaseReference db_reference = FirebaseDatabase.getInstance().getReference().child(FirebaseDatos.ESTACIONES_FI);


        System.out.println("HolaMundo");
        HashMap<String,String> info_user = (HashMap<String, String>) getArguments().getSerializable("info_user");
        System.out.println(info_user);
        System.out.println(info_user.get("user_name"));
        System.out.println(info_user.get("idModulo"));
        txt_name.setText(info_user.get("user_name"));
        txt_email.setText(info_user.get("user_email"));
        String photo = info_user.get("user_photo");
        Picasso.with(getActivity().getApplicationContext()).load(photo).into(imv_photo);

        cerrarS(btn);
        return mainF;
    }

    public void cerrarS( Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                getActivity().finish();
                Intent i = new Intent(getActivity(),MainActivity.class);
                i.putExtra("msg","cerrarSesion");
                startActivity(i);
            }
        });
    }




}