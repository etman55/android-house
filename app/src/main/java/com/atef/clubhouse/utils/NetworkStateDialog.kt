package com.atef.clubhouse.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.widget.ContentLoadingProgressBar
import com.atef.clubhouse.R
import com.google.android.material.button.MaterialButton


object NetworkStateDialog {

    private var dialog: Dialog? = null

    @SuppressLint("InflateParams")
    fun show(context: Context?) {
        if (null != dialog && dialog!!.isShowing)
            dialog?.dismiss()
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        val view = inflater!!.inflate(R.layout.item_network_state, null)
        dialog = Dialog(context!!)
        dialog?.setCancelable(false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(view)
        val errorMsg = view.findViewById<TextView>(R.id.errorMsg)
        val retryBtn = view.findViewById<MaterialButton>(R.id.retryBtn)
        errorMsg.visibility = View.GONE
        retryBtn.visibility = View.GONE
        view.findViewById<ContentLoadingProgressBar>(R.id.progressBar).visibility = View.VISIBLE
        dialog?.show()
    }

    fun dismiss() {
        dialog?.dismiss()
        dialog = null
    }

    @SuppressLint("InflateParams")
    fun showErrorWithAction(
        context: Context?,
        onErrorAction: (errorMsg: TextView, retryBtn: MaterialButton) -> Unit,
    ) {
        if (null != dialog && dialog!!.isShowing)
            dialog?.dismiss()
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        val view = inflater!!.inflate(R.layout.item_network_state, null)
        dialog = Dialog(context!!)
        dialog?.setCancelable(true)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(view)
        val errorMsg = view.findViewById<TextView>(R.id.errorMsg)
        val retryBtn = view.findViewById<MaterialButton>(R.id.retryBtn)
        errorMsg.visibility = View.VISIBLE
        retryBtn.visibility = View.VISIBLE
        view.findViewById<ContentLoadingProgressBar>(R.id.progressBar).visibility = View.GONE
        onErrorAction(errorMsg, retryBtn)
        dialog?.show()
    }

    fun showErrorMsg(context: Context?, @StringRes msg: Int) {
        context?.let { showErrorMsg(it, it.getString(msg)) }
    }

    @SuppressLint("InflateParams")
    fun showErrorMsg(context: Context?, errorMsg: String) {
        if (null != dialog && dialog!!.isShowing)
            dialog?.dismiss()
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        val view = inflater!!.inflate(R.layout.item_network_state, null)
        dialog = Dialog(context!!)
        dialog?.setCancelable(true)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(view)
        val errorTxt = view.findViewById<TextView>(R.id.errorMsg)
        val retryBtn = view.findViewById<MaterialButton>(R.id.retryBtn)
        errorTxt.visibility = View.VISIBLE
        retryBtn.visibility = View.GONE
        view.findViewById<ContentLoadingProgressBar>(R.id.progressBar).visibility = View.GONE
        errorTxt.text = errorMsg
        dialog?.show()
    }
}
