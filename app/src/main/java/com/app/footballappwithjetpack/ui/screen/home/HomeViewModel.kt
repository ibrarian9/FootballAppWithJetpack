package com.app.footballappwithjetpack.ui.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.footballappwithjetpack.model.Club
import com.app.footballappwithjetpack.repository.ClubRepository

class HomeViewModel(
    private val clubRepository: ClubRepository
): ViewModel() {

    private val _clubs = MutableLiveData<List<Club>>()
    val clubs: LiveData<List<Club>> = _clubs

    init {
        loadAllClubs()
    }

    private fun loadAllClubs() {
        _clubs.value = clubRepository.getAllClubs()
    }
}