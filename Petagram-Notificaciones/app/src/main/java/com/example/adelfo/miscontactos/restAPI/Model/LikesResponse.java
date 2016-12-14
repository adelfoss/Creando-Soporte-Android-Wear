package com.example.adelfo.miscontactos.restAPI.Model;

/**
 * Created by Adelfo on 10/12/2016.
 */

public class LikesResponse {
    private String id;
    private String token;
    private String animal;

    public LikesResponse(String id, String token, String animal) {
        this.id = id;
        this.token = token;
        this.animal = animal;
    }

    public LikesResponse() {
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
