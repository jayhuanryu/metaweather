package com.example.metaweather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.metaweather.R
import com.example.metaweather.repository.DetailsActivityRespository
import com.example.metaweather.viewmodel.DetailsActivityViewModel
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {


    private lateinit var viewModel: DetailsActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        viewModel = ViewModelProvider(this).get(DetailsActivityViewModel::class.java)

        if (intent.hasExtra("name")) {
            locationTextView.text = intent.getStringExtra("name")
        }
        if (intent.hasExtra("Location")) {
            // do a network call
            Log.d("Location", intent.getIntExtra("Location", 0).toString())

            val location = intent.getIntExtra("Location", 0)
            viewModel.getWeather(location)
        }

        viewModel.showProgress.observe(this, Observer {
            if (it) {
                pgDeatils.visibility = View.VISIBLE
            } else {
                pgDeatils.visibility = View.GONE
            }
        })

        viewModel.response.observe(this, Observer {
            if (it != null) {
                theTemp.text = it.consolidated_weather[0].the_temp.toString()
                maxTemp.text = it.consolidated_weather[0].max_temp.toString()
                minTemp.text = it.consolidated_weather[0].min_temp.toString()
                weatherStateName.text = it.consolidated_weather[0].weather_state_name.toString()
            }
        })


    }
}