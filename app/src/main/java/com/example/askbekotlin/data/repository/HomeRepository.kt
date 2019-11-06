package com.example.askbekotlin.data.repository

import com.example.askbekotlin.data.api.RetrofitClient
import com.example.askbekotlin.data.model.BaseResponse
import com.example.askbekotlin.data.model.DataHome
import com.example.askbekotlin.data.model.DataTotal

class HomeRepository : BaseRepository() {

    suspend fun loadHome(): BaseResponse<DataHome> {
        return apiCall { RetrofitClient.service.getHome() }
    }

    suspend fun getNumOfNotifies(
        type1: String,
        type2: String,
        type3: String,
        type4: String
    ): BaseResponse<DataTotal> {
        return apiCall { RetrofitClient.service.getNumOfNotifies(type1, type2, type3, type4) }
    }

    suspend fun loadHomeByCategory(cateId: String): BaseResponse<DataHome> {
        return apiCall { RetrofitClient.service.getHomeByCategory(cateId) }
    }
}