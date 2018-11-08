
package model.DAO;

import java.sql.SQLException;
import java.util.List;


public interface DAO<T> {
    //las los 4 metodos basicos de un CRUD
    void create(T ob) throws SQLException;
    List<T> read() throws SQLException;
    void update(T ob) throws SQLException;
    void delete(String id) throws SQLException;
    
    // extras, no CRUD
    /*
    List<T> read(String txt) throws SQLException;
    T findByID(String id) throws SQLException; 
    */
}