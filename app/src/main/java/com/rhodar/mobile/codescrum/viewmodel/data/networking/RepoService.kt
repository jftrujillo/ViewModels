package com.rhodar.mobile.codescrum.viewmodel.data.networking


import com.rhodar.mobile.codescrum.viewmodel.data.model.Repo
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface RepoService {

    @GET("orgs/Google/repos")
    fun getRepositories() : Observable<List<Repo>>
}