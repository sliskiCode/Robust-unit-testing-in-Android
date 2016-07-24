package com.code.sliski.preference

interface PreferencesManager {

    fun saveUserId(userId: Long)
    fun getUserId(): Long
}