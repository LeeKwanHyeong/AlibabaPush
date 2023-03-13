package com.example.alibabapush

import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.text.SimpleDateFormat
import java.util.*

class PLog {
    companion object{
        const val TAG = "Kwan PUSH!!!!"
        val sdf = SimpleDateFormat("HH:mm:ss.SSS")
    }

    private fun sendBroadCast(msg: String){
        val msg = sdf.format(Date(System.currentTimeMillis())) + " " + msg
        Intent(MainActivity.ACTION_P_LOG)
            .apply {
                putExtra(MainActivity.KEY_LOG, msg)
                LocalBroadcastManager.getInstance(MyApplication()).sendBroadcast(this)
            }
    }

    fun d(msg: String){
        Log.e(TAG, msg)
        sendBroadCast(msg)
    }

    fun d(msg: String, t: Throwable){
        Log.e(TAG, msg, t)
        sendBroadCast(msg)
        sendBroadCast(t.message.toString())
    }

}