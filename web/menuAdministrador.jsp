<%-- 
    Document   : menuAdministrador
    Created on : Nov 20, 2018, 7:08:43 PM
    Author     : Marce
--%>

<%@page import="java.util.List"%>
<%@page import="model.Usuario"%>
<%@page import="model.DAO.DAO_Video"%>
<%@page import="model.DAO.DAO_Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu maestro</title>
        <link rel="stylesheet" href="style.css" type="text/css">
    </head>
    <body>

        <%
            DAO_Usuario du = new DAO_Usuario();
            DAO_Video dv = new DAO_Video();

        %>

        <h2>Bienvenido administrador</h2>
        <h3>Estos son los datos que hay disponibles</h3>

        <h2>Videos descargados: <%=dv.getCantVideosDescargados()%></h2>
        <h2>Usuarios registrados: <%=du.getCantUsuariosRegistrados()%> </h2>


        <h3>Estos son los usuarios registrados:</h3>

        <%
            List<Usuario> listaDeUsuarios = du.read();

        %>

        <table border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Cantidad de videos descargados</th>
                    <th>Ver videos</th>
                    <th>Eliminar usuario</th>
                </tr>
            </thead>
            <tbody>

                <%for (Usuario usu : listaDeUsuarios) {%>

                <%
                    /*
                    if (usu.getNombre().equals("11-1") && (usu.getContrasenia().equals("123"))) {
                        listaDeUsuarios.remove(usu);
                    }
                     */
                %>

                <tr>
                    <td><%=usu.getId()%></td>
                    <td><%=usu.getNombre()%></td>
                    <td><%=usu.getVideosDeYoutubeDescargados()%></td>
                    <td>
                        <form id="eliminacion" action="verVideosDeUsuario.do" method="POST">
                            <input type="hidden" name="idDelUsuario" id="idDelUsuario" value="<%=usu.getId()%>"/>
                            <input type="submit" value="Ver videos"/>
                        </form> <!--No olvidar cerrar las forms! -->
                    </td>
                    <td>
                        <form id="eliminacionDeUsuario" action="atencion.jsp" method="POST">
                            <input type="hidden" name="idDelUsuarioABorrar" value="<%=usu.getId()%>">
                            <input type="hidden" name="datos" id="datos" value="<%=usu%>">
                            <input type="submit" value="Eliminar usuario" onclick="confirmacion()">
                        </form> <!--No olvidar cerrar las forms! -->
                    </td>
                </tr>   
                <% }
                %>

            </tbody>
        </table>

        <a href="cerrarSesion.do"><h3 align="center">Cerrar sesión</h3></a>
        <script src="js/JQuery.js"></script>
        <script>
                                function confirmacion() {
                                    $('#eliminacionDeUsuario').submit(function () {
                                        var seleccion = $("#datos").val();
                                        var r = confirm("Seguro que quiere eliminar al usuario " + seleccion + "?");
                                        if (r) {
                                            return true;
                                        } else if (!r) {
                                            return false;
                                        }
                                        // return r; si se apreto cancelar es falso y no pasa nada, si es true se hace el submit
                                    });
                                }
        </script>

    </body>
</html>
