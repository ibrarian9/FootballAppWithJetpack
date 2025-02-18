package com.app.footballappwithjetpack.module

import com.app.footballappwithjetpack.database.ClubDatabase
import com.app.footballappwithjetpack.repository.ClubRepository
import com.app.footballappwithjetpack.ui.screen.detail.DetailViewModel
import com.app.footballappwithjetpack.ui.screen.favorite.FavoriteViewModel
import com.app.footballappwithjetpack.ui.screen.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Provide Database Instance
    single { ClubDatabase.getInstance(get()) }

    // Provide DAO
    single { get<ClubDatabase>().clubDao() }

    // Provide Repository
    single { ClubRepository(get(), get()) }

    // Provide ViewModel
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}