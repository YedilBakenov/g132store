package kz.project.g132_store_magazine.service.impl;

import kz.project.g132_store_magazine.exeption.ProductNotFoundException;
import kz.project.g132_store_magazine.model.Product;
import kz.project.g132_store_magazine.repository.ProductRepository;
import kz.project.g132_store_magazine.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll(String name, double price, String description) {
        return List.of();
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Not Found Product By Id, Id: " + id + " not found!"));
    }
}
