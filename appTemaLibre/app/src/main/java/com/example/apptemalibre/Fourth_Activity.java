package com.example.apptemalibre;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import Utilidades.PreferenciasApp;


public class Fourth_Activity extends AppCompatActivity {
    ConstraintLayout fondo;

    TextView nombre, descripcion;
    ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_);

        fondo = (ConstraintLayout)findViewById(R.id.fondo4);
        nombre = (TextView)findViewById(R.id.textViewTitulo);
        descripcion = (TextView)findViewById(R.id.textViewDesc);
        foto = (ImageView) findViewById(R.id.imageViewJuego);

        Intent intent = getIntent();
        if(intent != null){
            //pasamos desde la tercera actividad  el juego que queremos mostrar
            String nombreJuego = intent.getStringExtra("Nombre");
            String descripci = intent.getStringExtra("Descripcion");
            int imagen = intent.getIntExtra("Imagen", 0);

            nombre.setText(nombreJuego);
            descripcion.setText(descripci);
            foto.setImageResource(imagen);

        }
    }

    public void asignarValoresPreferencias(){
        fondo.setBackgroundColor(getResources().getColor(PreferenciasApp.colorFondo));
        descripcion.setTextColor(getResources().getColor(PreferenciasApp.colorTexto));
        nombre.setTextColor(getResources().getColor(PreferenciasApp.colorTexto));
        descripcion.setTextSize(PreferenciasApp.tamañoTexto);
        nombre.setTextSize(PreferenciasApp.tamañoTexto);
    }

    @Override
    protected void onResume() {
        super.onResume();
        asignarValoresPreferencias();
    }
}