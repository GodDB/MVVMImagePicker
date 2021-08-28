package com.godgod.domain.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

abstract class BaseCoroutineUseCase<in PARAMS, out DATA> {

    suspend operator fun invoke(params: PARAMS): DATA {
        return withContext(Dispatchers.IO) {
            execute(params)
        }
    }

    abstract suspend fun execute(params: PARAMS): DATA
}

abstract class BaseFlowUseCase<in PARAMS, out DATA> {

    operator fun invoke(params: PARAMS) : Flow<DATA> {
        return execute(params)
    }

    abstract fun execute(params: PARAMS) : Flow<DATA>
}
