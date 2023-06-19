package com.dgheorghe.userpost.di

import com.dgheorghe.userpost.api.RetrofitUserInstance
import com.dgheorghe.userpost.api.UserListService
import com.dgheorghe.userpost.api.UserPostService
import com.dgheorghe.userpost.repository.UserPostRepository
import com.dgheorghe.userpost.repository.UserPostRepositoryImpl
import com.dgheorghe.userpost.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RetrofitUserInstance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserListService(retrofit: Retrofit): UserListService =
        retrofit.create(UserListService::class.java)

    @Provides
    @Singleton
    fun provideUserPostService(retrofit: Retrofit): UserPostService =
        retrofit.create(UserPostService::class.java)

    @Provides
    @Singleton
    fun provideUsersRepository(userListService: UserListService): UsersRepository =
        UsersRepository(userListService)

    @Provides
    @Singleton
    fun provideUserPostRepository(userPostService: UserPostService): UserPostRepository =
        UserPostRepositoryImpl(userPostService)
}