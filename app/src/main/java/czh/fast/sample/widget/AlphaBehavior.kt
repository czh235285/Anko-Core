package czh.fast.sample.widget

import android.content.Context
import com.google.android.material.appbar.AppBarLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vise.log.ViseLog


class AlphaBehavior : CoordinatorLayout.Behavior<View> {
    private var deltaY: Float = 0f

    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)


    override fun layoutDependsOn(
            parent: CoordinatorLayout,
            child: View,
            dependency: View
    ): Boolean {
        return dependency is RecyclerView
    }

    override fun onDependentViewChanged(
            parent: CoordinatorLayout,
            child: View,
            dependency: View
    ): Boolean {

       Log.d("滑动",dependency.y.toString())



        return true
    }
}