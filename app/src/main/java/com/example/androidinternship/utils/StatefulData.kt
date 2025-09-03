package com.example.androidinternship.utils

/**
 * Sealed class that holds either loading state, some typed data or error
 */
sealed class StatefulData<T: Any> {

    /**
     * Returns success data or throws error if value is not [SuccessData]
     */
    fun unwrap(): T {
        return when(this) {
            is LoadingData -> throw IllegalStateException("Cannot unwrap loading state")
            is SuccessData -> this.result
            is ErrorData -> throw IllegalStateException("Cannot unwrap error state: $error")
        }
    }

    /**
     * Returns success data or null if value is not [SuccessData]
     */
    fun unwrapOrNull(): T? {
        return when(this) {
            is LoadingData -> null
            is SuccessData -> this.result
            is ErrorData -> null
        }
    }

    /**
     * Returns true if value is [SuccessData]
     */
    fun isSuccessful(): Boolean {
        return this is SuccessData
    }

    /**
     * Returns true if value is not [SuccessData]
     */
    fun isLoadingOrError(): Boolean {
        return this !is SuccessData
    }

    /**
     * Returns true if value is [LoadingData]
     */
    fun isLoading(): Boolean {
        return this is LoadingData
    }

    /**
     * Returns true if value is [ErrorData]
     */
    fun isError(): Boolean {
        return this is ErrorData
    }

    /**
     * Returns error or null if value is not [ErrorData]
     */
    fun getErrorOrNull(): Any? {
        return when(this) {
            is ErrorData -> this.error
            else -> null
        }
    }
}

class LoadingData<T: Any> : StatefulData<T>()

class SuccessData<T: Any>(val result: T) : StatefulData<T>()

class ErrorData<T: Any>(val error: Any) : StatefulData<T>()
