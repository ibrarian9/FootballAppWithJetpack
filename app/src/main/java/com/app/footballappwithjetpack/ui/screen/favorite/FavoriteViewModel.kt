package com.app.footballappwithjetpack.ui.screen.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.footballappwithjetpack.model.Club
import com.app.footballappwithjetpack.repository.ClubRepository

class FavoriteViewModel(
    private val clubRepository: ClubRepository
): ViewModel() {

    val favoriteClubs: LiveData<List<Club>> = clubRepository.getAllFavorite()

}