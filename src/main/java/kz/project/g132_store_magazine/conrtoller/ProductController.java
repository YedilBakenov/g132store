package kz.project.g132_store_magazine.conrtoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.project.g132_store_magazine.dto.ProductDto;
import kz.project.g132_store_magazine.exeption.ProductNotFoundException;
import kz.project.g132_store_magazine.model.Product;
import kz.project.g132_store_magazine.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/product")
@Tag(name = "ProductController", description = "API для работы с продуктами")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    @Operation(summary = "Получение списка продуктов", description = "Получение списка продуктов по параметрам name, price, description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукты получены", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
            }),
            @ApiResponse(responseCode = "404", description = "Продукты не найдены", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
            })
    })
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String description
    ) {
        try {
            return ResponseEntity.ok(productService.findAll(name, price, description));
        } catch (ProductNotFoundException exception){
            throw new ProductNotFoundException("Not Found Products!");
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){

        try{
            return ResponseEntity.ok(productService.findById(id));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
