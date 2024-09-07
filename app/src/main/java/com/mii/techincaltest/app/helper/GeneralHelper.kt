package com.mii.techincaltest.app.helper

import java.text.NumberFormat
import java.util.Locale

object GeneralHelper {

    fun Int.convertToRupiah(): String {
        val localId = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val strFormat = formatter.format(this)
        return strFormat
    }

}