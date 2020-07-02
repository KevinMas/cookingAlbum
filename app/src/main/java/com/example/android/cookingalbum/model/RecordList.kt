package com.example.android.cookingalbum.model

import com.google.gson.annotations.SerializedName

data class RecordList (
    val pagination: Pagination,
    @SerializedName("cooking_records") val records: ArrayList<Record>
)