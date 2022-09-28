package com.hazem.newsapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hazem.newsapp.data.model.Articles
import com.hazem.newsapp.data.model.News
import com.hazem.newsapp.data.remote.APIs
import com.hazem.newsapp.data.remote.RetrofitClient
import com.hazem.newsapp.util.NetworkResult
import kotlinx.coroutines.launch
import retrofit2.Response

class ScienceNewsViewModel:ViewModel() {
private lateinit var response:Response<News>
private val retrofit=RetrofitClient().getRetrofit()
   private val apis: APIs =retrofit!!.create(APIs::class.java)
    private val mutableLiveData:MutableLiveData<NetworkResult<News>>by lazy {
        MutableLiveData()
    }
    var liveData: LiveData<NetworkResult<News>> = mutableLiveData
fun getNews(){
viewModelScope.launch {
    response=apis.getNews("","Science")
    when{
   response.isSuccessful->mutableLiveData.postValue(NetworkResult.Success(response.body()!!))
   response.code()!=200->{mutableLiveData.postValue(NetworkResult.Error(message = response.code().toString()))}
    }

}



}

}