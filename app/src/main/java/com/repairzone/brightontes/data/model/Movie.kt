package com.repairzone.brightontes.data.model

import com.repairzone.brightontes.ui.widget.OnActionModel

data class Movie(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String
): OnActionModel()