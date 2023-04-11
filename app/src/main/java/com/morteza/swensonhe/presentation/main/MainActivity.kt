package com.morteza.swensonhe.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.OnBackPressedCallback
import com.morteza.swensonhe.R
import com.morteza.swensonhe.databinding.ActivityMainBinding
import com.morteza.swensonhe.presentation.base.BaseActivity
import com.morteza.swensonhe.presentation.forecast.ForecastFragment
import com.morteza.swensonhe.presentation.forecast.ForecastPagerAdapter
import com.morteza.swensonhe.presentation.search.SearchDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class.java) {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun initializeUI(savedInstanceState: Bundle?) {
        super.initializeUI(savedInstanceState)
        observe()
        setupListeners()
        viewModel.getStoredLocations()
    }

    private fun observe() {
        viewModel.storedLocations.observe(this) { list ->
            val viewPagerAdapter = ForecastPagerAdapter(this)
            binding?.viewPager?.adapter = viewPagerAdapter
            val fragments = list.mapIndexed { index, location ->
                ForecastFragment.newInstance(index, location.id)
            }
            viewPagerAdapter.addFragments(fragments)
        }
    }

    private fun setupListeners() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack()
                } else {
                    finish()
                }
            }
        })
        binding?.searchIcon?.setOnClickListener {
            openSearchScreen()
        }
    }

    private fun openSearchScreen() {
        SearchDialog {
            viewModel.saveLocation(it)
        }.show(supportFragmentManager, "SearchFragment")
    }
}