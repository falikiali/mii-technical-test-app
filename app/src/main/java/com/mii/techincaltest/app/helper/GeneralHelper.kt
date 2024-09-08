package com.mii.techincaltest.app.helper

import com.google.gson.Gson
import com.mii.techincaltest.app.domain.model.Portofolio
import java.text.NumberFormat
import java.util.Locale

object GeneralHelper {

    fun Int.convertToRupiah(): String {
        val localId = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val strFormat = formatter.format(this)
        return strFormat
    }

    private fun jsonPortofolio(): String {
        return """
            {
                "type": "donutChart",
                "data": [
                    {
                        "label": "Tarik Tunai",
                        "percentage": "55",
                        "data": [
                            {
                                "trx_date": "21/01/2023",
                                "nominal": 1000000
                            },
                            {
                                "trx_date": "20/01/2023",
                                "nominal": 500000
                            },
                            {
                                "trx_date": "19/01/2023",
                                "nominal": 1000000
                            }
                        ]
                    },
                    {
                        "label": "QRIS Payment",
                        "percentage": "31",
                        "data": [
                            {
                                "trx_date": "21/01/2023",
                                "nominal": 159000
                            },
                            {
                                "trx_date": "20/01/2023",
                                "nominal": 35000
                            },
                            {
                                "trx_date": "19/01/2023",
                                "nominal": 1500
                            }
                        ]
                    },
                    {
                        "label": "Topup Gopay",
                        "percentage": "7.7",
                        "data": [
                            {
                                "trx_date": "21/01/2023",
                                "nominal": 200000
                            },
                            {
                                "trx_date": "20/01/2023",
                                "nominal": 195000
                            },
                            {
                                "trx_date": "19/01/2023",
                                "nominal": 5000000
                            }
                        ]
                    },
                    {
                        "label": "Lainnya",
                        "percentage": "6.3",
                        "data": [
                            {
                                "trx_date": "21/01/2023",
                                "nominal": 1000000
                            },
                            {
                                "trx_date": "20/01/2023",
                                "nominal": 500000
                            },
                            {
                                "trx_date": "19/01/2023",
                                "nominal": 1000000
                            }
                        ]
                    }
                ]
            }
        """.trimIndent()
    }

    fun parsingJsonPortofolioToModel(): Portofolio {
        return Gson().fromJson(jsonPortofolio(), Portofolio::class.java)
    }

}