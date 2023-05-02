package ru.netology

import java.lang.Exception

data class Post(
    var id: Int,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int,
    val date: Int,
    var text: String,
    val replyOwnerId: Int,
    val replyPostId: Int,
    var friendsOnly: Boolean,
    var comments: Comments?,
    var likes: Likes?,
    var attachments: Array<Attachment> = emptyArray(),
    var signer_id: Int = 0,
    var copy_history: Array<Int> = emptyArray(),
    var can_pin: Boolean = true,
    var can_edit: Boolean = true,
    var is_pinned: Boolean = false,
    var marked_as_ads: Boolean = false,
    var is_favorite: Boolean = false,
    var postponed_id: Int = 0
)

class Comments
class Likes {
    private var likedBy = emptyArray<Int>()

    fun add(id: Int) {
        // add liked userId
        this.likedBy += id
    }

    fun likedCount(): Int = this.likedBy.size

    // return liked userIds
    fun likedBy(): Array<Int> = this.likedBy

    fun disLike(id: Int) {
        // remove userId from liked array
        if (this.likedBy.contains(id))
            this.likedBy.drop(this.likedBy.indexOf(id))
    }
}

object WallService {
    private var posts = emptyArray<Post>()
    private var lastPostId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastPostId)
        return posts.last()
    }

    fun getLastPostId(): Int = lastPostId

    fun getPost(id: Int): Post {
        for ((_, post) in posts.withIndex()) {
            if (post.id == id)
                return post.copy() // return copy (not link to original)
        }
        throw Exception("Post not found")
    }

    fun update(newPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == newPost.id) {
                // replace old with new
                posts[index] = newPost
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        lastPostId = 0
    }
}

fun main() {

    val audio = Audio(1, 1, "Sting", "Shape of my heart", 3600, "http://sting.com", 0, 1, 3, 199999999)
    val photo = Photo(2, 2, 1, 10, "Photo", 1919919919, 100, 50)
    val direct_photo = DirectPhoto(3, 1, "http://myurl1", "http://myurl2")
    val album = Album(4, object {}, 1, "Album name", "Album description", 199999999, 0, 10)
    val page = Page(3, 10, "Page title")
    val post1Attachments = emptyArray<Attachment>().apply {
        plus(AudioAttachment(audio))
        plus(PhotoAttachment(photo))
    }
    val post2Attachments = emptyArray<Attachment>().apply {
        plus(DirectPhotoAttachment(direct_photo))
        plus(AlbumAttachment(album))
        plus(PageAttachment(page))
    }

    println(post1Attachments)
    println(post2Attachments)
    val post1 = Post(1, 1, 1, 1, 199999999, "Post text", 0, 0, true, null, null, attachments = post1Attachments)
    val post2 = Post(2, 1, 1, 1, 199994999, "Post text2", 0, 0, true, null, null, attachments = post2Attachments)

    println(post1)
    println(post2)
}