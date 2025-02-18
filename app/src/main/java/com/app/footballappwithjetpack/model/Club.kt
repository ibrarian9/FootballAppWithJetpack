package com.app.footballappwithjetpack.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Club(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val desc: String,
    val stadium: String,
    val epl: String,
    val ucl: String,
    val fa: String,
    val poto: Int
): Parcelable
