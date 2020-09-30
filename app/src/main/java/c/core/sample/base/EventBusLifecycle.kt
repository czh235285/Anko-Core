package c.core.sample.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import org.greenrobot.eventbus.EventBus

/**
 *  author : czh
 *  date : 2020/9/18 15:36
 *  description : 
 */
class EventBusLifecycle(
    private val eventBus: EventBus
) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(owner: LifecycleOwner) {
        if (!eventBus.isRegistered(owner)) {
            eventBus.register(owner)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDeStory(owner: LifecycleOwner) {
        eventBus.unregister(owner)
    }
}

fun <T : LifecycleOwner> T.registerEventBus() {
    this.lifecycle.addObserver(EventBusLifecycle(EventBus.getDefault()))
}