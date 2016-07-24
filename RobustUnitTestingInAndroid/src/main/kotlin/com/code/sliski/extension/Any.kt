package com.code.sliski.extension

inline fun <T> T?.ifNotNull(function: (T) -> Unit) {
    if (this != null) function(this)
}

inline fun <T> T?.ifNull(function: (T?) -> Unit) {
    if (this == null) function(this)
}

fun <T> T?.isNull() = this == null