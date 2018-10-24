package at.guetz.pirates.model

import android.util.Log
import at.guetz.pirates.data.PirateShip
import at.guetz.pirates.data.PirateShipResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Manager {

    lateinit var webService: WebService

    val shipList: MutableList<PirateShip> = mutableListOf()

    fun loadPirateShips(callback: PirateShipCallback) {
        if (!shipList.isEmpty()) return callback.shipsLoaded(shipList)

        webService.getShips().enqueue(object : Callback<PirateShipResponse> {
            override fun onResponse(call: Call<PirateShipResponse>, response: Response<PirateShipResponse>) {
                val shipResponse = response.body()
                if (response.isSuccessful && shipResponse != null && shipResponse.success) {
                    Log.d("ships", "Ships loaded:" + shipResponse)
                    shipList.clear()
                    shipList.addAll(shipResponse.ships.filterNotNull())
                    callback.shipsLoaded(shipList)
                } else {
                    Log.d("ships", "Error")
                }
            }

            override fun onFailure(call: Call<PirateShipResponse>, t: Throwable) {
                TODO("not implemented")
            }
        })
    }

    fun init() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://assets.shpock.com/android/interview-test/")
                .addConverterFactory(GsonConverterFactory.create()).build()
        webService = retrofit.create(WebService::class.java)
    }

    interface PirateShipCallback {
        fun shipsLoaded(ships: List<PirateShip>)
    }
}