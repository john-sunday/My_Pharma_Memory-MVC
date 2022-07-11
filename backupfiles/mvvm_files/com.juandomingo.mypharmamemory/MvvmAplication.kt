package com.juandomingo.mypharmamemorymvc

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
// 1er paso Dagger Hilt -> crear esta clase y la etiqueta:
@HiltAndroidApp
class MvvmAplication: Application()
/*
    Una clase Application es la que extiende de la propia aplicación
    de Android, o sea que va a ser la primera en llamarse cuando se inicie
    la app.
    Hay que declararla en el 'AndroidManifest.xml'.
    Dentro de la clase se implementaría todo el código, como el método
    'onCreate()', pero no hace falta, porque usaremos la etiqueta
    '@HiltAndroidApp'.
    Ahora tenemos que preparar nuestras Activities y nuestro ViewModel.

    -----------------------------------------------------------------------------------
    El principio de INYECCIÓN de DEPENDENCIAS es un conjunto de técnicas para disminuir
    el ACOPLAMIENTO de las CLASES.
    La clase INYECTORA se encargará de gestionar la inyección de clases en otras clases
    por constructor, con lo que dentro de una clase, no habrá que instanciar otra/s
    clase/s, por lo que la clase no tendrá la responsabilidad de crear las instancias,
    que a su vez pueden generar otras instancias, que a su vez pueden generar más
    instancias, etc, etc, etc.
    -----------------------------------------------------------------------------------
k*/
