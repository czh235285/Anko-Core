package czh.fast.lib.base


interface BaseView<T> : LoadingView {
    var presenter: T
}

interface LoadingView {
    fun showLoading()
    fun hideLoading()
}