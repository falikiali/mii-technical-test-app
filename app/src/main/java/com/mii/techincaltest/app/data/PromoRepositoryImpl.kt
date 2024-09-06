package com.mii.techincaltest.app.data

import com.mii.techincaltest.app.data.remote.ApiService
import com.mii.techincaltest.app.domain.model.Promo
import com.mii.techincaltest.app.domain.repository.PromoRepository
import com.mii.techincaltest.app.helper.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class PromoRepositoryImpl @Inject constructor(private val apiService: ApiService): PromoRepository {
    override suspend fun getPromos(): Flow<ResultState<List<Promo>>> {
        return flow {
            emit(ResultState.Loading)

            try {
                val response = apiService.getPromos()
                emit(ResultState.Success(response.data.map { it.toDomain() }))
            } catch (e: HttpException) {
                e.response()?.errorBody().let {
                    if (e.code() == 401) {
                        emit(ResultState.Unauthorized)
                    } else {
                        emit(ResultState.ServerUnderMaintenance)
                    }
                }
            } catch (e: IOException) {
                emit(ResultState.ConnectionTimeout)
            } catch (e: Exception) {
                emit(ResultState.ServerUnderMaintenance)
            }
        }.flowOn(Dispatchers.IO)
    }
}