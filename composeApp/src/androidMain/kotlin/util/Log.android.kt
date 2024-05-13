package util

actual fun Log(tag: String, message: String) {
    android.util.Log.d(tag, message)
}