package com.morteza.swensonhe.presentation.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseDialogFragment<T : ViewBinding, VM : BaseViewModel>(private val viewModelClass: Class<VM>) :
    DialogFragment() {
    private var _binding: T? = null
    protected val binding get() = _binding
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T

    val viewModel: VM by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireActivity()).apply {
            window?.apply {
                setGravity(Gravity.TOP)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, height)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}