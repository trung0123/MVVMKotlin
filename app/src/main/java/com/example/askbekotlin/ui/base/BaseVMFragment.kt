package com.example.askbekotlin.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.askbekotlin.data.model.ErrorBundle
import com.example.askbekotlin.ui.login.LoginActivity
import com.example.askbekotlin.utils.DialogUtil
import timber.log.Timber

abstract class BaseVMFragment<VM : BaseViewModel> : Fragment() {

    lateinit var mViewModel: VM
    lateinit var mActivity: AppCompatActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVM()
        initData()
        startObserve()
        super.onViewCreated(view, savedInstanceState)
    }

    open fun startObserve() {
        mViewModel.mException.observe(this, Observer { it?.let { onError(it) } })
    }

    open fun initData(){}

    open fun onError(e: Throwable) {
        Timber.d(e)
        DialogUtil.showSimpleDialog(mActivity, "", e.message!!, "確認")
    }

    fun handleApiError(e: ErrorBundle) {
        when (e.code) {
            "400", "401" -> {
                startActivity(LoginActivity.getStartIntent(mActivity))
                mActivity.finish()
            }
            "500" -> showErrorMessage("Server Error")
            "404" -> showErrorMessage("Not Found Error")
        }
    }

    fun showErrorMessage(message: String) {
        DialogUtil.showSimpleDialog(mActivity, "", message, "確認")
    }

    private fun initVM() {
        providerVMClass()?.let {
            mViewModel = ViewModelProvider(this).get(it)
            mViewModel.let(lifecycle::addObserver)
        }
    }

    open fun providerVMClass(): Class<VM>? = null

    override fun onDestroy() {
        mViewModel.let {
            lifecycle.removeObserver(it)
        }
        super.onDestroy()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AppCompatActivity) {
            this.mActivity = context
        }
    }

    fun getBaseActivity(): AppCompatActivity {
        return mActivity
    }

}