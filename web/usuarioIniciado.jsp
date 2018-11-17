<%-- 
    Document   : usuarioIniciado
    Created on : Nov 8, 2018, 12:26:21 AM
    Author     : Marce
--%>

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

        <h1>Bienvenido <%= u.getNombre()%></h1>
        <br>
        <h3>Por favor ingrese la url del video de Youtube a revisar</h3>
        <br>      
        <form action="descargarVideo.do" method="POST">
            <input type="text" name="url" id="url" required>
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

        <h3>Videos a revisar:</3>
            <br>
            <h3>Descargados de Youtube: <%=u.getVideosDeYoutubeDescargados()%> </3>
                <br>
                <%
                    DAO_Video dv = new DAO_Video();
                    List<Video> videosBajadosPorElusuario = dv.readVideosBajadosPorElUsuario(u.getId());

                    for (Video v : videosBajadosPorElusuario) {%>
                <h4 align="center"><%=v.getNombre()%></h4>
                <video class="centrarVideo" width="320" height="240" controls>
                    <source src="videosAlmacenados/<%=v.getNombre()%>.mp4" type="video/mp4"><!--nombre.mp4-->
                </video>
                <br>
                <%}
                %>


                <br>
                <a href="cerrarSesion.do"><h3 align="right">Cerrar sesion</h3></a>


                </body>
                </html>
