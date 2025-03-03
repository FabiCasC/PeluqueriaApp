-- tabla usuario
CREATE TABLE Usuario (
    id_usuario INT IDENTITY(1,1) PRIMARY KEY, -- Campo autoincremental como ID
    username VARCHAR(50) NOT NULL UNIQUE,     -- El username debe ser único
    contraseña VARCHAR(255) NOT NULL,         -- Almacenamos la contraseña en formato encriptado
    rol VARCHAR(20) CHECK (rol IN ('dueño', 'empleado')) -- El rol debe ser 'dueño' o 'empleado'
);

CREATE TABLE Empleado (
    id_empleado INT IDENTITY(1,1) PRIMARY KEY,
    nom_emp VARCHAR(50) NOT NULL,
    ape_emp VARCHAR(50) NOT NULL,
    dni_emp VARCHAR(20) NOT NULL,
    tel_emp VARCHAR(20) NOT NULL,
    cor_emp VARCHAR(100) NOT NULL,
    username VARCHAR(50) NULL  -- Aquí se almacenará el username generado por el trigger
);
DROP TRIGGER trg_InsertUsuario

CREATE TRIGGER trg_InsertUsuario
ON Empleado
AFTER INSERT
AS
BEGIN
    DECLARE 
        @idEmpleado INT, 
        @nombre VARCHAR(30), 
        @apellido VARCHAR(30), 
        @username CHAR(6), 
        @password CHAR(6);  -- La contraseña será igual al username

    -- Se asume que se inserta un solo registro a la vez.
    SELECT 
        @idEmpleado = id_empleado, 
        @nombre = nom_emp, 
        @apellido = ape_emp 
    FROM inserted;

    -- Generar username basado en las 3 primeras letras del nombre y apellido.
    SET @username = LEFT(@nombre, 3) + LEFT(@apellido, 3);
    
    -- Asegurar que el username sea único en la tabla Usuario.
    WHILE EXISTS (SELECT 1 FROM Usuario WHERE username = @username)
    BEGIN
        -- Si ya existe, se genera uno alternativo combinando 2 letras de cada campo y dos dígitos aleatorios.
        SET @username = LEFT(@nombre, 2) + LEFT(@apellido, 2) + CAST((RAND() * 90 + 10) AS INT);
    END

    -- Hacer que la contraseña sea igual al username
    SET @password = @username;

    -- Insertar en la tabla Usuario.
    INSERT INTO Usuario (username, contraseña, rol)
    VALUES (@username, @password, 'empleado');

    -- Actualizar el registro en Empleado para enlazar el username generado.
    UPDATE Empleado
    SET username = @username
    WHERE id_empleado = @idEmpleado;
END;




-- Relación entre Empleado y Usuario:
-- Cada empleado tendrá un usuario asociado.
-- La columna 'username' en Empleado es una clave foránea que referencia a 'username' en Usuario.
ALTER TABLE Empleado
ADD CONSTRAINT FK_Empleado_Usuario FOREIGN KEY (username)
REFERENCES Usuario(username);

select * from empleado
select * from Usuario

DELETE FROM Usuario;

CREATE TRIGGER trg_DeleteUsuario
ON Empleado
AFTER DELETE
AS
BEGIN
    DELETE FROM Usuario 
    WHERE username IN (SELECT username FROM deleted);
END;

-- tabla citas y clientes
CREATE TABLE Clientes (
    id_cli VARCHAR(10) PRIMARY KEY,  -- Código único generado con TRIGGER
    nom_cli VARCHAR(50) NOT NULL,
    ape_cli VARCHAR(50) NOT NULL,
    dni_cli VARCHAR(20) UNIQUE NOT NULL,
    tel_cli VARCHAR(20) NOT NULL
);
DROP TRIGGER trg_GenerarIDCliente
CREATE TRIGGER trg_GenerarIDCliente
ON Clientes
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @nuevo_id VARCHAR(10);
    
    -- Buscar el último código insertado
    SELECT @nuevo_id = MAX(id_clientes) FROM Clientes;
    
    -- Si no hay clientes, comenzamos desde CLI0001
    IF @nuevo_id IS NULL
        SET @nuevo_id = 'CLI0001';
    ELSE
        -- Extraer el número y sumarle 1
        SET @nuevo_id = 'CLI' + RIGHT('000' + CAST(CAST(RIGHT(@nuevo_id, 4) AS INT) + 1 AS VARCHAR), 4);

    -- Insertar el nuevo cliente con el ID generado
    INSERT INTO Clientes (id_clientes, nom_cli, ape_cli, dni_cli, tel_cli)
    SELECT @nuevo_id, nom_cli, ape_cli, dni_cli, tel_cli
    FROM inserted;
END;


select * from clientes

-- Eliminar la tabla si ya existe
IF OBJECT_ID('Cita', 'U') IS NOT NULL
    DROP TABLE Cita;
GO

-- Crear la tabla cita

DROP TABLE IF EXISTS Cita;

CREATE TABLE Cita (
    id_cita INT IDENTITY(1,1) PRIMARY KEY,
    id_clientes VARCHAR(10) NOT NULL,
    id_emp INT NOT NULL,  -- Clave foránea para conectar con Empleado
    nom_emp VARCHAR(50) NOT NULL,  -- Guardamos el nombre del empleado también
    servicio VARCHAR(100) NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    FOREIGN KEY (id_clientes) REFERENCES Clientes(id_clientes),
    FOREIGN KEY (id_emp) REFERENCES Empleado(id_empleado)  -- Conectamos con la clave primaria real
);


GO

-- Duplicado
CREATE TRIGGER trg_PrevenirDuplicados
ON Cita
INSTEAD OF INSERT
AS
BEGIN
    IF EXISTS (
        SELECT 1 
        FROM Cita c
        JOIN inserted i 
        ON c.nom_emp = i.nom_emp 
        AND c.fecha = i.fecha 
        AND c.hora = i.hora
    )
    BEGIN
        -- Lanzar un error si la cita ya existe
        THROW 50001, 'El empleado ya tiene una cita programada en esa fecha y hora.', 1;
        RETURN;
    END

    -- Si no hay duplicados, insertar los datos
    INSERT INTO Cita (id_clientes, nom_emp, servicio, fecha, hora)
    SELECT id_clientes, nom_emp, servicio, fecha, hora FROM inserted;
END;

SELECT id_empleado FROM Empleado WHERE nom_emp = 'Fabiana'

SELECT COLUMN_NAME, DATA_TYPE 
FROM INFORMATION_SCHEMA.COLUMNS 
WHERE TABLE_NAME = 'Cita';
ALTER TABLE Cita ALTER COLUMN hora TIME;
ALTER TABLE Cita
ALTER COLUMN hora VARCHAR(10); -- O usa un tamaño mayor si lo necesitas

SELECT * FROM Usuario