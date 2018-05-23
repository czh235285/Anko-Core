package czh.fast.lib.base


interface BaseView<T> : loadingView {
    var presenter: T
}

interface loadingView {
    fun showLoading()
    fun hideLoading()
}