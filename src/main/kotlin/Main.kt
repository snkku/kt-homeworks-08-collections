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
        likedBy += id
    }

    fun likedCount(): Int
    {
        // return like count
        return likedBy.size
    }

    // return liked userIds
    fun likedBy(): Array<Int>
    {
        return likedBy
    }

    fun disLike(id: Int)
    {
        // remove userId from liked array
        if (likedBy.contains(id))
            likedBy.drop(likedBy.indexOf(id))
    }
}

object WallService {
    private var posts = emptyArray<Post>()

    fun add (post: Post): Post
    {
        // ну ведь хэшкод должен быть уникальным?;)
        post.id = post.hashCode()
        posts += post
        return posts.last()
    }

    fun getLastPostId(): Int
    {
        val post = posts.last()
        return post.id
    }

    fun getPost(id: Int): Post
    {
        for ((_, post) in posts.withIndex())
        {
            if (post.id == id)
                return post.copy() // return copy (not link to original)
        }
        throw Exception("Post not found")
    }

    fun edit (newPost: Post): Boolean
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
    }
}

fun main() {

}