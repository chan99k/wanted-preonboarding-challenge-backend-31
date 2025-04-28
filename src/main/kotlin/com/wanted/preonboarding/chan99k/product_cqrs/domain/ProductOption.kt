package com.wanted.preonboarding.chan99k.product_cqrs.domain

/**
 * 상품 옵션의 스냅샷을 제공한다.
 */
data class ProductOption(
    val id: Long? = null,
    val optionGroupID: Long,
    val name: String, // 옵션명 (예: "빨강, "XL")
    val additionalPrice: Int,
    val sku: String, // 재고 관리 코드
    val displayOrder: Int, // 표시 순서, TODO :: DisplayOrder 빼내어 관리하기
) {
    /**
     * 상품 옵션을 수정한다.
     * @return ProductOption : 새로운 객체를 생성한 뒤, 필요에 의해 반환한다.
     */
    fun update(
        newName: String? = null,
        newAdditionalPrice: Int? = null,
        newDisplayOrder: Int? = null,
    ): ProductOption {

        return this.copy(
            id = this.id,
            optionGroupID = this.optionGroupID,
            name = newName ?: this.name,
            additionalPrice = newAdditionalPrice ?: this.additionalPrice,
            sku = this.sku,
            displayOrder = newDisplayOrder ?: this.displayOrder,
        )
    }
}