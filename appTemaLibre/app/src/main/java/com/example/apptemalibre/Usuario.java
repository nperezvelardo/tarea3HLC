package com.example.apptemalibre;

public class Usuario {
    int id;
    String Nombre, Apellidos, Email, Password;

    public Usuario() {

    }

    public Usuario(String nombre, String apellidos, String email, String password) {
        Nombre = nombre;
        Apellidos = apellidos;
        Email = email;
        Password = password;
    }

    public boolean isNull(){
        if(Nombre.equals("") && Apellidos.equals("") && Email.equals("") && Password.equals("")){
            return false;
        }else{
            return true;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
