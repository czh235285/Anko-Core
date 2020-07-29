package c.core.ex


import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.util.StateSet
import android.view.View

object ViewStates {
    fun enabled() = intArrayOf(android.R.attr.state_enabled)
    fun disabled() = intArrayOf(-android.R.attr.state_enabled)

    fun selected() = intArrayOf(android.R.attr.state_selected)
    fun notSelected() = intArrayOf(-android.R.attr.state_selected)

    fun pressed() = intArrayOf(android.R.attr.state_pressed)
    fun notPressed() = intArrayOf(-android.R.attr.state_pressed)

    fun checked() = intArrayOf(android.R.attr.state_checked)
    fun unchecked() = intArrayOf(-android.R.attr.state_checked)
}

inline fun View.stateListDrawable(block: StateListDrawable.() -> Unit) {
    background = StateListDrawable().also(block)
}


inline fun StateListDrawable.state(state: IntArray, block: () -> Drawable): Unit =
    addState(state, block())

inline fun StateListDrawable.enabledState(block: () -> Drawable): Unit =
    state(ViewStates.enabled(), block)

inline fun StateListDrawable.disabledState(block: () -> Drawable): Unit =
    state(ViewStates.disabled(), block)

inline fun StateListDrawable.selectedState(block: () -> Drawable): Unit =
    state(ViewStates.selected(), block)

inline fun StateListDrawable.unSelectedState(block: () -> Drawable): Unit =
    state(ViewStates.notSelected(), block)

inline fun StateListDrawable.pressedState(block: () -> Drawable): Unit =
    state(ViewStates.pressed(), block)

inline fun StateListDrawable.unPressedState(block: () -> Drawable): Unit =
    state(ViewStates.notPressed(), block)

inline fun StateListDrawable.checkedState(block: () -> Drawable): Unit =
    state(ViewStates.checked(), block)

inline fun StateListDrawable.unCheckedState(block: () -> Drawable): Unit =
    state(ViewStates.unchecked(), block)

inline fun StateListDrawable.defaultState(block: () -> Drawable): Unit =
    state(StateSet.WILD_CARD, block)

internal const val NO_GETTER = "Getter not available"

var StateListDrawable.exitFadeDuration: Int
    set(value) = setExitFadeDuration(value)
    @Deprecated(message = NO_GETTER, level = DeprecationLevel.ERROR) get() = error(NO_GETTER)