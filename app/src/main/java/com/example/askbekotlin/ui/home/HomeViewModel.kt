package com.example.askbekotlin.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.askbekotlin.data.model.*
import com.example.askbekotlin.data.repository.HomeRepository
import com.example.askbekotlin.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewModel : BaseViewModel() {

    private val repository by lazy { HomeRepository() }
    val mData: MutableLiveData<DataHome> = MutableLiveData()
    val mOverallRankLessons: MutableLiveData<List<Lesson>> = MutableLiveData()
    val mRecommendLessons: MutableLiveData<List<Lesson>> = MutableLiveData()
    val mNewestLessons: MutableLiveData<List<Lesson>> = MutableLiveData()
    val mNextLesson: MutableLiveData<Lesson> = MutableLiveData()
    val mWeeklyRankLesson: MutableLiveData<List<Lesson>> = MutableLiveData()
    val mCategories: MutableLiveData<List<Category>> = MutableLiveData()
    val mDataTotal: MutableLiveData<DataTotal> = MutableLiveData()
    val mError: MutableLiveData<ErrorBundle> = MutableLiveData()

    fun loadHome() {
        launch {
            val result = withContext(Dispatchers.IO) {
                repository.loadHome()
            }
            if (result.success) {
                // Xu ly thanh cong
                mData.value = result.data
                mOverallRankLessons.value = result.data.overallRankLessons
                mRecommendLessons.value = result.data.recommendLessons
                mNewestLessons.value = result.data.newestLessons
                mNextLesson.value = result.data.nextLesson
                mWeeklyRankLesson.value = result.data.weeklyRankLessons
                mCategories.value = result.data.categories
            } else {
                // Xu ly that bai
                mError.value = ErrorBundle(result.code, result.msg)
            }
        }
    }

    fun getNumOfNotifies(type1: String, type2: String, type3: String, type4: String) {
        launch {
            val result = withContext(Dispatchers.IO) {
                repository.getNumOfNotifies(type1, type2, type3, type4)
            }
            if (result.success) {
                mDataTotal.value = result.data
            } else {
                mError.value = ErrorBundle(result.code, result.msg)
            }
        }
    }

    fun loadHomeByCategory(cateId: String) {
        launch {
            val result = withContext(Dispatchers.IO) {
                repository.loadHomeByCategory(cateId)
            }
            if (result.success) {
                mOverallRankLessons.value = result.data.overallRankLessons
                mRecommendLessons.value = result.data.recommendLessons
                mNewestLessons.value = result.data.newestLessons
                mNextLesson.value = result.data.nextLesson
                mWeeklyRankLesson.value = result.data.weeklyRankLessons
            } else {
                mError.value = ErrorBundle(result.code, result.msg)
            }
        }
    }

}