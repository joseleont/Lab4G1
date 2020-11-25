package pe.pucp.dduu.tel306.lab4g1;

import android.app.AlertDialog;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

import pe.pucp.dduu.tel306.lab4g1.FragmentosPreguntas.ListaPreguntasFragmento;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


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




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_inicio_sesion_fragmento, container, false);
        EditText correo = view.findViewById(R.id.editTextCorreoIngreso);
        EditText pass = view.findViewById(R.id.editTextContraseñaIngreso);
        Button btnInicio =view.findViewById(R.id.btnIniciarSesion);
        Button btnRegistro = view.findViewById(R.id.btnRegistro);
        //otros botones y views

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logica de inicio
                String email = correo.getText().toString();
                String pw = pass.getText().toString();

                if(!email.equals("") && !pw.equals("")){
                    //ambos estan llenos
                    HashMap<String,String> params = new HashMap<>();
                    params.put("email", email);
                    params.put("password", pw);
                    JSONObject jusuario = new JSONObject(params);

                    Log.d("msg", "exito");

                    RequestQueue queue = Volley.newRequestQueue(getContext());
                    String urlLogin = "http://34.236.191.118:3000/api/v1/users/login";

                    JsonObjectRequest jsonrequest = new JsonObjectRequest(Request.Method.POST, urlLogin, jusuario, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            String respuesta = response.toString();
                            //poner el archivo json ***** FALTA ******
                            //((MainActivity) getActivity()).guardarArchivov2(respuesta);
                            //reenvio a las preguntas
                            ((MainActivity) getActivity()).reemplazarUnFragmento(ListaPreguntasFragmento.class);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //mandar un toast
                            String texto = "Usuario registrado";
                            Toast.makeText(getContext(), texto, Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                        }
                    });

                    queue.add(jsonrequest);

                }else {
                    String texto = "Se debe llenar el email y contraseña";
                    Toast.makeText(getContext(), texto, Toast.LENGTH_LONG).show();
                }

            }
        });
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).reemplazarUnFragmento(RegistroFragmento.class);
            }
        });

        return view;
    }


}