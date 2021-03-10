package com.example.apptemalibre;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import Utilidades.PreferenciasApp;

public class AjustesTemaActivity extends AppCompatActivity {

    Spinner comboTamaño;
    RelativeLayout layoutFondo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes_tema);

        comboTamaño = findViewById(R.id.comboTamaño);
        layoutFondo=findViewById(R.id.idFondo);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.tamaño,android.R.layout.simple_spinner_item);
        comboTamaño.setAdapter(adapter);

        asignarValoresPreferencias();
        comboTamaño.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("14")){
                    PreferenciasApp.tamañoTexto=14;
                }else if(parent.getItemAtPosition(position).toString().equals("16")){
                    PreferenciasApp.tamañoTexto=16;
                }else{
                    PreferenciasApp.tamañoTexto=18;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void asignarValoresPreferencias(){
        layoutFondo.setBackgroundColor(getResources().getColor(PreferenciasApp.colorFondo));
        if(PreferenciasApp.tamañoTexto==14){
            comboTamaño.setSelection(0);
        }else if(PreferenciasApp.tamañoTexto==16){
            comboTamaño.setSelection(1);
        }else{
            comboTamaño.setSelection(2);
        }
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cardNegro:
                PreferenciasApp.colorFondo=R.color.colorNegro;
                break;
            case R.id.cardAzul:
                PreferenciasApp.colorFondo=R.color.colorAzul;
                break;
            case R.id.cardRojo:
                PreferenciasApp.colorFondo=R.color.colorRojo;
                break;
            case R.id.cardVerde:
                PreferenciasApp.colorFondo=R.color.colorVerde;
                break;
            case R.id.cardNaranja:
                PreferenciasApp.colorFondo=R.color.colorNaranja;
                break;
            case R.id.cardAmarillo:
                PreferenciasApp.colorFondo=R.color.colorAmarillo;
                break;
            case R.id.cardLila:
                PreferenciasApp.colorFondo=R.color.colorPrimary;
                break;
            case R.id.cardTurquesa:
                PreferenciasApp.colorFondo=R.color.colorAccent;
                break;
            case R.id.cardGris:
                PreferenciasApp.colorFondo=R.color.colorGris;
                break;
            case R.id.cardNegro2:
                PreferenciasApp.colorTexto=R.color.colorNegro;
                break;
            case R.id.cardAzul2:
                PreferenciasApp.colorTexto=R.color.colorAzul;
                break;
            case R.id.cardRojo2:
                PreferenciasApp.colorTexto=R.color.colorRojo;
                break;
            case R.id.cardVerde2:
                PreferenciasApp.colorTexto=R.color.colorVerde;
                break;
            case R.id.cardNaranja2:
                PreferenciasApp.colorTexto=R.color.colorNaranja;
                break;
            case R.id.cardAmarillo2:
                PreferenciasApp.colorTexto=R.color.colorAmarillo;
                break;
            case R.id.cardLila2:
                PreferenciasApp.colorTexto=R.color.colorPrimary;
                break;
            case R.id.cardTurquesa2:
                PreferenciasApp.colorTexto=R.color.colorAccent;
                break;
            case R.id.cardGris2:
                PreferenciasApp.colorTexto=R.color.colorGris;
                break;
            case R.id.btnIcoAtras:
                finish();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
        layoutFondo.setBackgroundColor(getResources().getColor(PreferenciasApp.colorFondo));
    }

    @Override
    protected void onDestroy() {
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        PreferenciasApp.asignarPreferencias(preferences, getApplicationContext());
        super.onDestroy();
    }
}