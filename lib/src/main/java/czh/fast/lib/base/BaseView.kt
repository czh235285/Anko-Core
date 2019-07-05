package czh.fast.lib.base


interface BaseView : LoadingView {

}

interface LoadingView {
    fun showLoading()
    fun hideLoading()
}