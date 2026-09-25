package com.nexus.backend.service;

import com.nexus.backend.exceptions.ResourceNotFoundException;
import com.nexus.backend.model.Category;
import com.nexus.backend.model.Product;
import com.nexus.backend.payload.ProductDTO;
import com.nexus.backend.payload.ProductResponse;
import com.nexus.backend.repositories.CategoryRepository;
import com.nexus.backend.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(Long categoryId, Product product) {
        Category category =  categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        product.setImage("default.png");
        product.setCategory(category);
        double price = product.getPrice() != null ? product.getPrice() : 0.0;
        double discount = product.getDiscount() != null ? product.getDiscount() : 0.0;
        double specialPrice = price - ((discount * 0.01) * price);
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductResponse getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }
}
