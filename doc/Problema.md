# Práctica obligatoria: Galería de arte

*Rubén García*
*Octavio Jiménez*
*José Luis Notario*
*Dalia Ortiz*

## Problema

En la red de galerías de arte JDWS de Europa llevamos casi tres décadas ofreciendo exposiciones de pintura contemporánea de los artistas más relevantes del panorama nacional en todas partes del continente. La red de galerías fue fundada en el año 1985 y desde entonces se celebran exposiciones mensuales tanto de jóvenes talentos como de pintores consagrados.
Cada galería de arte JWD es muy exclusiva y permite exponer obras muy diversas y de autores reconocidos. Cada galería tiene una razón social y ubicación distintas.

Cada obra solo se expone en una sola galería, siempre es la misma.

La compañía ha contratado a tu empresa para empezar a automatizar tareas que antes hacían en papel y prefieren hacer utilizando software, para ello te solicitan una aplicación que permita al galerista (usuario de la aplicación):
1. Visualizar las obras de arte existentes en su galería. Permite conocer todas las obras que se exponen.
2. Dar de alta una nueva obra de arte en su galería.
3. Modificar los datos de una de las obras de arte expuestas. (El usuario debe indicar qué obra quiere visualizar).
4. Visualizar los datos de una de las obras de arte expuestas (El usuario debe indicar qué obra quiere visualizar).
5. Obtener el precio de venta de una de las obras de arte expuestas (El usuario debe indicar qué obra quiere visualizar).
6. Imprimir una etiqueta para clasificar una de las obras expuestas y dar información adicional a los visitantes de la galería (El usuario debe indicar qué obra quiere visualizar).
7. El sistema permite cargar un fichero con las obras de arte de la galería para inicializar el sistema, esta opción solo se ejecutará una vez.
8. El sistema también permitirá exportar a fichero las obras de arte.

Formato de la información contenida en el fichero:

| ID | Nombre | Autor | Tipo | Precio € | Altura m | Peso T | Piezas | Descripción |
|--|--|--|--|--|--|--|--|--|
|001|Guernica|P. Picasso|Óleo|1000|5|2|5|Cuadro de la guerra civil|
|002|La Vie|P. Picasso|Óleo|200|1|1|1|Óleo|
|003|El Sueño|P. Picasso|Óleo|300|1.3|1|1|Óleo|
|004|Retrato de Dora Maar| P. Picasso|Óleo|400|1|0.8|1|Óleo|
|005|El Piel Roja|U. Checa|Escultura|350|1|0.8|1|Escultura|

Esta tabla representa la información que debe tener el fichero y su estructura, no significa que la información esté cargada en formato tabla sino que:

- Obra 001
  - Nombre: Guernica
  - Autor: P. Picaso...

El alumno deberá pensar en la forma más adecuada de almacenar y recuperar esta información y justificar su decisión.

### Dar de alta una obra de arte

El sistema preguntará al galerista y, almacenará, los siguientes datos de una obra de arte:

- **ID de la obra** (autoincremental)
- **Tipo**
- Nombre
- Autor **(comprobar que exista en la base de datos)**
- Precio en Euros
- Altura en metros
- Peso en toneladas
- Material (solo para las esculturas: acero, cobre, hierro...)
- Técnica (solo para las obras pictóricas: óleo, acuarela, carboncillo...)
- Número de piezas
- Descripción
- Galería

### Visualizar los datos de la obra de arte expuesta

Se mostrará el número de serie y peso de la máquina dada de alta.

```bash
Nombre: El Guernica
Autor: Picasso
Precio(€): 999999
Altura (m): 5
Peso(t): 1
Número de piezas: 5
Descripción: El mural «Guernica» fue adquirido a Picasso por el Estado español en 1937.

```

### Obtener el precio de venta de la obra de arte

El precio de venta se calcula incrementando un 25% el precio original, que es la comisión que se lleva la galería.
Para cada pieza de la obra se cobrará transporte:
- Si pesa más de 1 kg se cobrará 100 €, en caso contrario solo 20€
- Si la altura es mayor de 2 metros se cobrará 100€ por pieza, en caso contrario solo 20
- Si una pieza tiene más de 2 partes, se cobrará un gasto adicional de 10€ por pieza **adicional**.
  
Se mostrará el precio en Dólares (ratio de conversión 1 dólar es 0,99€).
Conceptos particulares del precio:
- Obras de tipo pictórica:
  - Tienen un descuento del 10%
- Obras de tipo escultura:
  - Tienen un descuento del 20%
  - Tienen un sobrecoste de 50€ por gastos de manipulación

```bash
Nombre: El Guernica
Altura (m): 5
Peso(t): 2
Número de piezas: 5
Precio(€): 100
Comisión Galería(€): 25
Importe por peso(€): 20
Importe por altura(€): 100
Importe adicional - Pieza 3 (€): 10
Importe adicional - Pieza 4 (€): 10
Importe adicional - Pieza 5 (€): 10
Precio de venta(€): 275
Descuento (10% óleo €): 27,5
Precio de venta($): 247,5
```

### Imprimir una etiqueta

Se imprimirá (por pantalla) la siguiente etiqueta:

```bash
Nombre: El Guernica
Autor: Picasso
Descripción: El mural «Guernica» fue adquirido a Picasso por el Estado español en 1937.
```

## Entornos de desarrollo:

- Análisis del problema: Requisitos funcionales, no funcionales, Casos de Uso, Diagramas de actividad (25% de la calificación)
- Diseño de la solución: Arquitectura, Diagrama de componentes, Diagrama de Paquetes, Diagrama de clases, Diseño de las pruebas, Prototipo, Diseño de los ficheros de intercambio de datos (qué lenguaje, cómo se define la estructura...) (35% de la calificación)
- Documentación del código y de la solución: Documento markdown con toda la documentación y javadoc para el código generado. (20% de la calificación)
- Especificación del Workstation: Se deberá definir que herramientas se van a utilizar para la realización del proyecto (tanto de construcción, como de análisis, diseño, diagramado, versiones, documentación...) Este apartado deberá permitir a un nuevo desarrollador incorporarse al desarrollo de forma rápida. (20% de la calificación)

## Programación:

- Solución del problema usando java y OO: No cumplir con las normas de codificación, paquetes y nombrado penalizará la calificación del ejercicio.
  - Se deberá usar:
    - Codificar los casos de uso utilizando un diseño basado en MVC y por componentes (20% de la calificación)
    - Gestión de flujos de entrada y salida con ficheros (20% de la calificación)
    - Interfaces de usuario implementadas en AWT, Swing, JavaFX o HTML (20% de la calificación)
    - Acceso a la base de datos para la carga y gestión de la información. (20% de la calificación)
- Pruebas: Definición y ejecución de las pruebas utilizando JUnit (siempre que sea posible) (10% de la calificación)

## Base de datos:

El alumno deberá pensar en la forma más adecuada de almacenar y recuperar esta información y justificar su decisión. (modelo E/R, transformación al relacional e implementación SQL).

Se deberá crear una vista para cada sede: número de obras expuestas y el valor de las obras porque así lo requiere la agencia de seguros...

Se deberán almacenar los siguientes datos para los autores: código, nombre, año_nacimiento, estilo.

## Lenguaje de marcas:

La práctica obligatoria se dividirá en dos partes: una parte básica (de hasta 7 puntos del total) y una parte avanzada (de hasta 3 puntos). Para la parte básica (obligatoria) se pide:

- Crear un documento XML con cada una de las tablas del modelo relacional de la base de datos. 
- Escribir la hoja de estilos para que los documentos XML se muestren en formato tabla cuando se abran con el navegador 
- Validarlo con DTD. 
- Validarlo con Schema. 
- Argumentar cuál de ambas se prefiere utilizar para este caso concreto y por qué (puede no haber una única respuesta). 
- Comprobar que la validación se ha hecho correctamente con una herramienta específica (la que prefieras) para XSD y DTD. 
- Seleccionar todos los precios de las obras con XPath (la consulta dependerá de cómo hayas implementado la tabla del modelo relacional y el XML).

Para la parte avanzada (optativa) se pide: 

- Calcula con un programa en JAVA el valor total de los cuadros de la galería utilizando la selección hecha en XPath en el último apartado anterior.
- Parsea el documento XML con SAX para cargar sus datos en la base de datos. 
- Con la PowerShell de Windows o Linux (mejor Linux) cambia el nombre de todas las etiquetas que se llamen "descripcion" por "description".