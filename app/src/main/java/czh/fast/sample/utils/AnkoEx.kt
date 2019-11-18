package czh.fast.sample.utils

import czh.fast.lib.utils.screenHeight
import czh.fast.lib.utils.screenWidth
import czh.fast.sample.application.MyAPP


inline val Int.px: Int get() = (wProportion * this).toInt()
inline val Int.pxf: Float get() = (wProportion * this)
inline val Int.hpx: Int get() = (hProportion * this).toInt()
inline val Int.hpxf: Float get() = (hProportion * this)

/**
 * UI原型750*1334
 */
val wProportion by lazy {
    MyAPP.instance.screenWidth().toFloat() / 750
}
val hProportion by lazy {
    MyAPP.instance.screenHeight().toFloat() / 1334
}
