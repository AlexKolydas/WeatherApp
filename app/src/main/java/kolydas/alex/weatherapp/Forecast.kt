package kolydas.alex.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

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
    }
}
