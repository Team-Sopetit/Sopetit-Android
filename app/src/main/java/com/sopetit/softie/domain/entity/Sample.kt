package com.sopetit.softie.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sample(
    val name: String,
) : Parcelable
