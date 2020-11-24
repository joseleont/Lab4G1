package pe.pucp.dduu.tel306.lab4g1;

import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import pe.pucp.dduu.tel306.lab4g1.Clases.API.Preguntas.PreguntaYRespuesta;
import pe.pucp.dduu.tel306.lab4g1.Clases.API.Preguntas.Respuesta;
import pe.pucp.dduu.tel306.lab4g1.Clases.API.Usuario.Usuario;
import pe.pucp.dduu.tel306.lab4g1.FragmentosPreguntas.DetallePreguntasFragmento;
import pe.pucp.dduu.tel306.lab4g1.FragmentosPreguntas.ListaPreguntasFragmento;

public class MainActivity extends AppCompatActivity implements ListaPreguntasFragmento.BorrarFragmentoListaPreguntas{

    private int preguntaId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(verificarExistenciaDelArchivo()){
           //HAY UN ARCHIVO GUARDADO EN EL CELULAR
            abrirFragmentoIngreso();
        }
        else{ //NO EXISTE EL ARCHIVO

            abrirFragmentoListaPreguntas();
            for(int a=0;a<50;a++);
            if(preguntaId!=0){


            }

        }


    }

    //POST
    public void obtenerRespuestaUsersNew(){
        if (tengoInternet()) {
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            Map<String, String> parametros= new HashMap<String, String>();
            Usuario usuario = new Usuario();
            usuario.setName("Mario Sotelo");
            usuario.setEmail("mgsotelo@pucp.pe");
            usuario.setPassword("1234567890");

            parametros.put("name",usuario.getName());
            parametros.put("email",usuario.getEmail());
            parametros.put("password",usuario.getPassword());


            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    "http://34.236.191.118:3000/api/v1/users/new", new JSONObject(parametros),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("infoWs", response.toString());

                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    //Log.d("infoWs", "error");

                }
            }) {


                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                   // HashMap<String, String> headers = new HashMap<String, String>();
                   // headers.put("Content-Type", "application/json; charset=utf-8");
                    //return headers;
                    return super.getHeaders();
                }



            };


            requestQueue.add(jsonObjReq);
        }

    }




//FUNCION PARA GUARDAR EL ARCHIVO DE LA INFORMACION DE LA PERSONA
    public void guardarArchivo(String name,String email,String password){
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

    }

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



    public void abrirFragmentoListaPreguntas(){

        ListaPreguntasFragmento listaPreguntasFragmento= new ListaPreguntasFragmento().newInstance();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragmentContainerIngreso, listaPreguntasFragmento);
        fragmentTransaction.commit();
    }

    //FUNCION PARA BORRAR EL FRAGMENTO
    //LLAMADO DE ListaPreguntaFragmentos
    @Override
    public void borrarFragmentoListaPreguntas(int id) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();

        ListaPreguntasFragmento listaPreguntasFragmento = (ListaPreguntasFragmento) supportFragmentManager.findFragmentById(R.id.fragmentContainerIngreso);
        if ( listaPreguntasFragmento!= null) { //SI HAY UNFRAGMENTO QUE BORRAR SE INGRESA AL IF
            //se inicia la transaccion
            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
            fragmentTransaction.remove(listaPreguntasFragmento);
            fragmentTransaction.commit();
        }

        preguntaId= id; //CON ESTO SE OBTIENE EL ID DE LA PREGUNTA

        abrirFragmentoDetallePreguntas(int preguntaId);
    } //PARADO

    public void abrirFragmentoDetallePreguntas(int id){

        DetallePreguntasFragmento detallePreguntasFragmento= new DetallePreguntasFragmento().newInstance();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //PARADO USAR ESE ID
        fragmentTransaction.add(R.id.fragmentContainerIngreso, detallePreguntasFragmento);
        fragmentTransaction.commit();
    }




    public boolean tengoInternet() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null)
            return false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network networks = connectivityManager.getActiveNetwork();
            if (networks == null)
                return false;

            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(networks);
            if (networkCapabilities == null)
                return false;

            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                return true;
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                return true;
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
                return true;
            return false;
        } else {

            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null)
                return false;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI)
                return true;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
                return true;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_ETHERNET)
                return true;
            return false;

        }
    }

}