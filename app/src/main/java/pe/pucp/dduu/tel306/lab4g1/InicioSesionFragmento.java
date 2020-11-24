package pe.pucp.dduu.tel306.lab4g1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InicioSesionFragmento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioSesionFragmento extends Fragment {


    // TODO: Rename and change types of parameters


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
        return inflater.inflate(R.layout.fragment_inicio_sesion_fragmento, container, false);
    }
}