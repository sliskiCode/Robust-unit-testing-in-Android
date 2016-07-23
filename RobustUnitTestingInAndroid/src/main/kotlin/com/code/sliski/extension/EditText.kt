package com.code.sliski.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.setOnTextChangedListener(function: (String) -> Unit) =
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(text: Editable) {
                function(text.toString())
            }

            override fun beforeTextChanged(text: CharSequence, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence, p1: Int, p2: Int, p3: Int) {
            }
        })