package c.core.ex

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

fun ViewPager.bind(
    fm: FragmentManager,
    count: Int,
    action: (position: Int) -> Fragment
) {
    adapter = object : FragmentPagerAdapter(
        fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        override fun getItem(position: Int): Fragment = action.invoke(position)
        override fun getCount() = count
    }
}
