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
import model.DAO.DAO_Video;
import model.Usuario;
import model.Video;

/**
 *
 * @author Marce
 */
@WebServlet(name = "DescargarVideoServlet", urlPatterns = {"/descargarVideo.do"})
public class DescargarVideoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setCharacterEncoding("UTF-8");

            Usuario u = (Usuario) request.getSession().getAttribute("usuarioIniciado");

            String download_path = "C:\\Users\\Marce\\Desktop\\descargasDePrueba";
            String url = request.getParameter("url");
            String[] command
                    = {
                        "cmd",};
            Process p;
            try {
                p = Runtime.getRuntime().exec(command);
                new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
                new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
                PrintWriter stdin = new PrintWriter(p.getOutputStream());
                stdin.println("cd \"" + download_path + "\"");
                stdin.println(download_path + "\\youtube-dl " + url);
                stdin.close();
                p.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }

            download_path=download_path.replace("\\", "\\\\");

           
            Video v = new Video();
            v.setNombre("nombre");
            v.setRuta(download_path);
            v.setUsuario(u);
            DAO_Video dv = new DAO_Video();
            dv.create(v);

            response.sendRedirect("usuarioIniciado.jsp");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DescargarVideoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DescargarVideoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
