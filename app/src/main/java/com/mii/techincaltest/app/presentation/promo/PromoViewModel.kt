package com.mii.techincaltest.app.presentation.promo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mii.techincaltest.app.domain.model.Promo
import com.mii.techincaltest.app.domain.repository.PromoRepository
import com.mii.techincaltest.app.helper.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PromoViewModel @Inject constructor(private val promoRepository: PromoRepository): ViewModel() {

    private val _resultPromos = MutableLiveData<ResultState<List<Promo>>>(ResultState.Idle)
    val resultPromos: LiveData<ResultState<List<Promo>>> get() = _resultPromos

    fun getPromos() {
        viewModelScope.launch {
            promoRepository.getPromos().collect {
                _resultPromos.postValue(it)
            }
        }
    }

}