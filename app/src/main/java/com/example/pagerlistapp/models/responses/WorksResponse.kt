package com.example.pagerlistapp.models.responses

import com.example.pagerlistapp.models.*
import com.example.pagerlistapp.models.Set

data class WorksResponse(
        val data: Data?
) {

    data class Data(
            val count: String?,
            val works: List<Work>?,
            val media: List<Media>?,
            val styles: List<Style>?,
            val techniques: List<Technique>?,
            val genres: List<Genre>?,
            val sets: List<Set>?,
            val artists: List<Artist>?,
            val types: List<Type>?,
            val materials: List<Material>?,
            val tags: List<Tag>?
    ) {
        fun getArtWorks(): List<Work> {
            val _works = works ?: emptyList()
            _works.forEach {
                it.media = this.media?.firstOrNull { media -> it.media_id == media.media_id }
                it.techniques = findTechnicsByWork(it)
                it.styles = findStylesByWork(it)
                it.materials = findMaterialsByWork(it)
                it.genres = findGenresByWork(it)
                //it.tags = findTagsByWork(it)
                it.sets = findCollectionsByWork(it)
            }
            return _works
        }

        private fun findTechnicsByWork(work: Work): List<Technique>? {
            return techniques?.filter {
                (work.technique_ids ?: emptyList()).contains(it.technique_id ?: "")
            }
        }

        private fun findStylesByWork(work: Work): List<Style>? {
            return styles?.filter { (work.style_ids ?: emptyList()).contains(it.style_id ?: "") }
        }

        private fun findMaterialsByWork(work: Work): List<Material>? {
            return materials?.filter {
                (work.material_ids ?: emptyList()).contains(it.material_id ?: "")
            }
        }

        private fun findGenresByWork(work: Work): List<Genre>? {
            return genres?.filter { (work.genre_ids ?: emptyList()).contains(it.genre_id ?: "") }
        }

        /*private fun findTagsByWork(work: Work): List<Tag>? {
            return tags?.filter { tag ->
                (work.tags ?: emptyList()).map { it.tag_id }.contains(tag.tag_id ?: "")
            }
        }*/

        private fun findCollectionsByWork(work: Work): List<Set>? {
            return sets?.filter { collection ->
                (work.aset_ids ?: emptyList()).contains(collection.set_id ?: "")
            }
        }
    }
}