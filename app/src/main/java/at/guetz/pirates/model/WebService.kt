package at.guetz.pirates.model

import at.guetz.pirates.data.PirateShipResponse
import retrofit2.Call
import retrofit2.http.GET

interface WebService {

    @GET("pirateships")
    fun getShips(): Call<PirateShipResponse>
}