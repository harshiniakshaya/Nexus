package com.nexus.backend.service;

import com.nexus.backend.model.Product;
import com.nexus.backend.payload.ProductDTO;
import com.nexus.backend.payload.ProductResponse;

public interface ProductService {
    ProductDTO addProduct(Long categoryId, Product product);

    ProductResponse getAllProducts();

    ProductResponse searchByCategory(Long categoryId);

    ProductResponse searchProductByKeyword(String keyword);
}
