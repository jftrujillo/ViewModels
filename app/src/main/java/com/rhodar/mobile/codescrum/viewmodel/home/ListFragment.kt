package com.rhodar.mobile.codescrum.viewmodel.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.rhodar.mobile.codescrum.viewmodel.R
import kotlinx.android.synthetic.main.screen_list.*

class ListFragment : Fragment() {

    private var unbinder: Unbinder? = null

    private lateinit var viewModel: ListViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.screen_list, container, false)
        unbinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        recycler_view.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        recycler_view.adapter = RepoListAdapter(viewModel,this)
        recycler_view.layoutManager = LinearLayoutManager(context)
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.getRepos().observe(this, Observer{
            if (it != null) recycler_view.visibility = View.VISIBLE
        })
        viewModel.getError().observe(this, Observer {
            if (it != null && it) {
                txt_error.visibility = View.VISIBLE
                recycler_view.visibility = View.GONE
                txt_error.setText(R.string.api_error_message)
            }
            else {
                txt_error.visibility = View.GONE
                txt_error.text = null
            }
        })

        viewModel.getLoading().observe(this, Observer {
            if (it != null && !it){
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                txt_error.visibility = View.GONE
                recycler_view.visibility = View.VISIBLE
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (unbinder != null) {
            unbinder?.unbind()
            unbinder = null
        }
    }
}