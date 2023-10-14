package tbdbatista.eletriccardio.presentation.data

import retrofit2.Call
import retrofit2.http.GET
import tbdbatista.eletriccardio.presentation.domain.Carro

interface CarroApi {
    @GET("cars.json")
    fun getAllCars(): Call<List<Carro>>
}