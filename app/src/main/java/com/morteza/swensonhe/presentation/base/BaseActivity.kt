package com.morteza.swensonhe.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding, VM : BaseViewModel>(private val viewModelClass: Class<VM>) :
    AppCompatActivity() {

    abstract val bindingInflater: (LayoutInflater) -> T
    var binding: T? = null

    val viewModel: VM by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    open fun initializeUI(savedInstanceState: Bundle?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(binding).root)
        initializeUI(savedInstanceState)
    }
}