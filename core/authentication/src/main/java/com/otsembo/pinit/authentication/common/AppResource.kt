package com.otsembo.pinit.authentication.common

sealed class AppResource<T> (val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : AppResource<T>(data)
    class Error<T>(message: String, data: T? = null) : AppResource<T>(data, message)
    class Loading<T>(data: T? = null) : AppResource<T>(data)
    class Idle(data: String = "Idle State") : AppResource<String>(data)
}
