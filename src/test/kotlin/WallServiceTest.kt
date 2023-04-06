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
}