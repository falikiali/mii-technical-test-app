package com.mii.techincaltest.app.data.remote

import com.mii.techincaltest.app.data.remote.dto.response.PromoResponse
import com.mii.techincaltest.app.data.remote.dto.response.PromosResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("promos")
    suspend fun getPromos(): PromosResponse

    @GET("promos/{id}")
    suspend fun getPromoById(@Path("id") id: Int): PromoResponse

}