package util

actual fun Log(tag: String, message: String) {
    println("$tag: $message")
}