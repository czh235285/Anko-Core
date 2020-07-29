package c.core.sample.utils

import android.app.Activity
import java.util.*
import kotlin.system.exitProcess

/**
 * 全局app管理类
 * @author 管理员
 */
class AppManager private constructor() {

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack?.add(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity? {
        return activityStack?.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        activityStack?.lastElement()?.let {
            finishActivity(it::class.java)
        }
    }


    companion object {
        private var activityStack: Stack<Activity>? = null        // Activity栈
        private var instance: AppManager? = null                // 单例模式

        /**
         * 单一实例
         */
        val appManager: AppManager
            get() {
                if (instance == null) {
                    instance = AppManager()
                }
                return instance!!
            }

        /**
         * 结束所有Activity
         */
        fun finishAllActivity() {
            val iterator = activityStack?.iterator() ?: return
            while (iterator.hasNext()) {
                val act = iterator.next()
                iterator.remove()
                act.finish()
            }
        }


        fun finishActivity(cls: Class<*>) {
            val iterator = activityStack?.iterator() ?: return
            while (iterator.hasNext()) {
                val act = iterator.next()
                if (act.javaClass == cls) {
                    iterator.remove()
                    act.finish()
                }
            }
        }

        fun finishActivity(activity: Activity) {
            val iterator = activityStack?.iterator() ?: return
            while (iterator.hasNext()) {
                val act = iterator.next()
                if (act.javaClass == activity::class.java) {
                    iterator.remove()
                    act.finish()
                }
            }
        }

        fun finishActivityExclude(cls: Class<*>) {
            val iterator = activityStack?.iterator() ?: return
            while (iterator.hasNext()) {
                val act = iterator.next()
                if (act.javaClass != cls) {
                    iterator.remove()
                    act.finish()
                }
            }
        }

        inline fun <reified T : Any> finishActivity() {
            finishActivity(T::class.java)
        }

        inline fun <reified T : Any> finishActivityExclude() {
            finishActivityExclude(T::class.java)
        }


        /**
         * 获取指定的Activity
         * @author kymjs
         */
        fun getActivity(cls: Class<*>): Activity? {
            val iterator = activityStack?.iterator() ?: return null
            while (iterator.hasNext()) {
                val act = iterator.next()
                if (act.javaClass == cls) {
                    return act
                }
            }
            return null
        }

        /**
         * 退出应用程序
         */
        fun appExit() {
            try {
                finishAllActivity()
                // 杀死该应用进程
                android.os.Process.killProcess(android.os.Process.myPid())
                exitProcess(0)
            } catch (e: Exception) {
            }

        }
    }
}
