package com.example.android.cookingalbum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.cookingalbum.ui.album.AlbumFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, AlbumFragment.newInstance())
                    .commitNow()
        }
    }
}
