package com.example.mmkvdemo

import android.app.Application
import android.util.Log
import com.tencent.mmkv.*

class App : Application(), MMKVHandler, MMKVContentChangeNotification {

    companion object {
        private const val TAG = "App"
    }

    override fun onCreate() {
        super.onCreate()

        //初始化
        val rootDir = MMKV.initialize(this, filesDir.absolutePath + "/mmkv")
        Log.i(TAG, "rootDir $rootDir")
        //日志级别
        MMKV.setLogLevel(MMKVLogLevel.LevelInfo)
        //错误及日志处理
        MMKV.registerHandler(this)
        //内容修改注册的listener
        MMKV.registerContentChangeNotify(this)

//        MMKV.backupAllToDirectory("/baronqi")

    }

    override fun onMMKVCRCCheckFail(mmapID: String?): MMKVRecoverStrategic {
        return MMKVRecoverStrategic.OnErrorRecover
    }

    override fun onMMKVFileLengthError(mmapID: String?): MMKVRecoverStrategic {
        return MMKVRecoverStrategic.OnErrorRecover
    }

    override fun wantLogRedirecting(): Boolean {
        return true
    }

    override fun mmkvLog(
        level: MMKVLogLevel?,
        file: String?,
        line: Int,
        function: String?,
        message: String?
    ) {

        val log = "<$file:$line::$function> $message"
        when (level) {
            MMKVLogLevel.LevelDebug -> Log.d("redirect logging MMKV", log)
            MMKVLogLevel.LevelNone, MMKVLogLevel.LevelInfo -> Log.i("redirect logging MMKV", log)
            MMKVLogLevel.LevelWarning -> Log.w("redirect logging MMKV", log)
            MMKVLogLevel.LevelError -> Log.e("redirect logging MMKV", log)
        }
    }

    override fun onContentChangedByOuterProcess(mmapID: String?) {
        Log.i("MMKV", "other process has changed content of : $mmapID")
    }

}