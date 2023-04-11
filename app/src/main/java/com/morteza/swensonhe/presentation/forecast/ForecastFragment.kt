package com.morteza.swensonhe.presentation.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.morteza.swensonhe.R
import com.morteza.swensonhe.databinding.FragmentForecastBinding
import com.morteza.swensonhe.extension.load
import com.morteza.swensonhe.presentation.base.BaseFragment
import com.morteza.swensonhe.presentation.forecast.recyclerview.ForecastAdapter
import com.morteza.swensonhe.utils.DateTimeHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ForecastFragment :
    BaseFragment<FragmentForecastBinding, ForecastViewModel>(ForecastViewModel::class.java) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentForecastBinding
        get() = FragmentForecastBinding::inflate

    @Inject
    lateinit var forecastAdapter: ForecastAdapter

    override fun setup() {
        super.setup()
        observe()

        binding?.apply {
            val layoutManager = GridLayoutManager(requireContext(), 3)
            forecastRecyclerview.adapter = forecastAdapter
            forecastRecyclerview.layoutManager = layoutManager
        }

        val position = arguments?.getInt(POSITION_ARG)
        val locationId = arguments?.getInt(LOCATION_ID_ARG)
        locationId?.let {
            viewModel.getForecastsByLocation(it)
        }
    }

    private fun observe() {
        viewModel.location.observe(viewLifecycleOwner) {
            binding?.apply {
                name.text = it.name
            }
        }

        viewModel.fullForecast.observe(viewLifecycleOwner) {
            forecastAdapter.updateItems(it.forecast)
            binding?.apply {
                currentTime.text = DateTimeHelper.getFormattedCurrentLocalTime()
                currentDate.text = DateTimeHelper.getFormattedCurrentLocalDate()
                conditionIcon.load(it.currentWeather.httpUrl)
                val dayOrNight = if (it.currentWeather.isDay == 0) {
                    "night"
                } else {
                    "day"
                }
                conditionText.text = getString(
                    R.string.condition_text,
                    it.currentWeather.conditionText.lowercase(),
                    dayOrNight
                )
                temp.text = it.currentWeather.tempF.toString()
                windTextView.text = getString(
                    R.string.wind_mph,
                    it.currentWeather.windMph.toInt()
                )
                humidityTextView.text = getString(
                    R.string.humidity_percentage,
                    it.currentWeather.humidity
                )
            }
        }
    }

    companion object {
        const val POSITION_ARG = "position_arg"
        const val LOCATION_ID_ARG = "location_id_arg"

        fun newInstance(position: Int, locationId: Int) = ForecastFragment().apply {
            arguments = Bundle().apply {
                putInt(POSITION_ARG, position)
                putInt(LOCATION_ID_ARG, locationId)
            }
        }
    }
}