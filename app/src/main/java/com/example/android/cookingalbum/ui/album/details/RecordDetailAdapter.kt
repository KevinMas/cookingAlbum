package com.example.android.cookingalbum.ui.album.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.cookingalbum.R
import com.example.android.cookingalbum.model.Record
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.record_detail_card_view.view.*

/**
 * 詳細画面でのビューページャー用のアダプタークラスです。
 */
class RecordDetailAdapter(val onclick: () -> Unit)
    : PagedListAdapter<Record, RecordDetailAdapter.RecordHolder>(Record.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordHolder {
        return RecordHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.record_detail_card_view, parent, false))
    }

    override fun onBindViewHolder(holder: RecordHolder, position: Int) {
        val record = getItem(position)
        //　画像を準備
        Picasso.get()
            .load(record?.imageUrl)
            .into(holder.recordImage)
        //　ビューをタップするリスナーを準備
        holder.itemView.setOnClickListener { onclick() }

    }

    class RecordHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recordImage: ImageView = view.record_image_view
    }
}