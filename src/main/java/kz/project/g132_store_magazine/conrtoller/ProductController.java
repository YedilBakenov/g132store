package kz.project.g132_store_magazine.conrtoller;

import kz.project.g132_store_magazine.exeption.ProductNotFoundException;
import kz.project.g132_store_magazine.model.Product;
import kz.project.g132_store_magazine.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) double price,
            @RequestParam(required = false) String description
    ) {
        try {
            return ResponseEntity.ok(productService.findAll(name, price, description));
        } catch (ProductNotFoundException exception){
            throw new ProductNotFoundException("Not Found Products!");
        } catch (Exception e){
            e.getMessage();
        }
        return ResponseEntity.ok(productService.findAll(name, price, description));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){

        try{
            return ResponseEntity.ok(productService.findById(id));
        }catch (ProductNotFoundException exception) {
            throw new ProductNotFoundException("Not Found Product Id!");
        }catch (Exception e){
            e.getMessage();
        }
        return ResponseEntity.ok(productService.findById(id));
    }

}
