package com.example.askbekotlin.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.askbekotlin.data.model.ErrorBundle
import com.example.askbekotlin.ui.login.LoginActivity
import com.example.askbekotlin.utils.DialogUtil
import timber.log.Timber

abstract class BaseVMActivity<VM : BaseViewModel> : AppCompatActivity(), LifecycleObserver {

    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        initVM()
        super.onCreate(savedInstanceState)
        startObserve()
    }

    private fun initVM() {
        providerVMClass()?.let {
            mViewModel = ViewModelProvider(this).get(it)
            mViewModel.let(lifecycle::addObserver)
        }
    }

    open fun providerVMClass(): Class<VM>? = null


    open fun startObserve() {
        mViewModel.mException.observe(this, Observer {
            it?.let {
                onError(it)
            }
        })
    }

    open fun onError(e: Throwable) {
        Timber.d(e)
        DialogUtil.showSimpleDialog(this, "", e.message!!, "確認")
    }

    fun handleApiError(e: ErrorBundle) {
        when (e.code) {
            "400", "401" -> {
                startActivity(LoginActivity.getStartIntent(this))
                finish()
            }
            "500" -> showErrorMessage("Server Error")
            "404" -> showErrorMessage("Not Found Error")
        }
    }

    fun showErrorMessage(message: String) {
        DialogUtil.showSimpleDialog(this, "", message, "確認")
    }

    override fun onDestroy() {
        mViewModel.let {
            lifecycle.removeObserver(it)
        }
        super.onDestroy()
    }
}