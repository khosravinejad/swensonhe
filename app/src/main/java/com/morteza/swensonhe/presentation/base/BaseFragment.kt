package com.morteza.swensonhe.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding, VM : BaseViewModel>(private val viewModelClass: Class<VM>) :
    Fragment() {
    private var _binding: T? = null
    val binding get() = _binding
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T

    val viewModel: VM by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        setup()
        return requireNotNull(_binding).root
    }

    open fun setup() {}
}