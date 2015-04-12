package com.code.sliski.util

import android.widget.Toast
import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import com.code.sliski.R

public fun Fragment.isTablet() : Boolean {
    return  getResources().getBoolean(R.bool.isTablet)
}

public fun Fragment.replaceWith(fragment: Fragment) {
    getActivity()
            .getSupportFragmentManager()
            .beginTransaction()
            .replace((getView().getParent() as View).getId(), fragment)
            .commit()
}

public fun Fragment.addToBackStack(layoutId: Int, fragment: Fragment) {
    getParentFragment()
            .getChildFragmentManager()
            .beginTransaction()
            .replace(layoutId, fragment)
            .addToBackStack("backstack")
            .commit()
}

public fun Fragment.addChildFragment(layoutId: Int, fragment: Fragment) {
    getChildFragmentManager()
            .beginTransaction()
            .add(layoutId, fragment)
            .commit()
}

public fun Fragment.findView<T>(messageResId: Int): T {
    return getView().findViewById(messageResId) as T
}

public fun Fragment.showToastLong(messageResId: Int) {
    toastLong(messageResId).show()
}

public fun Fragment.showToastShort(messageResId: Int) {
    toastShort(messageResId).show()
}

public fun Fragment.toastLong(messageResId: Int): Toast {
    return createToast(getActivity().getApplicationContext(), getActivity().getString(messageResId), Toast.LENGTH_LONG)

}

public fun Fragment.toastShort(messageResId: Int): Toast {
    return createToast(getActivity().getApplicationContext(), getActivity().getString(messageResId), Toast.LENGTH_SHORT)
}

private fun createToast(context: Context, message: String?, length: Int): Toast {
    return Toast.makeText(context, message, length)
}