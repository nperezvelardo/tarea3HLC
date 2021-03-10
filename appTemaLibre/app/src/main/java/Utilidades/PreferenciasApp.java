package Utilidades;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.apptemalibre.R;

public class PreferenciasApp {

    public static final String COLOR_TEXTO = "colorTexto";
    public static final String COLOR_FONDO = "colorFondo";
    public static final String TAMAÑO_TEXTO = "tamañoTexto";

    public static int colorTexto;
    public static int colorFondo;
    public static int tamañoTexto;

    public static void asignarPreferencias(SharedPreferences preferences, Context context){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(COLOR_TEXTO,""+colorTexto);
        editor.putString(COLOR_FONDO,""+colorFondo);
        editor.putString(TAMAÑO_TEXTO,""+tamañoTexto);
        editor.commit();
        obtenerPreferencias(preferences, context);
    }

    public static void obtenerPreferencias(SharedPreferences preferences, Context context){
        String error = "ok";

        try{
            colorFondo = Integer.parseInt(preferences.getString(COLOR_FONDO, ""+ R.color.colorBlanco));
        }catch(NumberFormatException e){
            error = "Color fondo";
        }
        try{
            colorTexto = Integer.parseInt(preferences.getString(COLOR_TEXTO, ""+ R.color.colorNegro));
        }catch(NumberFormatException e){
            error = "Color texto";
        }
        try{
            tamañoTexto = Integer.parseInt(preferences.getString(TAMAÑO_TEXTO, "16"));
        }catch(NumberFormatException e){
            error = "Color texto";
        }

        if(!error.equals("ok")){
            Toast.makeText(context, "Configuracion erronea"+error,Toast.LENGTH_LONG).show();
        }
    }
}
