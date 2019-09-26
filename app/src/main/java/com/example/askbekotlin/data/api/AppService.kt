package com.example.askbekotlin.data.api

import com.example.askbekotlin.data.model.BaseResponse
import com.example.askbekotlin.data.model.DataLogin
import com.example.askbekotlin.data.model.Fullname
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AppService {
    companion object {
        const val BASE_URL = "https://api.askbe.net/"
    }

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("auth_token") tokenFCM: String
    ): BaseResponse<DataLogin>

//    @GET("user/logout")
//    abstract fun logout(): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("user/fb-callback")
//    abstract fun facebookLogin(
//        @Field("email") email: String,
//        @Field("fullname") fullName: String,
//        @Field("ref_id") ref_id: String,
//        @Field("fb_token") fb_token: String,
//        @Field("auth_token") tokenFCM: String
//    ): Call<BaseResponse<DataLogin>>

    @POST("user/signup")
    suspend fun signUp(@Body registerRequest: RegisterRequest): BaseResponse<Any>

    class RegisterRequest(
        private val fullname: Fullname,
        private val nickname: String,
        private val email: String,
        private val password: String
    )

//    @Multipart
//    @POST("retrofit/upload")
//    abstract fun upload(@Part file: MultipartBody.Part): Call<BaseResponse>
//
//    @Multipart
//    @POST("user/id-card")
//    abstract fun uploadIdCard(
//        @Part file: MultipartBody.Part,
//        @PartMap partMap: Map<String, RequestBody>
//    ): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("user/reset-password")
//    abstract fun resetPassword(@Field("email") email: String): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("user/change-password")
//    abstract fun changePassword(
//        @Field("password") pass: String,
//        @Field("new_password") newPass: String
//    ): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("user/edit-email")
//    abstract fun changeEmail(
//        @Field("email") email: String,
//        @Field("password") pass: String,
//        @Field("news_letter") news: Boolean
//    ): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("user/credit-card-pjp")
//    abstract fun registerCreditCard(
//        @Field("number") number: String,
//        @Field("owner_name") name: String,
//        @Field("token") token: String,
//        @Header("Csrf-Token") CsrfToken: String
//    ): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("user/bank-acc")
//    abstract fun updateBankInfo(
//        @Field("bank_id") bankId: String,
//        @Field("agency_id") agencyId: String,
//        @Field("type") type: String,
//        @Field("number") number: String,
//        @Field("owner_name") name: String,
//        @Field("owner_kataname") kataname: String
//    ): Call<BaseResponse>
//
//    @GET("user/id-card")
//    abstract fun getIdCard(): Call<BaseResponse<IDCard>>
//
//    @GET("user/get-calendar-evt")
//    abstract fun getCalendarEvent(
//        @Query("start_time") start: String,
//        @Query("end_time") end: String
//    ): Call<BaseResponse<HashMap<String, EventItem>>>
//
//    @GET("user/credit-card-pjp")
//    abstract fun getCreditCard(): Call<BaseResponse<CreditCard>>
//
//    @GET("user/bank-acc")
//    abstract fun getBankInfo(): Call<BaseResponse<BankInfo>>
//
//    @GET("user/get-pay-token")
//    abstract fun getLinkPay(): Call<BaseResponse<Link>>
//
//    @GET("user/schedule")
//    abstract fun getSchedule(): Call<BaseResponse<List<Lesson>>>
//
//    @GET("user/good-bad?limit=20")
//    abstract fun getGoodBad(
//        @Query("page") page: Int,
//        @Query("content") content: String,
//        @Query("type") type: String
//    ): Call<BaseResponse<BaseData<GoodBad>>>
//
//    @GET("user/follow?limit=20")
//    abstract fun getUserFollow(@Query("page") page: Int): Call<BaseResponse<BaseData<DataFollow>>>
//
//    @GET("/user/follower?limit=20")
//    abstract fun getUserFollower(@Query("page") page: Int): Call<BaseResponse<BaseData<DataFollow>>>
//
//    @GET("user/get-review-data")
//    abstract fun getReviewData(): Call<BaseResponse<HomeReview>>
//
//    @GET("home")
//    abstract fun getHome(): Call<BaseResponse<DataHome>>
//
//    @GET("home")
//    abstract fun getHomeByCategory(@Query("bcate") id: String): Call<BaseResponse<DataHome>>
//
//    @GET("home/get-numof-notifies")
//    abstract fun getNumOfNotifies(
//        @Query("types[1]") type1: String, @Query("types[2]") type2: String,
//        @Query("types[3]") type3: String, @Query("types[4]") type4: String
//    ): Call<BaseResponse<DataTotal>>
//
//    @GET("teacher-category?get_tree=2")
//    abstract fun getTeacherCategory(): Call<BaseResponse<List<CateTeacher>>>
//
//    @GET("teacher-category?get_tree=1")
//    abstract fun getTeacherCategory1(): Call<BaseResponse<HashMap<String, CateTeacherMap>>>
//
//    @GET("area")
//    abstract fun getArea(): Call<BaseResponse<List<Area>>>
//
//    @GET("home/get-notifies?limit=20")
//    abstract fun getNotify(@Query("type") type: String, @Query("page") page: Int): Call<BaseResponse<BaseData<NotifyItem>>>
//
//    @FormUrlEncoded
//    @POST("home/delete-notify")
//    abstract fun deleteNotify(@Field("id") id: String): Call<BaseResponse>
//
//    @GET("teacher/register")
//    abstract fun getTeacherInfo(): Call<BaseResponse<DataTeacherRegister>>
//
//    @GET("lesson?limit=20")
//    abstract fun getLessonByCategory(
//        @Query("keyword") keyword: String,
//        @Query("big_cate_id") bigId: String,
//        @Query("middle_cate_id") midId: String,
//        @Query("small_cate_id") smallId: String,
//        @Query("page") page: Int,
//        @QueryMap sort: Map<String, String>,
//        @QueryMap filter: Map<String, Double>,
//        @QueryMap filterMethod: Map<String, String>
//    ): Call<BaseResponse<BaseData<Lesson>>>
//
//    @GET("lesson/my?limit=20")
//    abstract fun getMyLesson(@Query("page") page: Int): Call<BaseResponse<BaseData<Lesson>>>
//
//    @GET("lesson?limit=20&type=monthly_ranking")
//    abstract fun getPopularLessons(@Query("page") page: Int, @Query("big_cate_id") cate: String): Call<BaseResponse<BaseData<Lesson>>>
//
//    @GET("lesson?limit=20&type=recommend")
//    abstract fun getRecommendLessons(@Query("page") page: Int, @Query("big_cate_id") cate: String): Call<BaseResponse<BaseData<Lesson>>>
//
//    @GET("lesson?limit=20&type=new_ranking")
//    abstract fun getNewArrivalLessons(@Query("page") page: Int, @Query("big_cate_id") cate: String): Call<BaseResponse<BaseData<Lesson>>>
//
//    @GET("lesson?type=weekly_ranking")
//    abstract fun getWeeklyRankingLessons(
//        @Query("page") page: Int, @Query("big_cate_id") cate: String,
//        @Query("limit") limit: Int
//    ): Call<BaseResponse<BaseData<Lesson>>>
//
//    @GET("lesson?limit=20&type=point_ranking&type_order=desc")
//    abstract fun getUserDetailLessons(@Query("user_id") id: String, @Query("page") page: Int): Call<BaseResponse<BaseData<Lesson>>>
//
//    @GET("lesson/selling?limit=20")
//    abstract fun getLessonSelling(@Query("page") page: Int): Call<BaseResponse<DataLessonSelling>>
//
//    @GET("lesson/buying?limit=20")
//    abstract fun getLessonBuying(@Query("page") page: Int): Call<BaseResponse<DataLessonSelling>>
//
//    @GET("lesson/bought?limit=20")
//    abstract fun getLessonBought(@Query("page") page: Int): Call<BaseResponse<BaseData<Order>>>
//
//    @GET("lesson/sold?limit=20")
//    abstract fun getLessonSold(@Query("page") page: Int): Call<BaseResponse<BaseData<Order>>>
//
//    @GET("lesson/detail")
//    abstract fun getLesson(@Query("lss_id") id: String): Call<BaseResponse<Lesson>>
//
//    @GET("lesson/detail?get_teacher=0&get_review=0&get_evt=1")
//    abstract fun getLessonReservation(@Query("lss_id") id: String): Call<BaseResponse<Lesson>>
//
//
//    @GET("lesson/get-more-review")
//    abstract fun getMoreReview(
//        @Query("lss_id") id: String,
//        @Query("page") page: Int
//    ): Call<BaseResponse<BaseData<ItemReview>>>
//
//    @GET("lesson/get-evt-infor")
//    abstract fun getEventInfo(
//        @Query("lss_id") id: String,
//        @Query("start") start: String,
//        @Query("end") end: String
//    ): Call<BaseResponse<EventInfo>>
//
//    @GET("lesson/get-order-events")
//    abstract fun getOrderEvents(
//        @Query("lss_id") id: String,
//        @Query("start") start: String,
//        @Query("end") end: String
//    ): Call<BaseResponse<Event>>
//
//    @GET("lesson/order-completed-support")
//    abstract fun getLessonCompleted(@Query("id") id: String): Call<BaseResponse<LessonCompletedData>>
//
//    @GET("logs?limit=20")
//    abstract fun getLessonHistory(
//        @Query("order") order: String,
//        @Query("page") page: Int,
//        @Query("mode") mode: String,
//        @Query("from_time") fromTime: String,
//        @Query("to_time") toTime: String
//    ): Call<BaseResponse<BaseData<Lesson>>>
//
//    @GET("lesson/favorite?limit=10")
//    abstract fun getLessonFavorite(@Query("page") page: Int): Call<BaseResponse<BaseData<Lesson>>>
//
//    @FormUrlEncoded
//    @POST("home/read-notify")
//    abstract fun readNotify(@Field("id") id: String, @Field("type") type: String): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("lesson/un-public")
//    abstract fun privateLesson(@Field("id") id: String): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("lesson/duplicate")
//    abstract fun duplicateLesson(@Field("id") id: String): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("lesson/delete")
//    abstract fun deleteLesson(@Field("lss_id") id: String): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("lesson/post-review")
//    abstract fun postReview(
//        @Field("ol_id") id: String,
//        @Field("score") score: Double,
//        @Field("content") content: String
//    ): Call<BaseResponse>
//
//    @GET("reserv/purchase-cash")
//    abstract fun getPurchaseCash(@Query("id") id: String): Call<BaseResponse<DataOrderBankInfo>>
//
//    @FormUrlEncoded
//    @POST("reserv/purchase-confirm")
//    abstract fun purchaseConfirm(
//        @Field("id") id: String,
//        @Field("pay_method") method: String,
//        @Field("use_coin") useCoin: Boolean,
//        @Field("coin") coin: Int,
//        @Field("news_letter") news: Boolean,
//        @Field("follow_lesson") follow: Boolean
//    ): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("reserv/accept-order")
//    abstract fun acceptOrder(@Field("id") id: String): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("reserv/cancel-order")
//    abstract fun cancelOrder(@Field("id") id: String): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("reserv/reject-order")
//    abstract fun rejectOrder(@Field("id") id: String): Call<BaseResponse>
//
//    @POST("lesson/order-event")
//    abstract fun orderEvent(@Body orderEventRequest: OrderEventRequest): Call<BaseResponse<ObjectId>>
//
//    class OrderEventRequest(
//        private val lss_id: String,
//        private val start: String,
//        private val end: String,
//        private val event: EventInfo
//    )
//
//    @Multipart
//    @POST("lesson/add")
//    abstract fun addLesson(
//        @PartMap partMap: Map<String, RequestBody>,
//        @Part files: List<MultipartBody.Part>
//    ): Call<BaseResponse<DataLessonAdd>>
//
//    @Multipart
//    @POST("lesson/edit")
//    abstract fun editLesson(
//        @PartMap partMap: Map<String, RequestBody>,
//        @Part files: List<MultipartBody.Part>
//    ): Call<BaseResponse<DataLessonAdd>>
//
//    @FormUrlEncoded
//    @POST("lesson/toggle-like")
//    abstract fun likeLesson(
//        @Field("id") id: String,
//        @Field("price") price: Int
//    ): Call<BaseResponse<DataBlockLike>>
//
//    @FormUrlEncoded
//    @POST("lesson/toggle-good-bad")
//    abstract fun goodBadLesson(
//        @Field("lss_id") id: String,
//        @Field("is_good") i: Int
//    ): Call<BaseResponse<DataBlockLike>>
//
//    @FormUrlEncoded
//    @POST("teacher/register")
//    abstract fun registerTeacher(
//        @Field("bcate") bCate: String,
//        @Field("mcate") mCate: String,
//        @Field("area_id") areaId: String,
//        @Field("one_word_msg") message: String,
//        @Field("pr") pr: String
//    ): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("lesson/report")
//    abstract fun reportLesson(
//        @Field("lss_id") id: String,
//        @Field("ol_id") orderId: String,
//        @Field("category") category: String,
//        @Field("title") title: String,
//        @Field("content") content: String
//    ): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("user/report")
//    abstract fun reportUser(
//        @Field("user_id") id: String,
//        @Field("category") category: String,
//        @Field("title") title: String,
//        @Field("content") content: String
//    ): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("user/toggle-like")
//    abstract fun followUser(@Field("id") id: String): Call<BaseResponse<DataBlockLike>>
//
//    @FormUrlEncoded
//    @POST("user/toggle-block")
//    abstract fun lockUser(@Field("user_id") id: String): Call<BaseResponse<DataBlockLike>>
//
//    @GET("user/detail")
//    abstract fun getUser(@Query("user_id") id: String): Call<BaseResponse<DataUserDetail>>
//
//    @GET("user/review?limit=10")
//    abstract fun getReview(
//        @Query("page") page: Int,
//        @Query("code") code: String,
//        @QueryMap sort: Map<String, String>
//    ): Call<BaseResponse<BaseData<UserReview>>>
//
//    @GET("user/my-review?limit=10")
//    abstract fun getMyReview(
//        @Query("page") page: Int,
//        @Query("code") code: String,
//        @QueryMap sort: Map<String, String>
//    ): Call<BaseResponse<BaseData<UserReview>>>
//
//    @GET("user/block?limit=20")
//    abstract fun getUserBlock(@Query("page") page: Int): Call<BaseResponse<BaseData<User>>>
//
//    @GET("user/infor")
//    abstract fun getUserInfo(): Call<BaseResponse<DataUserInfo>>
//
//    @GET("user/is-master")
//    abstract fun isTeacher(): Call<BaseResponse<Boolean>>
//
//
//    @GET("user/notify-setting")
//    abstract fun getNotifySetting(): Call<BaseResponse<NotiSetting>>
//
//    @FormUrlEncoded
//    @POST("user/notify-setting")
//    abstract fun editNotifySetting(@FieldMap stringMap: Map<String, String>): Call<BaseResponse>
//
//    @Multipart
//    @POST("user/infor")
//    abstract fun updateUserInfo(
//        @PartMap partMap: Map<String, RequestBody>,
//        @Part avatar: MultipartBody.Part
//    ): Call<BaseResponse>
//
//    @GET("user/get-more-review")
//    abstract fun getMoreUserReview(
//        @Query("user_id") id: String,
//        @Query("page") page: Int
//    ): Call<BaseResponse<BaseData<ItemReview>>>
//
//    @GET("user/get-amount")
//    abstract fun getAmount(): Call<BaseResponse<Amount>>
//
//    @GET("d-msg/contact?limit=20")
//    abstract fun getDMessageContact(@Query("page") page: Int): Call<BaseResponse<BaseData<Dmessage>>>
//
//    @FormUrlEncoded
//    @POST("d-msg/add-contact")
//    abstract fun addContact(
//        @Field("type") type: String,
//        @Field("content") content: String,
//        @Field("parent_id") parentId: String
//    ): Call<BaseResponse>
//
//    @GET("d-msg?limit=20")
//    abstract fun getDMessageChat(@Query("page") page: Int): Call<BaseResponse<BaseData<Dmessage>>>
//
//    @GET("d-msg/detail")
//    abstract fun getDMessageDetail(@Query("id") id: String): Call<BaseResponse<DMessageDetail>>
//
//    @GET("bank?limit=20")
//    abstract fun getBank(@Query("page") page: Int, @Query("keyword") keyword: String): Call<BaseResponse<BaseData<Bank>>>
//
//    @GET("bank/history")
//    abstract fun getBankHistory(
//        @Query("start_month") from: String,
//        @Query("end_month") to: String
//    ): Call<BaseResponse<BaseData<BankHistory>>>
//
//    @GET("bank/transaction-history")
//    abstract fun getCoinHistory(
//        @Query("type") type: String,
//        @Query("from_time") from: String,
//        @Query("to_time") to: String
//    ): Call<BaseResponse<BaseData<BankHistory>>>
//
//    @FormUrlEncoded
//    @POST("bank/convert-coin")
//    abstract fun convertYen(@Field("amount") amount: Int): Call<BaseResponse>
//
//    @GET("agency?limit=20")
//    abstract fun getAgency(@Query("bank_id") id: String, @Query("page") page: Int, @Query("keyword") keyword: String): Call<BaseResponse<BaseData<Bank>>>
//
//    @GET("thumbnail")
//    abstract fun getThumbnail(): Call<BaseResponse<List<Thumbnail>>>
//
//    @FormUrlEncoded
//    @POST("d-msg/add")
//    abstract fun directMessage(
//        @Field("user_id") userId: String,
//        @Field("lss_id") lessonId: String,
//        @Field("type") type: String,
//        @Field("title") title: String,
//        @Field("content") content: String,
//        @Field("parent_id") parentId: String
//    ): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("d-msg/delete")
//    abstract fun deleteMessage(@Field("id") userId: String): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("bank/withdrawal")
//    abstract fun withdraw(@Field("amount") amount: Int): Call<BaseResponse<Amount>>
//
//    @GET("chat/room")
//    abstract fun getRoomData(@Query("code") code: String): Call<BaseResponse<RoomData>>
//
//    @GET("chat/history")
//    abstract fun getChatHistory(@Query("room_code") code: String): Call<BaseResponse<DataHistory>>
//
//    @GET("chat/get-msg?limit=20")
//    abstract fun getChatMessage(@Query("room_code") code: String, @Query("page") page: Int): Call<BaseResponse<BaseData<Message>>>
//
//    @GET("chat/data-history?limit=20")
//    abstract fun getHistoryData(
//        @Query("type") type: String,
//        @Query("page") page: Int
//    ): Call<BaseResponse<BaseData<Order>>>
//
//    @FormUrlEncoded
//    @POST("chat/add-msg")
//    abstract fun addChatMessage(
//        @Field("room_code") code: String,
//        @Field("content") content: String,
//        @Field("type") type: String,
//        @Field("peers[]") peers: List<String>,
//        @Field("call_type") callType: String
//    ): Call<BaseResponse>
//
//    @FormUrlEncoded
//    @POST("chat/save-note")
//    abstract fun saveNote(
//        @Field("room_code") code: String,
//        @Field("content") content: String
//    ): Call<BaseResponse>
//
//    @GET("chat/ended")
//    abstract fun getLessonEnd(@Query("room_code") roomCode: String): Call<BaseResponse<LessonEnd>>
//
//    @GET("title-tail")
//    abstract fun getTitleTail(): Call<BaseResponse<List<TitleTail>>>
//
//    @GET("search")
//    abstract fun getZipCode(@Query("zipcode") zipCode: Int): Call<ZipCodeResponse<ZipCodeData>>
}