package com.blandinf.neighbors.models

data class Neighbor(
        val id : Int = 0,
        val name: String,
        val avatarUrl: String,
        val address: String,
        val phoneNumber: String,
        val aboutMe: String,
        val favorite: Boolean,
        val webSite: String
)