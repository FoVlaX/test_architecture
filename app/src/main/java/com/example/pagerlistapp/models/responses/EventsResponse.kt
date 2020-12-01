package com.example.pagerlistapp.models.responses

import com.example.pagerlistapp.models.Media
import com.example.pagerlistapp.models.Set
import com.example.pagerlistapp.models.*

data class EventsResponse(
        val data: Data?
) {

    data class Data(
        val count: String?,
        val events: List<Event>?,
        val media: List<Media>?,
        val expositions: List<Exposition>?,
        val topics: List<Topic>?,
        val works: List<Work>?,
        val types: List<Type>?,
        val styles: List<Style>?,
        val genres: List<Genre>?,
        val materials: List<Material>?,
        val techniques: List<Technique>?,
        val tags: List<Tag>?
    ) {

       fun getNewsEvents(): List<Event> {
            val _events = events ?: emptyList()
            _events.forEach {
                it.content = it.attaches?.mapNotNull { makeInstanceAttachByUri(it.uri ?: "") }
            }
            return _events
        }

        private fun makeInstanceAttachByUri(uri: String): Any? {
            return when {
                uri.startsWith("topic") -> findTopicByUri(uri)
                uri.startsWith("work") -> findWorkByUri(uri)
                uri.startsWith("exposition") -> findExpositionByUri(uri)
                else -> null
            }
        }

        private fun findTopicByUri(uri: String): Topic? {
            return (topics ?: emptyList()).firstOrNull { it.uri == uri }?.apply {
                media = this@Data.media?.filter { (uris ?: emptyList()).contains(it.uri ?: "") }
            }
        }

        private fun findWorkByUri(uri: String): Work? {
            return (works ?: emptyList()).firstOrNull { it.uri == uri }?.apply {
                media = this@Data.media?.firstOrNull { it.media_id == media_id }
                techniques = findTechnicsByWork(this)
                styles = findStylesByWork(this)
                materials = findMaterialsByWork(this)
                genres = findGenresByWork(this)
                //tags = findTagsByWork(this)
            }
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
                //(work.tags ?: emptyList()).map { it.tag_id }.contains(tag.tag_id ?: "")
            }
        }*/

        private fun findExpositionByUri(uri: String): Exposition? {
            return expositions?.firstOrNull { it.uri == uri }?.apply {
                image = this@Data.media?.firstOrNull { it.media_id == ava_id }
            }
        }
    }
}