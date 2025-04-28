package com.wanted.preonboarding.chan99k.product_cqrs.domain

data class ProductImage(
    val id: Long,
    val productId: Long, // FK
    val url: String,
    val altText: String, // 대체 텍스트
    val isPrimary: Boolean, // 대표 이미지 여부
    val displayOrder: String,
    val optionId: Long?, // 연관된 옵션 아이디, FK, Nullable
) {
    fun update(
        newUrl: String? = null,
        newAltText: String? = null,
        newIsPrimary: Boolean? = null,
        newDisplayOrder: String? = null,
        newOptionId: Long? = null,
    ): ProductImage {

        return this.copy(
            id = this.id,
            productId = this.productId,
            url = newUrl ?: this.url,
            altText = newAltText ?: this.altText,
            isPrimary = newIsPrimary ?: this.isPrimary,
            displayOrder = newDisplayOrder ?: this.displayOrder,
            optionId = newOptionId ?: this.optionId,
        )
    }
}