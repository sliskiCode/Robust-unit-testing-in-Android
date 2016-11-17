package com.code.sliski.extension

import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.code.sliski.R

fun Fragment.isTablet() = resources.getBoolean(R.bool.isTablet)

fun Fragment.replaceWith(fragment: Fragment) =
        activity.supportFragmentManager
                .beginTransaction()
                .replace((view!!.parent as View).id, fragment)
                .commit()

fun Fragment.addToBackStack(layoutId: Int, fragment: Fragment) =
        parentFragment
                .childFragmentManager
                .beginTransaction()
                .replace(layoutId, fragment)
                .addToBackStack("backstack")
                .commit()

fun Fragment.addChildFragment(layoutId: Int, fragment: Fragment) =
        childFragmentManager
                .beginTransaction()
                .add(layoutId, fragment)
                .commit()

fun Fragment.showMessage(messageResId: Int) {
    showMessage(activity.getString(messageResId))
}

fun Fragment.showMessage(message: String) {
    Toast.makeText(activity,
                   message,
                   Toast.LENGTH_SHORT).show()
}

@Suppress("UNCHECKED_CAST")
fun <T> Fragment.application() = activity.application as T