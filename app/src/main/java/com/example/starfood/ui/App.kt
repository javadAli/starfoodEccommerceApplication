package com.example.starfood.ui

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import com.example.starfood.common.networkMonitoring.NetworkStateHolder.registerConnectivityBroadcaster
import com.example.starfood.dao.AppDataBase
import com.example.starfood.datamodel.repository.cartrepository.CartRemoteDataSource
import com.example.starfood.datamodel.repository.cartrepository.CartRepository
import com.example.starfood.datamodel.repository.cartrepository.CartRepositoryImpl
import com.example.starfood.datamodel.repository.categoryrepo.CategoryRemoteDataSource
import com.example.starfood.datamodel.repository.categoryrepo.CategoryRepository
import com.example.starfood.datamodel.repository.categoryrepo.CategoryRepositoryImpl
import com.example.starfood.datamodel.repository.confirmlogin.ConfirmLocalDataSource
import com.example.starfood.datamodel.repository.confirmlogin.ConfirmLoginImpl
import com.example.starfood.datamodel.repository.confirmlogin.ConfirmLoginRepository
import com.example.starfood.datamodel.repository.confirmlogin.ConfirmRemoteDataSource
import com.example.starfood.datamodel.repository.homepartsrepo.HomePartsRemoteDataSource
import com.example.starfood.datamodel.repository.homepartsrepo.HomePartsRepository
import com.example.starfood.datamodel.repository.homepartsrepo.HomePartsRepositoryIml
import com.example.starfood.datamodel.repository.inviterepo.InviteLocalDataSource
import com.example.starfood.datamodel.repository.inviterepo.InviteRemoteDataSource
import com.example.starfood.datamodel.repository.inviterepo.InviteRepoImpl
import com.example.starfood.datamodel.repository.inviterepo.InviteRepository
import com.example.starfood.datamodel.repository.lotteryrepo.LotteryLocaleDataSource
import com.example.starfood.datamodel.repository.lotteryrepo.LotteryRemoteDataSource
import com.example.starfood.datamodel.repository.lotteryrepo.LotteryRepository
import com.example.starfood.datamodel.repository.lotteryrepo.LotteryRepositoryImpl
import com.example.starfood.datamodel.repository.messageRepository.MessageLocaleDataSource
import com.example.starfood.datamodel.repository.messageRepository.MessageRemoteDataSource
import com.example.starfood.datamodel.repository.messageRepository.MessageRepository
import com.example.starfood.datamodel.repository.messageRepository.MessageRepositoryImpl
import com.example.starfood.datamodel.repository.productdetail.SubProductRemoteDataSource
import com.example.starfood.datamodel.repository.productdetail.SubProductRepository
import com.example.starfood.datamodel.repository.productdetail.SubProductRepositoryImpl
import com.example.starfood.datamodel.repository.productdetail.productexplaindetail.ProductExplainDetailRemoteDataSource
import com.example.starfood.datamodel.repository.productdetail.productexplaindetail.ProductExplainDetailRepository
import com.example.starfood.datamodel.repository.productdetail.productexplaindetail.ProductExplainDetailRepositoryImpl
import com.example.starfood.datamodel.repository.profilerepo.ProfileLocalDataSource
import com.example.starfood.datamodel.repository.profilerepo.ProfileRemoteDataSource
import com.example.starfood.datamodel.repository.profilerepo.ProfileRepoImpl
import com.example.starfood.datamodel.repository.profilerepo.ProfileRepository
import com.example.starfood.datamodel.repository.profit.ProfitLocalDataSource
import com.example.starfood.datamodel.repository.profit.ProfitRemoteDataSource
import com.example.starfood.datamodel.repository.profit.ProfitRepoImpl
import com.example.starfood.datamodel.repository.profit.ProfitRepository
import com.example.starfood.datamodel.repository.shipping.ShippingRemoteDataSource
import com.example.starfood.datamodel.repository.shipping.ShippingRepoImpl
import com.example.starfood.datamodel.repository.shipping.ShippingRepository
import com.example.starfood.datamodel.repository.updateorder.UpdateOrderRemoteDataSource
import com.example.starfood.datamodel.repository.updateorder.UpdateOrderRepoImpl
import com.example.starfood.datamodel.repository.updateorder.UpdateOrderRepository
import com.example.starfood.datamodel.repository.userlogin.UserRepository
import com.example.starfood.datamodel.repository.userlogin.UserLocalDataSource
import com.example.starfood.datamodel.repository.userlogin.UserRemoteDataSource
import com.example.starfood.datamodel.repository.userlogin.UserRepositoryImpl
import com.example.starfood.datamodel.repository.slider.walletrepo.WalletLocalDataSource
import com.example.starfood.datamodel.repository.slider.walletrepo.WalletRemoteDataSource
import com.example.starfood.datamodel.repository.slider.walletrepo.WalletRepoImpl
import com.example.starfood.datamodel.repository.slider.walletrepo.WalletRepository
import com.example.starfood.service.FrescoImageLoadingServiceImpl
import com.example.starfood.service.ImageLoadingService
import com.example.starfood.service.createApiServiceInstance
import com.example.starfood.ui.cart.CartViewModel
import com.example.starfood.ui.cart.ShippingViewModel
import com.example.starfood.ui.cart.WebViewShippingViewModel
import com.example.starfood.ui.category.CategoryViewModel
import com.example.starfood.ui.favourite.FavouriteViewModel
import com.example.starfood.ui.home.subkalas.AmountDialogViewModel
import com.example.starfood.ui.home.HomeViewModel
import com.example.starfood.ui.home.subkalas.SubProductViewModel
import com.example.starfood.ui.home.subkalas.ProductExplainViewModel
import com.example.starfood.ui.login.ConfirmLoginViewModel
import com.example.starfood.ui.login.LoginViewModel
import com.example.starfood.ui.message.MessageViewModel
import com.example.starfood.ui.profile.FactorViewModel
import com.example.starfood.ui.profile.ProfileViewModel
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        Timber.plant()
        val myModule = module {
            single { createApiServiceInstance()}
            single<SharedPreferences> { this@App.getSharedPreferences("app_settings", MODE_PRIVATE) }
            single { Room.databaseBuilder(this@App,AppDataBase::class.java,"db_app").build() }
            factory <SubProductRepository>{ SubProductRepositoryImpl(SubProductRemoteDataSource(get()), get<AppDataBase>().productDetailDao())}
            factory <ProductExplainDetailRepository>{ ProductExplainDetailRepositoryImpl(ProductExplainDetailRemoteDataSource(get()),get<AppDataBase>().exPlainOfProduct())}
            factory <CategoryRepository> { CategoryRepositoryImpl(CategoryRemoteDataSource(get()), get<AppDataBase>().categoryDao())}
            factory <HomePartsRepository>{ HomePartsRepositoryIml(HomePartsRemoteDataSource(get()))}
            factory <CartRepository>{ CartRepositoryImpl(CartRemoteDataSource(get()), get<AppDataBase>().cartDao())}
            single  <UserRepository> {  UserRepositoryImpl(UserLocalDataSource(get()), UserRemoteDataSource(get()))}
            single  <ImageLoadingService>{FrescoImageLoadingServiceImpl()}
            factory <ConfirmLoginRepository>{ ConfirmLoginImpl(ConfirmRemoteDataSource(get()), ConfirmLocalDataSource())}
            factory <ProfileRepository>{ ProfileRepoImpl(ProfileRemoteDataSource(get()), ProfileLocalDataSource())}
            factory <UpdateOrderRepository>{ UpdateOrderRepoImpl(UpdateOrderRemoteDataSource(get()),  get<AppDataBase>().updateOrderDao())}
            factory <ShippingRepository>{ ShippingRepoImpl(ShippingRemoteDataSource(get()), get<AppDataBase>().shippingDao())}
            factory <LotteryRepository>{ LotteryRepositoryImpl(LotteryRemoteDataSource(get()), LotteryLocaleDataSource())}
            factory <ProfitRepository>{ ProfitRepoImpl(ProfitRemoteDataSource(get()), ProfitLocalDataSource())}
            factory <WalletRepository>{ WalletRepoImpl(WalletRemoteDataSource(get()), WalletLocalDataSource()) }
            factory <InviteRepository>{ InviteRepoImpl(InviteRemoteDataSource(get()), InviteLocalDataSource())}
            factory <MessageRepository>{ MessageRepositoryImpl(MessageRemoteDataSource(get()), MessageLocaleDataSource())}
            viewModel { LoginViewModel(get())}
            viewModel { CategoryViewModel(get())}
            viewModel { SubProductViewModel(get(),get(),get())}
            viewModel { ConfirmLoginViewModel(get())}
            viewModel { ProductExplainViewModel(get(),get(),get())}
            viewModel { FavouriteViewModel(get())}
            viewModel { ShippingViewModel(get())}
            viewModel { AmountDialogViewModel(get())}
            viewModel { ProfileViewModel(get())}
            viewModel { MessageViewModel(get())}
            viewModel { FactorViewModel(get())}
            viewModel { WebViewShippingViewModel(get())}
            viewModel { CartViewModel(get(),get(),get())}
            viewModel { HomeViewModel(get(),get(),get())}
            viewModel { MainViewModel(get(),get(),get(),get())}
        }
        startKoin {
            androidContext(this@App)
            modules(myModule)
        }
        registerConnectivityBroadcaster()
        val userRepository: UserRepository = get()
        userRepository.loadToken()
        userRepository.loadUserName()
        userRepository.loadPsn()
    }

}