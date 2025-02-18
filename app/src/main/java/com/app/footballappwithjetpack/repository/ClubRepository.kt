package com.app.footballappwithjetpack.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.app.footballappwithjetpack.R
import com.app.footballappwithjetpack.database.ClubDao
import com.app.footballappwithjetpack.model.Club
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ClubRepository(
    private val clubDao: ClubDao,
    private val context: Context
){
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    fun getAllClubs(): List<Club> {
        val resources = context.resources
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDesc = resources.getStringArray(R.array.data_desc)
        val dataStad = resources.getStringArray(R.array.data_stadium)
        val dataEpl = resources.getStringArray(R.array.data_epl)
        val dataUcl = resources.getStringArray(R.array.data_ucl)
        val dataFa = resources.getStringArray(R.array.data_fa)
        val dataPhoto = resources.obtainTypedArray(R.array.data_poto)

        val listClub = ArrayList<Club>()
        for (i in dataName.indices) {
            val club = Club(
                id = i,
                name = dataName[i],
                desc = dataDesc[i],
                stadium = dataStad[i],
                epl = dataEpl[i],
                ucl = dataUcl[i],
                fa = dataFa[i],
                poto = dataPhoto.getResourceId(i, -1)
            )
            listClub.add(club)
        }
        dataPhoto.recycle()
        return listClub
    }

    fun getAllFavorite(): LiveData<List<Club>> = clubDao.getFavoriteClub()

    fun isFavorite(club: Club): LiveData<Boolean> {
        return clubDao.isFavorite(club.id)
    }

    fun insertClub(club: Club) = executorService.execute {
        clubDao.insertClub(club)
    }

    fun deleteClub(club: Club) = executorService.execute {
        clubDao.deleteClub(club)
    }
}
