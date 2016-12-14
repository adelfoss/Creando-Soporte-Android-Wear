package com.example.adelfo.miscontactos.restAPI;

import android.util.Log;

import com.example.adelfo.miscontactos.restAPI.Model.ContactoResponse;
import com.example.adelfo.miscontactos.restAPI.Model.LikesResponse;
import com.example.adelfo.miscontactos.restAPI.Model.UsuarioResponse;
import com.example.adelfo.miscontactos.restAPI.Model.UsuarioResponse2;
import com.example.adelfo.miscontactos.restAPI.Model.followUnfollowResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Adelfo on 22/11/2016.
 */

public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_SEARCH_BY_USERNAME)
    Call<UsuarioResponse> getUser(@Query("q") String q, @Query("access_token") String access_token);

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_X_USER)
    Call<ContactoResponse> getRecentMedia(@Path("user_id") String user_id, @Query("access_token") String access_token);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_REGISTRAR_USUARIO)
    Call<UsuarioResponse2> registrarTokenID(@Field("id_dispositivo") String token, @Field("id_usuario_instagram") String animal);

    @GET(ConstantesRestApi.KEY_TOQUE_ANIMAL)
    Call<LikesResponse> toqueAnimal(@Path("id_dispositivo") String id_dispositivo, @Path("id_usuario_instagram") String id_usuario_instagram,
                                    @Path("nombre_usuario_instagram") String nombre_usuario_instagram, @Path("id_foto_instagram") String id_foto_instagram);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_FOLLOW_UNFOLLOW)
    Call<followUnfollowResponse> followUnfollow(@Field("id_usuario_instagram_followed") String token, @Field("id_usuario_instagram_follower") String animal);

}

