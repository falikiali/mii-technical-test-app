package com.mii.techincaltest.app.data.remote

import com.mii.techincaltest.app.data.remote.dto.response.PromoResponse
import retrofit2.http.GET

interface ApiService {

    @GET("promos")
    suspend fun getPromos(): PromoResponse

}