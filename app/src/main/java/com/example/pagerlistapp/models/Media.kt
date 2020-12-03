package com.example.pagerlistapp.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Ignore
import java.io.Serializable



data class Media(
    @ColumnInfo(name ="media_id_m")
    var media_id: Int? = null,
    var type: String? = null,
    @ColumnInfo(name ="user_id_m")
    var user_id: String? = null,
    var caption: String? = null,
    @Embedded
    var data: Data? = null,
    var create_date: String? = null,
    var use_type: String? = null,
    @ColumnInfo(name ="uri_m")
    var uri: String? = null,
    var base_url: String? = null,
    var _extended: String? = null
) : Serializable {
    companion object {
        @Ignore
        val sizes = arrayOf(0, 10, 20, 40, 80, 100, 200, 400, 800, 1000, 1200, 1400, 1800, 2000, 2200, 2400, 2800)
    }
    @Ignore
    fun makeUrl(ratio: MediaRatio, side: MediaSide, imageViewSize: Int): String {
        val size = getCorrectSizeImage(imageViewSize)
        val version = if (size > 1500) data?.version_big ?: "" else data?.version ?: ""
        return "${base_url}/img/${ratio}${side}${size}/${use_type}/${version}/${media_id}.${data?.ext}"
    }
    @Ignore
    private fun getCorrectSizeImage(sizeImage: Int): Int {
        for (i in 0..sizes.size - 1) {
            if (i < sizes.size - 1 && sizeImage >= sizes[i] && sizeImage <= sizes[i + 1]) {
                return sizes[i]
            }
        }

        return sizes.size - 1
    }

    data class Size(
            @Embedded
            var orig: SizeOrig? = null
    )

    data class SizeOrig(
            @ColumnInfo(name = "size_orig_x")
            var x: String? = null,
            @ColumnInfo(name = "size_orig_y")
            var y: String? = null,
            var hash_file_name: String? = null
    )

    enum class MediaRatio {
        o,  //-соотношение сторон оригинального изображения
        s,  //-соотношение 1:1 (квадрат)
        h,  //-соотношение 3:2 (ландшафт)
        v   //-соотношение 3:5 (портрет)
    }

    enum class MediaSide {
        x, // по ширине
        y  // по высоте
    }
}