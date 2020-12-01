package com.example.pagerlistapp.models.responses

import com.example.pagerlistapp.models.*


data class ArtistsResponce(
        val data: Data?
) {
    data class Data(
        val artists: List<Artist>?,
        val media: List<Media>?,
        var genres: List<Genre>?,
        val types: List<Type>?,
        var styles: List<Style>?,
        var techniques: List<Technique>?
    ) {

        fun getFirstArtist(): Artist {
            return (artists ?: emptyList())[0].apply {
                genres = this@Data.genres?.filter { findGenre(it.genre_id.toString() ?: "") != null }
                        ?: emptyList()
                styles = this@Data.styles?.filter { findStyle(it.style_id.toString() ?: "") != null }
                        ?: emptyList()
                techniques = this@Data.techniques?.filter {
                    findTechnique(it.technique_id ?: "") != null
                } ?: emptyList()
            }
        }

        private fun findAvatarByIdMedia(mediaId: String): Media? {
            return (media ?: emptyList()).firstOrNull { it.media_id.toString() == mediaId }
        }

        private fun findGenre(idGenre: String): Genre? {
            return (genres ?: emptyList()).firstOrNull { idGenre == it.genre_id.toString() }
        }

        private fun findTechnique(idTechnique: String): Technique? {
            return (techniques ?: emptyList()).firstOrNull { idTechnique == it.technique_id }
        }

        private fun findStyle(idStyle: String): Style? {
            return (styles ?: emptyList()).firstOrNull { idStyle == it.style_id.toString() }
        }
    }
}