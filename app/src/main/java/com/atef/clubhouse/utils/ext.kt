package com.atef.clubhouse.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import android.telephony.SubscriptionInfo
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.view.View
import com.atef.clubhouse.R


fun getIMEIDeviceId(context: Context): String {
    var deviceId = ""
    var simSerial = ""
    var sm: SubscriptionManager
    var sis: List<SubscriptionInfo>
    var si: SubscriptionInfo? = null

    try {
        sm = SubscriptionManager.from(context)
        sis = sm.activeSubscriptionInfoList
        si = sis[0]
    } catch (e: Exception) {
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        deviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

        simSerial = si?.iccId.toString()
    } else {
        val mTelephony =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return ""
            }
        }
        assert(mTelephony != null)
        if (mTelephony.deviceId != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (mTelephony.phoneType == TelephonyManager.PHONE_TYPE_CDMA) {
                    deviceId = mTelephony.meid
                } else if (mTelephony.phoneType == TelephonyManager.PHONE_TYPE_GSM) {
                    deviceId = mTelephony.getImei(0)
                }
                simSerial = mTelephony.simSerialNumber ?: "1234567891011121314151617"
            } else {
                deviceId = mTelephony.deviceId
                simSerial = mTelephony.simSerialNumber
            }
        } else {
            deviceId = Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ANDROID_ID
            )
            simSerial = si?.iccId.toString()
        }
    }
    return deviceId
}

fun Context.isNetworkConnected(): Boolean {
    val connectivityManager: ConnectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        with(connectivityManager) {
            val networkCapabilities = getNetworkCapabilities(activeNetwork)
            return networkCapabilities != null &&
                    (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN))
        }
    } else {
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected &&
                (activeNetwork.type == ConnectivityManager.TYPE_WIFI || activeNetwork.type == ConnectivityManager.TYPE_MOBILE ||
                        activeNetwork.type == ConnectivityManager.TYPE_VPN)
    }
}

fun Activity?.fullScreen() {
    this?.window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
}

fun String.textAsBitmap(context: Context): Bitmap {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    paint.textSize = context.resources.getDimension(R.dimen.space_16x)
    paint.color = Color.RED
    paint.textAlign = Paint.Align.LEFT
    val baseline = -paint.ascent()
    val width = (paint.measureText(this) + 0.5f).toInt()
    val height = (baseline + paint.descent() + 0.5f).toInt()
    val image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(image)
    canvas.drawText(this, 0F, baseline, paint)
    return image
}
