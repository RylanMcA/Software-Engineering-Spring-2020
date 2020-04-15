package edu.uark.registerapp.commands.products;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ProductEntity;
import edu.uark.registerapp.models.repositories.ProductRepository;

@Service
public class ProductsSearch implements ResultCommandInterface<List<Product>> {
	@Override
	public List<Product> execute() {
        final LinkedList<Product> products = new LinkedList<Product>();
        
        // local variable to store the search term, change to lowercase
        String searchTerm = "";
        searchTerm = searchTerm.toLowerCase();

		for (final ProductEntity productEntity : productRepository.findAll()) {
            // if the product lookupcode in lowercase contains the search term
            // then add it to the list and return those products instead of all
            if (productEntity.getLookupCode().toLowerCase().contains(searchTerm)){
                products.addLast(new Product(productEntity));
            }
			
		}
		
		return products;
	}

	@Autowired
	ProductRepository productRepository;
}
