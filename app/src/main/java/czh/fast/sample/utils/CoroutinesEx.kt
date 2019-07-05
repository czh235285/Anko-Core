package czh.fast.sample.utils

import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun <T> Call<T>.await(): T {
    return suspendCancellableCoroutine {
        it.invokeOnCancellation {
            it?.printStackTrace()
            cancel()
        }
        enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                //请求失败，抛出异常，手动结束当前协程
                it.resumeWithException(t)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    //请求成功，将请求结果拿到并手动恢复所在协程
                    it.resume(response.body()!!)
                } else {
                    //请求状态异常，抛出异常，手动结束当前协程
                    it.resumeWithException(Throwable(response.toString()))
                }
            }
        })
    }
}