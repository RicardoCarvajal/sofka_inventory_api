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

![ambiente]([https://i.imgur.com/VD7nJDi.png](https://viewer.diagrams.net/?tags=%7B%7D&highlight=0000ff&edit=_blank&layers=1&nav=1&title=sofka_inventory_environment.drawio#Uhttps%3A%2F%2Fdrive.google.com%2Fuc%3Fid%3D1M-XkW9-_1miFfePK96hZXP5HFG670nKt%26export%3Ddownload)https://viewer.diagrams.net/?tags=%7B%7D&highlight=0000ff&edit=_blank&layers=1&nav=1&title=sofka_inventory_environment.drawio#Uhttps%3A%2F%2Fdrive.google.com%2Fuc%3Fid%3D1M-XkW9-_1miFfePK96hZXP5HFG670nKt%26export%3Ddownload)

# Configuraciones env

```
PORTAPPSOFKAINVENTORY=8080

RABBITURL=amqp://guest:guest@<IP>:5672

HOSTDB=<IP>
PORTDB=27017
DBNAMEINVENTORY=sofkaInventario

```

# Diagrama de flujo de la aplicación

![flujo]([https://i.imgur.com/VD7nJDi.png](https://viewer.diagrams.net/?tags=%7B%7D&highlight=0000ff&edit=_blank&layers=1&nav=1&title=sofka_inventory_use_cases.drawio#Uhttps%3A%2F%2Fdrive.google.com%2Fuc%3Fid%3D17lQH3qLx26bKYb0P-Sjw7tMik-Trstjb%26export%3Ddownload)https://viewer.diagrams.net/?tags=%7B%7D&highlight=0000ff&edit=_blank&layers=1&nav=1&title=sofka_inventory_use_cases.drawio#Uhttps%3A%2F%2Fdrive.google.com%2Fuc%3Fid%3D17lQH3qLx26bKYb0P-Sjw7tMik-Trstjb%26export%3Ddownload)

# Base de datos

![bd]([https://i.imgur.com/VD7nJDi.png](https://viewer.diagrams.net/?tags=%7B%7D&highlight=0000ff&edit=_blank&layers=1&nav=1&title=sofka_inventory_data_bases.drawio#Uhttps%3A%2F%2Fdrive.google.com%2Fuc%3Fid%3D12h546jJLPn9_RMEG6BsZi2aB_6W_8k0g%26export%3Ddownload)https://viewer.diagrams.net/?tags=%7B%7D&highlight=0000ff&edit=_blank&layers=1&nav=1&title=sofka_inventory_data_bases.drawio#Uhttps%3A%2F%2Fdrive.google.com%2Fuc%3Fid%3D12h546jJLPn9_RMEG6BsZi2aB_6W_8k0g%26export%3Ddownload)

