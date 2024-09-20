package sps.codeinterview.reto1.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sps.codeinterview.reto1.exception.ResourceNotFoundException;
import sps.codeinterview.reto1.model.Product;
import sps.codeinterview.reto1.repository.ProductRepository;

@RestController
@RequestMapping("/crud")
@Validated
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;
/*se agrego el manejo de exepciones en los endpoints:

getProducts(): No tiene manejo de excepciones específico, lo cual es aceptable para una operación de lectura general.

getProductById():
Maneja la excepción ResourceNotFoundException si no se encuentra el producto.

createProduct():
Tiene una validación manual para campos nulos, devolviendo un BadRequest si faltan datos.
Usa @Valid para validaciones adicionales definidas en la entidad Product.

updateProduct():
Maneja ResourceNotFoundException si no se encuentra el producto a actualizar.

deleteProduct():
Maneja ResourceNotFoundException si no se encuentra el producto a eliminar.

*/
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return ResponseEntity.ok(product);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        if (product.getName() == null || product.getLabel() == null || product.getPrice() == null) {
            return ResponseEntity.badRequest().build();
        }
        product.setCreatedAt(new Date());
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        
        existingProduct.setName(product.getName());
        existingProduct.setLabel(product.getLabel());
        existingProduct.setPrice(product.getPrice());
        
        Product updatedProduct = productRepository.save(existingProduct);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
         }
/*endpoint GET para consultar los productos por nombre:
El método  recibe el texto a buscar por Name.
Las coincidencias se ordenan descendentemente.
*/
   
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String name) {
        List<Product> products = productRepository.findByNameContainingOrderByNameDesc(name);
        return ResponseEntity.ok(products);
    }
}

