package pe.pucp.dduu.tel306.lab4g1;

import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import pe.pucp.dduu.tel306.lab4g1.Clases.Preguntas.Respuesta;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

   //VERIFICAR SI EL ARCHIVO EXISTE
    public void verificarArchivo(){
        try(FileInputStream fileInputStream =openFileInput("archivoPersona.data");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);){

            while(fileInputStream.available()>0){ //available es para saber si hay para leer

                Respuesta persona=(Respuesta) objectInputStream.readObject();
                Log.d("infoApp",""+persona.getId());
            }


        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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