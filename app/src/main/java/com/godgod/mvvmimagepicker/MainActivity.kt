package com.godgod.mvvmimagepicker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.godgod.feature.AlbumPickerActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO permission check 후
        AlbumPickerActivity.start(this)
    }
}