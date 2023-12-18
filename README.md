![Intelix](https://i.imgur.com/VD7nJDi.png)

# Practica para capacitacion de Sofka
### API REST de inventario

 ![Static Badge](https://img.shields.io/badge/0.0.1-version-%2300bab4) ![Static Badge](https://img.shields.io/badge/0.0.1-release-%2300bab4)

## Crear un API REST que tenga las siguientes caracteristicas:

Servicio completo de gestión de productos en el inventario, incluyendo la creación, búsqueda, edición y eliminación (CRUD).

* Endpoint para solicitar el inventario paginado.
* Endpoint para registrar inventario por unidad.
* Endpoint para registrar inventario por lotes.
* Endpoint para registrar una venta al detal.
* Endpoint para registrar una venta al por mayor. 

# Configuraciones env

```
PORTAPPSOFKAINVENTORY=8080

RABBITURL=amqp://guest:guest@<IP>:5672

HOSTDB=<IP>
PORTDB=27017
DBNAMEINVENTORY=sofkaInventario

```
