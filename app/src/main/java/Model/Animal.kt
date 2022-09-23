package Model

import android.os.Parcel
import android.os.Parcelable

open class Animal(
    var namaHewan:String?,
    var jenisHewan:String?,
    var usiaHewan:String?,
    var imageUri: String?


): Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(namaHewan)
        parcel.writeString(jenisHewan)
        parcel.writeString(usiaHewan)
        parcel.writeString(imageUri)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Animal> {
        override fun createFromParcel(parcel: Parcel): Animal {
            return Animal(parcel)
        }

        override fun newArray(size: Int): Array<Animal?> {
            return arrayOfNulls(size)
        }
    }
}