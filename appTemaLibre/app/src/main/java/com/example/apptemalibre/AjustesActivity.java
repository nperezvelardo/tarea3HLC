package com.example.apptemalibre;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import Utilidades.AjustesFragment;
import Utilidades.PreferenciasApp;

public class AjustesActivity extends AppCompatActivity {

    Context context = this;
    RelativeLayout fondoL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        fondoL=findViewById(R.id.fondo);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorAjustes, new AjustesFragment()).commit();

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnIcoAtras:
                finish();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    public void asignarValoresPreferencias(){
        fondoL.setBackgroundColor(getResources().getColor(PreferenciasApp.colorFondo));
    }

    @Override
    protected void onResume() {
        super.onResume();
        asignarValoresPreferencias();
    }
}