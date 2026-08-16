import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ValidateCitizenIdTest {

    // ============================================================
    // ขั้นที่ 1: เทสต์ 3 ตัวแรก — นิยามพฤติกรรมพื้นฐาน
    // ============================================================

    @Test
    fun `valid 13 digit id return true`() {
        // 1101700185206 เป็นเลขที่ผ่าน checksum จริง
        val id = "1101700185206"
        val result = validateCitizenId(id)

        assertTrue(result)
    }

    @Test
    fun `id with wrong length return false`() {
        assertFalse(validateCitizenId("11017001852066")) // 14 หลัก
        assertFalse(validateCitizenId("110170018520"))   // 12 หลัก
        assertFalse(validateCitizenId(""))               // ว่าง
    }

    @Test
    fun `id containing non digit characters return false`() {
        assertFalse(validateCitizenId("110170018520a")) // letter
        assertFalse(validateCitizenId("ghsoahsdohoawg")) // letter
        assertFalse(validateCitizenId("11017 0018520")) // space
    }

    // ============================================================
    // ขั้นที่ 4: edge case ที่ทำให้ implementation เดิมพัง
    // ============================================================

    @Test
    fun `id with wrong checksum returns false`() {
        // หลักที่ 13 ต้องเป็น check digit ที่คำนวณจาก 12 หลักแรก
        // 110170018520 → check digit ที่ถูกต้องคือ 6
        assertFalse(validateCitizenId("1101700185207")) // หลักสุดท้ายผิด
        assertFalse(validateCitizenId("1234567890129")) // ที่ถูกคือ ...1

        // ใบที่ checksum ถูกต้อง ต้องยังผ่านอยู่
        assertTrue(validateCitizenId("3509900547250"))
        assertTrue(validateCitizenId("1234567890121"))
    }

    // ============================================================
    // เทสต์เพิ่มเติม: รองรับเลขไทย
    // ============================================================

    @Test
    fun `id with Thai number returns true`() {
        assertTrue(validateCitizenId("๑๑๐๑๗๐๐๑๘๕๒๐๖"))
    }

    @Test
    fun `Thai number with wrong checksum returns false`() {
        assertFalse(validateCitizenId("๑๑๐๑๗๐๐๑๘๕๒๐๗"))
    }

    @Test
    fun `mixed Thai and Arabic digits works`() {
        // ๑๑๐๑๗๐๐๑๘๕๒๐๖ เขียนสลับเลขไทย/อารบิก ก็ต้องได้ผลเหมือนกัน
        assertTrue(validateCitizenId("๑๑๐1700๑๘๕๒๐๖"))
    }
}