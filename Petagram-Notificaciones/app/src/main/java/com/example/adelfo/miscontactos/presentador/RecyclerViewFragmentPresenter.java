package com.example.adelfo.miscontactos.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.adelfo.miscontactos.MainActivity;
import com.example.adelfo.miscontactos.db.ConstructorContactos;
import com.example.adelfo.miscontactos.db.ConstructorUsuarios;
import com.example.adelfo.miscontactos.fragment.IRecyclerViewFragmentView;
import com.example.adelfo.miscontactos.pojo.Contacto;
import com.example.adelfo.miscontactos.restAPI.ConstantesRestApi;
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
 * Created by Adelfo on 21/11/2016.
 */

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter{
    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorContactos constructorContactos;
    private ArrayList<Contacto> contactos1;
    private ArrayList<Contacto> contactos2;
    private ArrayList<Contacto> contactos3;
    private ArrayList<Contacto> contactos4;

    private ConstructorUsuarios constructorUsuarios;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
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
        contactos2 = constructorContactos.obtenerDatos();
        contactos3 = constructorContactos.obtenerDatos();
        contactos4 = constructorContactos.obtenerDatos();
        mostrarContactosRV();
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<ContactoResponse> contactoResponseCall = endpointsApi.getRecentMedia(ConstantesRestApi.user0, ConstantesRestApi.TOKEN_APPLICATION);
        contactoResponseCall.enqueue(new Callback<ContactoResponse>() {
            @Override
            public void onResponse(Call<ContactoResponse> call, Response<ContactoResponse> response) {
                ContactoResponse contactoResponse = response.body();
                contactos1 = contactoResponse.getContactos();
                obtenerMediosRecientes2();
            }
            @Override
            public void onFailure(Call<ContactoResponse> call, Throwable t) {
                Toast.makeText(context, "Fallo la conexión1", Toast.LENGTH_SHORT).show();
                Log.e("Fallo la conexión: ", t.toString());
            }
        });
    }

    public void obtenerMediosRecientes2() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<ContactoResponse> contactoResponseCall = endpointsApi.getRecentMedia(ConstantesRestApi.user1, ConstantesRestApi.TOKEN_APPLICATION);
        contactoResponseCall.enqueue(new Callback<ContactoResponse>() {
            @Override
            public void onResponse(Call<ContactoResponse> call, Response<ContactoResponse> response) {
                ContactoResponse contactoResponse = response.body();
                contactos2 = contactoResponse.getContactos();
                contactos1.addAll(contactos2);
                obtenerMediosRecientes3();
            }
            @Override
            public void onFailure(Call<ContactoResponse> call, Throwable t) {
                Toast.makeText(context, "Fallo la conexión", Toast.LENGTH_SHORT).show();
                Log.e("Fallo la conexión: ", t.toString());
            }
        });
    }

    public void obtenerMediosRecientes3() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<ContactoResponse> contactoResponseCall = endpointsApi.getRecentMedia(ConstantesRestApi.user2, ConstantesRestApi.TOKEN_APPLICATION);
        contactoResponseCall.enqueue(new Callback<ContactoResponse>() {
            @Override
            public void onResponse(Call<ContactoResponse> call, Response<ContactoResponse> response) {
                ContactoResponse contactoResponse = response.body();
                contactos3 = contactoResponse.getContactos();
                contactos1.addAll(contactos3);
                obtenerMediosRecientes4();
            }
            @Override
            public void onFailure(Call<ContactoResponse> call, Throwable t) {
                Toast.makeText(context, "Fallo la conexión", Toast.LENGTH_SHORT).show();
                Log.e("Fallo la conexión: ", t.toString());
            }
        });
    }

    public void obtenerMediosRecientes4() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<ContactoResponse> contactoResponseCall = endpointsApi.getRecentMedia(ConstantesRestApi.user3, ConstantesRestApi.TOKEN_APPLICATION);
        contactoResponseCall.enqueue(new Callback<ContactoResponse>() {
            @Override
            public void onResponse(Call<ContactoResponse> call, Response<ContactoResponse> response) {
                ContactoResponse contactoResponse = response.body();
                contactos4 = contactoResponse.getContactos();
                contactos1.addAll(contactos4);

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
        Call<UsuarioResponse> usuarioResponseCall = endpointsApi.getUser(MainActivity.main_user_name, ConstantesRestApi.TOKEN_APPLICATION);

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
