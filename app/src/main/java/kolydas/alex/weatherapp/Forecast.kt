package kolydas.alex.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Forecast : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        //access the list view
        var listView= findViewById<ListView>(R.id.forecast_listView)

        var randomThings= listOf("Hello","How are you","I like ice cream")

        //take the randromThings and put them in the listView via "simple_list_item_1"
        var adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,randomThings)

        //connect the adapter with the listView we have created
        listView.adapter= adapter


        var retriever= WeatherRetriever()
        //make a callback object
        val callBack= object :Callback<List<ForecastActivity>>{
            override fun onFailure(call: Call<List<ForecastActivity>>, t: Throwable) {
                println("We got a failure")
            }

            override fun onResponse(call: Call<List<ForecastActivity>>, response: Response<List<ForecastActivity>>) {
                println("We got a response")
                println(response?.body())

                for(forecastDay in response!!.body()!!)
                {
                    println("High ${forecastDay.high} Low:${forecastDay.low}")
                }
            }

        }

        retriever.getForecast(callBack)
    }
}
