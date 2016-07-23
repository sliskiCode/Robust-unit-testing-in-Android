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
    Toast.makeText(activity,
                   activity.getString(messageResId),
                   Toast.LENGTH_SHORT).show()
}