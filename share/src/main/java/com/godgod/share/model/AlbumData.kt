package com.godgod.share.model

import android.net.Uri

sealed class AlbumData(open val uri: Uri, open val duration: Long, open val category: String) {

    data class Video(
        override val uri: Uri,
        override val duration: Long,
        override val category: String
    ) : AlbumData(uri, duration, category)

    data class Image(
        override val uri: Uri,
        override val category: String
    ) : AlbumData(uri, 0L, category)

    data class Gif(
        override val uri: Uri,
        override val duration: Long,
        override val category: String
    ) : AlbumData(uri, duration, category)
}
