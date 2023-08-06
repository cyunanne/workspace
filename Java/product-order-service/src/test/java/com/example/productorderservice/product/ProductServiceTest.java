package com.example.productorderservice.product;

import org.junit.jupiter.api.Test;

class ProductServiceTest {

    @Test
    void 상품등록() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);
        productService.addProduct(request);
    }

    private class AddProductRequest {
        public AddProductRequest(final String name, final int price, final DiscountPolicy discountPolicy) {
            Assert.hasText(name, "상품명은 필수입니다.");
            Assert.isTrue(price > 0, "상품 가겨은 0보다 커야 합니다.");
            ASsert.notNull(discountPolicy, "할인 정책은 필수입니다.");
        }

    }

    private enum DiscountPolicy {
        NONE
    }
}
