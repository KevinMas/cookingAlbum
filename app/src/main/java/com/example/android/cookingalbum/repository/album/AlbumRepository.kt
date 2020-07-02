package com.example.android.cookingalbum.repository.album

import com.example.android.cookingalbum.model.RecordList
import com.example.android.cookingalbum.repository.ServiceFactory
import retrofit2.Call

/**
 * APIからアルバム用のデータを取得するリポジトリです
 */
class AlbumRepository {

    private val service: AlbumService = ServiceFactory.getService(AlbumService::class.java)

    fun getAlbumData(limit: Int? = null, offset: Int? = null): Call<RecordList> =
        service.getAlbumRecords(limit, offset)

}