package com.morteza.swensonhe.presentation.search

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.morteza.swensonhe.databinding.DialogSearchBinding
import com.morteza.swensonhe.domain.model.Location
import com.morteza.swensonhe.presentation.base.BaseDialogFragment
import com.morteza.swensonhe.presentation.search.recyclerview.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchDialog(private val onSelectedLocation: ((Location) -> (Unit))? = null) :
    BaseDialogFragment<DialogSearchBinding, SearchViewModel>(SearchViewModel::class.java) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> DialogSearchBinding
        get() = DialogSearchBinding::inflate

    @Inject
    lateinit var searchAdapter: SearchAdapter

    override fun setup() {
        super.setup()
        observe()
        setupListeners()
        binding?.apply {
            val layoutManager =  LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            searchRecyclerview.adapter = searchAdapter
            if (searchRecyclerview.layoutManager == null) {
                searchRecyclerview.layoutManager = layoutManager
            }
        }
        handleBottomView(true)
    }

    private fun observe() {
        viewModel.locations.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                searchAdapter.updateItems(it.map { location ->
                    SearchListItem.LocationItem(location)
                })
            } else {
                searchAdapter.updateItems(listOf(SearchListItem.Empty))
            }
            handleBottomView(it.isNullOrEmpty())
        }
        searchAdapter.setOnItemClickListener {
            onSelectedLocation?.invoke(it)
            dismiss()
        }
    }

    private fun setupListeners() {
        binding?.apply {
            backIcon.setOnClickListener {
                dismiss()
            }
            search.doAfterTextChanged {
                it?.toString()?.let { query ->
                    if (query.length >= 3) {
                        viewModel.searchLocation(query)
                        searchRecyclerview.isVisible = true
                    } else {
                        handleBottomView(true)
                        searchRecyclerview.isVisible = false
                    }
                }
            }
        }
    }

    private fun handleBottomView(noItem: Boolean) {
        binding?.apply {
            shrinkIcon.isVisible = !noItem
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }
}