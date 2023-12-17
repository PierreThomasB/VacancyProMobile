package com.project.vacancypromobile.models

import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.Date

class Message(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("message")
    val message: String = "null",
    @SerializedName("date")
    val date: Date = Date(),
    @SerializedName("userName")
    val userName: String? = "null",
    @SerializedName("channel")
    val channel: String = "null"
) {


    fun showDate() : String{
        val format = SimpleDateFormat("dd/MM/yy")
        return format.format(date)
    }


    fun userInitials(): String? {
        if(userName == null){
            return null
        }
        val split = userName.split(" ")
        return split[0][0].toString() + split[1][0].toString()
    }
    fun stringToColor(): Color? {
        if(userName == null){
            return null
        }
        var hash = 0
        for (element in userName) {
            hash = element.code + ((hash shl 5) - hash)
        }

        val red = (hash shr 16) and 0xFF
        val green = (hash shr 8) and 0xFF
        val blue = hash and 0xFF

        return Color(red = red / 255f, green = green / 255f, blue = blue / 255f, alpha = 1f)
    }
}
