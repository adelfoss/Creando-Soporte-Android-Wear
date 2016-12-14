package com.example.adelfo.miscontactos.fragment;

import com.example.adelfo.miscontactos.adapter.ContactoAdaptador;
import com.example.adelfo.miscontactos.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by Adelfo on 21/11/2016.
 */

public interface IRecyclerViewFragmentView {
    public void generarLinearLayoutVertical();
    public void generarGridLayout();
    public ContactoAdaptador crearAdaptador(ArrayList<Contacto> contactos);
    public void inicializarAdaptadorRV(ContactoAdaptador adaptador);
}
