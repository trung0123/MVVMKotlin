package com.example.askbekotlin.ui.login

import androidx.lifecycle.MutableLiveData
import com.example.askbekotlin.data.model.DataLogin
import com.example.askbekotlin.data.model.ErrorBundle
import com.example.askbekotlin.data.repository.LoginRepository
import com.example.askbekotlin.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel : BaseViewModel() {

    private val repository by lazy { LoginRepository() }
    val mLoginData: MutableLiveData<DataLogin> = MutableLiveData()
    val mError: MutableLiveData<ErrorBundle> = MutableLiveData()

    fun login(username: String, password: String, token: String) {
        launch {
            mLoading.value = true
            val result = withContext(Dispatchers.IO) {
                repository.login(username, password, token)
            }
            mLoading.value = false
            if (result.success) {
                // Xu ly thanh cong
                mLoginData.value = result.data
            } else {
                // Xu ly that bai
                mError.value = ErrorBundle(result.code, result.msg)
            }
        }
    }
}

