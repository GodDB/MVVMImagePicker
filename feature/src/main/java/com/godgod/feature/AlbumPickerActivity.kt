package com.godgod.feature

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenResumed
import androidx.lifecycle.withResumed
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godgod.feature.base.BaseBindingActivity
import com.godgod.feature.databinding.ActivityAlbumPickerBinding
import com.godgod.share.model.AlbumData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AlbumPickerActivity :
    BaseBindingActivity<ActivityAlbumPickerBinding>(R.layout.activity_album_picker) {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AlbumPickerActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val viewModel by viewModels<AlbumPickerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.run {
            lifecycleOwner = this@AlbumPickerActivity
            vm = viewModel
        }

        setupAlbumPicker()
    }

    private fun setupAlbumPicker() {
        binding.rvAlbumList.run {
            adapter = AlbumPickerAdapter()
            layoutManager = GridLayoutManager(context, 3)
            addItemDecoration(AlbumPickerItemDecorator())
        }

        lifecycleScope.launchWhenResumed {
            viewModel.albumPagingData.collect {
                (binding.rvAlbumList.adapter as AlbumPickerAdapter).submitData(it)
            }
        }
    }


}