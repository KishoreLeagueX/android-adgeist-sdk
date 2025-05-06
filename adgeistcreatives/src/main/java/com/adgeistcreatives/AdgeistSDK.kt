package com.adgeistcreatives

import android.content.Context

class AdGeistSDK private constructor(private val context: Context) {
    companion object {
        @Volatile private var instance: AdGeistSDK? = null

        fun initialize(context: Context): AdGeistSDK {
            return instance ?: synchronized(this) {
                instance ?: AdGeistSDK(context.applicationContext).also { instance = it }
            }
        }
    }

    
    private val fingerprintGenerator = FingerprintGenerator(context)

    fun getCreative(): FetchCreative {
        return FetchCreative(context, fingerprintGenerator)
    }

    fun postCreativeAnalytics(): CreativeAnalytics {
        return CreativeAnalytics(context, fingerprintGenerator)
    }
}