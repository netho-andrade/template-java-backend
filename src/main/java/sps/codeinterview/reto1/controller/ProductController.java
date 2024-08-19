package sps.codeinterview.reto1.controller;

import sps.codeinterview.reto1.exception.ResourceNotFoundException;
import sps.codeinterview.reto1.model.Product;
import sps.codeinterview.reto1.repository.ProductRepository;

import java.lang.IllegalArgumentException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/crud")
@Validated
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;


	@GetMapping("/products")
	public List<Product> getProducts() {

		List<Product> listTodo = productRepository.findAllByOrderById();
		//List<Todo> listTodo = todoRepository.findAllByOrderByTitle();

		System.out.println("LISTA DE DATOS:: " + listTodo.toString());

		return listTodo;
	}


	@GetMapping("/product/{id}")
	public Product getTodos(@PathVariable Long id){
		System.out.println("Path variable: " + id.toString());
		
		Product product = null;
		try {
			product = productRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
			System.out.println(product.toString());
		} 
		catch (Exception e) {
			throw new IllegalArgumentException("Error accessing the database", e);
		}
		return product;
	}

	@PostMapping("/product")
	public Product createProduct(@RequestBody Product product) {
		System.out.println(product.toString());

		product = productRepository.save(product);

		return product;
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<String> updateCompletedStatus(@PathVariable Long id, @RequestBody Product product) {
		System.out.println("Updating completion status for " + id.toString() + ", " + product.toString());

		Product productFound = productRepository.findById(id).get();

		if (product.getLabel() != null) {
			productFound.setLabel(product.getLabel());
		}
		
		if(product.getPrice() != null) {
			productFound.setPrice(product.getPrice());
		}
		
		productRepository.save(productFound);

		return ResponseEntity.ok("Todo item status updated successfully");
	}

	@DeleteMapping("/product/{id}")
	public String deleteProduct(@PathVariable Long id) {
		System.out.println("Path variable: " + id.toString());

		productRepository.deleteById(id);

		return "Product deleted.";
	}
}