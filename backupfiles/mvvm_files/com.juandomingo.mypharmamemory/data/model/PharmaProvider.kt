package com.juandomingo.mypharmamemorymvc.data.model

import com.juandomingo.mypharmamemorymvc.model.PharmacoModel
import javax.inject.Inject
import javax.inject.Singleton

// 6º paso Dagger Hilt -> hacemos la clase inyectable y eliminamos 'companionObject{}'
/*  Necesitamos que esta clase sea Singleton, porque cuando el caso de uso
*   'GetListPharmaUseCase' llama a Provider, se genera una instancia diferente a
*   la se creó al llamar a Provider desde el caso de uso 'GetRandomPharmaUseCase', y
*   la instancia que tiene la lista de medicamentos(quotes) es la que llama a
*   Internet, el caso de uso 'GetRandomPharmaUseCase' está vacío, por lo que hay
*   que convertir a la clase PharmaProvider en Singleton:   */
@Singleton
class PharmaProvider @Inject constructor() {
    var pharmas: List<PharmacoModel> = emptyList()
}