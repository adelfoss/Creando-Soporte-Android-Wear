package com.example.adelfo.miscontactos.restAPI.Model;

import com.example.adelfo.miscontactos.pojo.Usuario;

import java.util.ArrayList;

/**
 * Created by Adelfo on 24/11/2016.
 */

public class UsuarioResponse {

    ArrayList<Usuario> usuarios;

    public ArrayList<Usuario> getUsuarios(){
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios){
        this.usuarios = usuarios;
    }
}
