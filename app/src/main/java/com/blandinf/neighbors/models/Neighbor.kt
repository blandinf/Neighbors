package com.blandinf.neighbors.models

import android.os.Parcel
import android.os.Parcelable
import kotlin.random.Random

data class Neighbor(
    val id: Long = Random.nextLong(from = 0, until = Long.MAX_VALUE),
    val name: String,
    val avatarUrl: String,
    val address: String,
    val phoneNumber: String,
    val aboutMe: String,
    var favorite: Boolean,
    val webSite: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(avatarUrl)
        parcel.writeString(address)
        parcel.writeString(phoneNumber)
        parcel.writeString(aboutMe)
        parcel.writeByte(if (favorite) 1 else 0)
        parcel.writeString(webSite)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Neighbor> {
        override fun createFromParcel(parcel: Parcel): Neighbor {
            return Neighbor(parcel)
        }

        override fun newArray(size: Int): Array<Neighbor?> {
            return arrayOfNulls(size)
        }
    }
}
