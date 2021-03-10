package com.example.apptemalibre;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;

import Utilidades.PreferenciasApp;
import Utilidades.RecyclerViewAdapter;

public class Third_Activity extends AppCompatActivity {
    ConstraintLayout fondo;

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    Menu menu;
    MenuItem item;
    List<Juego> listaJuego = new ArrayList<>();
    long initialCount;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.id_item1:
                Intent i = new Intent(getApplicationContext(), Five_Activity.class);
                startActivity(i);   //iniciamos actividad preferencias
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_);

        SugarContext.init(this);

        fondo = (ConstraintLayout)findViewById(R.id.fondo3);
        rv = (RecyclerView)findViewById(R.id.recyclerView);
        manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);

        initialCount = Juego.count(Juego.class);

        if (initialCount >= 0) {

            listaJuego = Juego.listAll(Juego.class);

            adapter = new RecyclerViewAdapter(this, listaJuego);
            ((RecyclerViewAdapter) adapter).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //cuando pulsamos sobre un elemento abrimos la cuarta actividad pasando los datos del elemento pulsado
                    Intent i = new Intent(getApplicationContext(), Fourth_Activity.class);
                    i.putExtra("Nombre", listaJuego.get(rv.getChildAdapterPosition(v)).getNombre());
                    i.putExtra("Imagen", listaJuego.get(rv.getChildAdapterPosition(v)).getImgId());
                    i.putExtra("Descripcion", listaJuego.get(rv.getChildAdapterPosition(v)).getDescripcion());
                    startActivity(i);
                }
            });
            rv.setAdapter(adapter);

            if(listaJuego.isEmpty())
                Toast.makeText(getApplicationContext(), "No hay Juegos", Toast.LENGTH_LONG).show();
        }
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