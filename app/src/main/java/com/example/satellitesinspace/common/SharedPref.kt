package com.example.satellitesinspace.common

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object SharedPref {
        private lateinit var sharedPreferences: SharedPreferences

        private val SHARED_PREFERENCES_KEY = "isClickedBefore"

        private fun getInstance(context: Context): SharedPreferences {
            if (!::sharedPreferences.isInitialized) {
                synchronized(SharedPref::class.java) {
                    if (!::sharedPreferences.isInitialized) {
                        sharedPreferences = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
                    }
                }
            }
            return sharedPreferences
        }

    fun getIsClickedBefore(satelliteID: Int,context: Context): Boolean {
        return getInstance(context).getBoolean("$satelliteID",false)
    }

    fun setIsClickedBefore(satelliteID: Int, isClicked: Boolean,context: Context) {
        getInstance(context).edit()
            .putBoolean("$satelliteID", isClicked)
            .apply()
    }

}