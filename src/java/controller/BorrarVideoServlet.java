package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
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
@WebServlet(name = "BorrarVideoServlet", urlPatterns = {"/borrarVideo.do"})
public class BorrarVideoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setCharacterEncoding("UTF-8");

            DAO_Usuario du = new DAO_Usuario();
            DAO_Video dv = new DAO_Video();

            int idVideo = Integer.parseInt(request.getParameter("idVideoABorrar"));

            Video v = dv.findVideoById(idVideo);

            
            
            Usuario u = du.getUsuarioByIdDeVideo(idVideo);
            u.setVideosDeYoutubeDescargados(u.getVideosDeYoutubeDescargados()-1);
            dv.delete(String.valueOf(idVideo));
            du.update(u);

            // borrando archivo con Path y Files
            
            /*
             File file = new File(v.getRuta());
             file.delete();
             */
            
            Files.deleteIfExists(Paths.get(v.getRuta()));

            response.sendRedirect("menuAdministrador.jsp");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BorrarVideoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BorrarVideoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
