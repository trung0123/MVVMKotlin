package com.example.askbekotlin.data.repository

import com.example.askbekotlin.data.api.RetrofitClient
import com.example.askbekotlin.data.model.*

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

    suspend fun getListWeeklyRankingLesson(page: Int, cate: String, limit: Int):BaseResponse<BaseData<Lesson>>{
        return apiCall { RetrofitClient.service.getWeeklyRankingLessons(page, cate, limit) }
    }
}