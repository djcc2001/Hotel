# Hotel Alura
"Hotel Alura" es una aplicación en Java 11 que utiliza JDBC y la base de datos MySQL. Este proyecto es parte del reto de Alura y Oracle y tiene como objetivo registrar huéspedes y reservas, además se agrego un modulo donde se podrá registrar usuarios para que puedan acceder al sistema.
# Instrucciones de Instalación
1. Descargue el zip o abra con gitHub para escritorio.
![image](https://user-images.githubusercontent.com/83192564/233817205-9c6d837b-6598-4872-a3ef-6c288e54855a.png)
2. Ejecute el archivo hotel_alura.sql que se encuentra en la carpeta sql, para poder iniciar sesion en el sistema el usuario por defecto tenemos nombre de usuario "admin" y contraseña "adm12345".
3. Abra la carpeta seleccionando la tercera opcion "Open Projects From File System" en eclipse.
![image](https://user-images.githubusercontent.com/83192564/233817248-b9f82514-f9c3-4068-8660-2a23b3d0c576.png)
4.Cargue las librerias de JCalendar.
5. Ejecute el sistema desde el paquete main clase Principal.java.
# Estructura del Proyecto
El proyecto sigue la estructura estándar de una aplicación MVC. Los diferentes componentes se organizan en paquetes separados como sigue:
Modelo: El paquete "model" contiene las clases de modelo que representan los objetos del mundo real que se utilizan en la aplicación, como Huespedes, Reservas y Usuarios.
DAO: Donde nos encargamos de realizar todas las operaciones con la base de datos.
Controlador: El paquete contiene las clases que se encargan de la lógica de negocio de la aplicación, es decir, la implementación de las operaciones de registro y consulta de huéspedes, reservas, asi como el iniciar sesion y agregar usuarios.
Vista: El paquete "view" contiene las clases que se encargan de la presentación de la información al usuario, utilizando interfaces de usuario gráficas (GUI) o vistas HTML.
# Uso de la aplicación
La aplicación "Hotel Alura" permite registrar huéspedes, sus reservas y usuarios que podran acceder a la aplicación. Los usuarios pueden realizar las siguientes acciones:
  Registrar un nuevo huésped.
  Registrar una nueva reserva para un huésped existente.
  Consultar la información de un huésped existente.
  Consultar las reservas de un huésped existente.
  Buscar reservas por su numero.
  Buscar huespedes por su apellido.
  Agregar usuarios para que puedan acceder a la aplicación.
