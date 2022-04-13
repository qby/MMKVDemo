package com.example.mmaplib

class NativeLib {

    /**
     * A native method that is implemented by the 'mmaplib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    external fun memoryMapping()

    companion object {
        // Used to load the 'mmaplib' library on application startup.
        init {
            System.loadLibrary("mmaplib")
        }
    }
}