package com.example.pagerlistapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.pagerlistapp.adapters.IAdapterFactory
import com.example.pagerlistapp.application.App
import com.example.pagerlistapp.viewmodels.MainActivityViewModel
import com.example.pagerlistapp.viewmodels.ViewModelsFactory
import javax.inject.Inject

open class BaseFragment : Fragment() {


    @Inject
    lateinit var viewModelsFactory: ViewModelsFactory

    @Inject
    lateinit var adaptersFactory: IAdapterFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance?.applicationComponent?.viewModelComponent()
                ?.with(this)
                ?.with(savedInstanceState)
                ?.build()
                ?.inject(this)
    }

}