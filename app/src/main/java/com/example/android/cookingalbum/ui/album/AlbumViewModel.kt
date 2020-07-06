package com.example.android.cookingalbum.ui.album

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.android.cookingalbum.model.Record

/**
 * アルバム用のViewModelです。データなどを持つクラスです。
 */
class AlbumViewModel(application: Application) : AndroidViewModel(application){

    private val dataSourceFactory = AlbumDataSource.AlbumDataSourceFactory()
    // アルバムデータのライブデータ
    var recordLiveData: LiveData<PagedList<Record>>
    // DataSource用の設定です.デフォルトで15件ずつを取得する
    private var config: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(15)
        .setEnablePlaceholders(false)
        .build()

    var currentPosition: Int = -1

    init {
        recordLiveData = LivePagedListBuilder<Int, Record>(dataSourceFactory, config).build()
    }

    /**
     * 現在のポジションのデータを取得する関数です。
     */
    fun getCurrentRecord(): Record? {
        return recordLiveData.value?.get(currentPosition)
    }

}
