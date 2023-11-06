package com.example.satellitesinspace.common

import android.content.Context
import java.io.IOException
import java.nio.charset.StandardCharsets

object FileUtil {
    fun loadJSONFromAsset(fileName: String?, context: Context): String {
        val json: String = try {
            val `is` = context.assets.open(fileName!!)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ""
        }
        return json
    }
}