package com.example.adelfo.miscontactos;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ContactarActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null)
            setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_contacto));
        getSupportActionBar().setIcon(R.drawable.dog_footprint_filled_50);
    }

    public void enviarEmail(View v){
        String[] TO = {getResources().getString(R.string.tv_email)};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        try {
            startActivity(Intent.createChooser(emailIntent, getResources().getString(R.string.enviar_email)));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactarActivity.this, getResources().getString(R.string.no_aplicacion_email), Toast.LENGTH_SHORT).show();
        }
    }

    public void abrirLinkedin(View v) {
        Uri webpage = Uri.parse(getResources().getString(R.string.etLinkedin));
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
