

<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Indice</title>

        <link rel="stylesheet" href="style.css" type="text/css">

    </head>
    <body>
        <h1>Bienvenido al sistema de registro de videos de youtube flageados por la institucion</h1>
        <br>
        <br>
        <h3>Por favor, identifiquese</h3>
        <form action="iniciarSesion.do" method="POST" >
            <input type="text" name="nombre" id="nombre" placeholder="Nombre: " required>
            <br>
            <input type="password" name="contrasenia" id="contrasenia" placeholder="Contrasenia: " required>
            <br>
            <input type="submit" value="Iniciar">       
        </form>

        <%
            HttpSession ses = request.getSession();

            if (ses.getAttribute("msg") != null) {
                String msg = (String) ses.getAttribute("msg");
        %> <h3><%= msg%></h3>  <%
            }
            ses.removeAttribute("msg");

            if (ses.getAttribute("usuarioIniciado") != null) {
            response.sendRedirect("usuarioIniciado.jsp");
            }

        %>   




        <br>
        <br>
        <h3>O bien, cree una cuenta</h3>
        <br>
        <form action="crearUsuario.do" method="POST" >
            <input type="text" name="nombre" id="nombre" placeholder="Nombre: " required>
            <br>
            <input type="password" name="contrasenia" id="contrasenia" placeholder="Contrasenia: " required>
            <br>
            <input type="submit" value="Crear">       
        </form>
    </body>
</html>
