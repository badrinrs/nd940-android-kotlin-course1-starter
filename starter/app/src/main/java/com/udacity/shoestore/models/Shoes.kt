package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Shoes: ArrayList<Shoe>(), Parcelable {

}
