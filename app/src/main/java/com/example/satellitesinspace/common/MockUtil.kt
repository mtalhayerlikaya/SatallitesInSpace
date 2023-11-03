package com.example.satellitesinspace.common

import timber.log.Timber

object MockUtil {
    fun getMockResponse(fileNameWithoutExtension: String): String {
        Timber.d("Will fetch mock file with name: $fileNameWithoutExtension")
        val fileName: String = "mockfiles/$fileNameWithoutExtension.json"
        val fileContent: String = FileUtil.loadJSONFromAsset(fileName, AppUtil.myApplication!!)
        return fileContent ?: ""
    }
}