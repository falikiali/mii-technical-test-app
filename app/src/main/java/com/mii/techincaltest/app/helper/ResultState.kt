package com.mii.techincaltest.app.helper

sealed class ResultState<out T: Any> {
    data class Success<T: Any>(val data: T): ResultState<T>()
    object Unauthorized: ResultState<Nothing>()
    object ConnectionTimeout: ResultState<Nothing>()
    object ServerUnderMaintenance: ResultState<Nothing>()
    object Loading: ResultState<Nothing>()
    object Idle: ResultState<Nothing>()
}