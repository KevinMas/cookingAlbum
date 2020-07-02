package com.example.android.cookingalbum.ui.album

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.cookingalbum.R
import com.example.android.cookingalbum.model.Record
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_card_view.view.*

/**
 * アルバムのリスト用のアダプタークラスです。
 * PagedListAdapterを使って少しずつデータをロードします。
 */
class AlbumListAdapter
    : PagedListAdapter<Record, AlbumListAdapter.AlbumHolder>(Record.DIFF_CALLBACK) {

    /**
     * アイテム一つずつレイアウトを設定する関数です
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        return AlbumHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.album_card_view, parent, false))
    }

    /**
     *　アイテムの情報をビューにバインドする関数です
     */
    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        //リストは画像だけをPicassoのおかげでロードします
        Picasso.get()
            .load(getItem(position)?.imageUrl)
            .into(holder.albumImage)
    }

    class AlbumHolder(view: View) : RecyclerView.ViewHolder(view) {
        val albumImage: ImageView = view.record_image
    }
}