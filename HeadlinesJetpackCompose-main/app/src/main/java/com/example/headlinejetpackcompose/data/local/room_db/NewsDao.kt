package com.example.headlinejetpackcompose.data.local.room_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.headlinejetpackcompose.domain.model.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {


    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article : Article)


    @Query("DELETE FROM Article WHERE url = :url")
    suspend fun deleteArticleByUrl(url:String)


    @Query("SELECT * FROM Article ORDER BY id DESC")
    fun getAllArticles(): Flow<List<Article>>

    @Query("SELECT * FROM Article WHERE url = :url")
    suspend fun getAnArticles(url: String) : Article?
}