package com.example.starfood.service
import com.example.starfood.datamodel.SubmitFavouriteDataModel
import com.example.starfood.datamodel.addfactor.AddFactorDataModel
import com.example.starfood.datamodel.amountofproduct.AmountProductDataModel
import com.example.starfood.datamodel.applydiscount.ApplyDiscount
import com.example.starfood.datamodel.brandParts.AllKalaOfBrandDataModel
import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.datamodel.categorydatamodel.CategoryDataModelItem
import com.example.starfood.datamodel.categorydatamodel.HomeAllKalaOfPartDataModel
import com.example.starfood.datamodel.categorydatamodel.HomePartListKalaOfPictureDataModel
import com.example.starfood.datamodel.categorydatamodel.SearchDataModelItem
import com.example.starfood.datamodel.detailcategorydatamodel.SubProductCategoryDataModel
import com.example.starfood.datamodel.detailcategorydatamodel.HeaderDetailCategoryModelItem
import com.example.starfood.datamodel.detailcategorydatamodel.appendkala.AppendSubKalaDataModel
import com.example.starfood.datamodel.favourite.FavouriteDataModel
import com.example.starfood.datamodel.homparts.HomePartsDataModel
import com.example.starfood.datamodel.homparts.AddBasketHomeDataModel
import com.example.starfood.datamodel.homparts.UpdateBasketFromHomeDataModel
import com.example.starfood.datamodel.invitefriend.InviteFriendDataModel
import com.example.starfood.datamodel.logindatamodel.ConfirmLoginDataModel
import com.example.starfood.datamodel.logindatamodel.LoginResponse
import com.example.starfood.datamodel.lottery.LotteryDataModel
import com.example.starfood.datamodel.productexplaindatamodel.ProductExplainDataModel
import com.example.starfood.datamodel.profile.ProfileDataModel
import com.example.starfood.datamodel.profile.factorview.FactorViewDataModel
import com.example.starfood.datamodel.profit.ProfitDataModel
import com.example.starfood.datamodel.shipping.ShippingDataModel
import com.example.starfood.datamodel.shipping.SuccessPayOnlineDataModel
import com.example.starfood.datamodel.slider.SliderDataModel
import com.example.starfood.datamodel.wallet.SendWalletFormDataModel
import com.example.starfood.datamodel.wallet.WalletDataModel
import com.example.starfood.message.MessageListDataModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("api/getMainGroups")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getCategoryList(@Header("Authorization") token: String): Single<List<CategoryDataModelItem>>
    @GET("api/getSubGroupList")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getProductDetailHeaderList(@Header("Authorization") token: String,@Query("mainGrId") grId: String): Single<List<HeaderDetailCategoryModelItem>>
    @GET("api/getMainGroupKala")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getProductDetailList(
        @Header("Authorization") token: String,
        @Query("mainGrId") grId: String,
        @Query("psn") psn: String,
        @Query("pageNumber") page: Int
    ): Single<SubProductCategoryDataModel>
    @GET("api/appendSubGroupKala")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun appendSubGroupKala(@Header("Authorization") token: String,@Query("mainGrId") grId: String,@Query("psn") psn: String, @Query("subKalaGroupId") subKalaGroupId:String): Single<AppendSubKalaDataModel>
    @GET("api/buySomething")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun purchaseRegistration(@Header("Authorization") token: String,@Query("psn") psn:String,@Query("kalaId") kalaId:String,@Query("amountUnit") amountUnit:String):Single<String>
    @GET("api/updateOrderBYS")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun updateOrder(@Header("Authorization") token: String,@Query("orderBYSSn") orderBYSSn:Long,@Query("amountUnit") amountUnit:Long,@Query("kalaId") kalaId:Long):Single<String>
    @GET("api/deleteOrderBYS")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun deleteFromCart(@Header("Authorization") token: String,@Query("SnOrderBYS") snOrderBYS:String):Single<String>
    @GET("api/setFavorite")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun submitFavourite(@Header("Authorization") token: String,@Query("goodSn") goodSn: String , @Query("psn") psn:String):Single<SubmitFavouriteDataModel>
    @GET("api/favoritKalaApi")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getFavourite(@Header("Authorization") token: String,@Query("psn") psn:String):Single<FavouriteDataModel>
    @GET("api/shippingData")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun shippingData(@Header("Authorization") token: String,@Query("psn") psn:String,@Query("allMoney") allMoney:Long,@Query("profit") profit:Long):Single<ShippingDataModel>
    @GET("api/addFactorApi")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun addFactor(@Header("Authorization") token: String,@Query("psn") psn:String,@Query("allMoney") allMoney:Long,@Query("customerAddress") customerAddress:String,@Query("recivedTime") recivedTime:String,@Query("profit") profit:Long):Single<AddFactorDataModel>
    //توی قسمت پروفایل در مرحله ی نهایی پرداخت که بانک ریدایرکت میکنه استفاده میکنیم
    @GET("api/profile")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getProfile(@Header("Authorization") token: String,@Query("psn") psn:String):Single<ProfileDataModel>
    //تو قسمت پروفایل روی دکمه بازگشتی کلیک میکنیم میره صفحه بعد برای فاکتورهای مرجوعی
    @GET("api/cartsList")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getCartItem(@Header("Authorization") token: String,@Query("psn") psn: String): Single<CartDataModel>
// در صفحه پروفایل در فاکتورهای ارسال شده وقتی روی جزییات یا پرداخت کلیک میکنیم میره صفحه بعد از طریق این لیست رو میگیره
    @GET("api/factorView")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getFactorView(@Header("Authorization") token: String,@Query("psn") psn:String,@Query("factorSn") factorSn:String):Single<FactorViewDataModel>
//توی drawerlayout  پایینش که لوگوی تلگرام اینا هست اونا رو میگیره
    @GET("api/descKala")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getProductExplain(@Header("Authorization") token: String,@Query("groupId") groupId: String,@Query("psn") psn: String,@Query("id") id: String): Single<ProductExplainDataModel>
    @GET("api/cancelRequestedProduct")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun submitCancelReminder(@Header("Authorization") token: String,@Query("psn") psn: String , @Query("gsn") gsn:String):Single<String>
    @GET("api/addRequestedProduct")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun submitReminder(@Header("Authorization") token: String,@Query("customerId") customerId: String , @Query("productId") productId:String):Single<Int>
    @GET("api/wallet")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun wallet(@Header("Authorization") token: String,@Query("psn") psn:String):Single<WalletDataModel>
    @GET("api/messageList")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getMessageList(@Header("Authorization") token: String,@Query("psn") psn: String): Single<MessageListDataModel>
    // برای اضافه کردن پیام جدید
    @GET("api/doAddMessage")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun doAddMessage(@Header("Authorization") token: String,@Query("psn") psn: String,@Query("pmContent") pmContent: String ): Single<MessageListDataModel>
    @GET("api/updateChangedPrice")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun updateCartChanged(@Header("Authorization") token: String,@Query("psn") psn: String,@Query("SnHDS") SnHDS: String): Single<CartDataModel>
    @GET("api/publicSearchKalaApi")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun searchList(@Header("Authorization") token: String,@Query("psn") psn: String,@Query("name") name: String): Single<List<SearchDataModelItem>>
   // در صفحه پروفایل فاکتورهای در حال انتظار روی جزییات کلیک میکنید میرید صفحه بعد لیست با این نشون میدیم
    @GET("api/addMoneyToCase")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun sendWalletForm(@Header("Authorization") token: String,@Query("psn") psn:String,@Query("takhfif") takhfif:String
                       ,@Query("answer1") answer1:String
                       ,@Query("answer2") answer2:String
                       ,@Query("answer3") answer3:String
                       ,@Query("nazarId") nazarId:String):Single<SendWalletFormDataModel>
    @GET("api/getSlidersApi")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getSliders(@Header("Authorization") token: String,@Query("psn") psn: String):Single<SliderDataModel>
    //وارد کردن کد دعوت موقع لاگین
    @GET("api/loginApi")
    fun loginInfo(@Query("email") email:String , @Query("password") password:String): Single<LoginResponse>
    @GET("api/logOutConfirm")
    fun logOutConfirm(@Query("customerId") customerId: String ,@Query("token") token:String,@Query("exitterToken") exitterToken:String,@Query("isAndroid") isAndroid:String,@Query("browser") browser:String,@Query("deviceToken") deviceToken:String):Single<ConfirmLoginDataModel>
    @GET("api/checkLogin")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun checkLogin(@Header("Authorization") token: String,@Query("psn") psn: String): Single<String>
    @GET("api/checkTakhfifCodeReliablity")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun checkTakhfifCodeReliablity(@Header("Authorization") token: String,@Query("psn") psn:String,@Query("code") code:String):Single<ApplyDiscount>
    @GET("api/getTakhfifAndPrize")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getProfit(@Header("Authorization") token: String,@Query("psn") psn:String):Single<ProfitDataModel>
    @GET("api/getInviteCodeApi")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun inviteFriend(@Header("Authorization") token: String,@Query("psn") psn:String):Single<InviteFriendDataModel>
    @GET("api/getLotteryInfoApi")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getLotteryInfo(@Header("Authorization") token: String,@Query("psn") psn:String):Single<LotteryDataModel>
    @GET("api/setCustomerLotteryHistory")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun setLotteryResult(@Header("Authorization") token: String,
        @Query("customerId") psn: String,
        @Query("product") product: String,
        @Query("remainedBonus") remainedBonus: Int
    ): Single<String>
    @GET("api/setWeeklyPresentApi")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun setWeeklyPresentApi(@Header("Authorization") token: String,
        @Query("dayPr") dayPr:String,
        @Query("psn")   psn:Int,
        @Query("bonus") bonus:Int
    ):Single<Int>
    @GET("api/getHomeParts")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getHomeParts(@Header("Authorization") token: String,@Query("psn") psn: String): Single<HomePartsDataModel>
    @GET("api/addToBasketFromHomePageApi")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun addToBasketFromHomePage(@Header("Authorization") token: String,@Query("psn") psn: String, @Query("kalaId")kalaId:String,@Query("amountUnit")amountUnit:String): Single<AddBasketHomeDataModel>
    @GET("api/updateBasketItemFromHomePage")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun updateBasketItemFromHomePage(@Header("Authorization") token: String,@Query("orderBYSSn") orderBYSSn: String, @Query("kalaId")kalaId:String,@Query("amountUnit")amountUnit:String): Single<UpdateBasketFromHomeDataModel>
    @GET("api/getUnitsForUpdate")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getAmountProduct(@Header("Authorization") token: String,@Query("Pcode") pCode: String , @Query("psn") psn:String):Single<AmountProductDataModel>
    @GET("api/getFactorPaymentFormApi")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getFactorPaymentFormApi(@Header("Authorization") token: String,@Query("allMoney") allMoney:String,@Query("psn") psn: String):Single<String>
    // تو صفحه home برای کلیک روی عکس های تکی و ....
    @GET("api/listKalaOfPictureApi")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun listKalaOfPicture(@Header("Authorization") token: String,@Query("picId") picId:String,@Query("homePartId") homepartId:String,@Query("customerId") customerId:String):Single<HomePartListKalaOfPictureDataModel>
// توی صفحه Hime برای مشاهده همه
    @GET("api/getAllKalaOfPartApi")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun showAllKalaOfHomePart(@Header("Authorization") token: String,@Query("psn") psn:String,@Query("partId") partId:String):Single<HomeAllKalaOfPartDataModel>
// برای برند صفحه خانه
    @GET("api/getKalaOfBrand")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getKalaOfBrand(@Header("Authorization") token: String,@Query("psn") psn:String,@Query("brandId") brandId:String):Single<AllKalaOfBrandDataModel>
    @GET("api/getPaymentFormApi")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getPaymentForm(@Header("Authorization") token: String,@Query("psn") psn:String,@Query("allMoney") allMoney:Long):Single<String>
    @GET("api/successPayApi")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun successPay(@Header("Authorization") token: String,
                    @Query("psn") psn:String
                   ,@Query("allMoney") allMoney:String
                   ,@Query("tref") tref:String
                   ,@Query("iN") iN:String
                   ,@Query("iD") iD:String
                   ,@Query("takhfif") takhfif:String
                   ,@Query("takhfifCode") takhfifCode:String
                   ,@Query("recivedTime") recivedTime:String
                   ,@Query("receviedAddress") receviedAddress:String):Single<SuccessPayOnlineDataModel>
    // تو صفحه پروفایل فاکتورهای ارسال شده بعد جزییات وقتی روی پرداخت کلیک میکنیم
    @GET("api/finalizeFactorPayApi")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun finalizeFactorPayApi(@Header("Authorization") token: String,
                            @Query("psn") psn:String
                           ,@Query("allMoney") allMoney:String
                           ,@Query("factorSn") factorSn:String
                           ,@Query("tref") tref:String
                           ,@Query("iN") iN:String
                           ,@Query("iD") iD:String):Single<SuccessPayOnlineDataModel>
    @GET("api/saveAppTokenToServer")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun sendAppTokenToServer(@Header("Authorization") token: String,@Query("psn") psn:String,@Query("token") appToken:String): Single<String>
    @GET("api/addCustomerFeedback")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun addCustomerFeedback(@Header("Authorization") token: String,@Query("psn") psn:String,@Query("feedbackState") feedbackState:String,@Query("feedbackType") feedbackType:String): Single<String>
    @GET("api/logout")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun logout(@Header("Authorization") token: String,@Query("token") mytoken:String,@Query("psn") psn:String): Single<String>
    @GET("api/checkButtonAllowance")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun checkButtonAllowance(@Header("Authorization") token: String,@Query("psn") psn:String): Single<String>
}
fun createApiServiceInstance() : ApiService{
    val retrofit = Retrofit.Builder()
        .baseUrl("https://starfoods.ir/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)
}