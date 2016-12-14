package com.example.adelfo.miscontactos.restAPI.Model;

/**
 * Created by Adelfo on 14/12/2016.
 */

public class followUnfollowResponse {

    private String estado;

    public followUnfollowResponse() {
    }

    public followUnfollowResponse(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
