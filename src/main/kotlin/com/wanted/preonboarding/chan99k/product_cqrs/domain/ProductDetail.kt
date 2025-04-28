package com.wanted.preonboarding.chan99k.product_cqrs.domain
/**
 * 상품 상세의 스냅샷을 제공한다.
 */
data class ProductDetail(
    val id: Long? = null,
    val productId: Long, // (FK)
    val weight: Int,
    val dimensions: String, //  (JSON)
    val materials: String,
    val countryOfOrigin: String,
    val warrantyInfo: String,
    val careInstructions: String,
    val additionalInfo: String,  // (JSONB)
) {
    /**
     * 상품 상세를 수정한다.
     * @return ProductDetail : 새로운 객체를 생성한 뒤, 필요에 의해 반환한다.
     */
    fun update(
        newWeight: Int?,
        newDimensions: String?,
        newMaterials: String?,
        newCountryOfOrigin: String?,
        newWarrantyInfo: String?,
        newCareInstructions: String?,
        newAdditionalInfo: String?,
    ): ProductDetail {

        return this.copy(
            id = this.id,
            productId = this.productId,
            weight = newWeight ?: this.weight,
            dimensions = newDimensions ?: this.dimensions,
            materials = newMaterials ?: this.materials,
            countryOfOrigin = newCountryOfOrigin ?: this.countryOfOrigin,
            warrantyInfo = newWarrantyInfo ?: this.warrantyInfo,
            careInstructions = newCareInstructions ?: this.careInstructions,
            additionalInfo = newAdditionalInfo ?: this.additionalInfo,
        )
    }
}
