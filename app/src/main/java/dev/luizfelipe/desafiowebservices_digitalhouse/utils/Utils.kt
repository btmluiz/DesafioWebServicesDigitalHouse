package dev.luizfelipe.desafiowebservices_digitalhouse.utils

import android.content.Context
import java.math.BigInteger
import java.security.MessageDigest

class Utils{
    companion object {
        fun hashFormat(privateKey: String, pkey: String, dateTime: String): String {
            val str = "${dateTime}${privateKey}${pkey}"
            return md5hash(str)
        }

        fun md5hash(str: String): String {
            val md = MessageDigest.getInstance("MD5")
            val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
            return String.format("%032x", bigInt)
        }

        fun calculateSpan(context: Context, dp: Int): Int {
            val displayMetrics = context.resources.displayMetrics
            val dpWidth = displayMetrics.widthPixels / displayMetrics.density
            return (dpWidth / dp).toInt()
        }
    }
}