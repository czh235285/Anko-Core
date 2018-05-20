package czh.fast.lib.utils

import java.util.Stack
import android.app.Activity
import android.content.Context

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
            activityStack = Stack<Activity>()
        }
        activityStack!!.add(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity {
        val activity = activityStack!!.lastElement()
        return activity
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = activityStack!!.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        if (activity != null && !activity.isFinishing) {
            activityStack!!.remove(activity)
            activity.finish()
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in activityStack!!) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
                break
            }
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
            var i = 0
            val size = activityStack!!.size
            while (i < size) {
                if (null != activityStack!![i]) {
                    //finishActivity方法中的activity.isFinishing()方法会导致某些activity无法销毁
                    //貌似跳转的时候最后一个activity 是finishing状态，所以没有执行
                    //内部实现不是很清楚，但是实测结果如此，使用下面代码则没有问题
                    // find by TopJohn
                    //finishActivity(activityStack.get(i));
                    activityStack!![i].finish()
                    //break;
                }
                i++
            }
            activityStack!!.clear()
        }

        /**
         * 获取指定的Activity
         * @author kymjs
         */
        fun getActivity(cls: Class<*>): Activity? {
            if (activityStack != null)
                for (activity in activityStack!!) {
                    if (activity.javaClass == cls) {
                        return activity
                    }
                }
            return null
        }

        /**
         * 退出应用程序
         */
        fun AppExit() {
            try {
                finishAllActivity()
                // 杀死该应用进程
                android.os.Process.killProcess(android.os.Process.myPid())
                System.exit(0)
            } catch (e: Exception) {
            }

        }
    }
}
