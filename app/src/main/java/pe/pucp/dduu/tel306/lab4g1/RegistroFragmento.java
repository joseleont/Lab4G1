package pe.pucp.dduu.tel306.lab4g1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class RegistroFragmento extends Fragment {

  

    public RegistroFragmento() {
        // Required empty public constructor
    }

   
    public static RegistroFragmento newInstance() {


        return new RegistroFragmento();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro_fragmento, container, false);
    }
}