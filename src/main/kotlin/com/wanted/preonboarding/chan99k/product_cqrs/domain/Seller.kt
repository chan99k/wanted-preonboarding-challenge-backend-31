package com.wanted.preonboarding.chan99k.product_cqrs.domain

import java.time.Instant

data class Seller(
    val id: Long? = null,
    val name: String, // 판매자명
    val description: String,
    val logoUrl: String,
    val rating: Int, // 평정
    val contactEmail: String,
    val contactPhone: String,
    val createdAt: Instant = Instant.now(),
) {
    fun update(
        newName: String? = null,
        newDescription: String? = null,
        newLogoUrl: String? = null,
        newRating: Int? = null,
        newContactEmail: String? = null,
        newContactPhone: String? = null,
    ): Seller {

        return this.copy(
            id = this.id,
            name = newName ?: this.name,
            description = newDescription ?: this.description,
            logoUrl = newLogoUrl ?: this.logoUrl,
            rating = newRating ?: this.rating,
            contactEmail = newContactEmail ?: this.contactEmail,
            contactPhone = newContactPhone ?: this.contactPhone,
        )
    }
}