package c.core.sample.model

import com.flyco.tablayout.listener.CustomTabEntity


/**
 * Created by admin on 2018/1/25.
 */

class TabEntity(var title: String, var selectedIcon: Int, var unSelectedIcon: Int) :
    CustomTabEntity {

    override fun getTabTitle(): String {
        return title
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }
}
