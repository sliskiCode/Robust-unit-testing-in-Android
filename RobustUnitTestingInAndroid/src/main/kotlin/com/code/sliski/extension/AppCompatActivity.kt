package com.code.sliski.extension

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.addFragment(layoutId: Int, fragment: Fragment) =
        supportFragmentManager
                .beginTransaction()
                .add(layoutId, fragment)
                .commit()

@Suppress("UNCHECKED_CAST")
fun <T> AppCompatActivity.application() = application as T