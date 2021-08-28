package com.godgod.data.dataSource

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.annotation.WorkerThread
import com.godgod.share.model.AlbumData
import javax.inject.Inject

interface AlbumDataSource {

    suspend fun getAllMediaType(pageIndex: Int, pageCount: Int): List<AlbumData>
}

class AlbumDataSourceImpl @Inject constructor(private val albumProvider: AlbumDataProvider) :
    AlbumDataSource {

    override suspend fun getAllMediaType(pageIndex: Int, pageCount: Int): List<AlbumData> {
        return albumProvider.loadAlbum(pageIndex, pageCount)
    }
}

class AlbumDataProvider @Inject constructor(private val context: Context) {

    @WorkerThread
    fun loadAlbum(pageIndex: Int, pageCount: Int): List<AlbumData> {
        val albumDatas = mutableListOf<AlbumData>()

        val resolver = context.contentResolver

        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DATE_ADDED,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Files.FileColumns.MEDIA_TYPE,
            MediaStore.Video.VideoColumns.DURATION,
            MediaStore.Files.FileColumns.MIME_TYPE
        )

        val selection = (MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)

        val queryUri: Uri = MediaStore.Files.getContentUri("external")

        val cursor = resolver.query(
            queryUri,
            projection,
            selection,
            null,  // Selection args (none).
            MediaStore.Files.FileColumns._ID + " DESC LIMIT $pageCount OFFSET ${pageIndex * pageCount}"
        )
        cursor?.use { c ->
            val idColumn = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
            val durationColumn = c.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.DURATION)
            val categoryColumn =
                c.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            val mediaTypeColumn = c.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)

            while (c.moveToNext()) {
                val id = c.getLong(idColumn)
                val duration = c.getLong(durationColumn)
                val category = c.getString(categoryColumn)
                val mediaType = c.getString(mediaTypeColumn)
                val uri = createUri(id, mediaType)

                albumDatas.add(createAlbumData(uri, duration, category, mediaType))
            }
        }
        return albumDatas
    }

    private fun createAlbumData(
        uri: Uri,
        duration: Long,
        category: String,
        mediaType: String
    ): AlbumData {
        return when {
            mediaType.contains("gif") -> {
                AlbumData.Gif(uri, duration, category)
            }
            mediaType.contains("video") -> {
                AlbumData.Video(uri, duration, category)
            }
            else -> {
                AlbumData.Image(uri, category)
            }
        }
    }

    private fun createUri(id: Long, mediaType: String): Uri {
        return if (mediaType.contains("image")) {
            ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
        } else {
            ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id)
        }
    }
}

