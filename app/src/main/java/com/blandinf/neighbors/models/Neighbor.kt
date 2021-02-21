package com.blandinf.neighbors.models

import kotlin.random.Random

data class Neighbor(
        val id : Long = Random.nextLong(from = 0, until = Long.MAX_VALUE),
        val name: String,
        val avatarUrl: String,
        val address: String,
        val phoneNumber: String,
        val aboutMe: String,
        val favorite: Boolean,
        val webSite: String
)