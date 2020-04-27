package com.leado.model

import android.os.Parcel
import android.os.Parcelable


data class Lesson(

 var title: String = "",
    var id: Int = 0,
    var description: String = "",
    var link: String = "",
    var icon: Int = 0,
    var isActive:Boolean = false
) :Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(id)
        parcel.writeString(description)
        parcel.writeString(link)
        parcel.writeInt(icon)
        parcel.writeByte(if (isActive) 1 else 0)
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
