package com.example.dmclaughlin.fortnitetracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun getFortniteStats(view: View)
    {
        var urlConnection: HttpURLConnection? = null
            val url = URL("https://api.fortnitetracker.com/v1/profile/pc/dmcglock")
            var apiKey : ArrayList<String>? = null
            apiKey!!.add("b5354183-cbba-46e1-a191-96b00e96ba30")

            var headers = mapOf<String, List<String>>("TRN-Api-Key" to apiKey)



            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.headerFields.set(headers.keys.first(), headers.values.first())
            urlConnection.connectTimeout = 1000
            urlConnection.readTimeout = 1000
    }
}
