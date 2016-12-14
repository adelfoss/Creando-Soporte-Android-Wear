package com.example.adelfo.miscontactos.db;

import android.content.Context;


import com.example.adelfo.miscontactos.pojo.Usuario;

import java.util.ArrayList;

/**
 * Created by Adelfo on 24/11/2016.
 */

public class ConstructorUsuarios {

    private Context context;

    public ConstructorUsuarios(Context context) {
        this.context = context;
    }

    public ArrayList<Usuario> obtenerDatos(){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerTodosLosContactos2();
    }
}
