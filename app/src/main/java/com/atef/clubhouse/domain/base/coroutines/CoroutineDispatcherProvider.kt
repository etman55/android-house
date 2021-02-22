package com.atef.clubhouse.domain.base.coroutines

import kotlinx.coroutines.CoroutineDispatcher

/**
 * A wrapper class for common coroutine dispatchers.
 * An instance of this can be injected to classes which are concerned about executing code
 * on different threads, but they don't need to know about the underlying implementation.
 * A single-threaded version for example can be injected for testing.
 */
open class CoroutineDispatcherProvider(
    val ui: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
    val io: CoroutineDispatcher
)
