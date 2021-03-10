package com.example.apptemalibre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;

import Utilidades.PreferenciasApp;

public class MainActivity extends AppCompatActivity {

    Button btnRegistro, btnLista;
    int request_code = 1;
    boolean registro;
    Menu menu;
    MenuItem item;
    EditText usuario, password;
    CheckBox recordar;
    ConstraintLayout fondo;
    daoUsuario dao;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.id_item1:
                Intent i = new Intent(getApplicationContext(), AjustesActivity.class);
                startActivity(i);   //iniciamos actividad preferencias
            default:
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceManager.setDefaultValues(this, R.xml.preferencias, false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        PreferenciasApp.obtenerPreferencias(preferences, this);

        btnRegistro = (Button)findViewById(R.id.buttonRegistro);
        btnLista = (Button)findViewById(R.id.buttonLista);
        usuario = (EditText)findViewById(R.id.editTextTextPersonName);
        password = (EditText)findViewById(R.id.editTextTextPassword);
        recordar = (CheckBox)findViewById(R.id.checkBox);
        fondo = (ConstraintLayout)findViewById(R.id.fondo);
        dao = new daoUsuario(this);

        SharedPreferences sharedPref = this.getSharedPreferences(
                "miSharedPreference", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        String emailtext = sharedPref.getString("usuario", "");
        String pass = sharedPref.getString("password", "");
        boolean stateSwitch = sharedPref.getBoolean("checked", false);
        recordar.setChecked(stateSwitch);
        usuario.setText(emailtext);
        password.setText(pass);



        //Abrimos segunda actividad al pulsar el boton registro
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), Second_Activity.class);
            startActivityForResult(i, request_code);

            }
        });

        //Abrimos tercera actividad al pulsar el boton lista
        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = usuario.getText().toString();
                String p = password.getText().toString();
                if(u.equals("") && p.equals("")){
                    Toast.makeText(getApplicationContext(), "Complete los campos", Toast.LENGTH_LONG).show();
                }else if(dao.login(u, p) == 1){
                    Usuario ux = dao.getUsuario(u, p);
                    if(recordar.isChecked()){
                        editor.putString("usuario", usuario.getText().toString());
                        editor.putString("password", password.getText().toString());
                        editor.putBoolean("checked", recordar.isChecked());
                        editor.commit();
                    }else{
                        editor.putString("usuario", ""); //quitamos el usuario de las preferencias
                        editor.putString("password", "");
                        editor.putBoolean("checked", recordar.isChecked());
                        editor.commit();
                    }
                    Intent i = new Intent(getApplicationContext(), Third_Activity.class);
                    startActivity(i);
                }else {
                //mostramos mensaje de advertencia de no registrado
                    Toast.makeText(getApplicationContext(), "Debes registrarte", Toast.LENGTH_LONG).show();
                }
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