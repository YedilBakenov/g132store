package kz.project.g132_store_magazine.mapper;

import kz.project.g132_store_magazine.dto.ProductDto;
import kz.project.g132_store_magazine.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "createdAt", target = "create")
    ProductDto toDto(Product product);

    @Mapping(source = "create", target = "createdAt")
    Product toModel(ProductDto productDto);
    List<ProductDto> listDto(List<Product> productList);
    List<Product>listModel(List<ProductDto> productDtosList);
}
