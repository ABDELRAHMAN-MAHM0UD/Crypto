package com.example.crypto.common

sealed class Resources<T>(val data: T? = null ,val message: T?=null) {
    class Loading<T>(data: T? =null) : Resources<T>(data)
    class Success<T>( data: T) : Resources<T>(data)
    class Failed<T>(data: T, message: T): Resources<T>(data,message)
}