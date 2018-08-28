package kolydas.alex.weatherapp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// interface

interface WeatherApi
{
    @GET("/bins/159y08")
    fun getForecast() : Call<List<ForecastActivity>>
}

class ForecastActivity(val high: String, val low: String)

class WeatherRetriever{
    val service : WeatherApi

    init{
        val retrofit=Retrofit.Builder().baseUrl("https://api.myjson.com").addConverterFactory(GsonConverterFactory.create()).build()
        service=retrofit.create(WeatherApi::class.java)
    }

    fun getForecast(callback : Callback<List<ForecastActivity>>){
        val call=service.getForecast()
        call.enqueue(callback)
    }
}

//addConverterFactory converts json to a kotlin object
//enqueue is running the call in the background asynchroniously