package com.example.evcharging.Controller;

import com.example.evcharging.Dto.ProductRequest;
import com.example.evcharging.Entity.Product;
import com.example.evcharging.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> create(
            @RequestBody ProductRequest request) {

        Product product = new Product();

        product.setName(request.getName());
        product.setType(request.getType());
        product.setDescription(request.getDescription());
        product.setUnitPrice(request.getUnitPrice());
        product.setUnit(request.getUnit());

        return ResponseEntity.ok(
                productService.save(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(
            @PathVariable Long id) {

        return productService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        productService.delete(id);

        return ResponseEntity.ok(
                "Product deleted successfully");
    }
}