package com.example.metaweather.model

class WeatherResponse(
    val consolidated_weather: List<ConsolidatedWeather>,
    val time: String,
    val title: String,
    val woeid: Int
)