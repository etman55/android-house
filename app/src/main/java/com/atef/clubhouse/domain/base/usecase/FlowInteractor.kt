package com.atef.clubhouse.domain.base.usecase

import com.atef.clubhouse.domain.base.coroutines.CoroutineDispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 * An interactor (use case in Clean Architecture) represents an execution unit of asynchronous work.
 * A [FlowInteractor] exposes a cold stream of values implemented with Kotlin [Flow].
 *
 * Work will be executed on thread as specified by the [dispatcher] of the interactor.
 */
public abstract class FlowInteractor<in Params, T>(private val dispatcher: CoroutineDispatcherProvider) {

    /**
     * Create a [Flow] for this interactor.
     */
    protected abstract fun execute(params: Params? = null): Flow<T>

    /**
     * Build a new [Flow] from this interactor.
     */
    public operator fun invoke(params: Params? = null): Flow<T> = execute(params).flowOn(dispatcher.io)
}
