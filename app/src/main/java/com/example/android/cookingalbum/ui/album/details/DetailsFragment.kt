package com.example.android.cookingalbum.ui.album.details

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.android.cookingalbum.R
import com.example.android.cookingalbum.model.RecipeType
import com.example.android.cookingalbum.model.Record
import com.example.android.cookingalbum.ui.album.AlbumViewModel
import kotlinx.android.synthetic.main.record_details_fragment.*
import kotlinx.android.synthetic.main.record_details_fragment.view.*

/**
 * ある画像を詳しく見るようなフラグメントクラスです。
 */
class DetailsFragment: Fragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(AlbumViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.record_details_fragment, container, false)

        // アダプターを作成
        val adapter = RecordDetailAdapter {
            //　ビューをタップしたら情報レイアウトをアニメーションで消すか表示する
            if (view.comment_container.isVisible) {
                view.comment_container.animate()
                    .alpha(0f)
                    .setDuration(300)
                    .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        comment_container.visibility = View.GONE
                    }
                }).start()
            } else {
                view.comment_container.alpha = 1f
                comment_container.visibility = View.VISIBLE
            }
            //　ビューをタップしたらトップメニューレイアウトをアニメーションで消すか表示する
            if (view.toolbar_container.isVisible) {
                view.toolbar_container.animate()
                    .alpha(0f)
                    .setDuration(300)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            toolbar_container.visibility = View.GONE
                        }
                    }).start()
            } else {
                view.toolbar_container.alpha = 1f
                toolbar_container.visibility = View.VISIBLE
            }
        }
        // ビューページャーにアダプターを設定
        view.view_pager_view.adapter = adapter
        // ビューモデルからのデータを見張ってアダプターに渡す
        viewModel.recordLiveData.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            view.view_pager_view.setCurrentItem(viewModel.currentPosition, false)
        })
        // ビューページャーのリスナーを準備
        //　別の画像にスワイプしたらビューモデルを更新して情報（コメントなど）を取得
        view.view_pager_view.registerOnPageChangeCallback(
            ViewPagerCallback(
                onRecordSelected = { position ->
                    viewModel.currentPosition = position
                    updateSideInformation(view, viewModel.getCurrentRecord())
                })
        )
        //　トップメニューの戻るボタンの準備で、押すと画像一覧に戻る
        view.return_button.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return view
    }

    /**
     * パラメータでもらう情報をUIに表示する関数です
     */
    private fun updateSideInformation(view: View, record: Record?) {
        view.comment_text_view.text = record?.comment
        view.date_text_view.text = record?.recordedAt
        //　タイプによってアイコンを表示
        when (record?.recipeType) {
            RecipeType.MAIN_DISH -> view.type_image_view.setImageResource(R.drawable.ic_main_dish_icon)
            RecipeType.SIDE_DISH -> view.type_image_view.setImageResource(R.drawable.ic_salad_icon)
            RecipeType.SOUP -> view.type_image_view.setImageResource(R.drawable.ic_soup_icon)
        }
    }

    /**
     * ビューページャー用のリスナークラス
     */
    class ViewPagerCallback(
        val onRecordSelected: (position: Int) -> Unit = {}
    ) : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            onRecordSelected(position)
        }
    }

}
