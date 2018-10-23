package at.guetz.pirates

import retrofit2.Call
import retrofit2.http.GET

interface WebService {

    @GET("pirateships")
    fun getShips(): Call<PirateShipResponse>
}