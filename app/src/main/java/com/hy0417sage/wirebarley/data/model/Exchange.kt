package com.hy0417sage.wirebarley.data.model

import com.google.gson.annotations.SerializedName

data class Exchange(
    @SerializedName("success") val success: Boolean,
    @SerializedName("source") val USD: String,
    @SerializedName("quotes") val quotes: Quotes,
) {
    data class Quotes(
        @SerializedName("USDKRW") val KRW: Double,
        @SerializedName("USDJPY") val JPY: Double,
        @SerializedName("USDPHP") val PHP: Double,
    )
}