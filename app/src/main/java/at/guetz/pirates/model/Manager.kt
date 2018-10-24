package at.guetz.pirates.model

import at.guetz.pirates.data.PirateShip
import at.guetz.pirates.data.PirateShipResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Manager {

    const val BASE_URL = "https://assets.shpock.com/android/interview-test/"

    lateinit var webService: WebService

    val shipList: MutableList<PirateShip> = mutableListOf()

    fun loadPirateShips(callback: PirateShipCallback) {
        if (!shipList.isEmpty()) return callback.shipsLoaded(shipList)

        //Should never happen
        if (webService == null) return callback.onError("Webservice not initialized!")

        webService.getShips().enqueue(object : Callback<PirateShipResponse> {
            override fun onResponse(call: Call<PirateShipResponse>, response: Response<PirateShipResponse>) {
                val shipResponse = response.body()
                if (response.isSuccessful && shipResponse != null && shipResponse.success) {
                    shipList.clear()
                    shipList.addAll(shipResponse.ships.filterNotNull())
                    callback.shipsLoaded(shipList)
                } else {
                    if (response.errorBody() != null) {
                        callback.onError(response.errorBody().toString())
                    } else {
                        callback.onError("UnknownError")
                    }
                }
            }

            override fun onFailure(call: Call<PirateShipResponse>, t: Throwable) {
                callback.onError(t.localizedMessage)
            }
        })
    }

    fun init() {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        webService = retrofit.create(WebService::class.java)
    }

    interface PirateShipCallback {
        fun shipsLoaded(ships: List<PirateShip>)

        fun onError(msg: String)
    }
}