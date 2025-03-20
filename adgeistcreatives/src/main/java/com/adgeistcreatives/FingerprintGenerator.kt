package com.adgeistcreatives

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import java.util.Locale

class FingerprintGenerator(private val context: Context) {
    fun generateFingerprint(): String {
        val attributes = listOf(
            Build.MANUFACTURER,
            Build.MODEL,
            Build.VERSION.RELEASE,
            context.resources.configuration.locale.toString(),
            context.resources.displayMetrics.widthPixels.toString(),
            context.resources.displayMetrics.heightPixels.toString()
        )
        return attributes.joinToString("|").hashCode().toString()
    }
}