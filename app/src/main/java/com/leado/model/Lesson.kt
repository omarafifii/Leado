package com.leado.model

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.ServerTimestamp
import java.util.*


data class Lesson(

    var title: String ?= "",
    var description: String ?= "",
    var link: String? = "",
    var courseCategory:String?="",
    var stringId: String ?= "",
    var id: Int = 0,
    var icon: Int = 0,
    var isActive:Boolean = false,
    var videoPoint:Float = 0f
) :Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readFloat()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(link)
        parcel.writeString(courseCategory)
        parcel.writeString(stringId)
        parcel.writeInt(id)
        parcel.writeInt(icon)
        parcel.writeByte(if (isActive) 1 else 0)
        parcel.writeFloat(videoPoint)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Lesson> {
        override fun createFromParcel(parcel: Parcel): Lesson {
            return Lesson(parcel)
        }

        override fun newArray(size: Int): Array<Lesson?> {
            return arrayOfNulls(size)
        }
    }


}
