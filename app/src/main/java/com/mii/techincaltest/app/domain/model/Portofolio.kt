package com.mii.techincaltest.app.domain.model

import com.google.gson.annotations.SerializedName

data class Portofolio(
    val type: String,
    val data: List<DataPortofolio>
)

data class DataPortofolio(
    val label: String,
    val percentage: String,
    val data: List<EachDataPortofolio>
)

data class EachDataPortofolio(
    @SerializedName("trx_date") val transactionDate: String,
    val nominal: Int
)
