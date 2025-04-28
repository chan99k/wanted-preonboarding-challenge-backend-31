package com.wanted.preonboarding.chan99k.product_cqrs.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.time.Instant

class ProductTest {

    @Test
    fun update_product_test() {
        val target = Product(
            1, "test_product_01", "product1",
            "테스트 상품", "테스트 상품입니다.",
            Instant.now(), Instant.now().plusNanos(100),
            1000, 1000, Product.ProductStatus.REGISTERED,
        )

        val updated = target.update(
            name = "변경된 상품명",
            slug = "변경된 슬러그",
            shortDescription = "변경된 설명",
            fullDescription = "변경된 전체 설명"
        )

        assertThat(updated.status).isEqualTo(Product.ProductStatus.REGISTERED)
        assertThat(updated.name).isEqualTo("변경된 상품명")
        assertThat(updated.slug).isEqualTo("변경된 슬러그")
        assertThat(updated.shortDescription).isEqualTo("변경된 설명")
        assertThat(updated.fullDescription).isEqualTo("변경된 전체 설명")


    }

    @Test
    fun update_product_fails_when_already_deleted_test() {
        val target = Product(
            1, "test_product_01", "product1",
            "테스트 상품", "테스트 상품입니다.",
            Instant.now(), Instant.now().plusNanos(100),
            1000, 1000, Product.ProductStatus.ON_SALE,
        )

        assertThatThrownBy {
            target.update(
                name = "변경된 상품명",
                slug = "변경된 슬러그",
                shortDescription = "변경된 설명",
                fullDescription = "변경된 전체 설명"
            )
        }.isInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("상품이 이미 판매중으로 수정할 수 없습니다.")
    }

    @Test
    fun mark_as_sale_test() {
        val target = Product(
            1, "test_product_01", "product1",
            "테스트 상품", "테스트 상품입니다.",
            Instant.now(), Instant.now().plusNanos(100),
            1000, 1000, Product.ProductStatus.REGISTERED,
        )

        assertThat(target.markAsOnSale().status)
            .isEqualTo(Product.ProductStatus.ON_SALE)
    }

    @Test
    fun mark_as_sale_fails_when_already_deleted_test() {
        val target = Product(
            1, "test_product_01", "product1",
            "테스트 상품", "테스트 상품입니다.",
            Instant.now(), Instant.now().plusNanos(100),
            1000, 1000, Product.ProductStatus.DELETED,
        )

        assertThatThrownBy { target.markAsOnSale() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("이미 삭제된 상품입니다.")
    }

    @Test
    fun mark_as_sold_out_test() {
        val target = Product(
            1, "test_product_01", "product1",
            "테스트 상품", "테스트 상품입니다.",
            Instant.now(), Instant.now().plusNanos(100),
            1000, 1000, Product.ProductStatus.REGISTERED,
        )

        assertThat(target.markAsSoldOut().status)
            .isEqualTo(Product.ProductStatus.SOLD_OUT)
    }

    @Test
    fun mark_as_sold_out_fails_when_already_deleted_test() {
        val target = Product(
            1, "test_product_01", "product1",
            "테스트 상품", "테스트 상품입니다.",
            Instant.now(), Instant.now().plusNanos(100),
            1000, 1000, Product.ProductStatus.DELETED,
        )

        assertThatThrownBy { target.markAsSoldOut() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("이미 삭제된 상품입니다.")
    }

    @Test
    fun mark_as_withdrew_test() {
        val target = Product(
            1, "test_product_01", "product1",
            "테스트 상품", "테스트 상품입니다.",
            Instant.now(), Instant.now().plusNanos(100),
            1000, 1000, Product.ProductStatus.REGISTERED,
        )

        assertThat(target.markAsWithDrew().status)
            .isEqualTo(Product.ProductStatus.WITHDREW)
    }

    @Test
    fun mark_as_withdrew_fails_when_already_deleted_test() {
        val target = Product(
            1, "test_product_01", "product1",
            "테스트 상품", "테스트 상품입니다.",
            Instant.now(), Instant.now().plusNanos(100),
            1000, 1000, Product.ProductStatus.DELETED,
        )

        assertThatThrownBy { target.markAsDelete() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("이미 삭제된 상품입니다.")
    }

    @Test
    fun mark_as_deleted_test() {
        val target = Product(
            1, "test_product_01", "product1",
            "테스트 상품", "테스트 상품입니다.",
            Instant.now(), Instant.now().plusNanos(100),
            1000, 1000, Product.ProductStatus.REGISTERED,
        )

        assertThat(target.markAsDelete().status)
            .isEqualTo(Product.ProductStatus.DELETED)
    }

    @Test
    fun mark_as_deleted_fails_when_already_deleted_test() {
        val target = Product(
            1, "test_product_01", "product1",
            "테스트 상품", "테스트 상품입니다.",
            Instant.now(), Instant.now().plusNanos(100),
            1000, 1000, Product.ProductStatus.DELETED,
        )

        assertThatThrownBy { target.markAsDelete() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("이미 삭제된 상품입니다.")
    }

}