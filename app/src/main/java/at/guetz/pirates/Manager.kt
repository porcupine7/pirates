package at.guetz.pirates

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Manager {

    fun loadPirateShips() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://assets.shpock.com/android/interview-test/")
                .addConverterFactory(GsonConverterFactory.create()).build()
        val webservice = retrofit.create(WebService::class.java)
        webservice.getShips().enqueue(object : Callback<PirateShipResponse> {
            override fun onResponse(call: Call<PirateShipResponse>, response: Response<PirateShipResponse>) {
                if (response.isSuccessful && response.body() != null && response.body()!!.success) {
                    Log.d("ships", "Ships loaded:" + response.body())
                } else {
                    Log.d("ships", "Error")
                }
            }

            override fun onFailure(call: Call<PirateShipResponse>, t: Throwable) {
                TODO("not implemented")
            }
        })
    }
}