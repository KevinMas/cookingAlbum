package com.example.android.cookingalbum.ui.album

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import com.example.android.cookingalbum.model.Record
import com.example.android.cookingalbum.model.RecordList
import com.example.android.cookingalbum.repository.album.AlbumRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * APIからユーザの画像の取得を管理するクラスです。
 * JetpackのDataSource機能を使って少しずつ情報を取得しに行きますので、ネットワーク利用が適当です。
 */
class AlbumDataSource: PositionalDataSource<Record>() {

    private val repository : AlbumRepository = AlbumRepository()

    /**
     * 最初にデータを取得する関数です。
     *　
     * @param params Configに渡したパラメータ
     * @param callback 次の取得用のコールバック
     */
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Record>) {
        // レポジトリを呼んでデータを取得
        repository.getAlbumData(params.pageSize, params.requestedStartPosition)
            .enqueue(object: Callback<RecordList> {

            override fun onFailure(call: Call<RecordList>, t: Throwable) {
                Log.e("AlbumDataSource", "Failed to fetch data!")
            }

            override fun onResponse(call: Call<RecordList>, response: Response<RecordList>) {
                Log.d("AlbumDataSource", "loadInitial  onResponse")
                if (response.isSuccessful) {
                    // レスポンスが成功であればコールバックに取得したデータと
                    // 次にどこから取得するかの情報と
                    // このAPIから何件まで取得できるかの情報を渡します
                    val offset = response.body()?.pagination?.offset ?: 0
                    val total = response.body()?.pagination?.total ?: 0
                    val records = response.body()?.records.orEmpty()
                    callback.onResult(records, offset, total)
                }
            }
        })
    }

    /**
     * ユーザがどんどんスクロールしたら、まだ取得されていないデータを取得する関数です。
     * パラメータでもらった設定によってデータを取得しに行きます
     *
     * @param params Configに渡したパラメータ（どこから取得するかと何件を取得するかの設定）
     * @param callback 次の取得用のコールバック
     */
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Record>) {
        repository.getAlbumData(params.loadSize, params.startPosition)
            .enqueue(object: Callback<RecordList> {
                override fun onFailure(call: Call<RecordList>, t: Throwable) {
                    Log.e("AlbumDataSource", "Failed to fetch data!")
                }

                override fun onResponse(call: Call<RecordList>, response: Response<RecordList>) {
                    if (response.isSuccessful) {
                        // レスポンスが成功であればコールバックにデータを渡します
                        val items = response.body()?.records.orEmpty()
                        callback.onResult(items)
                    }
                }
            })
    }

    /**
     * DataSourceクラスのファクトリークラスです。
     */
    class AlbumDataSourceFactory : DataSource.Factory<Int, Record>() {
        override fun create(): DataSource<Int, Record> {
            return AlbumDataSource()
        }
    }

}