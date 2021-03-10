package com.example.apptemalibre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.orm.SugarContext;

import Utilidades.PreferenciasApp;

public class Five_Activity extends AppCompatActivity {
    ConstraintLayout fondo;

    private Button mGuardar, mCancelar;
    private EditText mNombre, mDescripcion;
    private Juego mJuego;
    int imagenId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

        SugarContext.init(this);

        fondo = (ConstraintLayout)findViewById(R.id.fondo5);
        mNombre = (EditText) findViewById(R.id.edTxtNombreJ);
        mDescripcion = (EditText) findViewById(R.id.edTxtDescripcion);
        mGuardar = (Button) findViewById(R.id.buttonGuardar);
        mCancelar = (Button) findViewById(R.id.buttonCancel);
        mGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoNombre = mNombre.getText().toString();
                String textoDescripcion = mDescripcion.getText().toString();
                if(!textoNombre.equals("") && !textoDescripcion.equals("") && imagenId != 0){
                    mJuego = new Juego();
                    mJuego.setDescription(textoDescripcion);
                    mJuego.setNombre(textoNombre);
                    mJuego.setImgId(imagenId);
                    mJuego.save();
                    Toast.makeText(getApplicationContext(), "Juego Creado", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), Third_Activity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Debe introducir los datos del juego", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Third_Activity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cardAnimal:
                imagenId = R.drawable.animal;
                break;
            case R.id.cardBob:
                imagenId = R.drawable.bob;
                break;
            case R.id.cardBomber:
                imagenId = R.drawable.bomber;
                break;
            case R.id.cardCall:
                imagenId = R.drawable.call;
                break;
            case R.id.cardCrash:
                imagenId = R.drawable.crash;
                break;
            case R.id.cardDonkey:
                imagenId = R.drawable.donkey;
                break;
            case R.id.cardKirby:
                imagenId = R.drawable.kirby;
                break;
            case R.id.cardMario:
                imagenId = R.drawable.mario;
                break;
            case R.id.cardSonic:
                imagenId = R.drawable.sonic;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        SugarContext.terminate();
    }

    //asignamos valores de preferencias al color de fondo
    public void asignarValoresPreferencias(){
        fondo.setBackgroundColor(getResources().getColor(PreferenciasApp.colorFondo));
    }

    @Override
    protected void onResume() {
        super.onResume();
        asignarValoresPreferencias();
    }
}