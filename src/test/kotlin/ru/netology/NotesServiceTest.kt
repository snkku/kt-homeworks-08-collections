package ru.netology


import Comment
import Note
import NotesService
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class NotesServiceTest {
    val ns = NotesService

    @Before
    fun initiate() {
        ns.clear()
        ns.add(Note(0, "Test", "Test1", mutableListOf()))
        ns.add(Note(0, "Test2", "Test2", mutableListOf()))
        ns.add(Note(0, "Test3", "Test3", mutableListOf()))
        ns.add(Note(0, "Test4", "Test4", mutableListOf()))
        ns.addComment(2, Comment(1, "sadsadsadsad"))
    }

    @Test
    fun addNote() {
        val id = ns.add(Note(10, "Test10", "Test text", mutableListOf()))
        assertNotEquals(id, 0)
    }

    @Test
    fun addComment() {
        ns.addComment(3, Comment(0, "Test comment"))
        assertEquals("Test comment", ns.getComments(3)!![0].message)
    }

    @Test
    fun getComments() {
        assertNotNull(ns.getComments(2))
    }

    @Test
    fun edit() {
        val noteReplace = Note(10, "Replaced", "Aasdsdd", mutableListOf())
        ns.edit(3, noteReplace)
        assertEquals("Replaced", ns.get(10, ns.list)!!.title)
    }

    @Test
    fun getById() {
        val id = ns.get(3, ns.list)!!.id
        assertEquals(id, 3)
    }

    @Test
    fun remove() {
        ns.remove(10)
        assertNull(ns.get(10, ns.list))
    }

    @Test
    fun removeComment()
    {
        val result = ns.removeComment(1, 2)
        assertEquals(1, result)
    }

    @Test
    fun removeCommentUnsucess()
    {
        val result = ns.removeComment(2,2)
        assertEquals(180, result)
    }

    @Test
    fun restoreComment()
    {
        val result = ns.restoreComment(1,2)
        assertEquals(1, result)
    }

    @Test
    fun editComment()
    {
        val comment = ns.get(1, ns.getComments(2)!!)
        if (comment !== null)
        {
            val newMessage = "New message"
            comment.message = newMessage
            val result = ns.editComment(1, 2, comment)
            val newComment = ns.get(1, ns.getComments(2)!!)
            assertEquals(newMessage, newComment!!.message)
            assertEquals(1, result)
        }
    }
}