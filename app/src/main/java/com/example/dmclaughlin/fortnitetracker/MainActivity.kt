package com.example.dmclaughlin.fortnitetracker

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import android.widget.Toast.makeText
import com.example.dmclaughlin.fortnitetracker.vo.FortniteStatsVO
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity(), SensorEventListener {

    val client = OkHttpClient()
    val TAG = "Main"
    var username: String = ""
    //default to pc, for safety
    var platform: String = "pc"
    var mAccelerometer : Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        val radioGroup = findViewById<View>(R.id.platform) as RadioGroup
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            Log.i(TAG, "Made it into radio group")

            when (checkedId) {
                R.id.xbox_icon ->
                {
                    platform = "xbox"
                    findViewById<View>(R.id.playstation_icon).alpha = 0.5F
                    findViewById<View>(R.id.pc_icon).alpha = 0.5F
                    findViewById<View>(R.id.xbox_icon).alpha = 1F

                }
                R.id.playstation_icon -> {
                    platform = "playstation"
                    findViewById<View>(R.id.xbox_icon).alpha = 0.5F
                    findViewById<View>(R.id.pc_icon).alpha = 0.5F
                    findViewById<View>(R.id.playstation_icon).alpha = 1F
                }
                R.id.pc_icon -> {
                    platform = "pc"
                    findViewById<View>(R.id.playstation_icon).alpha = 0.5F
                    findViewById<View>(R.id.xbox_icon).alpha = 0.5F
                    findViewById<View>(R.id.pc_icon).alpha = 1F
                }
            }
        }
    }

    fun getFortniteStats(jsonString: String)
    {
        val playerStatsIntent = Intent(this, PlayerResultsActivity::class.java)
        playerStatsIntent.putExtra("jsonStats", jsonString)
        startActivity(playerStatsIntent)
    }

    @SuppressLint("ShowToast")
    fun runFortniteStats(view: View)
    {
        val input = findViewById<View>(R.id.username) as EditText
        username = input.text.toString()
        if(username.equals(""))
        {
            makeText(applicationContext, "Username needs to be longer", Toast.LENGTH_SHORT)
        }
        //Connection info
        val url = "https://api.fortnitetracker.com/v1/profile/${platform}/${username}"

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

                if (responseData != null) {
                    getFortniteStats(responseData)
                }
            }
        })
    }

    //Sensor stuff
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSensorChanged(event: SensorEvent?) {

    }

}

