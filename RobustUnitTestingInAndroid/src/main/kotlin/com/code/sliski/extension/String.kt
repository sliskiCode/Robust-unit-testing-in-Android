package com.code.sliski.extension

fun String.isPositiveNumber() = matches(Regex("^\\d+$"))
