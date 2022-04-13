package com.example.mmaplib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {


    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lib = NativeLib()
        val stringFromJNI = lib.stringFromJNI()
        lib.memoryMapping()
        Log.i(TAG, stringFromJNI)
    }

}