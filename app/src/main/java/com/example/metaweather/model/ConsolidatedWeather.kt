package com.example.metaweather.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ConsolidatedWeather(
    @PrimaryKey val id: Long,
    val the_temp: Int,
    val min_temp: Int,
    val max_temp: Int,
    val weather_state_name: String,
    val weather_state_abbr: String
)