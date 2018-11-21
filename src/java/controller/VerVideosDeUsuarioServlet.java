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
@WebServlet(name = "VerVideosDeUsuarioServlet", urlPatterns = {"/verVideosDeUsuario.do"})
public class VerVideosDeUsuarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setCharacterEncoding("UTF-8");

            DAO_Usuario du = new DAO_Usuario();

            int id = Integer.parseInt(request.getParameter("idDelUsuario"));
            Usuario u = du.getUsuarioById(id);
            request.getSession().setAttribute("usu", u);
            
            //System.out.println(u);
            response.sendRedirect("verVideosDeUsuario.jsp");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerVideosDeUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VerVideosDeUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
