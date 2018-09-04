package kolydas.alex.weatherapp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi
{
    @GET("yql?format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
    fun getForecast(@Query("q") q:String) : Call<Weather> // @Query("q") is for the city that the user will search
}

// the values must be the same name with the json values
class Weather(val query: WeatherQuery)

class WeatherQuery(val results : WeatherResult)

class WeatherResult(val channel :WeatherChannel)

class WeatherChannel(val title : String , val item : WeatherItem)

class WeatherItem(val forecast : List<Forecastw>)

class Forecastw(val date : String, val day: String ,val high:String ,val low:String,val text:String)



//create a class that grabs the weather using this interface
class WeatherRetriever{
    val service : WeatherApi

    init{
        //addConverterFactory() converts json to a kotlin object
        val retrofit=Retrofit.Builder().baseUrl("https://query.yahooapis.com/v1/public/").addConverterFactory(GsonConverterFactory.create()).build()
        service=retrofit.create(WeatherApi::class.java)
    }

    fun getForecast(callback : Callback<Weather>, searchTerm:String){

        var searchT=searchTerm
        if(searchTerm==""){
            searchT ="New York City"
        }

        val q= "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"$searchT\")"
        val call=service.getForecast(q) //gives the value of q (which is what the user typed "searchT" into the getForecast function
        call.enqueue(callback) //enqueue is running the call in the background asynchroniously
    }
}

