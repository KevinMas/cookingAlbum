package com.example.android.cookingalbum.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

class Record(
    @SerializedName("image_url") val imageUrl: String,
    val comment: String,
    @SerializedName("recipe_type") val recipeType: RecipeType,
    @SerializedName("recorded_at") val recordedAt: String
) {

    companion object {
        /**
         * RecyclerViewのアダプター用のDiffUtil。
         */
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Record> =
            object : DiffUtil.ItemCallback<Record>() {

                override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
                    return oldItem.imageUrl === newItem.imageUrl
                }

                override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        val article: Record = other as Record
        return article.imageUrl === this.imageUrl
    }

}