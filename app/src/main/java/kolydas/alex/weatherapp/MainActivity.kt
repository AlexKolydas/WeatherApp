package kolydas.alex.weatherapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var getForecast=findViewById<Button>(R.id.getForecastBtn)

        getForecast.setOnClickListener{
            val forecastIntent=Intent(this,Forecast::class.java)

            //send the text the user types in the search box into Forecast.kt ***
            val searchEdtText=findViewById<EditText>(R.id.searchEditText)
            forecastIntent.putExtra("searchTerm",searchEdtText.text.toString())
            //***
            startActivity(forecastIntent)
        }
    }
}
