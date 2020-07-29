package c.core.sample.ui.viewmodel

import androidx.lifecycle.viewModelScope
import c.core.sample.base.BaseViewModel
import com.vise.log.ViseLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *  author : czh
 *  date : 2020-7-27
 *  description : 
 */


class FirstViewModel : BaseViewModel() {
    fun test() {
        viewModelScope.launch {
            showLoading()
            delay(3000)
            hideLoading()
        }
    }
}