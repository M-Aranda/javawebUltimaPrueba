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
public class Usuario {

    private int id;
    private String nombre;
    private String contrasenia;
    private int videosDeYoutubeDescargados;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String contrasenia, int videosSubidos) {
        this.id = id;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.videosDeYoutubeDescargados = videosSubidos;
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getVideosDeYoutubeDescargados() {
        return videosDeYoutubeDescargados;
    }

    public void setVideosDeYoutubeDescargados(int videosDeYoutubeDescargados) {
        this.videosDeYoutubeDescargados = videosDeYoutubeDescargados;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", contrasenia=" + contrasenia + ", videosSubidos=" + videosDeYoutubeDescargados + '}';
    }

    
    
    
    
    

}
