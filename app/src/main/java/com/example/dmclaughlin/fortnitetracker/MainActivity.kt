package com.example.dmclaughlin.fortnitetracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import org.apache.http.impl.DefaultBHttpClientConnection
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun getFortniteStats(view: View)
    {
        //Connection info
        var urlConnection: HttpURLConnection? = null
        val url = URL("https://api.fortnitetracker.com/v1/profile/pc/dmcglock")
        var apiKey : ArrayList<String>? = null
        apiKey!!.add("b5354183-cbba-46e1-a191-96b00e96ba30")
        var headers = mapOf<String, List<String>>("TRN-Api-Key" to apiKey)

        //Open connection with settings
        urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.headerFields.set(headers.keys.first(), headers.values.first())
        urlConnection.connectTimeout = 1000
        urlConnection.readTimeout = 1000

        //Input
        var input: InputStream = urlConnection.inputStream
        var inputReader : InputStreamReader = InputStreamReader(input)
        var data = inputReader.read()
        while(data != -1)
        {
        }

    }
}
