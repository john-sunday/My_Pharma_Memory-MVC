package com.juandomingo.mypharmamemory.dependencyinjection

import com.juandomingo.mypharmamemory.data.network.PharmaApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/*  Este módulo nos va a facilitar dependencias que no sean tan fáciles de proveer
*   como las anteriores -> @Inject constructor(). Es decir, estos módulos nos proveerán
*   dependencias de bibliotecas y/o clases que contienen Interfaces.    */
// 6º paso Dagger Hilt ->
@Module
/*  Podemos decirle el nivel de alcance(de Actividad, de Aplicación, etc)
*   Es decir, cuanto tiempo queremos que dure la instancia o intancias que hemos
*   creado. Si es a nivel de Actividad, cuando se destruya la Actividad, la o las
*   instancias de esa Actividad se destruirán automaticamente con élla. */
@InstallIn(SingletonComponent::class)
object NetworkModule {
    /*  Parte compleja:
    *   Como queremos proveernos, nos creamos una función, con una nomenclatura
    *   determinada para que se entienda:
    *   'provide + lo que nos vamos a proveer -> Retrofit()'   */
    @Provides // -> para que se pueda proveer.
    @Singleton /*   -> Para no crear una instancia, cada vez que una clase de la
                    app solicite Retrofit, y así tener muchas instancias, se le coloca
                    la etiqueta '@Singleton', y una sola instancia es enviada a todas
                    las clses que soliciten Retrofit(instancia estática).   */
    fun provideRetrofit(): Retrofit {
        //  El objeto RetrofitHelper.kt, que hacía lo mismo, se elimina:
        return Retrofit.Builder()
            .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    /*  Para proveer una ApiClient como 'PharmaApiClient', que es una interface,
    *   necesitamos proveer, además del Retrofit, un ApiClient.
    *   Para devolver una Interface, necesitamos Retrofit, así que podemos llamar
    *   a la función creada 'provideRetrofit()' desde aquí:  */
    @Provides
    @Singleton
    fun providePharmaApiClient(retrofit: Retrofit): PharmaApiClient {
        return retrofit.create(PharmaApiClient::class.java)
    }
}