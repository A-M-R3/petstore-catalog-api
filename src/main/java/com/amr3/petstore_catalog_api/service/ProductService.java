package com.amr3.petstorecatalogapi.service;

import com.amr3.petstorecatalogapi.model.Producer;
import com.amr3.petstorecatalogapi.model.Product;
import com.amr3.petstorecatalogapi.repository.ProducerRepository;
import com.amr3.petstorecatalogapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProducerRepository producerRepository;

    // Constructor injection here instead of @Autowired
    public ProductService(ProductRepository productRepository, ProducerRepository producerRepository) {
        this.productRepository = productRepository;
        this.producerRepository = producerRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product, Long producerId) {
        Producer producer = producerRepository.findById(producerId)
                .orElseThrow(() -> new RuntimeException("Producer not found with id: " + producerId));
        
        product.setProducer(producer);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());
        
        // Update the dynamic JSON attributes
        if (productDetails.getAttributes() != null) {
            existingProduct.setAttributes(productDetails.getAttributes());
        }

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}