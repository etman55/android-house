package com.atef.clubhouse.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.Fragment
import com.atef.clubhouse.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar

fun ViewGroup.inflate(layout: Int): View {
    val layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    return layoutInflater.inflate(layout, this, false)
}

fun Fragment.getDrawable(@DrawableRes id: Int): Drawable? {
    return ContextCompat.getDrawable(
        requireContext(),
        id
    )
}

fun Fragment.onBackPress(onBackPressed: OnBackPressedCallback.() -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(
        viewLifecycleOwner,
        onBackPressed = onBackPressed
    )
}

val Fragment.actionBar: androidx.appcompat.app.ActionBar?
    get() = (requireActivity() as AppCompatActivity).supportActionBar

var androidx.appcompat.app.ActionBar.visible: Boolean
    set(value) = if (value) this.show() else this.hide()
    get() = this.isShowing

fun MaterialButton.setButtonIconPosition(
    isStart: Boolean = false,
    isEnd: Boolean = false,
    isTop: Boolean = false,
    isBottom: Boolean = false
) {
    if (isStart) TextViewCompat.setCompoundDrawablesRelative(this, this.icon, null, null, null)
    if (isEnd) TextViewCompat.setCompoundDrawablesRelative(this, null, null, this.icon, null)
    if (isTop) TextViewCompat.setCompoundDrawablesRelative(this, null, this.icon, null, null)
    if (isBottom) TextViewCompat.setCompoundDrawablesRelative(this, null, null, null, this.icon)
}

fun View.snack(
    message: String,
    length: Int = Snackbar.LENGTH_SHORT
) = Snackbar.make(this, message, length).show()

fun View.snack(
    @StringRes message: Int,
    length: Int = Snackbar.LENGTH_SHORT
) = Snackbar.make(this, message, length).show()

fun View.snackErrorWithAction(
    message: String,
    length: Int = Snackbar.LENGTH_LONG,
    backgroundColor: Int = R.color.black,
    actionTextColor: Int = R.color.error,
    actionText: String = this.context.resources.getString(R.string.retry),
    action: View.OnClickListener? = null
) = Snackbar.make(this, message, length).apply {
    setActionTextColor(ContextCompat.getColor(this.context, actionTextColor))
    this.view.setBackgroundColor(ContextCompat.getColor(this.context, backgroundColor))
    setAction(actionText, action)
}.show()

fun View.snackErrorWithAction(
    @StringRes message: Int,
    length: Int = Snackbar.LENGTH_LONG,
    backgroundColor: Int = R.color.black,
    actionTextColor: Int = R.color.error,
    actionText: String = this.context.resources.getString(R.string.retry),
    action: View.OnClickListener? = null
) = Snackbar.make(this, message, length).apply {
    setActionTextColor(ContextCompat.getColor(this.context, actionTextColor))
    this.view.setBackgroundColor(ContextCompat.getColor(this.context, backgroundColor))
    setAction(actionText, action)
}.show()
