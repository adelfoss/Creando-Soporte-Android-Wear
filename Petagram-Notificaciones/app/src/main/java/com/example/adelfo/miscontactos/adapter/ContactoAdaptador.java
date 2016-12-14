package com.example.adelfo.miscontactos.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.adelfo.miscontactos.MainActivity;
import com.example.adelfo.miscontactos.pojo.Contacto;
import com.example.adelfo.miscontactos.R;
import com.example.adelfo.miscontactos.restAPI.ConstantesRestApi;
import com.example.adelfo.miscontactos.restAPI.EndpointsApi;
import com.example.adelfo.miscontactos.restAPI.Model.LikesResponse;
import com.example.adelfo.miscontactos.restAPI.Model.followUnfollowResponse;
import com.example.adelfo.miscontactos.restAPI.adapter.RestApiAdapter;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.adelfo.miscontactos.ConfigurarCuenta.context;
import static java.security.AccessController.getContext;

/**
 * Created by Adelfo on 18/11/2016.
 */

public class ContactoAdaptador extends RecyclerView.Adapter<ContactoAdaptador.ContactoViewHolder>{

    ArrayList<Contacto> contactos;
    Activity activity;

    public ContactoAdaptador(ArrayList<Contacto> contactos, Activity activity){
        this.contactos = contactos;
        this.activity = activity;
    }

    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.carview_grid_contacto, parent, false);
        return new ContactoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ContactoViewHolder contactoViewHolder, int position) {
        final Contacto contacto = contactos.get(position);

        Picasso.with(activity).
                load(contacto.getUrlFoto()).
                placeholder(R.drawable.dog_footprint_filled_50).
                into(contactoViewHolder.imgFoto);

        contactoViewHolder.tvLikes.setText(String.valueOf(contacto.getLikes()));

        contactoViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Diste Like a " + contacto.getNombreCompleto(), Toast.LENGTH_SHORT).show();

                SharedPreferences miPrefereciaCompartida = v.getContext().getSharedPreferences(v.getContext().getResources().getString(R.string.nombre_base_datos), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = miPrefereciaCompartida.edit();
                editor.putString(v.getContext().getResources().getString(R.string.liked_user_id), contacto.getId());
                editor.putString(v.getContext().getResources().getString(R.string.liked_user_name), contacto.getNombreCompleto());
                editor.commit();

                int likes = contacto.getLikes();
                contacto.setLikes(likes+1);
                notifyDataSetChanged();

                Log.d("TOQUE_ANIMAL", "true");

                String id_foto = ConstantesRestApi.id_foto_example;

                //1.    -KYoQkGZuV1Vyxkb5l90
                //2.    -KYp5gVQ6zv0k8QkTOM9
                final LikesResponse likesResponse = new LikesResponse("-KYuUSykYqAdN7Su2JLr", "123", MainActivity.main_user_name);
                RestApiAdapter restApiAdapter =  new RestApiAdapter();
                EndpointsApi endpoints = restApiAdapter.establecerConexionRestAPI();
                Call<LikesResponse> likesResponseCall = endpoints.toqueAnimal(likesResponse.getId(), MainActivity.main_user_id, MainActivity.main_user_name, id_foto);

                likesResponseCall.enqueue(new Callback<LikesResponse>() {
                    @Override
                    public void onResponse(Call<LikesResponse> call, Response<LikesResponse> response) {
                        LikesResponse usuarioResponse1 = response.body();
                        Log.d("ID_FIREBASE", usuarioResponse1.getId());
                        Log.d("TOKEN_FIREBASE", usuarioResponse1.getToken());
                        Log.d("ANIMAL_FIREBASE", usuarioResponse1.getAnimal());
                    }

                    @Override
                    public void onFailure(Call<LikesResponse> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgFoto;
        private TextView tvLikes;

        public ContactoViewHolder(View itemView) {
            super(itemView);

            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvLikes = (TextView) itemView.findViewById(R.id.tvLikes);
        }
    }
}
