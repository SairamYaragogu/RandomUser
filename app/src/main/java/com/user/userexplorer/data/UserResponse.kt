package com.user.userexplorer.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

data class UserResponse(
    val results: List<User>
)

@Parcelize
data class User(
    val name: @RawValue Name,
    val location: @RawValue Location,
    val email: @RawValue String,
    val phone: @RawValue String,
    val picture: @RawValue Picture
):Parcelable

data class Name(
    val first: String,
    val last: String,
)

data class Location(
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String
)

data class Street(
    val number: Int,
    val name: String
)

data class Picture(
    val large: String
)

