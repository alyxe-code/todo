package com.p2lem8dev.todo

import android.util.Log
import java.io.File

open class FileSystemRepository(
    protected var commonDirectory: String
) {
    protected fun readFromFile(filename: String): String? {
        val file = File(filename)
        if (!file.exists()) {
            file.createNewFile()
            return null
        }
        return file.readText()
    }

    protected fun writeToFile(filename: String, content: String) {
        val file = File(filename)
        file.writeText(content)
    }
}