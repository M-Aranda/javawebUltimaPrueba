package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO.DAO_Usuario;
import model.Usuario;

/**
 *
 * @author Marce
 */
@WebServlet(name="IniciarSesionServlet", urlPatterns={"/iniciarSesion.do"})
public class IniciarSesionServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            response.setCharacterEncoding("UTF-8");
            
            String nombre=request.getParameter("nombre");
            String contrasenia=request.getParameter("contrasenia");
            
            DAO_Usuario du= new DAO_Usuario();
            
            Usuario u=du.getUsuarioByNombre(nombre);
            if(u.getContrasenia().equals(contrasenia) && (u.getNombre().equals("11-1"))){
                request.getSession().setAttribute("usuarioIniciado", u);
                response.sendRedirect("menuAdministrador.jsp");
                
            }    else if(u.getContrasenia().equals(contrasenia) ){
                request.getSession().setAttribute("usuarioIniciado", u);
                response.sendRedirect("usuarioIniciado.jsp");
                
            }else {
                 request.getSession().setAttribute("msg", "Datos incorrectos, reintente");
                 response.sendRedirect("index.jsp");
            }
            
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IniciarSesionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IniciarSesionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
