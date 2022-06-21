package com.example.capstoneproject2.core

import android.util.Log

class Utils {
    companion object {
        fun print(e: Exception?) {
            Log.d(Constants.TAG, e?.message ?: e.toString())
        }
    }
}