package com.example.android.cookingalbum.model

import com.google.gson.annotations.SerializedName

enum class RecipeType {
    @SerializedName("main_dish")
    MAIN_DISH,
    @SerializedName("soup")
    SOUP,
    @SerializedName("side_dish")
    SIDE_DISH
}