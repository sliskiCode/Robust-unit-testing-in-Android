package com.code.sliski.extension

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

fun AppCompatActivity.addFragment(layoutId: Int, fragment: Fragment) =
        supportFragmentManager
                .beginTransaction()
                .add(layoutId, fragment)
                .commit()

@Suppress("UNCHECKED_CAST")
fun <T> AppCompatActivity.application() = application as T

fun AppCompatActivity.showMessage(messageResId: Int) {
    showMessage(getString(messageResId))
}

fun AppCompatActivity.showMessage(message: String) {
    Toast.makeText(this,
                   message,
                   Toast.LENGTH_SHORT).show()
}