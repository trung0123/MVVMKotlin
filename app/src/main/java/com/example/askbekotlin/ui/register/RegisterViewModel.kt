package com.example.askbekotlin.ui.register

import androidx.lifecycle.MutableLiveData
import com.example.askbekotlin.data.model.DataLogin
import com.example.askbekotlin.data.model.ErrorBundle
import com.example.askbekotlin.data.model.Fullname
import com.example.askbekotlin.data.repository.RegisterRepository
import com.example.askbekotlin.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class RegisterViewModel : BaseViewModel(){

    private val repository by lazy { RegisterRepository() }
    val mData: MutableLiveData<Boolean> = MutableLiveData()
    val mError: MutableLiveData<ErrorBundle> = MutableLiveData()

    fun register(firstName:String, lastName:String, nickName:String, email:String, password:String) {
        launch {
            mProgress.value = true
            val result = withContext(Dispatchers.IO) {
                val fullName = Fullname(firstName, lastName)
                repository.register(fullName, nickName, email, password)
            }
            mProgress.value = false
            if(result.success){
                mData.value = true
            } else {
                mError.value = ErrorBundle(result.code, result.msg)
            }
        }
    }

}