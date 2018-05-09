package com.example.dmclaughlin.fortnitetracker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager

/**
 * Created by dmclaughlin on 5/8/18.
 */
class OnBootReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val sensorManager : SensorManager? = context!!.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(ShakeEventListener(), sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

}