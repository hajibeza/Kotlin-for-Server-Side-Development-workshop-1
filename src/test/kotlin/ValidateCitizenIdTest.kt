import kotlin.test.Test;
import kotlin.test.assertEquals;
import kotlin.test.assertTrue;
import kotlin.test.assertFalse;

class ValidateCitizenIdTest {
    @Test
    fun `valid 13 digital id return true` () {
        val id = "1234567890123";
        val result = ValidateCitizenId(id);

        assertTrue(result);
    }

    @Test
    fun `id with wrong length return false` () {
        assertFalse(ValidateCitizenId("12345678901234"));
        assertFalse(ValidateCitizenId("123456789012"));
        assertFalse(ValidateCitizenId(""));
    }

    @Test
    fun `id containing non digit characters return false` () {
        assertFalse(ValidateCitizenId("1234567890123a")); // letter
        assertFalse(ValidateCitizenId("ghsoahsdohoawgowhg")); // letter
        assertFalse(ValidateCitizenId("12345 6789012")); //space
    }
}
