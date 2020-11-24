package pe.pucp.dduu.tel306.lab4g1.FragmentosPreguntas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.File;

import pe.pucp.dduu.tel306.lab4g1.Clases.API.Preguntas.PreguntaYRespuesta;
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
        View view= inflater.inflate(R.layout.fragment_detalle_preguntas_fragmento, container, false);

        detallePreguntas(view);
        return view;

    }

    public void detallePreguntas(View view){
        if(!verificarExistenciaDelArchivo()){
            RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());

            String url1= "http://34.236.191.118:3000/api/v1/answers/detail?questionid=";
            int questionId=2;
            String url2="&userid=";
            int userId=1;
            String url = url1+questionId+url2+userId;
            Log.d("infoApp",url);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("infoApp",response);
                    if(response.equals("true")){
                        //EL USUARIO HA RESPONDIDO LA PREGUNTA

                    }
                    else{
                        //EL USUSARIO NO HA RESPONDIDO A ESTA PREGUNTA
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("infoApp","NO SE OBTUVO");

                }
            });

            requestQueue.add(stringRequest);
        }
    }


    public boolean verificarExistenciaDelArchivo(){

        File archivo = new File("configuracion.json");
        if (!archivo.exists()) {
            Log.d("infoApp","OJO: ¡¡No existe el archivo de configuración!!");
            return false;
        }

        return true;
    }
}