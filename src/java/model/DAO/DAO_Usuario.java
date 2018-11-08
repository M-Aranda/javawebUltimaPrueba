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
import model.Conexion;
import model.Usuario;

/**
 *
 * @author Marce
 */
public class DAO_Usuario extends Conexion implements DAO<Usuario>{

    public DAO_Usuario() throws ClassNotFoundException, SQLException {
        super("prueba4JavaWebMAAT");
    }

    @Override
    public void create(Usuario ob) throws SQLException {
        ejecutar("INSERT INTO usuario VALUES(NULL, '"+ob.getNombre()+"', '"+ob.getContrasenia()+"' ,0)");
    }

    @Override
    public List<Usuario> read() throws SQLException {
        List<Usuario> lista= new ArrayList<>();
        
        ResultSet rs= ejecutar("SELECT * FROM usuario");
        Usuario u=null;
        while(rs.next()){
            u = new Usuario();
            u.setId(rs.getInt(1));
            u.setNombre(rs.getString(2));
            u.setVideosSubidos(rs.getInt(3));
            
            lista.add(u);
        }

        return lista;
    }

    @Override
    public void update(Usuario ob) throws SQLException {
        ejecutar("UPDATE usuario SET nombre='"+ob.getNombre()+"', '"+ob.getContrasenia()+"'  videosSubidos="+ob.getVideosSubidos()+" WHERE id="+ob.getId()+"");
    }

    @Override
    public void delete(String id) throws SQLException {
        int idABorrar=Integer.parseInt(id);
        ejecutar("DELETE FROM usuario WHERE id="+idABorrar+"");
    }
 
    public Usuario getUsuarioById(int id)throws SQLException{
        ResultSet rs=ejecutar("SELECT * FROM usuario WHERE id="+id+" ");
        
        Usuario u=null;
        if(rs.next()){
            u= new Usuario();
            u.setId(rs.getInt(1));
            u.setNombre(rs.getString(2));
            u.setContrasenia(rs.getString(3));
            u.setVideosSubidos(rs.getInt(4));
        }
        return u;
    }
    
    
        public Usuario getUsuarioByNombre(String nombre)throws SQLException{
        ResultSet rs=ejecutar("SELECT * FROM usuario WHERE nombre='"+nombre+"' ");
        
        Usuario u=null;
        if(rs.next()){
            u= new Usuario();
            u.setId(rs.getInt(1));
            u.setNombre(rs.getString(2));
            u.setContrasenia(rs.getString(3));
            u.setVideosSubidos(rs.getInt(4));
        }
        return u;
    }
    
    
    
    
}
