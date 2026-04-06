package com.example.headlinejetpackcompose.data.local.room_db

import androidx.room.Database
import androidx.room.RoomDatabase

import androidx.room.TypeConverters
import com.example.headlinejetpackcompose.domain.model.Article

@TypeConverters(ArticleTypeConverter::class)
@Database(entities = [Article::class], version = 3)
abstract class NewsDb : RoomDatabase() {
    abstract val newsDao: NewsDao
}