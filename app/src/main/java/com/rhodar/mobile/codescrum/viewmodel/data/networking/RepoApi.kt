package com.rhodar.mobile.codescrum.viewmodel.data.networking

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RepoApi {
    companion object {
        const val BASE_URL = "https://api.github.com/"
        private var retrofit: Retrofit? = null
        private var repoService: RepoService? = null
        private fun initializeRetrofit() {
            retrofit = Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }

        fun getInstance(): RepoService {
            if (repoService != null) return repoService!!
            if (retrofit == null) initializeRetrofit()
            repoService = retrofit!!.create(RepoService::class.java)
            return repoService!!
        }
    }
}