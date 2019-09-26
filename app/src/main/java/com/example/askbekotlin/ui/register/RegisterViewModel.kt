package com.example.askbekotlin.ui.register

import com.example.askbekotlin.data.model.Fullname
import com.example.askbekotlin.data.repository.RegisterRepository
import com.example.askbekotlin.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterViewModel : BaseViewModel(){

    private val repository by lazy { RegisterRepository() }

    fun register(firstName:String, lastName:String, nickName:String, email:String, password:String) {
        launch {
            val result = withContext(Dispatchers.IO) {
                val fullName = Fullname(firstName, lastName)
                repository.register(fullName, nickName, email, password)
            }
        }
    }

}