package com.example.askbekotlin.ui.forgotPassword

import androidx.lifecycle.MutableLiveData
import com.example.askbekotlin.data.model.ErrorBundle
import com.example.askbekotlin.data.repository.ForgotPasswordRepository
import com.example.askbekotlin.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ForgotPasswordViewModel : BaseViewModel() {

    private val repository by lazy { ForgotPasswordRepository() }
    val mData: MutableLiveData<Boolean> = MutableLiveData()
    val mError: MutableLiveData<ErrorBundle> = MutableLiveData()

    fun resetPassword(email: String) {
        launch {
            mLoading.value = true
            val result = withContext(Dispatchers.IO) {
                repository.resetPassword(email)
            }
            mLoading.value = false
            if (result.success) {
                mData.value = true
            } else {
                mError.value = ErrorBundle(result.code, result.msg)
            }
        }
    }
}