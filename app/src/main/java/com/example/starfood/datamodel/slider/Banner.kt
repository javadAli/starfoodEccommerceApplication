package com.example.starfood.datamodel.slider

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Banner(
    var id: Int,
    var image: String
): Parcelable
/*{
    fun addList(id: Int,image: String){
        this.id = id
        this.image = image
    }
}*/