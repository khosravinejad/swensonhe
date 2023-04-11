package com.morteza.swensonhe.presentation.forecast.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.morteza.swensonhe.R
import com.morteza.swensonhe.databinding.ItemForecastBinding
import com.morteza.swensonhe.domain.model.Forecast
import com.morteza.swensonhe.extension.load
import com.morteza.swensonhe.utils.DateTimeHelper

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    private val items: MutableList<Forecast> = mutableListOf()
    private var itemClickListener: ((Forecast) -> (Unit))? = null

    fun updateItems(newItems: List<Forecast>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: ((Forecast) -> (Unit))? = null) {
        itemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(
            ItemForecastBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ForecastViewHolder(private val binding: ItemForecastBinding) :
        ViewHolder(binding.root) {
        fun bind(item: Forecast) {
            binding.apply {
                root.setOnClickListener {
                    itemClickListener?.invoke(item)
                }
                icon.load("https:${item.conditionIconUrl}")
                date.text = DateTimeHelper.getWeekDayForecast(item.dateInMilliSecond)
                minMax.text =
                    binding.root.context.getString(
                        R.string.min_max,
                        item.minTempC.toInt(),
                        item.maxTempC.toInt()
                    )
            }
        }
    }
}