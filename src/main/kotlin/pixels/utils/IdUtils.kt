package pixels.utils

import java.util.Base64


private val HEX_CHARS = "0123456789abcdef".toCharArray()

fun String.toBase64URLtoHex() : String{
    val idDecoder = Base64.getUrlDecoder()
    val decodedId =  idDecoder.decode(this)
    val result = StringBuffer()

    decodedId.forEach{
        val octet = it.toInt()
        val firstIndex = (octet and 0xF0).ushr(4)
        val secondIndex = octet and 0x0F
        result.append(HEX_CHARS[firstIndex])
        result.append(HEX_CHARS[secondIndex])
    }

    return result.toString()
}