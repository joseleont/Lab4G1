package pe.pucp.dduu.tel306.lab4g1.FragmentosPreguntas;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import pe.pucp.dduu.tel306.lab4g1.Clases.API.Preguntas.PreguntaYRespuesta;
import pe.pucp.dduu.tel306.lab4g1.R;


public class ListaPreguntasFragmento extends Fragment {


    public ListaPreguntasFragmento() {
        // Required empty public constructor
    }


    public static ListaPreguntasFragmento newInstance() {

        return new ListaPreguntasFragmento();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    PreguntaYRespuesta[] preguntaYRespuesta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.fragment_lista_preguntas_fragmento, container, false);
        obtenerPreguntasQuestions(view,container);
        return view;
    }



    public void obtenerPreguntasQuestions(View view,ViewGroup container){
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        String url = "http://34.236.191.118:3000/api/v1/questions";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        preguntaYRespuesta= gson.fromJson(response, PreguntaYRespuesta[].class);

                       // Log.d("info",preguntaYRespuesta[0].getQuestionText());


                        LinearLayout ll = view.findViewById(R.id.preguntasLayout);



        // AGREGAR LAS PREGUNTAS
        for(int pos=0;pos<preguntaYRespuesta.length;pos++){
            final Button button = new Button(container.getContext());
            button.setPadding(0,10,0,10);
            button.setText(preguntaYRespuesta[pos].getQuestionText());

            final int id=preguntaYRespuesta[pos].getId();

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LinearLayout ll = view.findViewById(R.id.preguntasLayout);
                    borrarFragmentoListaPreguntas.borrarFragmentoListaPreguntas(id);

                }
            });

            ll.addView(button);
        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("infoApp","ERROR");
                    }
                }) {

        };

        requestQueue.add(stringRequest);
    }



    private BorrarFragmentoListaPreguntas borrarFragmentoListaPreguntas;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

       borrarFragmentoListaPreguntas= (BorrarFragmentoListaPreguntas) context;
    }


    public interface BorrarFragmentoListaPreguntas{

        void borrarFragmentoListaPreguntas(int id);
    }


}