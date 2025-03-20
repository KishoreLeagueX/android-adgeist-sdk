package com.adgeistcreatives

import com.adgeistcreatives.AdData
import android.util.Log
import android.content.Context
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import com.google.gson.Gson

class LoadAd(private val context: Context) {
    fun loadAd(adSlot: String, publisherId: String, callback: (AdData?) -> Unit) {
        Log.d("MyLibrary", adSlot) // Log the message to the console

        // Fetch ad data from the server
        val url = "https://beta-api.adgeist.ai/campaign/dummy?adSpaceId=$adSlot"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                val adData = response.body?.string()?.let { parseAdData(it) }
                callback(adData)
            }
        })
    }

    private fun parseAdData(json: String): AdData {
        // Parse JSON into AdData object
        return Gson().fromJson(json, AdData::class.java)
    }
}