package com.hazem.newsapp.presentation.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.hazem.newsapp.*
import com.hazem.newsapp.data.model.Articles
import com.hazem.newsapp.presentation.ui.adapter.NewsItemAdapter
import com.hazem.newsapp.presentation.viewmodel.TechnologyNewsViewModel
import com.hazem.newsapp.util.NetworkResult


class TechnologyFragment : Fragment(R.layout.fragment_technology) {
    private val technologyNewsViewModel: TechnologyNewsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var list:List<Articles>
        technologyNewsViewModel.getNews()
        val recyclerView = view as RecyclerView
        technologyNewsViewModel.liveData.observe(viewLifecycleOwner){
            it.let { response ->
                when(response){
                    is NetworkResult.Success->{
                        list=response.data!!.articles!!
                        recyclerView.adapter = NewsItemAdapter(list)
                    }
                    is NetworkResult.Error-> Toast.makeText(requireContext(),response.message,
                        Toast.LENGTH_LONG)
                    else -> {

                    }

                }


            }
        }

    }

}