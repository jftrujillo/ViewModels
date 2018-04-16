package com.rhodar.mobile.codescrum.viewmodel.home

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rhodar.mobile.codescrum.viewmodel.R
import com.rhodar.mobile.codescrum.viewmodel.data.model.Repo
import com.rhodar.mobile.codescrum.viewmodel.databinding.ListRepoTemplateBinding

class RepoListAdapter(val viewModel: ListViewModel, val lifecycleOwner : LifecycleOwner)
    : RecyclerView.Adapter<RepoListAdapter.Companion.RepoViewHolder>() {

    private val data = mutableListOf<Repo>()

    init { viewModel.getRepos().observe(lifecycleOwner, Observer {
            data.clear()
            if (it != null){
                data.addAll(it)
                notifyDataSetChanged()
            }
        })
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = data[position].id

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_repo_template,parent,false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.binding.repo = data[position]
    }


    companion object {
        class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val binding =  ListRepoTemplateBinding.bind(view)
        }
    }
}