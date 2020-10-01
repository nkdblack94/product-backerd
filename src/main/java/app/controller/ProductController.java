package app.controller;

import app.model.Product;
import app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<Iterable<Product>> showProductList() {
        Iterable<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("showUpdate/{id}")
    public ResponseEntity<Optional<Product>> showUpdateProduct(@PathVariable("id") Long id) {
        Optional<Product> products = productService.findById(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,@RequestBody Product product) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Product products = optionalProduct.get();
        products.setName(product.getName());
        products.setDescription(product.getDescription());

        productService.save(products);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("showDelete/{id}")
    public ResponseEntity<Optional<Product>> showDeleteProduct(@PathVariable("id") Long id) {
        Optional<Product> products = productService.findById(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
