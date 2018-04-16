package com.rhodar.mobile.codescrum.viewmodel.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.rhodar.mobile.codescrum.viewmodel.R

class ListFragment : Fragment() {

    private var unbinder: Unbinder? = null

    @BindView(R.id.recycler_view)
    lateinit var listView: RecyclerView

    @BindView(R.id.txt_error)
    lateinit var errorTextView: TextView

    @BindView(R.id.loading_view)
    lateinit var loadingView: View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.screen_list, container, false)
        unbinder = ButterKnife.bind(this, view)
        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (unbinder != null) {
            unbinder?.unbind()
            unbinder = null
        }
    }
}