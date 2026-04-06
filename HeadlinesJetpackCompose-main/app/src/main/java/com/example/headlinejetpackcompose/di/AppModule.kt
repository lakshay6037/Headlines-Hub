package com.example.headlinejetpackcompose.di
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.headlinejetpackcompose.data.local.room_db.ArticleTypeConverter
import com.example.headlinejetpackcompose.data.local.room_db.NewsDao

import com.example.headlinejetpackcompose.data.local.room_db.NewsDb
import com.example.headlinejetpackcompose.data.manager.LocalUserManagerImpl
import com.example.headlinejetpackcompose.data.remote.NewsApi
import com.example.headlinejetpackcompose.data.repository.NewsReposioryImpl
import com.example.headlinejetpackcompose.domain.manager.LocalUserManager
import com.example.headlinejetpackcompose.domain.repository.NewsRepository
import com.example.headlinejetpackcompose.domain.useCases.DeleteArticle
import com.example.headlinejetpackcompose.domain.useCases.GetAllArticles
import com.example.headlinejetpackcompose.domain.useCases.GetNews
import com.example.headlinejetpackcompose.domain.useCases.InsertArticle
import com.example.headlinejetpackcompose.domain.useCases.SearchNews
import com.example.headlinejetpackcompose.domain.useCases.SelectArticle
import com.example.headlinejetpackcompose.domain.useCases.dataClasses.NewsUseCases
import com.example.headlinejetpackcompose.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class
AppModule() {

    @Provides
    @Singleton
    fun provideLocalUserManager(application : Application) : LocalUserManager {
        return LocalUserManagerImpl(application)

    }

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsApi::class.java)
    }



    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi , newsDao: NewsDao): NewsRepository {
        return NewsReposioryImpl(newsApi , newsDao)
    }


    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository ,
                            newsDao: NewsDao): NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository)
            ,
            searchNews = SearchNews(newsRepository),

            getArticles = GetAllArticles(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            insertArticle = InsertArticle(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }


    @Provides
    @Singleton
    fun provideNewsDataBase(
        @ApplicationContext context: Context
    ): NewsDb {
        return Room.databaseBuilder(
            context,
            NewsDb::class.java,
            "news_db"
        ).addTypeConverter(ArticleTypeConverter())
            .fallbackToDestructiveMigration().build()
    }


    @Provides
    @Singleton
    fun provideNewsDao(newsDb: NewsDb) = newsDb.newsDao


}



