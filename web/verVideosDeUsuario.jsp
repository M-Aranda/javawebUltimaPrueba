<%-- 
    Document   : verVideosDeUsuario
    Created on : Nov 20, 2018, 9:03:35 PM
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
        <title>Ver videos de usuario</title>
        <link rel="stylesheet" href="style.css" type="text/css">
    </head>
    <body>
        <%
            HttpSession ses = request.getSession();
            Usuario usu = null;
            if (ses.getAttribute("usu") != null) {
                usu = (Usuario) ses.getAttribute("usu");
            }%>




        <%
                if (usu.getVideosDeYoutubeDescargados() == 0) {%>
        <h1><%= usu.getNombre()%> aún no ha descargado ningún video</h1>

        <% } else if (usu.getVideosDeYoutubeDescargados() > 0) {%>
        <h1>Estos son los videos que <%= usu.getNombre()%> ha descargado</h1>
        <%} %>

        <br>
        <%
            DAO_Video dv = new DAO_Video();
            List<Video> videosBajadosPorElusuario = dv.readVideosBajadosPorElUsuario(usu.getId());

            for (Video v : videosBajadosPorElusuario) {%>
        <h4 align="center"><%=v.getNombre()%></h4>
        <video class="centrarVideo" width="320" height="240" controls>
            <source src="videosAlmacenados/<%=v.getNombre()%>.mp4" type="video/mp4"><!--nombre.mp4-->
        </video>

        <div style="text-align:center">
            <form id="elim" action="borrarVideo.do"  method="POST">
                <input type="hidden" name="idVideoABorrar" value="<%=v.getId()%>">
                <input type="hidden" name="datos" id="datos" value="<%=v%>">
                <input type="submit" value="Eliminar" onclick="confirmacion()">
            </form>
        </div>

        <br>
        <%}
        %>

        <%
            ses.removeAttribute("usuarioSeleccionado");
        %>

        <br>
        <a href="menuAdministrador.jsp"><h3 align="center">Volver atrás</h3></a>
        <br>
        <a href="cerrarSesion.do"><h3 align="center">Cerrar sesión</h3></a>



        <script src="js/JQuery.js"></script>
        <script>
                    function confirmacion() {
                        $('#elim').submit(function () {
                            var seleccion = $("#datos").val();
                            var r = confirm("Seguro que quiere eliminar el video " + seleccion + "?");
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
