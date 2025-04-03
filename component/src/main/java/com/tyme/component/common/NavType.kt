package com.tyme.component.common

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.BundleCompat
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


inline fun <reified T : Parcelable?> navType(
    isNullableAllowed: Boolean = false,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            BundleCompat.getParcelable(bundle, key, T::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): T {
        val deserializedResult = Json.decodeFromString<T>(value)
        return deserializedResult
    }

    override fun serializeAsValue(value: T) = Json.encodeToString(value)

    override fun put(bundle: Bundle, key: String, value: T) {
        if (value == null) {
            bundle.putParcelable(key, null)
        } else {
            bundle.putParcelable(key, value)
        }
    }
}