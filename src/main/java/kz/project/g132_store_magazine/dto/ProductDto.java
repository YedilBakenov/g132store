package kz.project.g132_store_magazine.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import kz.project.g132_store_magazine.model.Country;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "SchemaDto")
public class ProductDto {

    private int id;

    private String name;

    private String description;

    private double price;

    private LocalDateTime create;

    private LocalDateTime update;

    private List<Country> countries;
}
