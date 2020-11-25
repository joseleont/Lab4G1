package pe.pucp.dduu.tel306.lab4g1;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class InicioSesionFragmento extends Fragment {


    public InicioSesionFragmento() {
        // Required empty public constructor
    }


    public static InicioSesionFragmento newInstance() {

        return new InicioSesionFragmento();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_inicio_sesion_fragmento, container, false);
        Button btnRegistro = view.findViewById(R.id.btnRegistro);
        //otros botones y views xdxd

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).reemplazarUnFragmentPorElOtroXDXD(RegistroFragmento.class);
            }
        });

        return view;
    }


}