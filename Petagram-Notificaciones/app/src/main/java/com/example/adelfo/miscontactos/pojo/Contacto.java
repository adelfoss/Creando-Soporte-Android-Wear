package com.example.adelfo.miscontactos.pojo;

import java.util.StringTokenizer;

/**
 * Created by Adelfo on 18/11/2016.
 */

public class Contacto {
    private String id;
    private String nombreCompleto;
    private String urlFoto;
    private int likes = 0;

    public Contacto(String urlFoto, String nombreCompleto, int likes) {
        this.urlFoto = urlFoto;
        this.nombreCompleto = nombreCompleto;
        this.likes = likes;
    }

    public Contacto() {

    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

