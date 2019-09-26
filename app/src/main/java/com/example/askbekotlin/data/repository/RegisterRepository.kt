package com.example.askbekotlin.data.repository

import com.example.askbekotlin.data.api.AppService
import com.example.askbekotlin.data.api.RetrofitClient
import com.example.askbekotlin.data.model.BaseResponse
import com.example.askbekotlin.data.model.Fullname

class RegisterRepository : BaseRepository(){
    suspend fun register(fullName: Fullname, nickName:String, email:String, password:String):BaseResponse<Any>{
        val registerRequest = AppService.RegisterRequest(fullName, nickName, email, password)
        return apiCall { RetrofitClient.service.signUp(registerRequest) }
    }
}