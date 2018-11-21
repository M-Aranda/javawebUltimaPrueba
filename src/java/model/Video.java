/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Marce
 */
public class Video {
    
    private int id;
    private String nombre;
    private String ruta;
    private Usuario usuario;

    public Video() {
    }

    public Video(int id, String nombre, String ruta, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.ruta = ruta;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        //return "Video{" + "id=" + id + ", nombre=" + nombre + ", ruta=" + ruta + ", usuario=" + usuario + '}';
        return nombre;
    }


    
    
}
