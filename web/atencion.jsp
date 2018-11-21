<%-- 
    Document   : atencion
    Created on : Nov 21, 2018, 2:03:13 PM
    Author     : Marce
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adevertencia</title>
        <link rel="stylesheet" href="style.css" type="text/css">
    </head>
    <body>
        <div id="cambiarColor" align="right">
            <input id="btnRandom" type="button" value="Quiero otro color de fondo (aleatorio)" onclick="setColor()">  
        </div>

        <%
            int idUsuario = Integer.parseInt(request.getParameter("idDelUsuarioABorrar"));
        %>

        <h1>Borrar un usuario también borra sus videos.</h1>
        <br>
        <h2>Esta acción es irreversible. Desea proceder?</h2>
        <form action="eliminarUsuario.do" method="POST">
            <input type="hidden" name="usuarioABorrar" value="<%=idUsuario%>">
            <input type="submit" value="Sí">
        </form>
        <br>
        <form action="menuAdministrador.jsp" method="POST">
            <input type="submit" value="No">
        </form>
        <br>
        <br>
        <script src="js/RandomColor.js"></script>
        <br>


    </body>
</html>
