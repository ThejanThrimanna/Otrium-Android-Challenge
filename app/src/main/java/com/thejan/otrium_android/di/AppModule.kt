package com.thejan.otrium_android.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.apollographql.apollo.ApolloClient
import com.thejan.otrium_android.GitHubApplication
import com.thejan.otrium_android.config.AppBus
import com.thejan.otrium_android.config.AuthorizationInterceptor
import com.thejan.otrium_android.config.RxBus
import com.thejan.otrium_android.database.GitHubDatabase
import com.thejan.otrium_android.helper.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Reusable
    internal fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }

    @Provides
    @Reusable
    internal fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.builder()
            .serverUrl(BASE_URL)
            .okHttpClient(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideAppBus(): RxBus {
        return AppBus(EventBus.getDefault())
    }

    @Provides
    @Reusable
    internal fun provideRoomDatabase(): GitHubDatabase {
        return Room.databaseBuilder(GitHubApplication.instance, GitHubDatabase::class.java, "github_demo")
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .build()
    }


}