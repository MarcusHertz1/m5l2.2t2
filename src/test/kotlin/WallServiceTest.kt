import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import ru.netology.Post
import ru.netology.WallService

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add_ShouldAssignId() {
        val post = Post(text = "Test post")
        val result = WallService.add(post)
        assertNotEquals(0, result.id)
    }

    @Test
    fun update_ShouldReturnTrue_WhenPostExists() {
        val original = WallService.add(Post(text = "Original post"))
        val updated = original.copy(text = "Updated text")
        val result = WallService.update(updated)
        assertTrue(result)
    }

    @Test
    fun update_ShouldReturnFalse_WhenPostDoesNotExist() {
        WallService.add(Post(text = "Existing post"))
        val nonExisting = Post(id = 999, text = "Does not exist")
        val result = WallService.update(nonExisting)
        assertFalse(result)
    }
}