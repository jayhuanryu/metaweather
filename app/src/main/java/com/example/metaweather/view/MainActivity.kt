package com.example.metaweather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.metaweather.R
import com.example.metaweather.adapter.SearchAdapter
import com.example.metaweather.room.WeatherDatabase
import com.example.metaweather.viewmodel.SearchActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SearchActivityViewModel
    private lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_to_replace)
        WeatherDatabase.get(application)
        viewModel = ViewModelProvider(this).get(SearchActivityViewModel::class.java)

        searchImageView.setOnClickListener {
            viewModel.searchLocation(searchEditText.text.toString())
        }

        viewModel.locationList.observe(this, Observer {
            adapter.setLocation(it)
        })
        adapter = SearchAdapter(this)
        searchBtn.adapter = adapter
    }

}