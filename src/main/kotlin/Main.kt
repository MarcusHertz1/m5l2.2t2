package ru.netology

interface Attachment {
    val type: String
}

data class PhotoAttachment(val photo: Photo) : Attachment {
    override val type = "photo"
}
data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val text: String,
    val date: Int
)

data class AudioAttachment(val audio: Audio) : Attachment {
    override val type = "audio"
}
data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String
)

data class VideoAttachment(val video: Video) : Attachment {
    override val type = "video"
}
data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String,
    val duration: Int
)

data class FileAttachment(val file: File) : Attachment {
    override val type = "file"
}
data class File(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size: Int,
    val ext: String,
    val url: String,
    val date: Int
)

data class StickerAttachment(val sticker: Sticker) : Attachment {
    override val type = "sticker"
}
data class Sticker(
    val stickerId: Int,
    val productId: Int,
    val isAllowed: Boolean
)

data class Comments(
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = false
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = true
)

data class Post(
    val id: Int = 0,
    val date: Int? = null,
    val text: String? = null,
    val friendsOnly: Boolean = false,
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val comments: Comments? = Comments(),
    val likes: Likes? = Likes(),
    val attachments: List<Attachment> = emptyList()
)

object WallService {
    private var posts = mutableListOf<Post>()
    private var nextId = 1

    fun add(post: Post): Post {
        val postWithId = post.copy(id = nextId++)
        posts += postWithId
        return postWithId
    }

    fun update(post: Post): Boolean {
        for ((index, current) in posts.withIndex()) {
            if (current.id == post.id) {
                posts[index] = post.copy(
                    date = current.date,
                    comments = current.comments,
                    likes = current.likes
                )
                return true
            }
        }
        return false
    }

    fun clear() {
        posts.clear()
        nextId = 1
    }

    fun getAll(): List<Post> = posts.toList()
}

fun main() {
    val photo = PhotoAttachment(
        Photo(id = 1, albumId = 10, ownerId = 100, text = "Пейзаж", date = 1680001000)
    )

    val audio = AudioAttachment(
        Audio(id = 2, ownerId = 100, artist = "Artist", title = "Track", duration = 180, url = "https://audio")
    )

    val video = VideoAttachment(
        Video(id = 3, ownerId = 100, title = "Видео", description = "Описание видео", duration = 60)
    )

    val file = FileAttachment(
        File(id = 4, ownerId = 100, title = "Документ", size = 2048, ext = "pdf", url = "https://file", date = 1680001234)
    )

    val sticker = StickerAttachment(
        Sticker(stickerId = 5, productId = 500, isAllowed = true)
    )

    val post1 = Post(
        date = 1680000000,
        text = "Пост со всеми типами вложений",
        friendsOnly = true,
        canPin = true,
        canDelete = true,
        canEdit = true,
        isPinned = false,
        markedAsAds = false,
        isFavorite = true,
        attachments = listOf(photo, audio, video, file, sticker)
    )

    val addedPost1 = WallService.add(post1)
    println("Добавлен пост: $addedPost1")

    val post2 = Post(
        date = 1680000010,
        text = "Пост с фото, видео и стикером",
        friendsOnly = false,
        canPin = true,
        canDelete = false,
        canEdit = true,
        isPinned = true,
        markedAsAds = true,
        isFavorite = false,
        attachments = listOf(photo, video, sticker)
    )

    val addedPost2 = WallService.add(post2)
    println("Добавлен пост: $addedPost2")

    val updated = WallService.update(
        addedPost1.copy(text = "Пост обновлён (текст)")
    )
    println("Пост обновлён: $updated")

    println("\nВсе посты:")
    for (post in WallService.getAll()) {
        println(post)
    }
}

