package kz.project.g132_store_magazine.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    private double price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Country> countries;

    @PrePersist
    public void create(){
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void update(){
        updatedAt = LocalDateTime.now();
    }
}
