package com.p2lem8dev.todo

import android.util.Log
import java.util.*

class TodoItem(
    val id: UUID,
    text: String
) {
    val createdAt = Date()
    var updatedAt = Date()
    private var mCompleted: Boolean = false

    var completed: Boolean
        get() = mCompleted
        set(value: Boolean) {
            updatedAt = Date()
            mCompleted = value
        }

    private var mText = text

    var text: String
        get() = mText
        set(value){
            mText = value
            updatedAt = Date()
        }

    companion object {
        fun create(text: String): TodoItem = TodoItem(UUID.randomUUID(), text)
    }
}
