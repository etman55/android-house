package com.atef.clubhouse.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import java.util.Timer
import java.util.TimerTask

/**
 * Add an action which will be invoked before the text changed
 */
fun TextView.doBeforeTextChanged(
    action: (
        text: CharSequence?,
        start: Int,
        count: Int,
        after: Int,
    ) -> Unit,
) {
    addTextChangedListener(beforeTextChanged = action)
}

/**
 * Add an action which will be invoked when the text is changing
 */
fun TextView.doOnTextChanged(
    action: (
        text: CharSequence?,
        start: Int,
        count: Int,
        after: Int,
    ) -> Unit,
) {
    addTextChangedListener(onTextChanged = action)
}

/**
 * Add an action which will be invoked after the text changed
 */
fun TextView.doAfterTextChanged(
    action: (text: Editable?) -> Unit,
) {
    addTextChangedListener(afterTextChanged = action)
}

/**
 * Add action which will be invoked after the search query changed with 600 Millis delay
 */
fun TextView.doAfterSearchQueryChange(
    action: (text: Editable?) -> Unit,
) {
    addTextChangedListener(afterSearchQueryChange = action)
}

/**
 * Add a text changed listener to this TextView using the provided actions
 */
fun TextView.addTextChangedListener(
    beforeTextChanged: ((
        text: CharSequence?,
        start: Int,
        count: Int,
        after: Int,
    ) -> Unit)? = null,
    onTextChanged: ((
        text: CharSequence?,
        start: Int,
        count: Int,
        after: Int,
    ) -> Unit)? = null,
    afterTextChanged: ((text: Editable?) -> Unit)? = null,
    afterSearchQueryChange: ((text: Editable?) -> Unit)? = null,
) {
    var timer: Timer? = null
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            timer = Timer()
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    afterSearchQueryChange?.invoke(s)
                }
            }, 600) // 600ms delay before the timer executes the „run“ method from TimerTask
            afterTextChanged?.invoke(s)
        }

        override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
            beforeTextChanged?.invoke(text, start, count, after)
        }

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            if (timer != null) {
                timer?.cancel()
            }
            onTextChanged?.invoke(text, start, before, count)
        }
    })
}

fun TextInputLayout.showError(error: String) {
    isErrorEnabled = true
    this.error = error
}

fun TextInputLayout.hideError() {
    error = null
    isErrorEnabled = false
}
