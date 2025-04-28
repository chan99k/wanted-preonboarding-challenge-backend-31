package com.wanted.preonboarding.chan99k.product_cqrs.domain

import java.time.Instant

/**
 * 실제 상품의 스냅샷을 제공한다.
 */
data class Product(
    val id: Long? = null,
    val name: String,
    val slug: String,
    val shortDescription: String,
    val fullDescription: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val sellerId: Long, // FK
    val brandId: Long, // FK
    val status: ProductStatus,
) {
    /**
     * 상품 설명을 수정한다.
     * @return Product : 새로운 객체를 생성한 뒤, 필요에 의해 반환한다.
     */
    fun update(
        name: String? = null,
        slug: String? = null,
        shortDescription: String? = null,
        fullDescription: String? = null,
    ): Product {
        if (this.status == ProductStatus.ON_SALE) {
            throw IllegalStateException("상품이 이미 판매중으로 수정할 수 없습니다.")
        }

        return this.copy(
            name = name ?: this.name,
            slug = slug ?: this.slug,
            shortDescription = shortDescription ?: this.shortDescription,
            fullDescription = fullDescription ?: this.fullDescription,
            updatedAt = Instant.now() // 새로운 객체가 반환될 때 updatedAt 갱신
        )
    }

    /**
     * 상품을 판매중인 상태로 변경한다.
     * @return Product : 새로운 객체를 생성한 뒤, 필요에 의해 반환한다.
     */
    fun markAsOnSale(): Product {
        if (this.status == ProductStatus.DELETED) {
            throw IllegalStateException("이미 삭제된 상품입니다.")
        }

        return this.copy(
            status = ProductStatus.ON_SALE,
            updatedAt = Instant.now()
        )
    }

    /**
     * 상품을 품절된 상태로 변경한다.
     * @return Product : 새로운 객체를 생성한 뒤, 필요에 의해 반환한다.
     */
    fun markAsSoldOut(): Product {
        if (this.status == ProductStatus.DELETED) {
            throw IllegalStateException("이미 삭제된 상품입니다.")
        }

        return this.copy(
            status = ProductStatus.SOLD_OUT,
            updatedAt = Instant.now()
        )
    }

    /**
     * 상품을 판매 일시중단 상태로 변경한다.
     * @return Product : 새로운 객체를 생성한 뒤, 필요에 의해 반환한다.
     */
    fun markAsWithDrew(): Product {
        if (this.status == ProductStatus.DELETED) {
            throw IllegalStateException("이미 삭제된 상품입니다.")
        }

        return this.copy(
            status = ProductStatus.WITHDREW,
            updatedAt = Instant.now()
        )
    }

    /**
     * 상품을 삭제된 상태로 변경한다.
     * @return Product : 새로운 객체를 생성한 뒤, 필요에 의해 반환한다.
     */
    fun markAsDelete(): Product {
        if (status == ProductStatus.DELETED) {
            throw IllegalStateException("이미 삭제된 상품입니다.")
        }

        return this.copy(
            status = ProductStatus.DELETED,
            updatedAt = Instant.now()
        )
    }

    enum class ProductStatus(
        val description: String,
    ) {
        REGISTERED("등록됨"),
        ON_SALE("판매중"),
        WITHDREW("판매 일시중단됨"),
        SOLD_OUT("품절됨"),
        DELETED("삭제됨");
    }
}