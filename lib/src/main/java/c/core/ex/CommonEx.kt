package c.core.ex

import c.core.ApplicationProvider

/**
 * 全局上下文
 */
val appCtx by lazy {
    ApplicationProvider.ctx
}