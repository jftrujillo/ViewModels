package com.rhodar.mobile.codescrum.viewmodel.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.rhodar.mobile.codescrum.viewmodel.data.model.Repo
import com.rhodar.mobile.codescrum.viewmodel.data.networking.RepoApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {

    private val repos = MutableLiveData<List<Repo>>()
    private val loadError = MutableLiveData<Boolean>()
    private val isLoading = MutableLiveData<Boolean>()
    private var repoCall: Observable<List<Repo>>? = null
    private val dis = CompositeDisposable()

    init {
        fetchRepos()
    }

    fun getRepos(): LiveData<List<Repo>> = repos

    fun getError(): LiveData<Boolean> = loadError

    fun getLoading(): LiveData<Boolean> = isLoading


    private fun fetchRepos() {
        isLoading.value = true
        repoCall = RepoApi.getInstance().getRepositories()
        dis.add(repoCall!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loadError.value = false
                    repos.value = it
                    isLoading.value = false
                }, {
                    it.printStackTrace()
                    loadError.value = true
                    isLoading.value = false
                }))
    }

    override fun onCleared() {
        super.onCleared()
        if (repoCall != null) {
            dis.dispose()
            repoCall = null
        }
    }


}