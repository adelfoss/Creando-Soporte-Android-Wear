package com.example.adelfo.miscontactos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.adelfo.miscontactos.restAPI.ConstantesRestApi;
import com.example.adelfo.miscontactos.restAPI.EndpointsApi;
import com.example.adelfo.miscontactos.restAPI.Model.UsuarioResponse2;
import com.example.adelfo.miscontactos.restAPI.Model.followUnfollowResponse;
import com.example.adelfo.miscontactos.restAPI.adapter.RestApiAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.adelfo.miscontactos.ConfigurarCuenta.context;

/**
 * Created by Adelfo on 12/12/2016.
 */

public class toqueAnimal extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        String accion = intent.getAction();

        if(accion.equals("VER_PERFIL")){
            SharedPreferences sharedPreferences2 = context.getSharedPreferences(context.getResources().getString(R.string.nombre_base_datos), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences2.edit();
            editor.putString("liked_user", "false");
            editor.commit();

            MainActivity.numTab = 1;
            Intent intentone = new Intent(context.getApplicationContext(), MainActivity.class);
            intentone.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentone);
        }

        if(accion.equals("FOLLOW_UNFOLLOW")){
            SharedPreferences sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.nombre_base_datos), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.commit();

            String id_usuario_instagram_followed = sharedPreferences.getString(context.getResources().getString(R.string.liked_user_id), ConstantesRestApi.default_user_id);
            final String nombre_usuario_instagram_followed = sharedPreferences.getString(context.getResources().getString(R.string.liked_user_name), ConstantesRestApi.default_user_name);

            String id_usuario_instagram_follower = sharedPreferences.getString(ConstantesRestApi.default_user_id, ConstantesRestApi.default_user_id);

            RestApiAdapter restApiAdapter0 = new RestApiAdapter();
            EndpointsApi endpoints0 = restApiAdapter0.establecerConexionRestAPI();
            Call<followUnfollowResponse> folloResponseCall = endpoints0.followUnfollow(id_usuario_instagram_followed, id_usuario_instagram_follower);

            folloResponseCall.enqueue(new Callback<followUnfollowResponse>() {
                @Override
                public void onResponse(Call<followUnfollowResponse> call, Response<followUnfollowResponse> response) {
                    followUnfollowResponse followUnfollowResponse = response.body();

                    if(followUnfollowResponse.getEstado().equals("1"))
                        Toast.makeText(context, "Ahora sigues a "+nombre_usuario_instagram_followed, Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(context, "Ya no sigues a "+nombre_usuario_instagram_followed, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<followUnfollowResponse> call, Throwable t) {

                }
            });
        }

        if(accion.equals("VER_USUARIO")){
            SharedPreferences miPrefereciaCompartida = context.getSharedPreferences(context.getResources().getString(R.string.nombre_base_datos), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = miPrefereciaCompartida.edit();
            editor.putString("liked_user", "true");
            editor.commit();

            MainActivity.numTab = 1;
            Intent intentone = new Intent(context.getApplicationContext(), MainActivity.class);
            intentone.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentone);
        }
    }
}
