package com.example.adelfo.miscontactos.presentador;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.adelfo.miscontactos.MainActivity;
import com.example.adelfo.miscontactos.db.ConstructorContactos;
import com.example.adelfo.miscontactos.db.ConstructorUsuarios;
import com.example.adelfo.miscontactos.fragment.IRecyclerViewFragmentView;
import com.example.adelfo.miscontactos.pojo.Contacto;
import com.example.adelfo.miscontactos.restAPI.EndpointsApi;
import com.example.adelfo.miscontactos.restAPI.Model.ContactoResponse;
import com.example.adelfo.miscontactos.restAPI.Model.UsuarioResponse;
import com.example.adelfo.miscontactos.restAPI.adapter.RestApiAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Adelfo on 08/12/2016.
 */

public class RecyclerViewFragmentPresenterUsuarioPrincipal implements IRecyclerViewFragmentPresenter{
    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorContactos constructorContactos;
    private ArrayList<Contacto> contactos1;

    private ConstructorUsuarios constructorUsuarios;

    public RecyclerViewFragmentPresenterUsuarioPrincipal(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerMediosRecientes();
        obtenerIDUsuario();
    }

    @Override
    public void obtenerContactosBaseDatos() {
        constructorContactos = new ConstructorContactos(context);
        constructorUsuarios = new ConstructorUsuarios(context);
        contactos1 = constructorContactos.obtenerDatos();
        mostrarContactosRV();
    }

    @Override
    public void obtenerMediosRecientes() {
        SharedPreferences miPrefereciaCompartida = context.getSharedPreferences("UsuariosPetagram", Context.MODE_PRIVATE);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<ContactoResponse> contactoResponseCall = endpointsApi.getRecentMedia(MainActivity.main_user_id, "4179481767.6a67fa6.ca3f237670924e89b088d8a088a08216");
        contactoResponseCall.enqueue(new Callback<ContactoResponse>() {
            @Override
            public void onResponse(Call<ContactoResponse> call, Response<ContactoResponse> response) {
                ContactoResponse contactoResponse = response.body();
                contactos1 = contactoResponse.getContactos();
                mostrarContactosRV();
            }
            @Override
            public void onFailure(Call<ContactoResponse> call, Throwable t) {
                Toast.makeText(context, "Fallo la conexión", Toast.LENGTH_SHORT).show();
                Log.e("Fallo la conexión: ", t.toString());
            }
        });
    }

    @Override
    public void obtenerIDUsuario() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonUsuario = restApiAdapter.construyeGsonDeserializadorUsuarioID();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonUsuario);
        Call<UsuarioResponse> usuarioResponseCall = endpointsApi.getUser(MainActivity.main_user_name, "4179481767.6a67fa6.ca3f237670924e89b088d8a088a08216");

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {

            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void mostrarContactosRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(contactos1));
        iRecyclerViewFragmentView.generarGridLayout();
    }
}
