package com.example.android.cookingalbum.repository.album

import com.example.android.cookingalbum.model.RecordList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * APIからアルバム用のデータを取得するサービスです
 */
interface AlbumService {

    @GET("cooking_records")
    fun getAlbumRecords(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Call<RecordList>

}