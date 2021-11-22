package com.finewine.core.service.product;

import com.finewine.core.exception.NoElementWithSuchIdException;
import com.finewine.core.model.product.Product;
import com.finewine.core.model.product.ProductDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductDao productDao;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product;
        try {
            product = productDao.findById(id);
        } catch (NumberFormatException | EmptyResultDataAccessException e) {
            throw new NoElementWithSuchIdException(id.toString());
        }
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new NoElementWithSuchIdException(id.toString());
        }
    }

    @Override
    public List<Product> getProductsFromWithLimit(int offset, int limit) {
        return productDao.findAll(offset, limit);
    }

    @Override
    public List<Product> getProducts() {
        return productDao.findAllNoLimit();
    }
}
