CREATE DATABASE prueba4JavaWebMAAT;

USE prueba4JavaWebMAAT;

CREATE TABLE usuario(
id INT AUTO_INCREMENT,
nombre VARCHAR (30) UNIQUE,
contrasenia VARCHAR (50),
videosSubidos INT,
PRIMARY KEY(id)
);


CREATE TABLE video(
id INT AUTO_INCREMENT,
nombre VARCHAR (50),
ruta VARCHAR(100),
usuario_fk INT,
FOREIGN KEY (usuario_fk) REFERENCES usuario(id),
PRIMARY KEY(id)
);

-- SELECT * FROM usuario;



-- DROP DATABASE prueba4JavaWebMAAT;


