# ContactsList


Aplicación que muestra listado de contactos, Los mismos estan ubicados en servidor remoto y se solicitan mediante REST API  con Retrofit2/Okhttp3

El proyecto esta estructurado en la arquitectura MVVM, por lo cual se usa ViewModel con LiveData

Dichas consultas se hace aprovechando la potencialidad de las Corrutinas de Kotlin

Los contactos se clasifican de acuerdo a favoritos u otros contactos y se puede ver el detalle de un contacto en particular

Se usa Navigation Component y Fragment para la interacción entre las dos pantallas.

Para las listas se usa ListView y RecyclerView cada uno con sus respectivos adaptadores y modelos de datos.

Para mas detalles puedes consultar el codigo fuente.




