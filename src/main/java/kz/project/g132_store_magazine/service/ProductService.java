package kz.project.g132_store_magazine.service;

import kz.project.g132_store_magazine.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> findAll(String name, Double price, String description);

    Product findById(int id);
}
