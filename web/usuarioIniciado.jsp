<%-- 
    Document   : usuarioIniciado
    Created on : Nov 8, 2018, 12:26:21 AM
    Author     : Marce
--%>

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



    </body>
</html>
