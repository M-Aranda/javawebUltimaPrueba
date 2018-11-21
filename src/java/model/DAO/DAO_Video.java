/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Video;

/**
 *
 * @author Marce
 */
public class DAO_Video extends Conexion implements DAO<Video> {

    public DAO_Video() throws ClassNotFoundException, SQLException {
        super("prueba4JavaWebMAAT");
    }

    @Override
    public void create(Video ob) throws SQLException {
        ejecutar("INSERT INTO video VALUES (NULL, '" + ob.getNombre() + "', '" + ob.getRuta() + "', " + ob.getUsuario().getId() + "  )");
    }

    @Override
    public List<Video> read() throws SQLException {
        List<Video> lista = new ArrayList<>();
        ResultSet rs = ejecutar("SELECT * FROM video");

        Video v = null;
        while (rs.next()) {
            try {
                v = new Video();
                v.setId(rs.getInt(1));
                v.setNombre(rs.getString(2));
                v.setRuta(rs.getString(3));

                DAO_Usuario du = new DAO_Usuario();
                v.setUsuario(du.getUsuarioById(rs.getInt(4)));

                lista.add(v);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DAO_Video.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        close();
        return lista;

    }

    @Override
    public void update(Video ob) throws SQLException {
        ejecutar("UPDATE video SET nombre='" + ob.getNombre() + "', ruta='" + ob.getRuta() + "', usuario_fk=" + ob.getUsuario().getId() + "   WHERE id=" + ob.getId() + "");
    }

    @Override
    public void delete(String id) throws SQLException {
        int idABorrar = Integer.parseInt(id);
        ejecutar("DELETE FROM video WHERE id=" + id + " ");
    }

    public List<Video> readVideosBajadosPorElUsuario(int id) throws SQLException {
        List<Video> lista = new ArrayList<>();
        ResultSet rs = ejecutar("SELECT * FROM video WHERE usuario_fk=" + id + " ");

        Video v = null;
        while (rs.next()) {
            try {
                v = new Video();
                v.setId(rs.getInt(1));
                v.setNombre(rs.getString(2));
                v.setRuta(rs.getString(3));

                DAO_Usuario du = new DAO_Usuario();
                v.setUsuario(du.getUsuarioById(rs.getInt(4)));

                lista.add(v);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DAO_Video.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        close();
        return lista;

    }

    public int getCantVideosDescargados() throws SQLException {
        int cantidad = 0;

        ResultSet rs = ejecutar("SELECT COUNT(*) FROM video");
        if (rs.next()) {
            cantidad = rs.getInt(1);
        }

        close();
        return cantidad;

    }

    public Video findVideoById(int id) throws SQLException {

        Video v = null;
        try {

            DAO_Usuario du = new DAO_Usuario();

            ResultSet rs = ejecutar("SELECT * FROM video WHERE id=" + id + " ");

            if (rs.next()) {
                v = new Video();
                v.setId(rs.getInt(1));
                v.setNombre(rs.getString(2));
                v.setRuta(rs.getString(3));
                v.setUsuario(du.getUsuarioById(rs.getInt(4)));
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO_Video.class.getName()).log(Level.SEVERE, null, ex);
        }

        close();
        return v;
    }

}
