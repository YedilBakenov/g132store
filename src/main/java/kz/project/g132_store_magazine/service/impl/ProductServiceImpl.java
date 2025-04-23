package kz.project.g132_store_magazine.service.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kz.project.g132_store_magazine.exeption.ProductNotFoundException;
import kz.project.g132_store_magazine.model.Product;
import kz.project.g132_store_magazine.repository.ProductRepository;
import kz.project.g132_store_magazine.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final EntityManager entityManager;

    @Override
    public List<Product> findAll(String name, Double price, String description) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if(name!=null && !name.isEmpty()){
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + name + "%"));
        }

        if(price!=null){
            predicates.add(cb.equal(root.get("price"), price));
        }

        if(description!=null && !description.isEmpty()){
            predicates.add(cb.like(cb.lower(root.get("description")), "%" + description + "%"));
        }

        query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Not Found Product By Id, Id: " + id + " not found!"));
    }

    @Override
    public Product addProduct(Product product) { // implementation of addProduct method
        if (product != null) {
            return productRepository.save(product);
        }
        // Handle the case when product is null
        // You can throw an exception or return a default value
        // For example, you can throw an exception:
        // throw new IllegalArgumentException("Product cannot be null");
        // Or return null:

        return null;
    }
}
