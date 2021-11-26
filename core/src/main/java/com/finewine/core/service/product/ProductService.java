package com.finewine.core.service.product;

import com.finewine.core.model.product.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(String id);

    List<Product> getProductsFromWithLimit(int offset, int limit);

    List<Product> getProducts();
}
