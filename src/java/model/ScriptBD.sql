CREATE DATABASE prueba4JavaWebMAAT;

USE prueba4JavaWebMAAT;

CREATE TABLE usuario(
id INT AUTO_INCREMENT,
nombre VARCHAR (30) UNIQUE,
contrasenia VARCHAR (50),
videosDeYoutubeDescargados INT,
PRIMARY KEY(id)
);


CREATE TABLE video(
id INT AUTO_INCREMENT,
nombre VARCHAR (100),
ruta VARCHAR(500),
usuario_fk INT,
FOREIGN KEY (usuario_fk) REFERENCES usuario(id),
PRIMARY KEY(id)
);

INSERT INTO usuario VALUES (NULL, '11-1', '123',0);
INSERT INTO usuario VALUES (NULL, 'Hola', 'jaja',0);
INSERT INTO usuario VALUES (NULL, 'Dante', 'se',0);
/*
UPDATE usuario SET nombre='weee', contrasenia='qwewe', 
 videosDeYoutubeDescargados=3 WHERE id=1
 */

-- SELECT usuario.id, usuario.nombre, usuario.contrasenia, usuario.videosDeYoutubeDescargados FROM usuario, video WHERE video.usuario_fk=usuario.id AND video.id=1;

-- ALTER TABLE video MODIFY nombre VARCHAR(100);
-- SELECT * FROM usuario;
-- SELECT * FROM video;

-- DROP DATABASE prueba4JavaWebMAAT;


