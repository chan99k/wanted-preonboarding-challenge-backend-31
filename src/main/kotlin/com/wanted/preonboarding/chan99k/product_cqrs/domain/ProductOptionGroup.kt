package com.wanted.preonboarding.chan99k.product_cqrs.domain

/**
 * 상품 옵션 그룹의 스냅샷을 제공한다.
 * 옵션 그룹 예시 : 색상, 사이즈
 */
data class ProductOptionGroup(
    // TODO :: Enum 으로 관리 가능하지 않을까?
    val id: Long? = null,
    val productId: Long,
    val name: String,
    val displayOrder: Int,
) {
    /**
     * 상품 옵션 그룹을 수정한다.
     * @return ProductOptionGroup : 새로운 객체를 생성한 뒤, 필요에 의해 반환한다.
     */
    fun update(
        newName: String?,
        newDisplayOrder: Int?
    ): ProductOptionGroup {

        return this.copy(
            id = this.id,
            productId = this.productId,
            name = newName ?: this.name,
            displayOrder = newDisplayOrder ?: this.displayOrder,
        )
    }
}
