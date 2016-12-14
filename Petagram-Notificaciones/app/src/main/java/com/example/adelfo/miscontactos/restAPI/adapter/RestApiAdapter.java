package com.example.adelfo.miscontactos.restAPI.adapter;

import com.example.adelfo.miscontactos.db.ConstantesBaseDatos;
import com.example.adelfo.miscontactos.restAPI.ConstantesRestApi;
import com.example.adelfo.miscontactos.restAPI.EndpointsApi;
import com.example.adelfo.miscontactos.restAPI.Model.ContactoResponse;
import com.example.adelfo.miscontactos.restAPI.Model.UsuarioResponse;
import com.example.adelfo.miscontactos.restAPI.deserializador.ContactoDeserializador;
import com.example.adelfo.miscontactos.restAPI.deserializador.UsuarioDeserializador;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Adelfo on 22/11/2016.
 */

public class RestApiAdapter {

    public EndpointsApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public EndpointsApi establecerConexionRestAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_FIREBASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        return retrofit.create(EndpointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ContactoResponse.class, new ContactoDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorUsuarioID(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UsuarioResponse.class, new UsuarioDeserializador());
        return gsonBuilder.create();
    }
}
