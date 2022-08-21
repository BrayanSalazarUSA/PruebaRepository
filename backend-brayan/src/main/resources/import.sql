INSERT INTO authorities(nombre) VALUES('Admin');
INSERT INTO authorities(nombre) VALUES('Soporte');
INSERT INTO authorities(nombre) VALUES('Asesor de ventas');


INSERT INTO usuarios(nombre,apellido, edad, fecha_ingreso, authority_id) VALUES('Brayan', 'Salazar', 22, '2021-12-5', 1);
INSERT INTO usuarios(nombre,apellido, edad, fecha_ingreso, authority_id) VALUES('Jhon', 'Gomez', 30, '2021-12-5', 2);
INSERT INTO usuarios(nombre,apellido, edad, fecha_ingreso, authority_id) VALUES('Anna', 'Rodrigez', 25, '2021-12-5', 3);

INSERT INTO productos(nombre,cantidad, fecha_ingreso, usuario_id) VALUES('Tesla S', 50, '2021-12-5', 1);
INSERT INTO productos(nombre,cantidad, fecha_ingreso, usuario_id) VALUES('Tesla E', 60, '2021-12-5', 2);
INSERT INTO productos(nombre,cantidad, fecha_ingreso, usuario_id) VALUES('Tesla X', 18, '2021-12-5', 3);
INSERT INTO productos(nombre,cantidad, fecha_ingreso, usuario_id) VALUES('Tesla', 75, '2021-12-5', 3);

INSERT INTO productos(nombre,cantidad, fecha_ingreso, usuario_id) VALUES('Audi r8', 50, '2021-12-5', 1);
INSERT INTO productos(nombre,cantidad, fecha_ingreso, usuario_id) VALUES('Audi r9', 60, '2021-12-5', 2);
INSERT INTO productos(nombre,cantidad, fecha_ingreso, usuario_id) VALUES('Audi r7', 18, '2021-12-5', 3);
INSERT INTO productos(nombre,cantidad, fecha_ingreso, usuario_id) VALUES('Audi r6', 75, '2021-12-5', 3);

INSERT INTO productos(nombre,cantidad, fecha_ingreso, usuario_id) VALUES('BMW 505', 50, '2021-12-5', 1);
INSERT INTO productos(nombre,cantidad, fecha_ingreso, usuario_id) VALUES('BMW 504', 60, '2021-12-5', 2);
INSERT INTO productos(nombre,cantidad, fecha_ingreso, usuario_id) VALUES('BMW 503', 18, '2021-12-5', 3);
INSERT INTO productos(nombre,cantidad, fecha_ingreso, usuario_id) VALUES('BMW 506', 75, '2021-12-5', 3);







