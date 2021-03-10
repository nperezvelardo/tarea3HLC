package com.example.apptemalibre;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoUsuario {
    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd ="BDUsuarios";
    String tabla = "Create table if not exists usuario(id integer primary key autoincrement, nombre text, pass text, apellido text, email text)";

    public daoUsuario(Context c) {
        this.c = c;
        sql = c.openOrCreateDatabase(bd, c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        u = new Usuario();
    }

    public boolean insertUsuario(Usuario u){
        if(buscar(u.getEmail())==0){
            ContentValues cv = new ContentValues();
            cv.put("nombre", u.getNombre());
            cv.put("pass", u.getPassword());
            cv.put("apellido", u.getApellidos());
            cv.put("email", u.getEmail());
            return (sql.insert("usuario", null, cv)>0);
        }else{
            return false;
        }
    }

    public int buscar(String email){
        int x = 0;
        lista = selectUsuario();
        for(Usuario user : lista){
            if(user.getEmail().equals(email)){
                x++;
            }
        }
        return x;
    }

    public ArrayList<Usuario> selectUsuario(){
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if(cr!=null && cr.moveToFirst()){
            do{
                Usuario u = new Usuario();
                u.setId(cr.getInt(0));
                u.setNombre(cr.getString(1));
                u.setPassword(cr.getString(2));
                u.setApellidos(cr.getString(3));
                u.setEmail(cr.getString(4));
                lista.add(u);
            }while(cr.moveToNext());
        }
        return lista;
    }

    public int login(String u, String p){
        int log = 0;
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if(cr!=null && cr.moveToFirst()){
            do{
                if(cr.getString(1).equals(u) && cr.getString(2).equals(p)){
                    log++;
                }
            }while(cr.moveToNext());
        }
        return log;
    }

    public Usuario getUsuario(String u, String p){
        lista = selectUsuario();
        for(Usuario user: lista){
            if(user.getNombre().equals(u) && user.getPassword().equals(p)){
                return user;
            }
        }
        return null;
    }

    public Usuario getUsuarioId(int id){
        lista = selectUsuario();
        for(Usuario user: lista){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public boolean deleteUsuario(int id){
        return(sql.delete("usuario", "id=" + id, null) > 0);
    }

}
