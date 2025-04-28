package com.wanted.preonboarding.chan99k.product_cqrs.domain

/**
 * 상품 가격의 스냅샷을 제공한다.
 */
data class ProductPrice(
    val id: Long? = null,
    val productId: Long, // (FK)
    val basePrice: Int,
    val salePrice: Int,
    val costPrice: Int, // (관리용)
    val currency: Currency, // (기본값 KRW)
    val taxRate: Int,
) {
    /**
     * 상품 가격을 수정한다.
     * @return ProductPrice : 새로운 객체를 생성한 뒤, 필요에 의해 반환한다.
     */
    fun update(
        newBasePrice: Int? = null,
        newSalePrice: Int? = null,
        newCostPrice: Int? = null,
        newTaxRate: Int? = null,
    ): ProductPrice {

        return this.copy(
            id = this.id,
            productId = this.productId,
            basePrice = newBasePrice ?: this.basePrice,
            salePrice = newSalePrice ?: this.salePrice,
            costPrice = newCostPrice ?: this.costPrice,
            taxRate = newTaxRate ?: this.taxRate,
        )
    }

    enum class Currency(
        val description: String,
    ) {
        KRW("원화"), EN("달러화"), CN("위안회"), JP("엔화"),
    }
}