package com.leado.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.leado.common.extensions.gone
import com.leado.common.extensions.show
import com.leado.common.viewstate.BaseViewState
import com.leado.common.viewstate.consume
import org.koin.android.viewmodel.ext.android.viewModel

abstract class BaseFragment< T : BaseViewModel> : Fragment() {
    private val _viewModel: BaseViewModel by viewModel()

    @Suppress("UNCHECKED_CAST")
    protected val viewModel: T
        get() = _viewModel as T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutResource(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loading.observe(this, Observer {
            renderLoading(it)
        })
        viewModel.error.observe(this, Observer {
            renderError(it.message)
        })
        useView(view)
    }

    protected abstract fun getLayoutResource(): Int
    protected abstract fun getLoadingView(): View?
    protected abstract fun useView(view: View)


    fun renderError(message: String?) {
        message?.let {
            showMessage(it)

        }
    }

    fun showMessage(message: String) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        )
            .show()
    }


    fun renderLoading(isLoading: Boolean) {
        if (isLoading)
            getLoadingView()?.show()
        else
            getLoadingView()?.gone()
    }

}