package com.example.adelfo.miscontactos.restAPI.Model;

import com.example.adelfo.miscontactos.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by Adelfo on 22/11/2016.
 */

public class ContactoResponse {

    ArrayList<Contacto> contactos;

    public ArrayList<Contacto> getContactos(){
        return contactos;
    }

    public void setContactos(ArrayList<Contacto> contactos){
        this.contactos = contactos;
    }
}
