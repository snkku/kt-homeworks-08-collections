package ru.netology

sealed class Attachment(
    open val type: String
)

data class AudioAttachment(val audio: Audio) : Attachment("audio")

data class Audio(
    val id: Int,
    val owner_id: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String,
    val lyrics_id: Int,
    val album_id: Int,
    val genre_id: Int,
    val date: Int,
    val no_search: Boolean = true,
    val is_hq: Boolean = true
)


data class PhotoAttachment(val photo: Photo) : Attachment("photo")

data class Photo(
    val id: Int,
    val album_id: Int,
    val owner_id: Int,
    val user_id: Int,
    val text: String,
    val date: Int,
    val width: Int,
    val height: Int
)

data class DirectPhotoAttachment(val direct_photo: DirectPhoto) : Attachment("direct_photo")
data class DirectPhoto(
    val id: Int,
    val owner_id: Int,
    val photo_130: String,
    val photo_604: String
)

data class AlbumAttachment(val album: Album) : Attachment("album")
data class Album(
    val id: Int,
    val thumb: Any,
    val owner_id: Int,
    val title: String,
    val description: String,
    val created: Int,
    val updated: Int,
    val size: Int
)

data class PageAttachment(val page: Page) : Attachment("page")
data class Page(
    val id: Int,
    val group_id: Int,
    val title: String
)