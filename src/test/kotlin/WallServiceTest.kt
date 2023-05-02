package ru.netology

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
        // заполняем несколькими постами
        WallService.add(Post(0, 1, 1, 1, 1680692400, "My post 1", 1, 1, false, Comments(), Likes()))
        WallService.add(Post(0, 1, 1, 1, 1680692401, "My post 2", 1, 1, true, Comments(), Likes()))
        WallService.add(Post(0, 1, 1, 1, 1680692402, "My post 3", 1, 1, false, Comments(), Likes()))

    }

    @Test
    fun updateExists() {
        val service = WallService
        val postId = service.getLastPostId()
        val update = service.getPost(postId)
        update.text = "New Text in Last Post!"
        val result = service.update(update)
        assertTrue(result)
    }

    @Test
    fun updateNonExists() {
        val service = WallService
        val postId = service.getLastPostId()
        val update = service.getPost(postId)
        update.text = "New Text in Last Post!"
        update.id = 99999 //non exists
        val result = service.update(update)
        assertFalse(result)
    }

    @Test
    fun addPost()
    {
        val service = WallService
        val lastId = service.getLastPostId()
        val post = service.add(Post(0, 1, 1, 1, 1680692402, "My post 4", 1, 1, false, Comments(), Likes()))
        val newId = service.getLastPostId()
        assertNotEquals(lastId, newId)
        assertNotNull(post)
    }

    @Test
    fun addPostWithAttachments()
    {
        val service = WallService
        val lastId = service.getLastPostId()
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
        val post1 = Post(1, 1, 1, 1, 199999999, "Post text", 0, 0, true, null, null, attachments = post1Attachments)
        val post2 = Post(1, 1, 1, 1, 199999999, "Post text", 0, 0, true, null, null, attachments = post2Attachments)
        val post1_id = service.add(post1)
        val post2_id = service.add(post2)
        val newId = service.getLastPostId()
        assertNotEquals(lastId, newId)
        assertNotNull(post1_id)
        assertNotNull(post2_id)
    }
}