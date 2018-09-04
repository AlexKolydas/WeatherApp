package kolydas.alex.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Forecast : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)


        var retriever = WeatherRetriever()
        //make a callback object
        val callBack = object : Callback<Weather> {

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                println("it's not working")

            }

            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                println("it's working")
                title = response?.body()?.query?.results?.channel?.title

                val forecasts = response?.body()?.query?.results?.channel?.item?.forecast
                var forecastStrings = mutableListOf<String>()
                if (forecasts != null) {
                    for (forecast in forecasts) {
                        val newString = "${forecast.date}- High:${forecast.high} Low:${forecast.low}- ${forecast.text}"
                        forecastStrings.add(newString)
                    }
                }
                //access the list view
                var listView = findViewById<ListView>(R.id.forecast_listView)

                //take the forecastStrings and put them in the listView via "simple_list_item_1"
                var adapter = ArrayAdapter(this@Forecast, android.R.layout.simple_list_item_1, forecastStrings)

                //connect the adapter with the listView we have created
                listView.adapter = adapter
            }
        }

        //get from MainActivity what the user typed in search
        val searchTerm = intent.extras.getString("searchTerm")

        retriever.getForecast(callBack, searchTerm)
    }
}
// GIA TA EROTIMATIKA **?**
/*
To xrisimopoioume gia na deiiksoume oti mia metavliti mporei na einai kai null(na min petaei error)
var age : Int? =28

age = 4
age = 88
age = null

*/