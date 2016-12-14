package com.example.adelfo.miscontactos.restAPI.deserializador;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.adelfo.miscontactos.pojo.Usuario;
import com.example.adelfo.miscontactos.restAPI.JsonKeys;
import com.example.adelfo.miscontactos.restAPI.Model.UsuarioResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.adelfo.miscontactos.ConfigurarCuenta.context;

/**
 * Created by Adelfo on 24/11/2016.
 */

public class UsuarioDeserializador implements JsonDeserializer<UsuarioResponse> {
    @Override
    public UsuarioResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        UsuarioResponse usuarioResponse = gson.fromJson(json, UsuarioResponse.class);
        JsonArray usuarioResponseData = json.getAsJsonObject().getAsJsonArray("data");

        usuarioResponse.setUsuarios(deserializarUsuarioDeJson(usuarioResponseData));
        return usuarioResponse;
    }

    private ArrayList<Usuario> deserializarUsuarioDeJson(JsonArray usuarioResponseData) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        for (int i = 0; i < usuarioResponseData.size(); i++) {
            JsonObject contactoResponseDataObject = usuarioResponseData.get(i).getAsJsonObject();
            String id               = contactoResponseDataObject.get(JsonKeys.USER_ID).getAsString();

            SharedPreferences miPrefereciaCompartida = context.getSharedPreferences("UsuariosPetagram", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = miPrefereciaCompartida.edit();
            editor.putString("usuario_id", id);
            editor.commit();

            Log.i("ID:", id);
            Usuario usuarioActual = new Usuario();
            usuarioActual.setId(id);
            usuarios.add(usuarioActual);
        }
        return usuarios;
    }
}
