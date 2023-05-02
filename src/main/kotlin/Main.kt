package ru.netology

import kotlin.Exception

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
    var comments: Comments = Comments(), // init comments with post creations
    var likes: Likes = Likes(),
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

class Comments {
    private var comment_id = 0
    private var comments = emptyArray<Comment>()

    fun addComment(authorId: Int, text: String): Comment {
        comments += Comment(authorId, text)
        comment_id++
        return comments.last()
    }

    fun modifyComment(commentId: Int, text: String): Boolean {
        comments.withIndex().forEach { (index, comment) ->
            run {
                if (commentId == index) {
                    comments[index] = comment.copy(text = text)
                    return true
                }
            }
        }
        return false
    }

    fun removeComment(commentId: Int): Boolean {
        comments.withIndex().forEach { (index) ->
            run {
                if (commentId == index) {
                    comments.drop(index)
                    return true
                }
            }
        }
        return false
    }
}

data class Comment(
    val author_id: Int,
    var text: String
)

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

class PostNotFoundException(message: String) : Exception(message)

object WallService {
    private var posts = emptyArray<Post>()
    private var lastPostId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastPostId)
        return posts.last()
    }

    fun addComment(postId: Int, authorId: Int, text: String): Comment {
        posts.withIndex().forEach { (index, post) ->
            run {
                if (index == postId) {
                    return posts[index].comments.addComment(99, "Comment number 1")
                }
            }
        }
        throw PostNotFoundException("Post $postId not found! Cant add comment")
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

}