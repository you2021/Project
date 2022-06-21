package com.example.project

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field

class SaveViewModel : ViewModel() {

    val retrofit = RetrofitHelper.getRetrofitInstanceGson().create(RetrofitService::class.java)

    private val _result = MutableLiveData<ResultItem>()
    val result : LiveData<ResultItem>
        get() = _result

    fun save(name:String,serial:String,state:String,type:String,user:String,dt_reg:String,dt_cor:String ){
        retrofit.save(name, serial, state, type, user).enqueue(object : Callback<ResultItem>{
            override fun onResponse(call: Call<ResultItem>, response: Response<ResultItem>) {
                _result.value = response.body()
                Log.d("TAG", response.body().toString())
            }

            override fun onFailure(call: Call<ResultItem>, t: Throwable) {
                Log.d("TAG", "Failure : ${t.message}")
            }

        })
    }

}