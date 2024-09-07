package com.mii.techincaltest.app.domain.repository

import com.mii.techincaltest.app.domain.model.Promo
import com.mii.techincaltest.app.helper.ResultState
import kotlinx.coroutines.flow.Flow

interface PromoRepository {

    suspend fun getPromos(): Flow<ResultState<List<Promo>>>
    suspend fun getPromoById(id: Int): Flow<ResultState<Promo>>

}