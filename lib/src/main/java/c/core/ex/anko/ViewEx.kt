package c.core.ex.anko

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

fun AnkoComponent<Context>.generateView(ctx: Context): View =
    createView(AnkoContext.Companion.create(ctx))

fun <T : Fragment> AnkoComponent<T>.generateView(fragment: T): View =
    createView(AnkoContext.create(fragment.requireActivity(), fragment))
