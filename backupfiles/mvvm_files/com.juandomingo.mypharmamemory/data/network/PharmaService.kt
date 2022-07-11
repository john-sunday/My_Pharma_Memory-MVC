package com.juandomingo.mypharmamemorymvc.data.network

import com.juandomingo.mypharmamemorymvc.model.PharmacoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PharmaService @Inject constructor(
    private val api: PharmaApiClient
) {
    /*  Desde esta clase llammos a 'suspend fun getAllPharma(): Response<List<Pharmaco>>'
        de la clase data/PharmaProvider.kt  */
    /*  Deberemos tener un repositorio, que cuando se le soliciten datos, el propio repo sabrá
    *   si la información la saca de Internet o de la BBDD, y si tiene que sacarla de Internet,
    *   utilizará esta clase, por lo que si cambiamos de Retrofit a Firebase, pues sólo tengamos
    *   que cambiar esta clase. Y el resto de la app no sabe lo que hay aquí, lo único que sabe
    *   es que esta clase gestiona las llamadas a Internet para obtener datos -> principio 'solid'.
    *  */
    //private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getPharma(): List<PharmacoModel> {
        /*  Con esta corrutina, ejecutamos la llamada en un hilo secundario
            y conseguimos liberar el hilo principal o hilo main, y no saturamos
            la interfaz del usuario.    */
        return withContext(Dispatchers.IO) {
            //val response: Response<List<PharmacoModel>> = retrofit.create(PharmaApiClient::class.java).getAllPharma()
            val response: Response<List<PharmacoModel>> = api.getAllPharma()
            response.body() ?: emptyList()
        }
    }
}