package com.example.adelfo.miscontactos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.adelfo.miscontactos.adapter.PageAdapter;
import com.example.adelfo.miscontactos.fragment.RecyclerViewFragment;
import com.example.adelfo.miscontactos.fragment.RecyclerViewFragmentUsuarioPrincipal;
import com.example.adelfo.miscontactos.restAPI.ConstantesRestApi;
import com.example.adelfo.miscontactos.restAPI.EndpointsApi;
import com.example.adelfo.miscontactos.restAPI.Model.UsuarioResponse2;
import com.example.adelfo.miscontactos.restAPI.adapter.RestApiAdapter;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static android.content.ContentValues.TAG;
import static com.example.adelfo.miscontactos.ConfigurarCuenta.context;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static Context mainActivityContext;


    public static String main_user_id = "";
    public static String main_user_name = "";

    public static int numTab = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityContext = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();

        if(toolbar != null){
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setLogo(R.drawable.dog_footprint_filled_50);

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                String value = getIntent().getExtras().getString(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.nombre_bd), Context.MODE_PRIVATE);

        try {
            if(sharedPreferences.getString("liked_user", "false").equals("true")){
                SharedPreferences sharedPreferences2 = getSharedPreferences(getResources().getString(R.string.nombre_base_datos), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences2.edit();
                editor.putString("liked_user", "false");
                editor.commit();

                main_user_id = sharedPreferences.getString(getResources().getString(R.string.liked_user_id), ConstantesRestApi.default_user_id);
                main_user_name = sharedPreferences.getString(getResources().getString(R.string.liked_user_name), ConstantesRestApi.default_user_name);
            }else {
                main_user_id = sharedPreferences.getString("usuario_id", ConstantesRestApi.default_user_id);
                main_user_name = sharedPreferences.getString("usuario_nombre", ConstantesRestApi.default_user_name);
            }
        }catch (Exception err){
            main_user_id = ConstantesRestApi.default_user_id;
            main_user_name = ConstantesRestApi.default_user_name;
        }
        setUpViewPager();
    }

    public ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new RecyclerViewFragmentUsuarioPrincipal());
        return fragments;
    }

    public void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        viewPager.setCurrentItem(numTab);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_full_dog_house);
        tabLayout.getTabAt(1).setIcon(R.drawable.year_of_dog_50);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        numTab = 0;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mContacto) {
            Intent intent = new Intent(this, ContactarActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.mAcercaDe){
            Intent intent = new Intent(this, AcercaDeActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.mConfigurarCuenta) {
            Intent intent = new Intent(this, ConfigurarCuenta.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.mRecibirNotificaciones) {
            enviarToken();
        }
        return super.onOptionsItemSelected(item);
    }

    public void guardarUsuarioPetagram(View v){
        EditText etAgregarUsuario = (EditText) findViewById(R.id.etAgregarUsuario);
        String usuario = etAgregarUsuario.getText().toString();

        SharedPreferences miPrefereciaCompartida = getSharedPreferences("UsuariosPetagram", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = miPrefereciaCompartida.edit();
        editor.putString(usuario, usuario);
        editor.commit();

        Toast.makeText(MainActivity.this, "Se ha guardado el usuario "+usuario+".", Toast.LENGTH_SHORT).show();
        etAgregarUsuario.setText("");
    }

    public void enviarToken(){
        String token = FirebaseInstanceId.getInstance().getToken();
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpoints = restApiAdapter.establecerConexionRestAPI();
        Call<UsuarioResponse2> usuarioResponseCall = endpoints.registrarTokenID(token, main_user_id);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse2>() {
            @Override
            public void onResponse(Call<UsuarioResponse2> call, Response<UsuarioResponse2> response) {
                UsuarioResponse2 usuarioResponse2 = response.body();
                Log.d("ID FIREBASE: ", usuarioResponse2.getId());
                Log.d("ID DISPOSITIVO: ", usuarioResponse2.getId_dispositivo());
                Log.d("ID USUARIO INSTAGRAM: ", usuarioResponse2.getId_usuario_instagram());
            }

            @Override
            public void onFailure(Call<UsuarioResponse2> call, Throwable t) {

            }
        });
    }
}
