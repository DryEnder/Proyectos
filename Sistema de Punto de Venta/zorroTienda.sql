SET NAMES 'UTF8MB4';

DROP Database IF EXISTS tiendazorro;

CREATE DATABASE IF NOT EXISTS tiendazorro DEFAULT CHARACTER SET UTF8MB4;

USE tiendazorro;

-- tiendazorro.almacen definition

CREATE TABLE `almacen` (
                           `idalmacen` int(11) NOT NULL AUTO_INCREMENT,
                           `nombre` varchar(100) NOT NULL,
                           `descripcion` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`idalmacen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- tiendazorro.categoria definition

CREATE TABLE `categoria` (
                             `idcategoria` int(11) NOT NULL AUTO_INCREMENT,
                             `nombre` varchar(50) NOT NULL,
                             `descripcion` varchar(256) DEFAULT NULL,
                             PRIMARY KEY (`idcategoria`),
                             UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- tiendazorro.proveedor definition

CREATE TABLE `proveedor` (
                             `idproveedor` int(11) NOT NULL AUTO_INCREMENT,
                             `nombre` varchar(100) NOT NULL,
                             `rfc` varchar(20) DEFAULT NULL,
                             `email` varchar(50) DEFAULT NULL,
                             PRIMARY KEY (`idproveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- tiendazorro.rol definition

CREATE TABLE `rol` (
                       `idrol` int(11) NOT NULL AUTO_INCREMENT,
                       `nombre` varchar(30) NOT NULL,
                       `descripcion` varchar(100) DEFAULT NULL,
                       PRIMARY KEY (`idrol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- tiendazorro.articulo definition

CREATE TABLE `articulo` (
                            `idarticulo` int(11) NOT NULL AUTO_INCREMENT,
                            `idcategoria` int(11) NOT NULL,
                            `idproveedor` int(11) NOT NULL,
                            `codigo` varchar(50) NOT NULL,
                            `nombre` varchar(100) NOT NULL,
                            `url_foto` varchar(255) NOT NULL,
                            `precio_venta` decimal(11,2) NOT NULL,
                            `descripcion` varchar(256) DEFAULT NULL,
                            PRIMARY KEY (`idarticulo`),
                            UNIQUE KEY `nombre` (`nombre`),
                            KEY `idcategoria` (`idcategoria`),
                            KEY `idproveedor` (`idproveedor`),
                            CONSTRAINT `articulo_ibfk_1` FOREIGN KEY (`idcategoria`) REFERENCES `categoria` (`idcategoria`),
                            CONSTRAINT `articulo_ibfk_2` FOREIGN KEY (`idproveedor`) REFERENCES `proveedor` (`idproveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- tiendazorro.articulo_almacen definition

CREATE TABLE `inventario` (
                              `idarticulo` int(11) NOT NULL,
                              `idalmacen` int(11) NOT NULL,
                              `stock` int(11) NOT NULL DEFAULT 0,
                              PRIMARY KEY (`idarticulo`,`idalmacen`),
                              KEY `idalmacen` (`idalmacen`),
                              CONSTRAINT `articulo_almacen_ibfk_1` FOREIGN KEY (`idarticulo`) REFERENCES `articulo` (`idarticulo`),
                              CONSTRAINT `articulo_almacen_ibfk_2` FOREIGN KEY (`idalmacen`) REFERENCES `almacen` (`idalmacen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- tiendazorro.cliente definition

CREATE TABLE `cliente` (
                           `idcliente` int(11) NOT NULL AUTO_INCREMENT,
                           `num_cliente` char(10) DEFAULT NULL,
                           `nombre` varchar(100) NOT NULL,
                           `telefono` varchar(20) NOT NULL,
                           `email` varchar(50) NOT NULL,
                           PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- tiendazorro.usuario definition

CREATE TABLE `usuario` (
                           `idusuario` int(11) NOT NULL AUTO_INCREMENT,
                           `idrol` int(11) NOT NULL,
                           `nombre` varchar(100) NOT NULL,
                           `email` varchar(50) NOT NULL,
                           `password` varchar(255) NOT NULL,
                           PRIMARY KEY (`idusuario`),
                           KEY `idrol` (`idrol`),
                           CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idrol`) REFERENCES `rol` (`idrol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- tiendazorro.venta definition

CREATE TABLE `venta` (
                         `idventa` INT(11) NOT NULL AUTO_INCREMENT,
                         `idcliente` INT(11) NOT NULL,
                         `idalmacen` INT(11) NOT NULL,
                         `num_comprobante` VARCHAR(15) NOT NULL COLLATE 'utf8mb4_general_ci',
                         `fecha_hora` DATETIME NOT NULL,
                         `total` DECIMAL(11,2) NOT NULL,
                         `completado` TINYINT(4) NOT NULL DEFAULT '0',
                         PRIMARY KEY (`idventa`) USING BTREE,
                         INDEX `idcliente` (`idcliente`) USING BTREE,
                         INDEX `idalmacen` (`idalmacen`) USING BTREE,
                         CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`) ON UPDATE RESTRICT ON DELETE RESTRICT,
                         CONSTRAINT `venta_ibfk_3` FOREIGN KEY (`idalmacen`) REFERENCES `almacen` (`idalmacen`) ON UPDATE RESTRICT ON DELETE RESTRICT
)
    COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;



-- tiendazorro.detalle_venta definition

CREATE TABLE `detalle_venta` (
                                 `iddetalle_venta` int(11) NOT NULL AUTO_INCREMENT,
                                 `idventa` int(11) NOT NULL,
                                 `idarticulo` int(11) NOT NULL,
                                 `cantidad` int(11) NOT NULL,
                                 `precio` decimal(11,2) NOT NULL,
                                 PRIMARY KEY (`iddetalle_venta`),
                                 KEY `idventa` (`idventa`),
                                 KEY `idarticulo` (`idarticulo`),
                                 CONSTRAINT `detalle_venta_ibfk_1` FOREIGN KEY (`idventa`) REFERENCES `venta` (`idventa`) ON DELETE CASCADE,
                                 CONSTRAINT `detalle_venta_ibfk_2` FOREIGN KEY (`idarticulo`) REFERENCES `articulo` (`idarticulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- tiendazorro.ingreso definition

CREATE TABLE `ingreso` (
                           `idingreso` int(11) NOT NULL AUTO_INCREMENT,
                           `idproveedor` int(11) NOT NULL,
                           `idusuario` int(11) NOT NULL,
                           `idalmacen` int(11) NOT NULL,
                           `num_comprobante` varchar(10) NOT NULL,
                           `fecha` datetime NOT NULL,
                           `total` decimal(11,2) NOT NULL,
                           PRIMARY KEY (`idingreso`),
                           KEY `idproveedor` (`idproveedor`),
                           KEY `idusuario` (`idusuario`),
                           KEY `idalmacen` (`idalmacen`),
                           CONSTRAINT `ingreso_ibfk_1` FOREIGN KEY (`idproveedor`) REFERENCES `proveedor` (`idproveedor`),
                           CONSTRAINT `ingreso_ibfk_2` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`),
                           CONSTRAINT `ingreso_ibfk_3` FOREIGN KEY (`idalmacen`) REFERENCES `almacen` (`idalmacen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- tiendazorro.detalle_ingreso definition

CREATE TABLE `detalle_ingreso` (
                                   `iddetalle_ingreso` int(11) NOT NULL AUTO_INCREMENT,
                                   `idingreso` int(11) NOT NULL,
                                   `idarticulo` int(11) NOT NULL,
                                   `cantidad` int(11) NOT NULL,
                                   `precio` decimal(11,2) NOT NULL,
                                   PRIMARY KEY (`iddetalle_ingreso`),
                                   KEY `idingreso` (`idingreso`),
                                   KEY `idarticulo` (`idarticulo`),
                                   CONSTRAINT `detalle_ingreso_ibfk_1` FOREIGN KEY (`idingreso`) REFERENCES `ingreso` (`idingreso`) ON DELETE CASCADE,
                                   CONSTRAINT `detalle_ingreso_ibfk_2` FOREIGN KEY (`idarticulo`) REFERENCES `articulo` (`idarticulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO rol (nombre, descripcion) VALUES
            ('ADMIN', 'El más perrón aquí'),
            ('CAJERO', 'Esclavo con sueldo');

INSERT INTO `usuario` (`idusuario`, `idrol`, `nombre`, `email`, `password`) VALUES
            (4, 1, 'Admin chido', 'admin@tienda.com', '$2a$10$M1nRlHuYCg6Yie6YV5kkqOS7amYi7637DmG.q8zGqy/1iRfhWBww.'),
            (5, 2, 'Cajero no tan chido', 'empleado@tienda.com', '$2a$10$xjc0f9Wwo/99YInO6q4GYORZQgq1h5YRGY1zC.goimSbrWUeIq9SK');

INSERT INTO cliente (num_cliente, nombre, telefono, email) VALUES
        ('C001', 'Bienvenido', '5526263960', 'yo'),
        ('C002', 'Jorge Angel Jimenez', '5534771492', 'jorgeangel901@gmail.com'),
        ('C003', 'Confi', '5528384178', 'luisgemelo2003@gmail.com'),
        ('C004','Josue','5538013191', 'nemesisghoul246@gmail.com');

INSERT INTO almacen (nombre, descripcion) VALUES
    ('Almacén Central', 'Almacén principal de la tienda');

INSERT INTO tiendazorro.almacen (nombre,descripcion) VALUES
    ('Principal','Se almacena todo');

INSERT INTO tiendazorro.categoria (nombre,descripcion) VALUES
        ('Tecnologia','Productos de tecnologia'),
        ('Abarrotes','Alimentos, cereales y bebidas'),
        ('Cosmeticos','Productos de belleza e higiene personal'),
        ('Botana','Frituras de harina, trigo, semillas, entre otros');

INSERT INTO tiendazorro.proveedor (nombre,rfc,email) VALUES
        ('Electronica Steren','ES-0015','steren@gmail.com'),
        ('Grupo Martinez Leal','GML-0020','martinleal@gmail.com'),
        ('Arabela','A-0025','arabe25@gmail.com'),
        ('Botanas Wipo','BW-0030','josue.hernandez.rosas246@gmail.com');


INSERT INTO tiendazorro.articulo (idcategoria,idproveedor,codigo,nombre,url_foto,precio_venta,descripcion) VALUES
        (1,1,'MMG84','Motorola Moto G84','/imagenes/motog84.jpg',3500.00,'Gama media con buena cámara y batería duradera'),
        (1,1,'AIQL','Audífonos inalámbricos QCY Lite','/imagenes/audifonos.jpg',400.00,'Con Bluetooth y buena calidad de sonido'),
        (1,1,'LHPPB','Laptop HP ProBoock','/imagenes/laptop.jpg',9500.00,'Económica, ideal para tareas escolares u oficina'),
        (1,1,'TMS11','Teclado Mecanico Scribe','/imagenes/teclado.jpg',1000.00,'Teclado portatil para dispositivos'),
        (1,1,'CP111','Cargador portátil','/imagenes/cargaportal.jpg',300.00,'Para recargar dispositivos móviles'),
        (2,2,'AVV1','Arroz Verde Valle 1kg','/imagenes/arroz.jpg',40.00,NULL),
        (2,2,'FN22','Frijol Negro 430g','/imagenes/frijol.jpg',35.00,NULL),
        (2,2,'AC22','Aceite Capullo 900ml','/imagenes/aceite.jpg',100.00,NULL),
        (2,2,'CSN2','Cafe soluble Nescafé 225g','/imagenes/cafe.jpg',95.00,NULL),
        (2,2,'A222','Azucar 1kg','/imagenes/azucar.jpg',35.00,NULL);

INSERT INTO tiendazorro.articulo (idcategoria,idproveedor,codigo,nombre,url_foto,precio_venta,descripcion) VALUES
        (3,3,'BMM3','Base de maquillaje Maybelline','/imagenes/base.jpg',150.00,NULL),
        (3,3,'RLS33','Rimel Lash Sensational','/imagenes/rimel.jpg',185.00,NULL),
        (3,3,'PSN33','Paleta de sombras Nude','/imagenes/paletasom.jpg',200.00,NULL),
        (3,3,'LLMNYX','Labial líquido mate NYX','/imagenes/labial.jpg',220.00,NULL),
        (3,3,'DLNLO','Delineador líquido negro L’Oreal','/imagenes/delineador.jpg',165.00,NULL),
        (4,4,'PSC4','Papas Sabritas Clásicas 170g','/imagenes/papas.jpg',55.00,NULL),
        (4,4,'CJ44','Cacahuates Japoneses 200g','/imagenes/cacahua.jpg',22.00,NULL),
        (4,4,'CDM44','Churritos de maiz','/imagenes/churritos.jpg',100.00,NULL),
        (4,4,'GOC44','Galletas Oreo Clasicas 270g','/imagenes/oreo.jpg',30.00,NULL),
        (4,4,'PA2M44','Palomitas ACT II Mantequilla','/imagenes/palomas.jpg',38.00,NULL);


INSERT INTO tiendazorro.inventario (idarticulo,idalmacen,stock) VALUES
        (1,1,5),
        (2,1,10),
        (3,1,15),
        (4,1,20),
        (5,1,25),
        (6,1,30),
        (7,1,35),
        (8,1,40),
        (9,1,12),
        (10,1,14);
INSERT INTO tiendazorro.inventario (idarticulo,idalmacen,stock) VALUES
        (11,1,17),
        (12,1,26),
        (13,1,4),
        (14,1,22),
        (15,1,19),
        (16,1,33),
        (17,1,41),
        (18,1,13),
        (19,1,10),
        (20,1,8);


