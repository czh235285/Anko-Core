package czh.fast.lib.utils

import com.vise.xsnow.http.config.HttpGlobalConfig
import com.vise.xsnow.http.func.ApiRetryFunc
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> norTransformer(): ObservableTransformer<T, T> {
    return ObservableTransformer { apiResultObservable ->
        apiResultObservable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(ApiRetryFunc(HttpGlobalConfig.getInstance().retryCount,
                        HttpGlobalConfig.getInstance().retryDelayMillis))
    }
}

fun <T> norTransformer(retryCount: Int, retryDelayMillis: Int): ObservableTransformer<T, T> {
    return ObservableTransformer { apiResultObservable ->
        apiResultObservable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(ApiRetryFunc(retryCount, retryDelayMillis))
    }
}