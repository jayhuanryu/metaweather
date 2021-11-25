package com.example.metaweather.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.metaweather.model.Location
import com.example.metaweather.network.BASE_URL
import com.example.metaweather.network.WeatherNetwork
import com.example.metaweather.room.WeatherDatabase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivityRespository(var application: Application) {

    val showProgress = MutableLiveData<Boolean>()

    val locationList = MutableLiveData<List<Location>>()

    fun changeState() {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }

    fun searchLocation(location: String) {
        showProgress.value = true
        //network Call

        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(WeatherNetwork::class.java)

        val call = service.getLocation(location)
        call.enqueue(object : Callback<List<Location>> {
            override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application, "Error while getting dsata", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<Location>>,
                response: Response<List<Location>>
            ) {
                Log.d("SearchRespository ", "Response${Gson().toJson(response.body())}")
                showProgress.value = false
                insert(response.body()!!)
                locationList.value = response.body()

            }
        })

    }

    fun insert(list: List<Location>) = CoroutineScope(Dispatchers.Main).launch {
        WeatherDatabase.get(application).getWeatherDao().insertLocation(list)
    }
}
