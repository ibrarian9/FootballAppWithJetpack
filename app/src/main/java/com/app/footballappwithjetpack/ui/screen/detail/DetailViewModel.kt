package com.app.footballappwithjetpack.ui.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.footballappwithjetpack.model.Club
import com.app.footballappwithjetpack.repository.ClubRepository

class DetailViewModel(
    private val clubRepository: ClubRepository
): ViewModel() {

    private val _clubs = MutableLiveData<List<Club>>()
    val clubs: LiveData<List<Club>> = _clubs

    private val _isFav = MutableLiveData<Boolean>()
    val isFav: LiveData<Boolean> = _isFav

    init {
        loadAllClubs()
    }

    fun addFavorite(club: Club) {
        clubRepository.insertClub(club)
        _isFav.value = true
    }

    fun removeFavorite(club: Club) {
        clubRepository.deleteClub(club)
        _isFav.value = false
    }

    fun checkFavorite(club: Club) {
        clubRepository.isFavorite(club).observeForever { isFavorite ->
            _isFav.value = isFavorite
        }
    }

    private fun loadAllClubs() {
        _clubs.value = clubRepository.getAllClubs()
    }
}