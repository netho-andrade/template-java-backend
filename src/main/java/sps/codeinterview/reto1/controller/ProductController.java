package sps.codeinterview.reto1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;
import sps.codeinterview.reto1.model.Product;
import sps.codeinterview.reto1.repository.ProductRepository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/crud")
@Validated
public class ProductController {
	@Autowired
	//Add the correct notations
	private ProductRepository productRepository;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@GetMapping("/products")
	//Add notation GET: /products
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@GetMapping("/product/{id}")
	//Add notation GET: /product/{id}
	public Product getProductById(@PathVariable Long id){
		return productRepository.findById(id).orElse(null);
	}
	@PostMapping("/product")
	//Add notation POST: /product
	public Product createProduct(@RequestBody Product product) {
		System.out.println(product.toString());
		product = productRepository.save(product);
		return product;
	}
	@PutMapping("/product/{id}")
	//Add notation PUT: product/{id}
	public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		if(productRepository.findById(id).isPresent()) {
			Product updatedProduct = productRepository.findById(id).get();
			updatedProduct.setName(product.getName());
			updatedProduct.setPrice(product.getPrice());
			updatedProduct.setLabel(product.getLabel());
			productRepository.save(updatedProduct);
			return ResponseEntity.ok("Product updated");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/product/{id}")
	//Add notation DELETE: product/{id}
	public String deleteProduct(@PathVariable Long id) {
		if (productRepository.findById(id).isPresent()) {
			productRepository.deleteById(id);
			return "Product deleted successfully";
		} else {
			return "Product not found";
		}
	}

	@GetMapping("/products/search")
	public List<Product> getProductsByName(@RequestParam String name) {
		return productRepository.findByNameContainingIgnoreCaseOrderByCreatedAtDesc(name);
	}
}