package sps.codeinterview.reto1.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
//Esta anotación @Entity marca la clase Product  Define la entidad Product. 
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String label;

	private Double price;

	@CreationTimestamp
	private Date createdAt;

	public Product() {

        
		this.name = name;
        this.label = label;
        this.price = price;
	}

	// Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

	// toString method for debugging
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", label=" + label + ", price=" + price + ", createdAt=" + createdAt + "]";
    }

}
