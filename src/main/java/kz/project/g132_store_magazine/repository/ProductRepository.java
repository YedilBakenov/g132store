package kz.project.g132_store_magazine.repository;

import jakarta.transaction.Transactional;
import kz.project.g132_store_magazine.model.Brand;
import kz.project.g132_store_magazine.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
