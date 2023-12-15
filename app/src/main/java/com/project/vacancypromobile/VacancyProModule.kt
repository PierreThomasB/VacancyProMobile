package com.project.vacancypromobile

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.project.vacancypromobile.datas.ActivityRepository
import com.project.vacancypromobile.datas.ChatRepository
import com.project.vacancypromobile.datas.MeteoRepository
import com.project.vacancypromobile.datas.PeriodRepository
import com.project.vacancypromobile.datas.UserRepository
import com.project.vacancypromobile.services.ApiService
import com.project.vacancypromobile.services.AuthInterceptor
import com.project.vacancypromobile.utils.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")
@Module
@InstallIn(SingletonComponent::class)
class VacancyProModule {
    private val BASE_URL = "https://porthos-intra.cg.helmo.be/e190476/"
    //private val BASE_URL = "http://10.0.2.2:5053/"
    private val client = OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS)
        .build()
    @Singleton
    @Provides
    fun provideApiService (authInterceptor: AuthInterceptor) : ApiService {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(authInterceptor).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
    @Singleton
    @Provides
    fun provideAuthInterceptor(tokenManager: TokenManager): AuthInterceptor = AuthInterceptor(tokenManager)

    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager = TokenManager(context)

    @Singleton
    @Provides
    fun provideUserRepository(tokenManager: TokenManager, apiService: ApiService): UserRepository = UserRepository(tokenManager, apiService)

    @Singleton
    @Provides
    fun providePeriodRepository(apiService: ApiService): PeriodRepository = PeriodRepository(apiService)

    @Singleton
    @Provides
    fun provideActivityRepository(apiService: ApiService): ActivityRepository = ActivityRepository(apiService)

    @Singleton
    @Provides
    fun provideMeteoRepository(apiService: ApiService): MeteoRepository = MeteoRepository(apiService)

    @Singleton
    @Provides
    fun provideChatRepository(apiService: ApiService): ChatRepository = ChatRepository(apiService)

}