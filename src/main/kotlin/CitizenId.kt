fun validateCitizenId(id: String): Boolean {
    // 1. ความยาวต้องเป็น 13 พอดี
    if (id.length != 13) return false
    val digits = IntArray(13)
    for (i in 0 until 13) {
        val c = id[i]
        digits[i] = when (c) {
            in '0'..'9' -> c - '0'   // เลขอารบิก
            in '๐'..'๙' -> c - '๐'   // เลขไทย
            else -> return false      // ตัวอักษร ช่องว่าง หรืออักขระอื่น
        }
    }

    // 3. ตรวจ check digit หลักที่ 13
    var sum = 0
    for (i in 0 until 12) {
        sum += digits[i] * (13 - i)
    }
    val checkDigit = (11 - (sum % 11)) % 10

    return checkDigit == digits[12]
}