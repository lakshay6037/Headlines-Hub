package com.example.headlinejetpackcompose.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import com.example.headlinejetpackcompose.domain.model.Source


@Entity(
    indices = [Index(value = ["url"], unique = true)]
)
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val source: Source,
    val title: String?,

    val url: String,

    val urlToImage: String?
): Parcelable