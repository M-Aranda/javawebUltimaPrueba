<%-- 
    Document   : usuarioIniciado
    Created on : Nov 8, 2018, 12:26:21 AM
    Author     : Marce
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Video"%>
<%@page import="java.util.List"%>
<%@page import="model.DAO.DAO_Video"%>
<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina de usuario</title>
        <link rel="stylesheet" href="style.css" type="text/css">
    </head>
    <body>
        <%
            HttpSession ses = request.getSession();
            Usuario u = null;
            if (ses.getAttribute("usuarioIniciado") != null) {
                u = (Usuario) ses.getAttribute("usuarioIniciado");
            }%>

        <h1>Hola <%= u.getNombre()%></h1>
        <br>
        <h3>Por favor ingrese la url del video de Youtube a descargar</h3>   
        <form action="descargarVideo.do" method="POST">
            <input type="text" name="url" id="url" size="50" required>
            <br>
            <input type="submit" value="Descargar">
        </form>
        <br>

        <!-- 
        Estoy tratando de que tambien se puedan subir videos

        <h3>También es posible subir sus propios videos (sólo formato mp4)</h3>
        <br>    
        <form action="" method="post" enctype="multipart/form-data">
            <input type="text" name="urlVideoASubir" id="urlVideoASubir" required />
            <br>
            <input type="file" name="file" accept="video/mp4,video/x-m4v,video/*"  />
            <br>
            <input type="submit" value="Subir" />
        </form>
        <br>
        -->

        <%
            if (u.getVideosDeYoutubeDescargados() == 0) {%>
        <h3>Usted no tiene ningún video descargado.</3>
            <%  } else if (u.getVideosDeYoutubeDescargados() > 0) {%>
            <h3>Videos  descargados:</3>
                <%  }
                %>
                <h3>Cantidad de videos descargados: <%=u.getVideosDeYoutubeDescargados()%> </3>
                    <br>
                    <%
                        DAO_Video dv = new DAO_Video();
                        List<Video> videosBajadosPorElusuario = dv.readVideosBajadosPorElUsuario(u.getId());

                        for (Video v : videosBajadosPorElusuario) {%>

                    <%
                        //algoritmo para obtener el ombre sin lo que esta despues del ultimo -
                        String nombreVideo = v.getNombre();
                        List<String> partes = new ArrayList<>();
                        for (String str : nombreVideo.split("-")) {
                            partes.add(str);
                        }
                        int tamanioList = partes.size();
                        partes.remove(tamanioList - 1);
                        nombreVideo = "";
                        for (String s : partes) {
                            nombreVideo = partes + s;
                        }


                    %>


                    <h4 align="center"><%=nombreVideo%></h4>
                    <video class="centrarVideo" width="320" height="240" controls>
                        <source src="videosAlmacenados/<%=v.getNombre()%>.mp4" type="video/mp4"><!--nombre.mp4-->
                    </video>
                    <br>
                    <%}
                    %>


                    <br>
                    <a href="cerrarSesion.do"><h3 align="center">Cerrar sesión</h3></a>

                    </body>
                    </html>
