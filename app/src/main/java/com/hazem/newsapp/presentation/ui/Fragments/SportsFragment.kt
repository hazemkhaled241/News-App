package com.hazem.newsapp.presentation.ui.Fragments

import android.os.Bundle

import android.view.View

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.hazem.newsapp.*
import com.hazem.newsapp.data.model.Articles

import com.hazem.newsapp.presentation.ui.adapter.NewsItemAdapter

import com.hazem.newsapp.presentation.viewmodel.SportsNewsViewModels
import com.hazem.newsapp.util.NetworkResult


class SportsFragment : Fragment(R.layout.fragment_sports) {

    private val sportsNewsViewModel: SportsNewsViewModels by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var list:List<Articles>
        sportsNewsViewModel.getNews()
        val recyclerView = view as RecyclerView
        sportsNewsViewModel.liveData.observe(viewLifecycleOwner){
            it.let { response ->
                when(response){
                    is NetworkResult.Success->{
                        list=response.data!!.articles!!
                        recyclerView.adapter = NewsItemAdapter(list)
                    }
                    is NetworkResult.Error-> Toast.makeText(requireContext(),response.message,
                        Toast.LENGTH_LONG).show()
                    else -> {

                    }

                }


            }
        }


    }


}