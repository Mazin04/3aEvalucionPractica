## Diseño de las pruebas

Para los **métodos "getPrecioFinalPic" y "getPrecioFinalEsc"**:

Constantes para estos métodos:

|Constante|Valor|
|--|--|
|Comisión|+25%|

Variables que se tienen que tener en cuenta: precio inicial de la obra, peso, altura, número de piezas y tipo de obra.
Valores:

|Variable|Rango|Precio|
|--|--|--|
|Precio inicial|-|(0, infinito)|
|Peso|[0, 1]|+20|
|Peso|(1, infinito)|+100|
|Altura|[0, 2]|+20|
|Altura|(2, infinito)|+100/pieza|
|Num piezas|[0, 2]|0|
|Num piezas|(2, infinito)|+10/pieza|
|Tipo Pintura|-|-10%|
|Tipo Escultura|-|-20%|
|Tipo Escultura|-|+50|

Según estos datos, se pueden tener los siguientes casos de prueba para estos métodos (los valores límite y más representativos), donde al precio inicial de la obra se le da valor 100 en todos los casos:

|CP|Variables-Entradas| | | | |Salidas|
|--|--|--|--|--|--|--|
| |Precio inicial|Peso|Altura|Num Piezas|Tipo|
|CP1|100|1|1|2|Pintura|148.5|
|CP2|100|2|1|1|Pintura|220.5|
|CP3|100|1|3|1|Pintura|220.5|
|CP4|100|1|2|1|Pintura|148.5|
|CP5|100|1|1|1|Pintura|148.5|
|CP6|100|1|1|4|Pintura|166.5|
|CP7|100|1|1|0|Escultura|182|
|CP8|100|1|2|1|Escultura|246|
|CP9|100|1|3|1|Escultura|246|
|CP10|100|1|2|1|Escultura|182|
|CP11|100|1|1|1|Escultura|182|
|CP12|100|1|1|4|Escultura|198|

# Pruebas

Implementadas con JUnit en la clase ObraTest. Las 12 pruebas realizadas a los métodos getPrecioFinalPic y getPrecioFinalEsc han sido correctas.