package com.example.dmclaughlin.fortnitetracker

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener

/**
 * Created by dmclaughlin on 5/8/18.
 */
class ShakeEventListener :  SensorEventListener{
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //https://stackoverflow.com/questions/24538116/how-to-create-an-android-app-that-activates-on-shake-events-when-screen-locked
    override fun onSensorChanged(event: SensorEvent?) {
        handleShake(event)
    }

    private fun handleShake(event: SensorEvent?)
    {
        if(event != null)
        {
            val intent: Intent? = Intent(this, MainActivity::class.java)
        }
    }

}