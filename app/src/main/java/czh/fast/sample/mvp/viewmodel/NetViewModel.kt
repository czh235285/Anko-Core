package czh.fast.sample.mvp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import czh.fast.sample.api.apiservice
import czh.fast.sample.base.safeLaunch
import czh.fast.sample.mvp.model.Banner

class NetViewModel : ViewModel() {
    private val _mBanner = MediatorLiveData<Banner>()

val mBanner: LiveData<Banner>
    get() = _mBanner

fun getBanner() {
    viewModelScope.safeLaunch {
        block = {
            val data = apiservice.getBanner()
            _mBanner.value = data
        }
    }
}
}