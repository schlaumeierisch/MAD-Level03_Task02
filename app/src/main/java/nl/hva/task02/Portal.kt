package nl.hva.task02

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Portal(
    val title: String,
    val url: String
) : Parcelable