package kz.project.g132_store_magazine.conrtoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.project.g132_store_magazine.dto.ProductDto;
import kz.project.g132_store_magazine.mapper.ProductMapper;
import kz.project.g132_store_magazine.model.Product;
import kz.project.g132_store_magazine.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/product")
@Tag(name = "ProductController", description = "API для работы с продуктами")
@Validated
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    @Operation(summary = "Получение списка продуктов", description = "Получение списка продуктов по параметрам name, price, description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукты получены", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
            })
    })
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String description
    ) {
        List<Product> products = productService.findAll(name, price, description);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение продукта по ID", description = "Получение продукта по его уникальному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт найден", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Продукт не найден", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
            })
    })
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    @Operation(summary = "Добавление нового продукта", description = "Добавление нового продукта в базу данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Продукт добавлен", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
            })
    })
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductDto productDto) {
        Product product = productService.addProduct(productMapper.toModel(productDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}