package pe.pucp.dduu.tel306.lab4g1;

import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import pe.pucp.dduu.tel306.lab4g1.Clases.API.Usuario.Usuario;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        abrirFragmentoRegistro();




    }

//FUNCION PARA GUARDAR EL ARCHIVO DE LA INFORMACION DE LA PERSONA
    /*public void guardarArchivo(String name,String email,String password){
        Usuario usuario = new Usuario();
        usuario.setName(name);
        usuario.setEmail(email);
        usuario.setPassword(password);

        try(FileOutputStream fileOutputStream =openFileOutput("InformacionUsuario",MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);){

            objectOutputStream.writeObject(usuario);
            Log.d("msg","escritura de objeto exitosa");

        }catch(IOException e){
            e.printStackTrace();
        }

    }*/

   //VERIFICAR SI EL ARCHIVO EXISTE
    public boolean verificarExistenciaDelArchivo(){

        File archivo = new File("configuracion.json");
        if (!archivo.exists()) {
            Log.d("infoApp","OJO: ¡¡No existe el archivo de configuración!!");
          return false;
        }

        return true;
    }


    //FRAGMENTOS
    //FUNCIONES PARA ABRIR Y BORRAR FRAGMENTOS*********************************

    //FUNCION PARA SOLO ABRIR EL FRAGMENTO INGRESO CON CONTRASEÑA
    public void abrirFragmentoIngreso(){
        InicioSesionFragmento inicioSesionFragmento = new InicioSesionFragmento().newInstance();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragmentContainerIngreso,inicioSesionFragmento);
        fragmentTransaction.commit();
    }

    public void borrarFragmentoIngreso() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();

        InicioSesionFragmento inicioSesionFragmento = (InicioSesionFragmento) supportFragmentManager.findFragmentById(R.id.fragmentContainerIngreso);
        if (inicioSesionFragmento != null) { //SI HAY UNFRAGMENTO QUE BORRAR SE INGRESA AL IF
            //se inicia la transaccion
            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
            fragmentTransaction.remove(inicioSesionFragmento);
            fragmentTransaction.commit();

        }
    }


    //FUNCION PARA SOLO ABRIR EL FRAGMENTO REGISTRO
    public void abrirFragmentoRegistro(){
        RegistroFragmento registroFragmento= new RegistroFragmento().newInstance();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragmentContainerIngreso,registroFragmento);
        fragmentTransaction.commit();
    }

    //FUNCION PARA BORRAR EL FRAGMENTO
    public void borrarFragmentoRegistro() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();

        RegistroFragmento registroFragmento = (RegistroFragmento) supportFragmentManager.findFragmentById(R.id.fragmentContainerIngreso);
        if (registroFragmento != null) { //SI HAY UNFRAGMENTO QUE BORRAR SE INGRESA AL IF
            //se inicia la transaccion
            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
            fragmentTransaction.remove(registroFragmento);
            fragmentTransaction.commit();
        }
    }

}