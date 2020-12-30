package com.example.pagerlistapp.repository

import androidx.lifecycle.LiveData
import com.example.pagerlistapp.amodels.RDataItem
import com.example.pagerlistapp.amodels.Value
import com.fovlaxcorp.autodatasource.GenDataSource.Type
import com.fovlaxcorp.autodatasource.GenDataSource
import com.fovlaxcorp.autodatasource.KeyItem
import com.fovlaxcorp.autodatasource.PageConfig
import com.fovlaxcorp.autodatasource.WithDataSource

@WithDataSource
interface Repository {

    companion object{
        const val CHARACTERS = "characters"
        const val IMAGE = "images"
    }

    @PageConfig(initialLoadSizeHint = 16,
                pageSize = 8)
    @GenDataSource(sourceName = IMAGE, type = Type.Positional)
    fun getImages(offset: Int, count: Int): List<Value?>?

    @PageConfig(initialLoadSizeHint = 10,
                pageSize = 10)
    @GenDataSource(sourceName = CHARACTERS, type = Type.ItemKeyedAfter)
    fun getCharacters(id: Int, count: Int): List<RDataItem?>?

    @KeyItem(name = CHARACTERS)
    fun getKeyCharacter(rDataItem: RDataItem?): Int

    fun setQuery(query: String?)

    fun getStatus() : LiveData<State>

}

