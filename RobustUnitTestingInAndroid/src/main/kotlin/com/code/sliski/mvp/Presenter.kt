package com.code.sliski.mvp

abstract class Presenter<T> {

    protected var view: T? = null

    fun attach(view: T) {
        this.view = view
    }

    fun detach() {
        view = null
    }
}