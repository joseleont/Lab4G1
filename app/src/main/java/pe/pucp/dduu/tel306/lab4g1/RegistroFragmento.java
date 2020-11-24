package pe.pucp.dduu.tel306.lab4g1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import pe.pucp.dduu.tel306.lab4g1.Clases.API.Usuario.Usuario;

import static android.content.Context.MODE_PRIVATE;
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

        String nombreRegistro = editTextNombreRegistro.getText().toString();
        String correoRegistro = editTextCorreoRegistro.getText().toString();
        String contrasenaRegistro = editTextContrasenaRegistro.getText().toString();


        //GUARDAR EL ARCHIVO DE LA INFORMACION DE LA PERSONA
        btnGuardarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario();
                usuario.setName(nombreRegistro);
                usuario.setEmail(correoRegistro);
                usuario.setPassword(contrasenaRegistro);

                Log.d ("msg", "exito");

                /*try(FileOutputStream fileOutputStream = getActivity().openFileOutput("InformacionUsuario",MODE_PRIVATE);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);){

                    objectOutputStream.writeObject(usuario);
                    Log.d("msg","escritura de objeto exitosa");

                }catch(IOException e){
                    e.printStackTrace();
                }*/

                //POST al API
                RequestQueue queue = Volley.newRequestQueue(getContext());
                String urlRegistro =  "http://34.236.191.118:3000/api/v1/users/new";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, urlRegistro,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("msg", response);
                                Toast.makeText(getContext(), response, LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("msg", "error POST registro");
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<>();
                        params.put("name", nombreRegistro);
                        params.put("email", correoRegistro);
                        params.put("password", contrasenaRegistro);

                        return params;
                    }
                };

                queue.add(stringRequest);


            }
        });

        return view;
    }
    }
