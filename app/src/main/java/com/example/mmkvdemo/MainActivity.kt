package com.example.mmkvdemo

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.mmkvdemo.databinding.ActivityMainBinding
import com.tencent.mmkv.MMKV


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "MainActivity"
    }

    val mHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        initMMKV()

        defaultMMKV()

    }

    /**
     * 初始化
     */
    fun initMMKV() {

    }

    /**
     * 默认对象
     */
    fun defaultMMKV() {
        //defaultMMKV 默认只有单进程， 没有加密
        val kv = MMKV.defaultMMKV()
        kv.encode("bool", true)
        val bValue = kv.decodeBool("bool")
        Log.i(TAG, "bool $bValue")

        kv.encode("int", Int.MIN_VALUE)
        val iValue = kv.decodeInt("int")
        Log.i(TAG, "int $iValue")

        kv.encode("string", "Hello from mmkv")
        val str = kv.decodeString("string")
        Log.i(TAG, "str $str")
    }



    fun mmkvWithId() {
        val mmkvWithID = MMKV.mmkvWithID("mmkvWithID", MMKV.MULTI_PROCESS_MODE, "dfslkfiouwrdjf", "")
    }

    fun mmkvNotify() {
        MMKV.registerContentChangeNotify {
            Log.i(TAG, "mmkvNotify: $it")
        }
    }

    fun mmkvToPreference() {
        val preferences = getSharedPreferences("imported", MODE_PRIVATE)
        val kv = MMKV.mmkvWithID("imported")
        kv.clearAll()
        kv.importFromSharedPreferences(preferences)
    }


}