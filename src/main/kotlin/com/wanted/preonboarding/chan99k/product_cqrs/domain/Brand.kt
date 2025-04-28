package com.wanted.preonboarding.chan99k.product_cqrs.domain

data class Brand(
    val id: Long? = null,
    val name: String,
    val slug: String,
    val description: String,
    val logoUrl: String,
    val website: String,
) {
    fun update(
        newName: String? = null,
        newSlug: String? = null,
        newDescription: String? = null,
        newLogoUrl: String? = null,
        newWebsite: String? = null,
    ): Brand {

        return this.copy(
            id = this.id,
            name = newName ?: this.name,
            slug = newSlug ?: this.slug,
            description = newDescription ?: this.description,
            logoUrl = newLogoUrl ?: this.logoUrl,
            website = newWebsite ?: this.website,
        )
    }
}