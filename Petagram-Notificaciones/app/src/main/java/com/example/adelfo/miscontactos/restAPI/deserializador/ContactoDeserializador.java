package com.example.adelfo.miscontactos.restAPI.deserializador;

import com.example.adelfo.miscontactos.pojo.Contacto;
import com.example.adelfo.miscontactos.restAPI.JsonKeys;
import com.example.adelfo.miscontactos.restAPI.Model.ContactoResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Adelfo on 22/11/2016.
 */

public class ContactoDeserializador implements JsonDeserializer<ContactoResponse>{

    @Override
    public ContactoResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        ContactoResponse contactoResponse = gson.fromJson(json, ContactoResponse.class);
        JsonArray contactoResponseData = json.getAsJsonObject().getAsJsonArray("data");

        contactoResponse.setContactos(deserializarContactoDeJson(contactoResponseData));
        return contactoResponse;
    }

    Contacto contactoActual;
    ArrayList<Contacto> contactos;
    private ArrayList<Contacto> deserializarContactoDeJson(JsonArray contactoResponseData){
         contactos = new ArrayList<>();
        for (int i = 0; i < contactoResponseData.size(); i++) {
            JsonObject contactoResponseDataObject = contactoResponseData.get(i).getAsJsonObject();

            JsonObject userJson     = contactoResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id               = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto   = userJson.get(JsonKeys.USER_USERNAME).getAsString();

            JsonObject imageJoson       = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolution    = imageJoson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto              = stdResolution.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likeJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likeJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            contactoActual = new Contacto();
            contactoActual.setId(id);
            contactoActual.setNombreCompleto(nombreCompleto);
            contactoActual.setUrlFoto(urlFoto);
            contactoActual.setLikes(likes);

            contactos.add(contactoActual);
        }
        return contactos;
    }
}
