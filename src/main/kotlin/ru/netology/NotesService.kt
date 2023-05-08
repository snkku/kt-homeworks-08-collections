data class Note(
    override val id: Int,
    val title: String,
    val text: String,
    val comments: MutableList<Comment>,
) : Identifiable

data class Comment(
    override val id: Int,
    val message: String,
) : Identifiable

interface Identifiable {
    val id: Int
}

object NotesService {
    private var id = 0
    var list = mutableListOf<Note>()
    fun add(note: Note): Int {
        list.add(note.copy(id = id++))
        return id
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
        for (elem in list) {
            if (elem.id == id)
                return elem
        }
        return null
    }

    fun <E : Identifiable> get(id: Int, list: List<E>): E? {
        for (elem in list) {
            if (elem.id == id)
                return elem
        }
        return null
    }

    fun remove(id: Int) {
        for (elem in this.list) {
            if (elem.id == id)
            {
                this.list.remove(elem)
                return
            }
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for ((index, note) in this.list.withIndex()) {
            sb.appendLine("ID: $index -> $note")
        }
        return sb.toString()
    }
}