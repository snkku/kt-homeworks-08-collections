package ru.netology

object Attachments {
    private var attachments = emptyArray<Attachment>()

    fun add(id: Int, type: String, obj: Any)
    {
        this.attachments += Attachment(id, type, obj)
    }

    override fun toString(): String {
        val str = StringBuilder()
        for (item in this.attachments)
        {
            str.appendLine(item.toString())
        }
        return str.toString()
    }
}

data class Attachment(
    val id: Int,
    val type: String,
    val obj: Any
)

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

data class DirectPhoto(
    val id: Int,
    val owner_id: Int,
    val photo_130: String,
    val photo_604: String
)

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

data class Page(
    val id: Int,
    val group_id: Int,
    val title: String
)