package com.mii.techincaltest.app.presentation.promo.detail

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
class DetailPromoViewModel @Inject constructor(private val promoRepository: PromoRepository): ViewModel() {

    private val _resultPromo = MutableLiveData<ResultState<Promo>>(ResultState.Idle)
    val resultPromo: LiveData<ResultState<Promo>> get() = _resultPromo

    fun getPromoById(id: Int) {
        viewModelScope.launch {
            promoRepository.getPromoById(id).collect {
                _resultPromo.postValue(it)
            }
        }
    }

}