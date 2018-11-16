package controller;

import java.io.File;
import java.io.FileFilter;
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

            String download_path = "C:\\Users\\Marce\\Desktop\\ultimaPruebaJavaWeb\\web\\videos";
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

            File f = lastFileModified(download_path);

            download_path = download_path.replace("\\", "\\\\"); //hay que duplicar el numero de backslash para que cambien

            Video v = new Video();

            String nombreSinExtension = f.getName().replaceFirst("[.][^.]+$", "");

            v.setNombre(nombreSinExtension);
            // ruta de videos almacenados era otra, era download_path
            v.setRuta("C:\\Users\\Marce\\Desktop\\ultimaPruebaJavaWeb\\web\\videosAlmacenados\\"
                    + "\\" + f.getName());
            v.setUsuario(u);
            DAO_Video dv = new DAO_Video();

            dv.create(v);

            //cambio los archivos de directorio porque hay un problema para identificar el archivo mas reciente
            //f.renameTo(new File("C:\\Users\\Marce\\Desktop\\ultimaPruebaJavaWeb\\web\\videosAlmacenados\\"+f.getName()));
            //Usar path para conservar la extension del archivo 
            Path temp = Files.move(Paths.get("C:\\Users\\Marce\\Desktop\\ultimaPruebaJavaWeb\\web\\videos\\"
                    + "\\" + f.getName()),
                    Paths.get(v.getRuta()));

            response.sendRedirect("usuarioIniciado.jsp");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DescargarVideoServlet.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(DescargarVideoServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
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

    public static File lastFileModified(String dir) {
        File fl = new File(dir);
        File[] files = fl.listFiles(new FileFilter() {
            public boolean accept(File file) {
                //return file.isFile();
                return file.getName().toLowerCase().endsWith(".mp4");
            }

        });
        long lastMod = Long.MIN_VALUE;
        File choice = null;
        for (File file : files) {
            if (file.lastModified() > lastMod) {
                choice = file;
                lastMod = file.lastModified();
            }
        }
        return choice;
    }

}
