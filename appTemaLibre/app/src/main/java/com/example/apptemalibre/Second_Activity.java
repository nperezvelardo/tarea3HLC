package com.example.apptemalibre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import Utilidades.PreferenciasApp;


public class Second_Activity extends AppCompatActivity {

    ConstraintLayout fondo;
    EditText edTxtNombre, edTxtApellidos, edTxtEmail, edTxtPass;
    Button btnRegistrar, btnCancelar;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_);

        fondo = (ConstraintLayout)findViewById(R.id.fondo2);
        btnRegistrar = (Button)findViewById(R.id.buttonRgtro);
        btnCancelar = (Button)findViewById(R.id.buttonCancelar);
        edTxtNombre = (EditText) findViewById(R.id.edTxtNombre);
        edTxtApellidos = (EditText) findViewById(R.id.edTxtEdad);
        edTxtEmail = (EditText) findViewById(R.id.edTxtEmail);
        edTxtPass = (EditText) findViewById(R.id.editTextTextPassword2);

        dao = new daoUsuario(this);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario u = new Usuario();
                u.setNombre(edTxtNombre.getText().toString());
                u.setApellidos(edTxtApellidos.getText().toString());
                u.setEmail(edTxtEmail.getText().toString());
                u.setPassword(edTxtPass.getText().toString());
                if(!u.isNull()) {
                    Toast.makeText(getApplicationContext(), "ERROR: Campos vacíos", Toast.LENGTH_LONG).show();
                }else if(dao.insertUsuario(u)){
                    Toast.makeText(getApplicationContext(), "Usuario Registrado", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Usuario ya registrado", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    public void asignarValoresPreferencias(){
        fondo.setBackgroundColor(getResources().getColor(PreferenciasApp.colorFondo));
    }

    @Override
    protected void onResume() {
        super.onResume();
        asignarValoresPreferencias();
    }
}