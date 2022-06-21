package com.example.project

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field

class GetListModel : ViewModel() {

    val retrofit = RetrofitHelper.getRetrofitInstanceGson().create(RetrofitService::class.java)

    private val _result = MutableLiveData<ArrayList<Item>>()
    val result : LiveData<ArrayList<Item>>
        get() = _result

    fun get(){
        retrofit.get().enqueue(object : Callback<ArrayList<Item>>{
            override fun onResponse(call: Call<ArrayList<Item>>, response: Response<ArrayList<Item>>) {
                _result.value = response.body()
                Log.d("TAG", "${response.body()}")
            }

            override fun onFailure(call: Call<ArrayList<Item>>, t: Throwable) {
                Log.d("TAG", "List Failure : ${t.message}")
            }

        })
    }

}