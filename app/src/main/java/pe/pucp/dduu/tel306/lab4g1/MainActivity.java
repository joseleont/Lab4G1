package pe.pucp.dduu.tel306.lab4g1;

import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InicioSesionFragmento inicioSesionFragmento = new InicioSesionFragmento().newInstance();

        FragmentManager fragmentManager=getSupportFragmentManager();


    }


}