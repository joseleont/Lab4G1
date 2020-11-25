package pe.pucp.dduu.tel306.lab4g1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pe.pucp.dduu.tel306.lab4g1.Clases.API.Usuario.Usuario;

import static android.widget.Toast.LENGTH_SHORT;


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
        View view = inflater.inflate(R.layout.fragment_registro_fragmento, container, false);
        EditText editTextNombreRegistro = view.findViewById(R.id.editTextNombreRegistro);
        EditText editTextCorreoRegistro = view.findViewById(R.id.editTextCorreoRegistro);
        EditText editTextContrasenaRegistro = view.findViewById(R.id.editTextContraseñaRegistro);
        Button btnGuardarRegistro = view.findViewById(R.id.btnGuardarRegistro);
        Button btnIniciarSesionRegistro = view.findViewById(R.id.btnIniciarSesionRegistro);




        //GUARDAR EL ARCHIVO DE LA INFORMACION DE LA PERSONA
        btnGuardarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombreRegistro = editTextNombreRegistro.getText().toString();
                String correoRegistro = editTextCorreoRegistro.getText().toString();
                String contrasenaRegistro = editTextContrasenaRegistro.getText().toString();
                String regexNombre = "(^[A-Z]{1}[a-z]{1,}.[A-Z]{1}[a-z]{1,}$)";

                Usuario usuario = new Usuario();
                usuario.setName(nombreRegistro);
                usuario.setEmail(correoRegistro);
                usuario.setPassword(contrasenaRegistro);

                // JsonObject jusuario = new JsonObject();

                HashMap<String,String> params = new HashMap<>();
                params.put("name", usuario.getName());
                params.put("email", usuario.getEmail());
                params.put("password", usuario.getPassword());
                JSONObject jusuario = new JSONObject(params);


                if (cumpleRegex(nombreRegistro, regexNombre)) {
                    //hacer el post al API
                    Log.d("msg", "exito");
                    //POST al API
                    RequestQueue queue = Volley.newRequestQueue(getContext());
                    String urlRegistro = "http://34.236.191.118:3000/api/v1/users/new";
                    JsonObjectRequest jsonrequest = new JsonObjectRequest(Request.Method.POST, urlRegistro, jusuario, new Response.Listener<JSONObject>() {
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
                            Log.d("msg", "onErrorResponse: "+ "si arriba dice true no hay de que preocuparse :'vvvv es por la API");
                            // Log.d("msg", "error POST registro");
                        }
                    });

                    queue.add(jsonrequest);

                    /*
                    try(FileOutputStream fileOutputStream = getActivity().openFileOutput("InformacionUsuario",MODE_PRIVATE);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);){

                    objectOutputStream.writeObject(usuario);
                    Log.d("msg","escritura de objeto exitosa");

                    }catch(IOException e){
                    e.printStackTrace();
                    }
                    */
                } else {
                    //mostrar un mensaje de error y no dejar hacer el post al API xdxd
                    Log.d("msg", "no cumple regex");
                }


            }
        });

        btnIniciarSesionRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).reemplazarUnFragmentPorElOtroXDXD(InicioSesionFragmento.class);
            }
        });

        return view;
    }

    private boolean cumpleRegex(String nombreRegistro, String regexNombre) {

        Log.d("msg", "cumpleRegex: "+nombreRegistro + " - " + regexNombre);
        Pattern patt = Pattern.compile(regexNombre);
        Matcher matcher = patt.matcher(nombreRegistro);
        return matcher.matches();
        //deberia funcionar asi :'v

    }
}
