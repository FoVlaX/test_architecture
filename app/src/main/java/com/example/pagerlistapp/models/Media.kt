package com.example.pagerlistapp.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Ignore
import java.io.Serializable


data class Media(
    @ColumnInfo(name ="media_id_m")
        var media_id: Int?,
    var type: String?,
    @ColumnInfo(name ="user_id_m")
        var user_id: String?,
    var caption: String?,
    @Embedded
        var data: Data?,
    var create_date: String?,
    var use_type: String?,
    @ColumnInfo(name ="uri_m")
        var uri: String?,
    var base_url: String?,
    var _extended: String?
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

    data class Data(
        var version: String?,
        var version_big: String?,
        var version_orig: String?,
        @Ignore
            var sizes: Size?,
        var x: String?,
        var y: String?,
        var ratio: String?,
        var ext: String?,
        @Ignore
            var is_animated: String?
    )

    data class Size(
            val orig: SizeOrig?
    )

    data class SizeOrig(
            val x: String?,
            val y: String?,
            val hash_file_name: String?
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