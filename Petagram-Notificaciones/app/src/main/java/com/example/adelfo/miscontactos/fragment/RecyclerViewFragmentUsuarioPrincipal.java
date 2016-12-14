package com.example.adelfo.miscontactos.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adelfo.miscontactos.MainActivity;
import com.example.adelfo.miscontactos.R;
import com.example.adelfo.miscontactos.adapter.ContactoAdaptador;
import com.example.adelfo.miscontactos.pojo.Contacto;
import com.example.adelfo.miscontactos.presentador.IRecyclerViewFragmentPresenter;
import com.example.adelfo.miscontactos.presentador.RecyclerViewFragmentPresenterUsuarioPrincipal;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Adelfo on 08/12/2016.
 */

public class RecyclerViewFragmentUsuarioPrincipal extends Fragment implements IRecyclerViewFragmentView{
    ArrayList<Contacto> contactos;
    private RecyclerView listaContactos;
    private IRecyclerViewFragmentPresenter presenter;

    private TextView tvNombreUsuarioPrincipal;
    private CircularImageView imgPerfilUsuarioPrincipal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recyclerview_usuario_principal, container,false);

        listaContactos = (RecyclerView) v.findViewById(R.id.rvContactos);

        tvNombreUsuarioPrincipal = (TextView) v.findViewById(R.id.tvNombreUsuarioPrincipal);
        imgPerfilUsuarioPrincipal = (CircularImageView) v.findViewById(R.id.imgPerfilUsuarioPrincipal);
        tvNombreUsuarioPrincipal.setText(MainActivity.main_user_name);
        Picasso.with(getContext()).load("http://lorempixel.com/400/400/animals/").into(imgPerfilUsuarioPrincipal);

        presenter = new RecyclerViewFragmentPresenterUsuarioPrincipal(this, getContext());

        return v;
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaContactos.setLayoutManager(llm);
    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        listaContactos.setLayoutManager(gridLayoutManager);
    }

    @Override
    public ContactoAdaptador crearAdaptador(ArrayList<Contacto> contactos) {
        ContactoAdaptador adaptador =new ContactoAdaptador(contactos, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(ContactoAdaptador adaptador) {
        listaContactos.setAdapter(adaptador);
    }
}
