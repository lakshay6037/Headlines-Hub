package com.example.headlinejetpackcompose.data.local.room_db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.headlinejetpackcompose.domain.model.Source
import dagger.Provides


@ProvidedTypeConverter
class ArticleTypeConverter {


    @TypeConverter
    fun sourceToString(sources: Source): String {

        return "${sources.id},${sources.name}"

    }


    @TypeConverter
    fun stringToSource(string: String): Source {

        val list = string.split(",")

        return Source(list[0], list[1])

    }
}