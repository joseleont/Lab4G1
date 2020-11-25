package pe.pucp.dduu.tel306.lab4g1.FragmentosPreguntas;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.HashMap;

import pe.pucp.dduu.tel306.lab4g1.Clases.API.Preguntas.Estadisticas.Conjunto;
import pe.pucp.dduu.tel306.lab4g1.Clases.API.Preguntas.Estadisticas.Cuenta;
import pe.pucp.dduu.tel306.lab4g1.Clases.API.Preguntas.PreguntaYRespuesta;
import pe.pucp.dduu.tel306.lab4g1.Clases.API.Preguntas.Respuesta;
import pe.pucp.dduu.tel306.lab4g1.R;

import static android.widget.Toast.LENGTH_SHORT;


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

        detallePreguntas(view,container);
        return view;

    }

    public void detallePreguntas(View view,ViewGroup container){
        if(!verificarExistenciaDelArchivo()){
            RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());

            //TODO FALTA OBTNER EL ID DEL USUARIO
            String url1= "http://34.236.191.118:3000/api/v1/answers/detail?questionid=";
            int questionId=obtenerIdPregunta.obtenerIdPregunta();
            String url2="&userid=";
            int userId=1;
            String url = url1+questionId+url2+userId;
            Log.d("infoApp",url);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("infoApp",response);


                    if(Boolean.parseBoolean(response)){
                        //EL USUARIO HA RESPONDIDO LA PREGUNTA
                        mostrarEstadisticasPreguntas(view,questionId,container);


                    }
                    else{
                        //EL USUSARIO NO HA RESPONDIDO A ESTA PREGUNTA

                        mostrarPreguntasDetalladas(view,questionId,container,userId);
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


    Conjunto conjunto;
    public void mostrarEstadisticasPreguntas(View view,int idPregunta, ViewGroup container){
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        String url1="http://34.236.191.118:3000/api/v1/answers/stats?questionid=";
        String url=url1+idPregunta;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                conjunto = gson.fromJson(response, Conjunto.class);
                Cuenta[] cuenta=conjunto.getAnswerstats();
                //cuenta contiene la cantidad de respuestas y la respuesta



                LinearLayout ll = view.findViewById(R.id.respuestasLayout);
                TextView textView =view.findViewById(R.id.textViewPregunta);

                textView.setText(conjunto.getQuestion().getQuestionText());

                Log.d("infoApp",""+cuenta.length);

                int sumatoriaCuentas=0;
                for(int pos=0;pos<cuenta.length;pos++){
                    sumatoriaCuentas=sumatoriaCuentas+cuenta[pos].getCount();
                }

                for(int pos=0;pos<cuenta.length;pos++){

                    final Button button = new Button(container.getContext());
                    button.setPadding(0,10,0,10);

                    String option= cuenta[pos].getAnswer().getAnswerText();
                    int porcentaje=cuenta[pos].getCount()*100/sumatoriaCuentas;
                    button.setText(option+"->"+porcentaje+"%");

                   button.setBackground(Drawable.createFromPath("@android:color/transparent"));


                    ll.addView(button);
                }// FIN DEL FOR

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("infoApp","ERROR");
            }
        });

        requestQueue.add(stringRequest);
    }


    PreguntaYRespuesta preguntaYRespuesta;

    public void mostrarPreguntasDetalladas(View view,int idPregunta,ViewGroup container,int userId){

        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        String url1="http://34.236.191.118:3000/api/v1/questions/";

        String url = url1+idPregunta;

        StringRequest stringRequest = new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) { //response tiene todas las preguntas

                LinearLayout ll = view.findViewById(R.id.respuestasLayout);
                TextView textView =view.findViewById(R.id.textViewPregunta);

                Gson gson = new Gson();
                preguntaYRespuesta= gson.fromJson(response, PreguntaYRespuesta.class);


                    textView.setText(preguntaYRespuesta.getQuestionText());
                    Respuesta[] respuestas=preguntaYRespuesta.getAnswers();

                    // AGREGAR LAS PREGUNTAS
                    for(int pos=0;pos<respuestas.length;pos++){
                        final Button button = new Button(container.getContext());
                        button.setPadding(0,10,0,10);
                        button.setText(respuestas[pos].getAnswerText());

                        final int idRespuesta=respuestas[pos].getId();

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.d("infoApp",""+userId);

                                mandarRespuestas(idPregunta,userId,idRespuesta,container);
                                obtenerIdPregunta.borrarFragmentoDetallePreguntas();

                            }
                        });

                        ll.addView(button);
                    }// FIN DEL FOR


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("infoApp","NO SE OBTUVO");
            }
        });

        requestQueue.add(stringRequest);

    }


    public void mandarRespuestas(int idPregunta,int idUser,int idAnswer,ViewGroup container){
        HashMap<String, Integer> params = new HashMap<>();
        params.put("iduser",idUser );
        params.put("idanswer", idAnswer);
        JSONObject objeto = new JSONObject(params);

        RequestQueue queue = Volley.newRequestQueue(container.getContext());
        String url1 = "http://34.236.191.118:3000/api/v1/questions/";
        String url2="/answer";
        String url=url1+idPregunta+url2;

        JsonObjectRequest jsonrequest = new JsonObjectRequest(Request.Method.POST, url, objeto, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("msg", String.valueOf(response));
                // Toast.makeText(getContext(), response, LENGTH_SHORT).show();
                Toast.makeText(getContext(),response.toString(),LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("msg", "onErrorResponse: "+ error.getMessage());

            }
        });

        queue.add(jsonrequest);

    }


    private ObtenerIdPregunta obtenerIdPregunta;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        obtenerIdPregunta= (ObtenerIdPregunta) context;
    }

    public interface ObtenerIdPregunta{

        int obtenerIdPregunta();

        void borrarFragmentoDetallePreguntas();
    }

}