# Análisis

## Requisitos funcionales:

- Visualizar todas las obras que se encuentran expuestas.
- Dar de alta una nueva obra de arte.
- Visualizar los datos de una de las obras de arte que están en exposición.
- Modificar los datos de una de las obras de arte que se encuentren expuestas.
- Obtener el precio por el que se vende una obra de arte.
- Imprimir una etiqueta que recopile los datos de una de las obras de arte.
- Cargar un fichero con las obras de arte de la galería al inicializar la aplicación.
- Exportar a ficheros las obras de arte.

## Requisitos no funcionales:

- Atributos de cada obra de arte:
  - ID
  - Tipo 
  - Nombre
  - Autor
  - Precio en Euros
  - Altura en metros
  - Peso en toneladas
  - Material (solo para las esculturas)
  - Técnica (solo para las obras de tipo pictórico)
  - Número de piezas
  - Descripción
  - Galería
- Formato de visualización
- Formato de la etiqueta que se imprimirá por pantalla:
  - Nombre
  - Autor
  - Descripción
- Las comisiones y valores añadidos a la obra:
  - Comisión de la galería:
    - El precio de la venta se incrementará un 25% al precio original.
  - Transporte:
    - Si la obra pesa más de 1 kg se cobrará 100 €.
    - Si la obra pesa menos de 1 kg se cobrará 20 €.
    - Si la altura de la obra es mayor de 2 metros se cobrará 100 € por cada pieza.
    - Si la altura de la obra es menor de 2 metros se cobrará 20 € por cada pieza.
    - Si la pieza tiene más de 2 partes, se cobrará 10 € por cada pieza adicional.
  - Se mostrará el precio final de la venta de la obra en dólares.
  - Casos particulares según el tipo de la obra:
    - Obras pictóricas:
      - Descuento del 10%.
    - Esculturas:
      - Descuento del 20%.
      - Sobrecoste de 50€ por manipulación.
- Se mostrarán por pantalla todos los costes extras relativos a la obra en cuestión.
- Tipo de fichero al que se quiere exportar la lista de obras de arte.
- Uso de una interfaz gráfica que interactúe con el galerista. Elementos que debe tener la interfaz:
  - Al iniciar la aplicación, mostrará la interfaz con un menú que muestre las 7 opciones que puede hacer la aplicación (visualizar, dar de alta, modifica datos, visualizar datos, obtener precio, imprimir etiqueta y exportar a fichero). Cada opción será un botón.