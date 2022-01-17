# Documentación

- ¿Qué has empezado implementando y por qué?

Lo primero que he hecho es extraer métodos. Esto es para que cada método haga solo una cosa. Porque es más sencillo tocar una pieza pequeña que ir tocando una pieza grande, lleva más tiempo y hay que ir con cuidadonde de no arruinar el comportamiento general.

- ¿Qué problemas te has encontrado al implementar los tests y cómo los has solventado?

Básicamente el tema de los asserts, aunque no recordaba muy bien cómo hacerlo he buscado por internet y algunos otros proyectos de la FP que hice. Me lié un poco con los nombres de los métodos, lo que hice fue ser más descriptivo.

- ¿Qué componentes has creado y por qué?

Principalmente un método que de la previsión del tiempo de un día en particular respetando las fecha de los periodos de tiempo de la APi. A veces solo queremos saber cómo será el clima en un día en particular y no demás días, no tener un método que haga esto es quitarle funcionalidades al usuario.

- Si has utilizado dependencias externas, ¿por qué has escogido esas dependencias?

Agregué Commons-lang3 porque antes ya había usado esta dependencia y es muy últil para trabajar con String.

- Si has utilizado dependencias externas, ¿por qué has escogido esas dependencias?

Agregué Commons-lang3 porque antes ya había usado esta dependencia y es muy últil para trabajar con String.

- ¿Cuánto tiempo has invertido para implementar la solución?

Aproximadamente un día. Me llevó más tiempo desarrollar los meétodos que tienen que ver con fechas.

- ¿Crees que en un escenario real valdría la pena dedicar tiempo a realizar esta refactorización?

Pienso que sí. Más allá que había que ordenar código y demás. Desarrollarla sería útil para quien busca algo minimalista. 