package pe.pucp.dduu.tel306.lab4g1.FragmentosPreguntas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.pucp.dduu.tel306.lab4g1.R;


public class DetallePreguntasFragmento extends Fragment {


    public DetallePreguntasFragmento() {
        // Required empty public constructor
    }


    public static DetallePreguntasFragmento newInstance() {


        return new DetallePreguntasFragmento();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_preguntas_fragmento, container, false);
    }
}