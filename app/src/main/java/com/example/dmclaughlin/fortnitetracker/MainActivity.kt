package com.example.dmclaughlin.fortnitetracker

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import okhttp3.*
import org.apache.http.impl.DefaultBHttpClientConnection
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    val client = OkHttpClient()
    val TAG = "Main"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getFortniteStats(view: View)
    {
        //Connection info
        val url = "https://api.fortnitetracker.com/v1/profile/pc/dmcglock"
        val jsonString = run(url)
        Log.i(TAG, "Returned ${jsonString}")
    }

    private fun run(url: String) : String
    {
        var responseJson : String = ""
        val request = Request.Builder()
                .url(url)
                .header("TRN-Api-Key", "b5354183-cbba-46e1-a191-96b00e96ba30")
                .build()

        val clientCall = client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e(TAG, "Error contacting server: ${e!!.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if(!response.isSuccessful)
                {
                    Log.e(TAG, "Response was not successful : ${response}")
                }

                val responseData = response.body()?.string()

                this@MainActivity.runOnUiThread(java.lang.Runnable {
                    fun run(){
                        
                    }
                })
            }
        })

        return responseJson
    }

}
