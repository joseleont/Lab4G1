package pe.pucp.dduu.tel306.lab4g1;

import android.app.AlertDialog;
import android.os.Bundle;
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

    private AlertDialog crearDialogo() {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_inicio_sesion_fragmento,null);
        builder.setView(v);

        Button btnAceptar=v.findViewById(R.id.btnIniciarSesion);


        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int error=0;

                //OBTENER LA INFORMACION DE LOS VIEWS



            }
        });


        return builder.create();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      // return crearDialogo();
        return inflater.inflate(R.layout.fragment_inicio_sesion_fragmento, container, false);
    }
}