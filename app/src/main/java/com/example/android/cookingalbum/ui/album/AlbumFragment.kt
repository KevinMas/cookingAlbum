package com.example.android.cookingalbum.ui.album

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.cookingalbum.R
import com.example.android.cookingalbum.model.Record
import kotlinx.android.synthetic.main.album_fragment.view.*

/**
 * ユーザが載せた画像のアルバムリストのフラグメントです。
 */
class AlbumFragment : Fragment() {

    companion object {
        fun newInstance() = AlbumFragment()
    }

    // RecyclerView用のアダプター
    private val adapter = AlbumListAdapter()
    // アルバム用のViewModel
    private val viewModel by lazy { ViewModelProvider(this).get(AlbumViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.album_fragment, container, false)

        // RecyclerViewはGridLayoutで表示するようにマネージャー準備する
        // 肖像画であれば3カルムにしたりランドスケープであれば6カルムにしたりしています
        view.album_recycler_view.layoutManager = GridLayoutManager(context,
           if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 3 else 6,
            RecyclerView.VERTICAL, false)

        //RecyclerViewの準備してアダプターの設定します
        view.album_recycler_view.adapter = adapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // データを見張って届いたらアダプターに渡します
        viewModel.recordLiveData.observe(viewLifecycleOwner, Observer<PagedList<Record>> {
            adapter.submitList(it)
        })
    }

}
