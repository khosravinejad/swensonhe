package com.morteza.swensonhe.presentation.search

import com.morteza.swensonhe.domain.model.Location

sealed class SearchListItem {
    data class LocationItem(val location: Location) : SearchListItem()
    object Empty : SearchListItem()
}