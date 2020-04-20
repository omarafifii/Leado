package com.leado.common.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.leado.common.extensions.gone
import com.leado.common.extensions.show

import org.koin.android.viewmodel.ext.android.viewModel

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    protected val viewModel: T
        get() = getFeatureViewModel()

    abstract fun getFeatureViewModel(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        viewModel.loading.observe(this, Observer {
            renderLoading(it)
        })
        viewModel.error.observe(this, Observer {
            renderError(it.message)
        })
        useView()
    }

    protected abstract fun getLayoutResource(): Int
    protected abstract fun getLoadingView(): View?
    protected abstract fun useView()

    fun renderError(message: String?) {
        message?.let {
            showMessage(it)

        }
    }

    fun showMessage(message: String) {
        Snackbar.make(
            findViewById(android.R.id.content),
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