package com.schwarz.pedro.southmarket_android.ui.core.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schwarz.pedro.southmarket_android.providers.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.*

class SplashViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {

    private val _validToken = MutableLiveData<Boolean>()
    val validToken: LiveData<Boolean>
        get() = _validToken

    fun validateTokenExpireDate() = viewModelScope.launch(Dispatchers.IO) {
        val long: Long = dataStoreManager.getExpiresIn().first()

        if (long == 0.toLong()) _validToken.postValue(false)
        else {
            val expiresIn = Calendar.getInstance().apply { timeInMillis = long }
            val currentDate = Calendar.getInstance()
            _validToken.postValue(expiresIn.after(currentDate))
        }
    }
}