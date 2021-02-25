package com.atef.clubhouse.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


fun String.createPartFromString(): RequestBody {
    return this.toRequestBody(MultipartBody.FORM)
}

fun File.prepareImagePart(partName: String): MultipartBody.Part {
    return MultipartBody.Part.createFormData(
        partName, this.name, this.asRequestBody("image/*".toMediaTypeOrNull())
    )
}

fun File.preparePdfPart(partName: String): MultipartBody.Part {
    return MultipartBody.Part.createFormData(
        partName, this.name, this.asRequestBody("application/pdf".toMediaTypeOrNull())
    )
}

fun File.isMoreThanXMB(size: Int = 2): Boolean {
    return (size < this.length() / (1024 * 1024))
}