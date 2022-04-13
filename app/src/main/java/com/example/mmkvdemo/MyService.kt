package com.example.mmkvdemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    companion object {
        private const val TAG = "MyService"

    }
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "myService create")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}