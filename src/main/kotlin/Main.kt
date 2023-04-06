package ru.netology

import java.lang.Exception

data class Post (
    var id: Int,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int,
    val date: Int,
    var text: String,
    val replyOwnerId: Int,
    val replyPostId: Int,
    var friendsOnly: Boolean,
    var comments: Comments,
    var likes: Likes)

class Comments
class Likes {
    private var likedBy = emptyArray<Int>()

    fun add (id: Int)
    {
        // add liked userId
        this.likedBy += id
    }

    fun likedCount(): Int = this.likedBy.size

    // return liked userIds
    fun likedBy(): Array<Int> = this.likedBy

    fun disLike(id: Int)
    {
        // remove userId from liked array
        if (this.likedBy.contains(id))
            this.likedBy.drop(this.likedBy.indexOf(id))
    }
}

object WallService {
    private var posts = emptyArray<Post>()
    private var lastPostId = 0

    fun add (post: Post): Post
    {
        post.id = ++lastPostId
        posts += post
        return posts.last()
    }

    fun getLastPostId(): Int = lastPostId

    fun getPost(id: Int): Post
    {
        for ((_, post) in posts.withIndex())
        {
            if (post.id == id)
                return post.copy() // return copy (not link to original)
        }
        throw Exception("Post not found")
    }

    fun update (newPost: Post): Boolean
    {
        for ((index, post) in posts.withIndex()) {
            if (post.id == newPost.id) {
                // replace old with new
                posts[index] = newPost
                return true
            }
        }
        return false
    }

    fun clear()
    {
        posts = emptyArray()
        lastPostId = 0
    }
}

fun main() {

}