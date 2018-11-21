package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO.DAO_Usuario;
import model.DAO.DAO_Video;
import model.Usuario;
import model.Video;

/**
 *
 * @author Marce
 */
@WebServlet(urlPatterns = {"/eliminarUsuario.do"})
public class EliminarUsuarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setCharacterEncoding("UTF-8");

            DAO_Usuario du = new DAO_Usuario();
            DAO_Video dv = new DAO_Video();

            Usuario u = (Usuario) du.getUsuarioById(Integer.parseInt(request.getParameter("idDelUsuarioABorrar")));
            List<Video> videos=dv.readVideosBajadosPorElUsuario(u.getId());
            for (Video v : videos) {
                Files.deleteIfExists(Paths.get(v.getRuta()));
            }
            
            dv.borrarVideosDeUsuario(u.getId());
            du.delete(String.valueOf(u.getId()));

            response.sendRedirect("menuAdministrador.jsp");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EliminarUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EliminarUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
