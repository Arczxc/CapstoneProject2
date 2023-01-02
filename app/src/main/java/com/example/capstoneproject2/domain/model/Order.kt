package com.example.capstoneproject2.domain.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Order(
    val id: String? = null,
    @ServerTimestamp
    val dateOfSubmission: Date? = null,
    val total: Int? = null
)