data class Note(
    override val id: Int,
    val title: String,
    val text: String,
    val comments: MutableList<Comment>,
    override var deleted: Boolean = false
) : Identifiable

data class Comment(
    override val id: Int,
    var message: String,
    override var deleted: Boolean = false
) : Identifiable

interface Identifiable {
    val id: Int
    val deleted: Boolean
}

object NotesService {
    private var id = 0
    var list = mutableListOf<Note>()
    fun add(note: Note): Int {
        list.add(note.copy(id = id++))
        return id
    }

    fun clear()
    {
        this.list = mutableListOf<Note>()
        this.id = 0
    }

    fun addComment(noteId: Int, comment: Comment) {
        val note = this.get(noteId, list)
        if (note !== null) {
            note.comments.add(comment)
        }
    }

    fun getComments(noteId: Int): MutableList<Comment>? {
        val note = this.get(noteId, list)
        if (note !== null)
            return note.comments
        return null
    }

    fun edit(id: Int, noteReplace: Note): Int {
        for ((index, note) in list.withIndex()) {
            if (note.id == id) {
                list[index] = noteReplace.copy()
                return 1
            }
        }
        return 180
    }

    fun getById(id: Int): Note? {
        for (elem in this.list) {
            if (elem.id == id)
                return elem
        }
        return null
    }

    fun <E : Identifiable> get(id: Int, list: List<E>, deleted: Boolean = false): E? {
        for (elem in list) {
            if (elem.id == id && elem.deleted == deleted)
                return elem
        }
        return null
    }

    fun remove(id: Int) {
        for (elem in list) {
            if (elem.id == id)
            {
                this.list.remove(elem)
                return
            }
        }
    }

    fun removeComment(id: Int, noteId: Int, permanent: Boolean = false): Int {
        val note = this.getById(noteId)
        if (note !== null) {
            for (comment in note.comments) {
                if (comment.id == id) {
                    when (permanent) {
                        true -> note.comments.remove(comment)
                        false -> comment.deleted = true
                    }
                    return 1
                }
            }
        }
        return 180
    }
    fun restoreComment(id: Int, noteId: Int): Int
    {
        val note = this.getById(noteId)
        if (note !== null) {
            for (elem in note.comments)
            {
                if (elem.id == id)
                {
                    elem.deleted = false
                    return 1
                }
            }
        }
        return 180
    }

    fun editComment(id: Int, noteId: Int, comment: Comment): Int
    {
        val note = this.getById(noteId)
        if (note !== null)
        {
            val thisComment = this.get(id, note.comments)
            thisComment ?: comment.copy()
            return 1
        }
        return 180
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for ((index, note) in this.list.withIndex()) {
            sb.appendLine("ID: $index -> $note")
        }
        return sb.toString()
    }
}