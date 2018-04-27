package com.example.dmclaughlin.fortnitetracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import okhttp3.*
import org.apache.http.impl.DefaultBHttpClientConnection
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getFortniteStats(view: View)
    {
        //Connection info
        val url = "https://api.fortnitetracker.com/v1/profile/pc/dmcglock"
        run(url)
    }

    private fun run(url: String)
    {
        val request = Request.Builder()
                .url(url)
                .header("TRN-Api-Key", "b5354183-cbba-46e1-a191-96b00e96ba30")
                .build()

        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call?, e: IOException?) {}
            override fun onResponse(call: Call, response: Response) = println(response.body()?.string())

        })
    }
}
