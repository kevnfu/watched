package com.example.watched.domain

data class Show (
    val id: Int,
    val name: String?,
    val status: String?,
    val runtime: Int?,
    val premiered: String?,
    val officialSite: String?,
    val image: String?,
    val imageMedium: String?,
    val summary: String?,
    val seasons: List<Int>?
) {

}